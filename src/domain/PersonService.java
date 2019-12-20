package domain;

import java.util.List;

import db.PersonRepository;
import db.PersonRepositoryStub;

public class PersonService {
	private PersonRepository personRepository = new PersonRepositoryStub();

	public PersonService(){
	}
	//person CRUD

	public void addPerson(Person person) {
		getPersonRepository().add(person);
	}

	public Person getPerson(String personId)  {	return getPersonRepository().get(personId);	}

	public List<Person> getPersons() {
		return getPersonRepository().getAll();
	}

	public void updatePersons(Person person) {
		getPersonRepository().update(person);
	}

	public void deletePerson(String id) {
		getPersonRepository().delete(id);
	}


	// andere person methodes
	public Person getAuthenticatedUser(String email, String password) { return getPersonRepository().getAuthenticatedUser(email, password); }

	public void makeFriends(String id, Person friend) {
		this.getPerson(id).addFriend(friend);
	}

	public void updateStatus(Person person, String status){
		getPersonRepository().updateStatus(person,status);
	}

	private PersonRepository getPersonRepository() {
		return personRepository;
	}


	//conversation CRD

	public void addConversation(Conversation c) { getPersonRepository().addConversation(c); }

	public List<Conversation> getConversations() { return getPersonRepository().getConversations(); }

	public List<Conversation> getAllConversationsFromThisPerson(String user) { return getPersonRepository().getAllConversationsFromThisPerson(user); }

	public Conversation getConversationBetweenTwoPersons(String thisPerson, String myFriend) { return getPersonRepository().getConversationBetweenTwoPersons(thisPerson, myFriend); }

	public void deleteConversation(Conversation c) { getPersonRepository().deleteConversation(c); }


}
