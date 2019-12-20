<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Blog" />
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Blog" />
</jsp:include>
<main>
<div>
    <h3>Blog posts</h3>
    <div>
        <ul>
            <li>
                <%-- nr komt overeen met artikel pagina --%>
                <a href="Controller?action=GetArticle&nr=1">Was	het	een	interessante projectweek?</a>
            </li>
            <li>
                <a href="Controller?action=GetArticle&nr=2">Wat	ben	je	van	plan om	te doen	vandaag?</a>
            </li>
            <li>
                <a href="Controller?action=GetArticle&nr=3">Wat zijn je plannen na je studies?</a>
            </li>
            <li>
                <a href="Controller?action=GetArticle&nr=4">Naar welke muziek ben je momenteel aan het luisteren?</a>
            </li>
            <li>
                <a href="Controller?action=GetArticle&nr=5">Wat ga je doen vandaag?</a>
            </li>

        </ul>
    </div>
</div>
</main>

<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home" />
</jsp:include>
</body>
</html>
