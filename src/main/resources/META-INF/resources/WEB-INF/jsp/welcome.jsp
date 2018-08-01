<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
      <c:forEach items="${products}" var="product">
         <p>Product Description: <c:out value="${product.description}"/></p>
      </c:forEach>
</body>
</html>