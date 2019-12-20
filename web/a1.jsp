<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Article 1: Was	het	een	interessante projectweek?"/>
</jsp:include>
<body onload="openSocket()"> <%-- websocket openen--%>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Article 1: Was	het	een	interessante projectweek?"/>
</jsp:include>
<main>
    <a href="Controller" id="backButton">
        <button type="submit" onclick="closeSocket()">Back</button><%-- session sluiten--%>
    </a>

    <div>
        <h3>Was	het	een	interessante projectweek?</h3>
    </div>

    <div>
        <h3>Comments</h3>
        <div id="NameCommentStars"> <%-- hierin komen de comments --%>
        </div>
    </div>

    <div>
        <h3>Wat vind jij ervan? laat het ons weten hieronder:</h3>
        <p>
            <label for="name">Jouw naam:</label>
            <input type="text" id="name" name="name"/>
        </p>
        <p>
            <label for="stars">Aantal sterren:</label>
            <input type="number" min="0" max="5" id="stars" name="stars"/>
        </p>
        <p>
            <label for="comment">Comment:</label>
            <input type="text" id="comment" name="comment"/>
        </p>
        <p>
            <button type="submit" onclick="send()">versturen</button> <%-- wanneer je op de knop drukt roep je de send function op van blog.js --%>
        </p>
    </div>

</main>
<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Article 1: Was	het	een	interessante projectweek?"/>
</jsp:include>
</body>
</html>
