package controller;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetFriendAppFirstName extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person user = (Person) request.getSession().getAttribute("thisPerson");
        String userIdParam = request.getParameter("userId");
        String userFirstNameParam = request.getParameter("firstName");

        if (userIdParam == null) {
            user.setFirstName(userFirstNameParam);
            response.setContentType("application/json");
            response.getWriter().write(toJSON(user.getFirstName()));
            return null;
        } else {
            getPersonService().getPerson(userIdParam).setFirstName(userFirstNameParam);
            response.setContentType("application/json");
            response.getWriter().write(toJSON(getPersonService().getPerson(userIdParam).getFirstName()));
            return null;
        }
    }
}