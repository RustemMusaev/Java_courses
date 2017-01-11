<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Страница с декларациями и скриптлетами</title>
</head>
<body>
<h1>JSP Страница с декларациями и скриптлетами</h1>
<h3> Этот пример показывает, как работать с декларациями и скриптлетами</h3>
<!-- Декларируем переменную count-->
<%! private int count = 0; %>
<% String outParameter = request.getParameter("param");
    if (outParameter != null){
        out.println(outParameter);
    } else {
        out.println("Значение параметра не установлено");}%>
Страница была показана <%= ++count %> раз.
<jsp:useBean id="simpleBean" class="servlet.Util"/>
Начальное значение свойства: <I><%= simpleBean.getStringProperty()%>
</I><br>
<% simpleBean.setStringProperty("This is a String property");%>
Значение после установки: <I><%= simpleBean.getStringProperty()%>
</I><br>
</body>
</html>