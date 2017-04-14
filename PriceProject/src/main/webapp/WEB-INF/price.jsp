<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Прайс лист</title>
    <link rel="icon" type="image/x-icon" href="<c:url value="/img/favicon.ico"/>" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="<c:url value="/js/script.js" />" defer></script>
</head>
<body>
<h2>Прайс лист</h2>
<div>
    <form id="requestForm" onsubmit="return false;">
        <div>
            <label for="category">Категория: </label><hr>
            <input type="text" id="category" name="category" placeholder="Введите категорию..">
        </div>
        <div>
            <label for="product">Наименование: </label><hr>
            <input type="text" id="product" name="product" placeholder="Введите наименование..">
        </div>
        <div>
            <label for="min_price">Цена от</label><hr>
            <input type="text" id="min_price" name="min_price" >
        </div>
        <div>
            <label for="max_price">Цена до</label><hr>
            <input type="text" id="max_price" name="max_price" >
        </div>
        <div><input type="submit" value="Найти"></div>
        <span id="errorMessage"></span>
    </form>
</div>
<table id="table"></table>
</body>
</html>