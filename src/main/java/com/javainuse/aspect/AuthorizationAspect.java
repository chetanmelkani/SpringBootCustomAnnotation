package com.javainuse.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javainuse.model.Role;
import com.javainuse.model.User;

@Aspect
@Component
public class AuthorizationAspect {

	static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationAspect.class);

	@Autowired
	private HttpServletRequest mRequest;

	public AuthorizationAspect() {
	}

	enum Type {
		NONE, CLASS, METHOD
	}

	public static void denyAccess() throws Exception {
		throw new Exception();
	}

	@Around("execution(* com.javainuse.controllers.*.*(..))")
	public Object checkAuthorization(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Type annotationType = Type.NONE;
		Authorization authorization = null;
		Authorization.Type authType = null;

		authorization = proceedingJoinPoint.getTarget().getClass().getAnnotation(Authorization.class);

		if (authorization != null) {
			annotationType = Type.CLASS;
			authType = authorization.value();
		}

		authorization = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod()
				.getAnnotation(Authorization.class);

		// method annotated
		if (authorization != null) {
			annotationType = Type.METHOD;
			authType = authorization.value();
		}

		boolean isAuthorized = true;
		LOGGER.info("annotationType " + annotationType + "authType " + authType);
		if (annotationType != Type.NONE && authType != null) {
			isAuthorized = false;

			LOGGER.info("Authorization detected at "+ annotationType+ " level: "+ authType);
			
			User u = (User) mRequest.getSession().getAttribute("user");
			
			switch (authType) {
			case REGULAR_USER:
				LOGGER.info("Authorization accepted at "+ annotationType+ " level: "+ authType);
				System.out.println("Authorization accepted at "+ annotationType+ " level: "+ authType);
				
				if (u != null && u.getRole() == Role.REGULAR_USER) {
					LOGGER.info("Authorization accepted at ", annotationType, " level: ", authType);
					isAuthorized = true;
				}
				break;

			case ADMIN:

				if (u != null && u.getRole() == Role.ADMIN) {
					LOGGER.info("Authorization accepted at "+ annotationType+ " level: "+ authType);
					isAuthorized = true;
				}
				break;

			default:
			}
		}
		else{
			
		}

		if (!isAuthorized) {
			LOGGER.warn("Authorization not accepted at ", annotationType, " level: ", authType);
			System.out.println("Authorization not accepted at "+ annotationType+ " level: "+ authType);

			throw new Exception();
		}

		return proceedingJoinPoint.proceed();
	}
}
