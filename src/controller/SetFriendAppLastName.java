package controller;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetFriendAppLastName extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person user = (Person) request.getSession().getAttribute("thisPerson");
        String userIdParam = request.getParameter("userId");
        String userLastNameParam = request.getParameter("lastName");

        if (userIdParam == null) {
            user.setLastName(userLastNameParam);
            response.setContentType("application/json");
            response.getWriter().write(toJSON(user.getLastName()));
            return null;
        } else {
            getPersonService().getPerson(userIdParam).setLastName(userLastNameParam);
            response.setContentType("application/json");
            response.getWriter().write(toJSON(getPersonService().getPerson(userIdParam).getLastName()));
            return null;
        }
    }
}