package domain;

public class Message {
    private String sender;
    private String message;

    //constructor
    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    //Getters
    public String getSender() {

        return sender; }

    public String getMessage() {

        return message; }


    //Setters
    public void setSender(String sender) {

        this.sender = sender; }

    public void setMessage(String message) {

        this.message = message; }
}