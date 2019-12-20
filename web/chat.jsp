<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Chat" />
</jsp:include>
<%-- polling - client vraagt server voor vriendenlijst --%>
<body onload="getMyFriends()">
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Chat" />
</jsp:include>
<main>
    <div class="status">
        <p>Status: <span id="myStatus"> <c:if test="${thisPerson != null}"> Online </c:if> </span> </p>
        <%-- hier word de status naar toe gestuurd --%>
    </div>

    <h2>Jouw vriendenlijst</h2>
    <table>
        <thead>
            <tr>
                <th>email</th>
                <th>voornaam</th>
                <th>achternaam</th>
                <th>status</th>
                <th>chat</th>
            </tr>
        </thead>
        <%-- hier komt de tabel van getmyfriends.js --%>
        <tbody id="myFriends">

        </tbody>
    </table>


    <button id="Show">Show friendslist</button>
    <%-- fancy ding om vriendenlist te tonen/ verbergen (showHide.js) --%>
    <br>
    <button id="Hide">Hide friendslist</button>

    <%-- polling add --%>
    <h2>Voeg een vriend toe</h2>
    <%-- hiervan komen de gegevens die naar de js worden gestuurd--%>
    <input id="idFriend" type="text" placeholder="email">
    <input id="firstNameFriend" type="text" placeholder="voornaam">
    <input id="lastNameFriend" type="text" placeholder="achternaam">
    <button type="submit" onclick="addNewFriend()">Add Friend</button>

    <h2>Verander je status</h2>
    <input type="text" name="myNewStatus" id="myNewStatus" list="myStatussen"> <%-- hiervan komt de status --%>
    <datalist id="myStatussen">
        <option value="Online">
        <option value="Offline">
        <option value="Away">
    </datalist>
    <button type="submit" onclick="changeMyStatus()">Change Status</button>

    <h2>Chat hier</h2>
    <div id="conversations"></div> <%-- van getMyFriends.js --%>

</main>

<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home" />
</jsp:include>
</body>
</html>
