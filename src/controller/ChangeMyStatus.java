package controller;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeMyStatus extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ingelogd persoon opvragen
        Person p = (Person) request.getSession().getAttribute("thisPerson");
        String myNewStatus = request.getParameter("myNewStatus");// status halen van js


        //starus veranderen
        getPersonService().getPerson(p.getUserId()).setUserStatus(myNewStatus);
        getPersonService().updateStatus(p,myNewStatus);

        return "chat.jsp";
    }
}
