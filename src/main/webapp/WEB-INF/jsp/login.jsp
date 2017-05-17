<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!doctype html>

<html>

  <body>
	
    <form:form modelAttribute="form">
        <form:errors path="" element="div" />
        <div>
            <form:label path="userName">Name</form:label>
            <form:input path="userName" />
            <form:errors path="userName" />
        </div>
        <div>
            <form:label path="password">Password</form:label>
            <form:password path="password" />
            <form:errors path="password" />
        </div>        
        <div>
            <input type="submit" />
        </div>
    </form:form>	
  </body>
</html>
