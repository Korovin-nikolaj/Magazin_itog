<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Корзина</title>
    </head>
    <body>
        <p><a href="http://localhost:8082/">На главную</a>  |  <a href="http://localhost:8082/account">Профиль</a></p>
        <h3>Товары в корзине</h3>
        <c:forEach var="product" items="${basket}">
            <p><c:out value="${product}"/>  <a href="http://localhost:8082/removeFromBasket?productId=${product.id}">Удалить</a></p>
        </c:forEach>
    </body>
</html>