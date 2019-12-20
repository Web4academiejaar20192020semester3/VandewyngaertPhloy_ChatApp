package controller;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetSpecificUser extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = getPersonService().getPerson(request.getParameter("userId"));
        response.setContentType("application/json");
        response.getWriter().write(toJSON(person));
        return null;
    }
}