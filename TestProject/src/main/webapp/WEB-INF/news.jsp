<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>News</title>
        <meta charset="utf-8">
        <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <style>
            body {
                border-style: outset;
                width: 820px;
                margin: auto;
                padding: 10px;
            }
            .colums {
                margin-left: 30px;
            }
            div {
                text-align: left;
                padding: 2px;
            }
            .form {
                padding: 5px;
                border-style: outset;
            }
            a {
                text-decoration: none;
                font-size: 20px;
                color: green;
            }
            a:visited {
                color: green;
            }
            a:hover {
                color: greenyellow;
                text-decoration: underline;
            }
            .context {
                width : 800px;
            }
            .news {
                border-style: outset;
                margin-top: 10px;

            }
            .title {
                overflow: hidden;
                text-overflow: ellipsis;
                margin-top: 2px;
                height: 25px;
                font-size: 20px;
                font-weight: bold;
            }
            .date {
                margin-top: 2px;
                height: 10px;
                font-size: 10px;
            }
            .container{
                border-style: outset;
                margin-top: 2px;
                text-align: center;
                height: 85px;
            }
            .container div{
                margin-left: 2px;
                float: left;
            }
            .textmessage {
                overflow: hidden;
                text-overflow: ellipsis;
                width: 600px;
                height: 60px;
            }
            .text {
                margin-top: 5px;
            }
            img {
                height: 60px;
                widows: 60px;
            }
            .pageCount {
                margin-top: 5px;
                text-align: center;
            }
            .countRows {
                text-align: center;
            }
        </style>
    </head>
<body>
    <div class="countRows">
        <a class="colums" href="/news/count/10">10 publication</a><a href="/news/count/20">20 publication</a>        <a href="/news/count/50">50 publication</a>
    </div>
    <hr>
    <div class="pageCount">SELECT PAGE(currentPage: <c:out value="${requestScope.listPageCount[0]}" />) :
        <c:forEach var="i" begin="1" end="${requestScope.listPageCount[1]}">
            <a href="/news/${i}">${i} </a>
        </c:forEach>
    </div>
    <hr>
    <div class="form">
        <form action="news" method="post" enctype="multipart/form-data">
            Inset Title : <input type="text" name="title" charset="utf-8" required="required" placeholder="Enter a title"/>
            Insert image for news : <input type="file" name="file" />
            <textarea class="text" cols="80" rows ="5" name="message" required="required" placeholder="Enter text this(max 500 simbol) .."></textarea>
            <br><input type="submit" value="add news" />
        </form>
    </div>
    <hr>
    <div>
        <p>Configurae template :</p>
        <label><input type='checkbox' id="checkTitle" checked>checkTitle</label>
        <label><input type="checkbox" id="checkDate" value="Date" checked> Date</label>
        <label><input type="checkbox" id="checkMessage" value="Message" checked> Message</label>
        <label><input type="checkbox" id="checkImage" value="Image" checked> Image</label>
    </div>
    <br><hr>
    <div class="context">
        <c:forEach items="${requestScope.articleDtoList}" var="articleDtoList">
            <div class="news">
                <div class="title" id="title">
                    <c:out value="${articleDtoList.title}"/>
                </div>
                <div class="date" id="date">
                    <c:out value="${articleDtoList.date}"/>
                </div>
                <div class="container" id="container">
                    <div class="textmessage" >
                        <c:out value="${articleDtoList.message}"/>
                    </div>
                    <c:choose>
                        <c:when test="${articleDtoList.picture != null}">
                            <div class="image" id="image">
                                <img src="/getImage/${articleDtoList.picture}" alt="/getImage/${articleDtoList.picture}.jpg">
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </c:forEach>
    </div>
    <script>
        $("input[id='checkTitle']").change(function() {
            console.log("checkTitle");
            if(this.checked) {
                $(".title").show();
            } else {
                $(".title").hide();
            }
        });
        $("input[id='checkDate']").change(function() {
            console.log("checkDate");
            if(this.checked) {
                $(".date").show();
            } else {
                $(".date").hide();
            }
        });
        $("input[id='checkMessage']").change(function() {
            console.log("checkMessage");
            if(this.checked) {
                $(".container").show();
            } else {
                $(".container").hide();
            }
        });
        $("input[id='checkImage']").change(function() {
            console.log("checkImage");
            if(this.checked) {
                $(".image").show();
            } else {
                $(".image").hide();
            }
        });
    </script>
</body>
</html>