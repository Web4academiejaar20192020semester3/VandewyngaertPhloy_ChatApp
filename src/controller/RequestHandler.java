package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.PersonService;

import java.io.IOException;

public interface RequestHandler {

	String handleRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	void setModel (PersonService personService);

	PersonService getPersonService();

}