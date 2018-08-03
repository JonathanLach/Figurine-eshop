<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/tags.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
      <c:forEach items="${translations}" var="translation">
         <img src="data:image/jpg;base64,[base_64_String_goes_here]" />
         <p><spring:message code="label.name"/>: <c:out value="${translation.translation}"/></p>
         <p><spring:message code="label.price"/>: <c:out value="${translation.product.price}"/></p>
         <p><spring:message code="label.description"/>: <c:out value="{translation.product.description"/></p>
      </c:forEach>
</body>
</html>