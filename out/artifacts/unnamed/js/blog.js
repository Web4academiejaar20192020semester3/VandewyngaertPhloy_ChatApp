var webSocket;
var backButton = document.getElementById("backButton")
var sendButton = document.getElementById("postCommentButton")

backButton.onclick = closeSocket;
sendButton.onclick = send;

window.onload = openSocket;

function openSocket() {//opent connectie
    webSocket = new WebSocket("ws://localhost:8080/Blog");

    webSocket.onmessage = function(event) {
        console.log("Wat steekt er in de evetn.data " + event.data);
        writeResponse(event.data);// data gekregen door send method van websocket
    }
}

function closeSocket() {//sluit connectie
    webSocket.close();
}

function send() { //haalt alle info op van jsp en stuurt ze naar de websocket

    var comment =
        "Name: " + document.getElementById("name").value +
        " - Stars: " + document.getElementById("stars").value +
        " - Comment: " + document.getElementById("comment").value;

    webSocket.send(comment);//data doorsturen over de websocket connection
}

function writeResponse(text) {//text = data die je mee krijgt via websocket
    var NameCommentStars = document.getElementById("NameCommentStars");
    var div = document.createElement("div");
    var comment = document.createTextNode(text);
    var p = document.createElement("p");

    div.id = "divVanComment";

    p.appendChild(comment);
    div.appendChild(p);
    NameCommentStars.appendChild(div);
}