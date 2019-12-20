<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Register" />
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Register" />
</jsp:include>
<main>
    <c:if test="${errors.size()>0 }">
        <div class="danger">
            <ul>
                <c:forEach var="error" items="${errors }">
                    <li>${error }</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <h2>Register here</h2>
    <form method="post" action="Controller?action=RegisterMe">

        <p>
            <label for="firstname">Firstname</label>
            <input type="text" id="firstname" name="firstname" value="">
        </p>
        <p>
            <label for="surname">Surname</label>
            <input type="text" id="surname" name="surname" value="">
        </p>
        <p>
            <label for="gender">Gender</label>
            <input type="text" id="gender" name="gender" value="">
        </p>
        <p>
            <label for="age">Age</label>
            <input type="text" id="age" name="age" value="">
        </p>
        <p>
            <label for="email">Email</label>
            <input type="text" id="email" name="email" value="">
        </p>

        <p>
            <label for="pwd">Password</label>
            <input type="password" id="pwd" name="pwd" value="">
        </p>
        <p>
            <label for="pwd2">Repeat Password</label>
            <input type="password" id="pwd2" name="pwd2" value="">
        </p>

        <p>
            <input type="submit" id="registerButton" value="Register!">
        </p>
    </form>
</main>

<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home" />
</jsp:include>
</body>
</html>