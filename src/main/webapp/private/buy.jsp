<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Оплата товара</title>
</head>
<body>
<p><a href="http://localhost:8082/">На главную</a></p>
<h3>Личная информация</h3>
<c:if test="${totalSum > clientBalance}">
    <p>Не хватает средств для оплаты. Пополните счет.</p>
</c:if>
<c:if test="${totalSum <= clientBalance}">
    <p>После нажатия на кнопку будет произведена оплат товара.</p>
    <p><a href="/private/buyPage"> Произвести оплату </a></p>
</c:if>
<p> ИД: ${clientId}</p>
<p> Имя: ${clientName}</p>
<p> Телефон: ${clientPhone}</p>
<br>
<h3>История покупок</h3>
<br>
<p><a href="http://localhost:8082/private/editUserInfo?clientId=${clientId}">Редактирование личной информации</a></p>
<h3>Пополнение баланса</h3>
<c:if test="${countAddRows == 1}">
    Деньги были успешно внесены на счет!
</c:if>
<p> Текущий баланс: ${clientBalance}</p>
<form action="/private/enterMoney" method="post">
    <input type="hidden"  name="clientId" value="${clientId}"/>
    <p> Внести на баланс <input type="number" maxlength="20" size="20" name="sum"> рублей</p>
    <input type="submit" value = "Пополнить"/>
</form>
</body>
</html>