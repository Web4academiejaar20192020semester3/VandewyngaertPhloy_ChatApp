var changeMyStatusRequest = new XMLHttpRequest();

function changeMyStatus() {//status ophalen van jsp
    var myNewStatusValue = document.getElementById("myNewStatus").value;
    var status = "myNewStatus=" + encodeURIComponent(myNewStatusValue);

    changeMyStatusRequest.open("POST", "Controller?action=ChangeMyStatus", true);
    changeMyStatusRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    changeMyStatusRequest.send(status);//status opsturen naar controller

    var myStatus = document.getElementById("myStatus");// id in jsp

    if (myNewStatusValue === "") {//standaard op online zetten
        myStatus.innerHTML = "online"
    } else {
        myStatus.innerHTML = myNewStatusValue;
    }
}