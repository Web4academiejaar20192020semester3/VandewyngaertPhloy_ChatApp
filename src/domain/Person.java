package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

	private String userId;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String salt;
	private String firstName;
	private String lastName;
	private Role role;
	@JsonIgnore
	private ArrayList<Person> myFriends;
	private String userStatus;


	public Person(String userId, String password, String firstName, String lastName, Role role) {
		setUserId(userId);
		setHashedPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setRole(role);
		this.userStatus = "offline";
		this.myFriends = new ArrayList<>();
	}

	public Person(String userId, String firstName, String lastName) {
		setUserId(userId);
		this.setPassword("t");
		setFirstName(firstName);
		setLastName(lastName);
		this.role = Role.LID;
		this.userStatus = "offline";
		this.myFriends = new ArrayList<>();
	}

	public Person(String userId, String password, String firstName, String lastName, String userStatus) {
		setUserId(userId);
		setHashedPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setUserStatus(userStatus);
		this.myFriends = new ArrayList<>();
	}

	public Person(String userId, String password, String salt, String firstName, String lastName, Role role) {
		setUserId(userId);
		setPassword(password);
		setSalt(salt);
		setFirstName(firstName);
		setLastName(lastName);
		setRole(role);
		this.userStatus = "offline";
		this.myFriends = new ArrayList<>();
	}

	public Person() {
	}


//getters
	public Role getRole() {
	return this.role;
}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserStatus() {
		return userStatus;
	}

	//setters
	public void setUserStatus(String userStatus) {
			this.userStatus = userStatus;
	}

	public void setFirstName(String firstName) {
		if (firstName.isEmpty()) {
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;// firstName;

	}

	public void setRole(Role role) {
		this.role=role;
	}

	public void setLastName(String lastName) {
		if (lastName.isEmpty()) {
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}

	public void setUserId(String userId) {
		if (userId.isEmpty()) {
			throw new IllegalArgumentException("No id given");
		}
		String USERID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(userId);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.userId = userId;
	}

	public void setPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}

	public void setHashedPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		this.password = hashPassword(password);
	}
//methodes
	//password
	public boolean isCorrectPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		return getPassword().equals(hashPassword(password, getSalt()));
	}

	private String hashPassword(String password) {
		SecureRandom random = new SecureRandom();
		byte[] seed = random.generateSeed(20);

		String salt = new BigInteger(1, seed).toString(16);
		this.setSalt(salt);

		return hashPassword(password, salt);
	}

	private String hashPassword(String password, String seed) {
		String hashedPassword = null;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(salt.getBytes("UTF-8"));
			crypt.update(password.getBytes("UTF-8"));
			hashedPassword = new BigInteger(1, crypt.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			throw new DomainException(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			throw new DomainException(e.getMessage(), e);
		}
		return hashedPassword;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSalt() {
		return salt;
	}

	//friends
	public void addFriend(Person person){
		if (!myFriends.contains(person)){
			myFriends.add(person);
		}else {
			throw new IllegalArgumentException("persoon bestaal al");
		}
	}

	public void removeFriend(Person person){
		if (myFriends.contains(person)){
			myFriends.remove(person);
		}else {
			throw new IllegalArgumentException("persoon bestaal niet");
		}
	}

	public ArrayList<Person> getMyFriends() {
		return myFriends;
	}
//--
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Person)) return false;
		Person person = (Person) o;
		return Objects.equals(getUserId(), person.getUserId()) &&
				Objects.equals(getPassword(), person.getPassword()) &&
				Objects.equals(getFirstName(), person.getFirstName()) &&
				Objects.equals(getLastName(), person.getLastName());
	}

	@Override
	public String toString() {
		return 	"firstName = " + this.firstName +
				" lastName = " + this.lastName +
				" userStatus = " + this.userStatus + '\n';
	}
}
