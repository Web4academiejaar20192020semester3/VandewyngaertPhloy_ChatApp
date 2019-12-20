package controller;
import domain.Person;
import domain.PersonService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class SyncRequestHandler implements RequestHandler {
    private PersonService personService;

    @Override
    public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    @Override
    public void setModel (PersonService personService) {
        this.personService = personService;
    }

    @Override
    public PersonService getPersonService() {
        return personService;
    }
}
