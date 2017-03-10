        var stompClient = null;

        function connect() {
            var socket = new SockJS('http://localhost:8080/chat');
            stompClient = Stomp.over(socket);
            stompClient.connect({"Auth-Token": localStorage.getItem("token")}, function(frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe("/topic/newsletter", function(messageOutput) {
                    showMessageOutput(JSON.parse(messageOutput.body));
                });
            });
        }

        function showMessageOutput(messageOutput) {
	        $("#chatText").append("<br><div style='border: solid 1px red'>Title: "+messageOutput.title+"<br>Message: "+messageOutput.message+"<br>Date: "+messageOutput.date+"</div>");
        }

        $("form#data").submit(function(){

            var formData = new FormData($(this)[0]);

            $.ajax({
                url: window.location.pathname,
                type: 'POST',
                data: formData,
                async: false,
                success: function (data) {
                    alert(data)
                },
                cache: false,
                contentType: false,
                processData: false
            });

            return false;
        });


        function showNews(count) {
            if (stompClient == null) {
               connect();
            }
            document.getElementById("lastNews").style.visibility="visible";
            var pageNumber = 1;
            $.ajax({
                url: "http://localhost:8080/news",
                type: "GET",
                dataType: "json",
                data: {"pageNumber":pageNumber, "count":count},
                success: function (data, textStatus, xhr) {
                    console.log(xhr);
                    console.log(xhr.getResponseHeader('currentPage'));
                    $("#page").empty();
                    $("#lastNews").empty();
                    for(var i = 0;i< data.length; i++){
                        var outputTitle = "\n"+data[i].title;
                        var outputMessage = "\n"+data[i].message;
                        var outputDate = "\n"+data[i].date;
                        var fileName = data[i].picture;
                        if(fileName != undefined){
                            $("#lastNews").append("<br><div style='border: solid 1px black'>Title: "+outputTitle+"<br>"+
                                "Message: "+outputMessage+"<br>"+
                                "Date: "+outputDate+"<br><img src='"+fileName+"' alt='Smile image' height='43' width='42' /></div>");
                        } else {
                            $("#lastNews").append("<br><div style='border: solid 1px red'>Title: "+outputTitle+"<br>"+
                                "Message: "+outputMessage+"<br>"+
                                "Date: "+outputDate+"</div>");
                        }
                    }
                }
            });
        }
