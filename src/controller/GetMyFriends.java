package controller;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetMyFriends extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //vriendenlijst halen van  de gebruiker
        Person thisPerson = (Person) request.getSession().getAttribute("thisPerson");
        List<Person> myFriends = thisPerson.getMyFriends();

        System.out.println(thisPerson.getMyFriends());


        //json klaarmaken
        response.setContentType("application/json");//content type
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");//header + url
        response.getWriter().write(toJSON(myFriends)); // polling - server stuurt een json vriendenlijst mee in de response

        return null;
    }
}
