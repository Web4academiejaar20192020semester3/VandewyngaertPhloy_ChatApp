var xHRObjectAddFriend = new XMLHttpRequest();

//AddFriend
function addNewFriend() {


    //Waardes van de input veldjes opslagen
    var idFriend = document.getElementById("idFriend").value;
    var firstNameFriend = document.getElementById("firstNameFriend").value;
    var lastNameFriend = document.getElementById("lastNameFriend").value;

    //Waardes naar controller sturen
    xHRObjectAddFriend.open("POST", "Controller?action=AddNewFriend&idFriend="+idFriend+"&firstNameFriend="+firstNameFriend+"&lastNameFriend="+lastNameFriend, true);
    xHRObjectAddFriend.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    //Input veldjes leegmaken
    document.getElementById("idFriend").value = "";
    document.getElementById("firstNameFriend").value = "";
    document.getElementById("lastNameFriend").value = "";

    //Waardes doorsturen naar response
    xHRObjectAddFriend.send("idFriend=" + idFriend);
    xHRObjectAddFriend.send("firstNameFriend=" + firstNameFriend);
     xHRObjectAddFriend.send("lastNameFriend=" + lastNameFriend);
}