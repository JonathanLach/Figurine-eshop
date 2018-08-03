<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/tags.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<body>
     <img height="50" width="50" src="data:image/jpg;base64, ${product.base64Picture}" />
     <p>Description: <c:out value="${product.description}"/></p>
     <p>Price: <c:out value="${product.price}"/></p>
     <p>Names:
        <c:forEach items="${product.translations}" var="translation">
            <p>Translation: <c:out value="${translation.translation}"/></p>
        </c:forEach>
     </p>
</body>
</html>