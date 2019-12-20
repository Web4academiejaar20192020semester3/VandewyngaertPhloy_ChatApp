package controller;

import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddNewFriend extends AsyncRequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //ingelogde persoon opvragen
        Person thisPerson = (Person) request.getSession().getAttribute("thisPerson");

        System.out.println("##################################" + request.getParameter("idFriend") + " "
                + request.getParameter("firstNameFriend") + " "
                + request.getParameter("lastNameFriend") + "##################################");

        //gegevens opvragen van de request in de url - aadNewFriends.js
        String idFriend = request.getParameter("idFriend");
        String firstNameFriend = request.getParameter("firstNameFriend");
        String lastNameFriend = request.getParameter("lastNameFriend");


        //na kijken of die persoon al bestaat & vriendjes maken als ze bestaan
        for (Person p: getPersonService().getPersons() ) {
            if(p.getUserId().equals(idFriend)
            && p.getFirstName().equals(firstNameFriend)
            && p.getLastName().equals(lastNameFriend)){

                getPersonService().makeFriends(thisPerson.getUserId(),p);
                getPersonService().makeFriends(idFriend,thisPerson);

                return null;
            }
        }


        //als die persoon niet bestaat, maak je hem, later voeg je hem toe
        Person nieuwPerson = new Person(idFriend, firstNameFriend, lastNameFriend);
        getPersonService().addPerson(nieuwPerson);

        System.out.println("Nieuwe " + nieuwPerson.getUserId() + " " + nieuwPerson.getFirstName() + " " + nieuwPerson.getLastName());

        //error controle
        ArrayList<String> errors = new ArrayList<>();

        //zien of jullie al vriendjes zijn, of als die email al bestaat
        if(idFriend.equals(thisPerson.getUserId())) errors.add("deze id bestaat al");
        else if(thisPerson.getMyFriends().contains(nieuwPerson))errors.add("jullie zijn al vriendjes stommerik");

        if(errors.size() > 0){
        } else {//nieuw persoon toevoen als die nog niet bestond
            thisPerson.addFriend(nieuwPerson);
            nieuwPerson.addFriend(thisPerson);
        }
        
        return null;
    }
}
