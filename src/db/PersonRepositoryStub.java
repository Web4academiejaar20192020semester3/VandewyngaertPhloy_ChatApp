package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Conversation;
import domain.Person;
import domain.Role;

public class PersonRepositoryStub implements PersonRepository {
	private Map<String, Person> persons = new HashMap<String, Person>();
	private List<Conversation> conversations = new ArrayList<>();


	public PersonRepositoryStub () {
		//person CREATE

		Person administrator = new Person("bib@ucll.be", "t", "Bib", "Liothekaris", Role.BIB);
		add(administrator);//toevogen

		Person jan = new Person("jan@ucll.be", "t", "Jan", "Janssens", Role.LID);
		add(jan);

		Person an = new Person("an@ucll.be", "t", "An", "Cornelissen", Role.LID);
		add(an);

		Person phloy = new Person("phloy@ucll.be", "t", "Phloy", "Vandewyngaert", Role.LID);
		add(phloy);

		//vrienden maken
		administrator.addFriend(jan);
		administrator.addFriend(an);
		administrator.addFriend(phloy);

		jan.addFriend(administrator);
		jan.addFriend(an);

		an.addFriend(administrator);
		an.addFriend(jan);

		phloy.addFriend(administrator);


	}
	public void add(Person person){//persoon toevoegen aan hashmap
		if(person == null){
			throw new IllegalArgumentException("No person given");
		}
		if (persons.containsKey(person.getUserId())) {
			throw new IllegalArgumentException("User already exists");
		}
		persons.put(person.getUserId(), person);
	}
	// Person READ
	public Person get(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id given");
		}
		return persons.get(personId);
	}
	
	public List<Person> getAll(){
		return new ArrayList<Person>(persons.values());	
	}


	//Person update
	public void update(Person person){
		if(person == null){
			throw new IllegalArgumentException("No person given");
		}
		persons.put(person.getUserId(), person);
	}

	//Person DELETE
	public void delete(String personId){
		if(personId == null){
			throw new IllegalArgumentException("No id given");
		}
		persons.remove(personId);
	}
	//andere person methodes
	public Person getAuthenticatedUser(String email, String password) {
		Person person = get(email);
		
		if (person != null && person.isCorrectPassword(password)) {
			return person;
		}
		else {
			return null;
		}
	}
	public void updateStatus(Person p, String userStatus){
		if(p == null || userStatus == null)	throw new IllegalArgumentException("No person and/or status given");
		p.setUserStatus(userStatus);
		persons.put(p.getUserId(), p);
	}



	//conversation CREATE
	public void addConversation(Conversation c) {

		conversations.add(c);
	}

	//conversation READ
	public List<Conversation> getConversations() {

		return conversations;
	}

	public List<Conversation> getAllConversationsFromThisPerson(String thisPerson) {

		List<Conversation> conversations = new ArrayList<>();

		for (Conversation c: conversations) {
			if (c.getThisPerson().equals(thisPerson) || c.getMyFriend().equals(thisPerson)) {
				conversations.add(c);
			}
		}

		return conversations;
	}

	public Conversation getConversationBetweenTwoPersons(String thisPerson, String mtFriend) throws IllegalArgumentException {

		for (Conversation c: conversations) {
			if (c.getThisPerson().equals(thisPerson) && c.getMyFriend().equals(mtFriend) ||
					c.getThisPerson().equals(mtFriend) && c.getMyFriend().equals(thisPerson)) {
				return c;
			}
		}

		throw new IllegalArgumentException("Er bestaat nog geen conversatie");
	}

	//conversation DELETE
	public void deleteConversation(Conversation c) {
		conversations.remove(c);
	}










}
