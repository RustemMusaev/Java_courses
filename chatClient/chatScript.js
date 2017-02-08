        var stompClient = null;
		function connect(id) {
            var socket = new SockJS('http://localhost:8080/chat');
            stompClient = Stomp.over(socket);
            stompClient.connect({"Auth-Token": localStorage.getItem("token")}, function(frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe("/topic/"+id+"/messages", function(messageOutput) {
                    showMessageOutput(JSON.parse(messageOutput.body),id);
                });
            });
        }
        function showMessageOutput(messageOutput, id) {
            var text="\n"+messageOutput.text;
            $("textarea#ExampleMessage"+id+"").append(text);
        }
        function registration(){
			var xhr = new XMLHttpRequest();
			var login = document.getElementById('loginReg').value;
			var password = document.getElementById('passwordReg').value;
			xhr.open("POST", 'http://localhost:8080/users', true)
			xhr.setRequestHeader('Content-Type', 'application/json')
			xhr.setRequestHeader('Accept', 'application/json');
			xhr.send(JSON.stringify({'login':login, 'password':password}));				
			}
		function authorization(){
		    if (localStorage.getItem("token") == "null"){
                var xhr = new XMLHttpRequest();
                var login = document.getElementById('login').value;
                var password = document.getElementById('password').value;
                xhr.open("POST", 'http://localhost:8080/login', true)
                xhr.setRequestHeader('Content-Type', 'application/json')
                xhr.setRequestHeader('Accept', 'application/json');
                xhr.setRequestHeader('login', login);
                xhr.setRequestHeader('password', password);
                xhr.send(null);
                xhr.onreadystatechange = function() {
                    if(this.readyState == this.HEADERS_RECEIVED) {
                        localStorage.setItem("token",xhr.getResponseHeader('Auth-Token'));
                    }
                }
            } else {
		        alert("you is login now")
            }
			}
        function logout(){
            var request = new XMLHttpRequest();
            request.open("POST","http://localhost:8080/singout",true);
            request.setRequestHeader("Auth-Token",localStorage.getItem("token"));
            request.send();
            localStorage.setItem("token","null");
        }
		function getchats(){
            document.getElementById('chatName').style.visibility= 'hidden';
            document.getElementById('addChat').style.visibility= 'hidden';
            document.getElementById('showChats').style.visibility= 'hidden';
            document.getElementById('chats').style.visibility= 'visible';
            $.ajax({
                url:'http://localhost:8080/chats',
                type: 'GET',
                headers: {'Auth-Token': localStorage.getItem("token")},
                dataType: 'json',
                success: function(data) {
                    for(var i = 0; i < data.length; i++){
                        $("#chats").append("<br /><button onclick=\"enterToChat("+data[i].id+")\">Insert to "+data[i].name+"</button> " +
                            "<button onclick=\"logoutToChat("+data[i].id+")\">Logout to "+data[i].name+"</button>");
                    }
                }
            });
        }
        function enterToChat(id) {
		        document.getElementById("chatText").style.visibility="visible";
                $.ajax({
                url: "http://localhost:8080/chats/"+id+"/members",
                type: "POST",
                headers : {
                    "Auth-Token": localStorage.getItem("token"),
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' },
                dataType: "json",
                    success: function () {
                    $("#chatText").append("<br /><textarea rows='10' cols='50' id='ExampleMessage"+id+"' >You insert chat id = "+id+"</textarea>" +
                            "<button onclick=\"getMessages("+id+",1)\">Get All Messages</button>" +
                            "<button onclick=\"getMessages("+id+",2)\">Get New Messages</button> "+
                            "<button onclick=\"logoutToChat()\">logoutToChat</button> " +
                        "<br /><input type='text' id='textMessages' placeholder='Write a message....'/>"+
                        "<button id='sendMessage' onclick='sendMessage("+id+")'>Send</button>");
                    }
               });
                connect(id);
        }
        function getMessages(id, param) {
		    if (param == 1) {
		        param = "all";
            } else {
		        param = "new";
            }
                $.ajax({
                url: "http://localhost:8080/chats/"+id+"/messages",
                type: "GET",
                headers : {
                    "Auth-Token": localStorage.getItem("token"),
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' },
                dataType: "json",
                    data: {"get":param},
                success: function (data) {
                    $("textarea#ExampleMessage"+id+"").empty();
                    $("textarea#ExampleMessage"+id+"").append("You insert chat id = "+id+"");
                    for(var i = 0; i < data.length; i++){
                        var text="\n"+data[i].text;
                        $("textarea#ExampleMessage"+id+"").append(text);
                    }
                }
            });
        }
        function logoutToChat() {
            $("#chatText").empty();
        }
        function addChat(){
            var name = document.getElementById('chatName').value;
		    var request = $.ajax({
                url: "http://localhost:8080/chats",
                type: "POST",
                headers : {
                    "Auth-Token": localStorage.getItem("token"),
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' },
                dataType: "json",
                data: JSON.stringify({'name':name}),
            });
            getchats();
        }
        function sendMessage(id) {
            var textMessage = document.getElementById('textMessages').value;
            var request = $.ajax({
                url: "http://localhost:8080/chats/"+id+"/messages",
                type: "POST",
                headers : {
                    "Auth-Token": localStorage.getItem("token"),
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' },
                dataType: "json",
                data: JSON.stringify({'text':textMessage}),
            });
            }
