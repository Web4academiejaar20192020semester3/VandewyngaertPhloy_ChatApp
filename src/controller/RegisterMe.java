package controller;

import domain.Person;
import domain.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterMe extends SyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person newUser = new Person();
        ArrayList<String> errors = new ArrayList<>();

        try {
            newUser.setUserId(request.getParameter("email"));
            newUser.setFirstName(request.getParameter("firstname"));
            newUser.setLastName(request.getParameter("surname"));
            newUser.setPassword(request.getParameter("pwd"));
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        //kijken of passworden hetzelfde zijn

        if (!request.getParameter("pwd").equals(request.getParameter("pwd2"))) {
            errors.add("passords do not match");
        }

        if (errors.size() != 0) { request.setAttribute("errors", errors);
        } else {
            Person p = new Person(request.getParameter("email"),request.getParameter("pwd"), request.getParameter("firstname"), request.getParameter("surname"), Role.LID);
            getPersonService().addPerson(p);
        }

        return "index.jsp";
    }
}
