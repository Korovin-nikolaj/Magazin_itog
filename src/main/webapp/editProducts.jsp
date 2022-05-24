<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Редактирование товаров</title>
</head>
<body>
<p> Ввод нового товара: </p>
<form action="/inputProducts" method="post">
    <p>Наименование товара: <input maxlength="50" size="50" name="productName"></p>
    <p>Цена: <input type="number"  size="15" name="price"></p>
        <input type="submit" value = "Добавить товар"/>
</form>
<p><a href="http://localhost:8082/editProducts">Редактирование товаров</a></p>
<p><a href="http://localhost:8082/editClients">Управление клиентами</a></p>
</body>
</html>