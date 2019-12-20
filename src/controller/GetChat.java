package controller;

import domain.Conversation;
import domain.Message;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetChat  extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //huidige user opvragen
        Person thisPerson = (Person) request.getSession().getAttribute("thisPerson");

        String thisPersonString = thisPerson.getUserId(); //id van 'mezelf'
        String myFriend = request.getParameter("myFriend"); //van getMyFrients.js

        //chat opvragen tussen 2 mensen
        Conversation c = getPersonService().getConversationBetweenTwoPersons(thisPersonString, myFriend);


        List<Message> berichtjes = c.getMessages();//lijst van alle berichten tussen 2 personen

        String chatJSON = toJSON(berichtjes);//Message casten naar json
        response.setContentType("application/json");
        response.getWriter().write(chatJSON);//json sturen in de response

        return null;
    }
}
