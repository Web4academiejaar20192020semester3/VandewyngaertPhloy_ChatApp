package domain;

import java.util.ArrayList;

public class Conversation {
    private String thisPerson;
    private String myFriend;
    private ArrayList<Message> messages;

    //leeg constructor
    public Conversation(){}

    //volledige constructor
    public Conversation(String thisPerson, String myFriend) {
        setThisPerson(thisPerson);
        setMyFriend(myFriend);
        messages = new ArrayList<>();
    }

    //Getters
    public String getThisPerson() {
        return thisPerson;
    }

    public String getMyFriend() {
        return myFriend;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }


    //Setters
    public void setThisPerson(String thisPerson) {
        this.thisPerson = thisPerson;
    }

    public void setMyFriend(String myFriend) {
        this.myFriend = myFriend;
    }

    //Andere Methodes

    public void addMessage(Message message) {
        messages.add(message); }

}