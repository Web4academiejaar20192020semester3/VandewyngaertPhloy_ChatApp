var xHRObjectMyFriends = new XMLHttpRequest();//xhttp request object

//GetMyFriends
function getMyFriends() {
    xHRObjectMyFriends.open("GET", "Controller?action=GetMyFriends", true);
    xHRObjectMyFriends.onreadystatechange = getData;
    xHRObjectMyFriends.send(null);
}

function getData() {
    /*
        0 Not initialized
        1 Open
        2 Sent
        3 Received
        4 Loaded

        short status:
        200 OK
        404 file not found
        500 error
     */
    if (xHRObjectMyFriends.readyState === 4) {
        if (xHRObjectMyFriends.status === 200 || xHRObjectMyFriends.status === 500) {
            var jsonResponse = JSON.parse(xHRObjectMyFriends.responseText); //respons casten naar json

            /*
            vriendenlijst tabel maken in html
             */
            var html = "";
            for (var i = 0; i < jsonResponse.length; i++) {
                var userId = jsonResponse[i].userId;
                html += "<tr>" + "<td>" + jsonResponse[i].userId  + "</td>" +
                                 "<td>" + jsonResponse[i].firstName + "</td>" +
                                 "<td>" + jsonResponse[i].lastName + "</td>" +
                                 "<td>" + jsonResponse[i].userStatus + "</td>" +
                                 "<td>" +
                                    "<button id='sendChat' onclick='chatVenster(\"" + userId + "\")'>Chat</button>" + //functon chatVenster oproepen & userId meegeven
                                 "</td>" +
                        "</tr>";
            }
            document.getElementById("myFriends").innerHTML = html;// tabel zetten op plaats waar "myFriends" staat
            setTimeout(getMyFriends, 1500); // dit word herhaald elke 1.5 sec
        }
    }
}

function chatVenster(userId){
    var html =
        "<div id=\'" + userId + "\'>" +
            "<h3>" + userId + "</h3>" +
                "<div id='chatMessage'>" + "</div>" + //naam van veldje waarin chat komt
                "<div id='chatDiv" + userId + "'>" +
                    "<input type='text' id='chat" + userId + "'>" + //input van chat id van chat = chatjan@ucll.be
                    "<button id='chatSend' onclick='sendChat(\"" + userId + "\")'>Send</button>" + //function send chat + userid meegeven
                "</div>" +
        "</div>";

    document.getElementById("conversations").innerHTML = html;//plaats op jsp pagina
}

function sendChat(userId) {
    $.ajax({
        type: "POST",
        url: "Controller?action=SendChat",
        data: {
            userId: userId,
            chat: document.getElementById("chat" + userId).value // inhound van chatjan@ucll.be
        },
        dataType: "json",
        success: function() {//herhaling als er geen error is om de 0,6 seconden
            setInterval(getChat(userId), 600);
        }
    });
}

function getChat(userId) {
    $('#chatMessage').empty();// # = id DUS vedje waarin chat komt leegmaken
    $.ajax({
        type: "GET",
        url: "Controller?action=GetChat&myFriend=" + userId, //userid mee geven aan controller (getChat)
        dataType: "json",
        success: function(chatJSON) {
            $(chatJSON).each(function(index, b) {// voor elke chat: in div van chat messeges een p zetten met als text  email: chat bericht
                $('#chatMessage').append($('<p />').text(b.sender + ": " + b.message));
            });
        },
        error: function() {// bij error
            alert("Da werkt weer ni zeee");
        }
    });
}