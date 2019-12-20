package controller;


import domain.Conversation;
import domain.Message;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendChat extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //huidige user opvragen
        Person thisPerson = (Person) request.getSession().getAttribute("thisPerson");

        String thisPersonString = thisPerson.getUserId();//user id van 'mezelf'
        String myFriend = request.getParameter("userId");// iser id van 'vriend'
        String chat = request.getParameter("chat");// berchit die ik wil versturen

        //bestaande converstie toevoegen
        try {
            getPersonService().getConversationBetweenTwoPersons(thisPersonString, myFriend).addMessage(new Message(thisPersonString, chat));//eigen id en bericht toevoegen aan conversation
        } catch (IllegalArgumentException e) {// als er nog geen conversatie bestaat

            getPersonService().addConversation(new Conversation(thisPersonString, myFriend));//niewe conversatie maken
            getPersonService().getConversationBetweenTwoPersons(thisPersonString, myFriend).addMessage(new Message(thisPersonString, chat));
        }

        return null;
    }
}
