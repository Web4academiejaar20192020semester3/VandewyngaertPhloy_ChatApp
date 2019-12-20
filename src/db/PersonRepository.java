package db;

import java.util.List;

import domain.Conversation;
import domain.Person;

public interface PersonRepository {
//person CRUD

   abstract void add(Person person);

	abstract List<Person> getAll();

	abstract Person get(String userId);

	abstract void update(Person person);

	abstract void delete(String userId);

	//andere person methodes
	abstract Person getAuthenticatedUser(String email, String password);

	void updateStatus(Person person, String status);

	//conversation CRD

	void addConversation(Conversation c);

	List<Conversation> getConversations();

	List<Conversation> getAllConversationsFromThisPerson(String thisPerson);

	void deleteConversation(Conversation c);

	// andere converation methodes
	Conversation getConversationBetweenTwoPersons(String user, String chatBuddy);




}