<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/tags.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <c:if test="${param.error != null}">
        <div id="error">
            <spring:message code="error.badUsernamePassword" />
        </div>
    </c:if>
    <form:form id="loginForm"
                method = "POST"
                action = "/login"
                modelAttribute="user">
        <form:label path="username"><spring:message code="label.username" /> : </form:label> <form:input path="username" required="required"/><br>
        <form:errors path="username" /><br>
        <form:label path="password"><spring:message code="label.password" /> : </form:label> <form:password path="password" required="required"/><br>
        <form:errors path="password" /><br>
        <form:button>Submit</form:button>
    </form:form>
</body>
</html>