<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action = "User/${param.userid}/Car/${param.carid}" method = "POST">
    <table border = "0">
        <tr>
            <td><b>car model</b></td>
            <td><input type = "text" name = "model" placeholder="${param.model}" size = "70"/></td>
        </tr>
        <tr>
            <td><b>car color</b></td>
            <td><input type = "text" name = "color" placeholder="${param.color}" size = "65"/></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input type="hidden" name="_method" value="put">
                <input type = "submit" value = "update car"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
