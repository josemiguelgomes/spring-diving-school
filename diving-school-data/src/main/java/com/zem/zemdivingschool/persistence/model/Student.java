package com.zem.zemdivingschool.persistence.model;

import java.util.*;


public class Student {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthdate;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private Language language;
    private Location homeAddress;
    private Byte[] photo;

 	private List<Card> cards = new ArrayList<>();
	private List<Slot> slots = new ArrayList<>();

    //
    // Constructors
    //
    public Student() {
    }

    public Student(Long id, String firstName, String middleName, String lastName, Date birthdate, Gender gender,
                   String email, String phoneNumber, Language language, Location homeAddress, Byte[] photo,
                   List<Card> cards, List<Slot> slots) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.language = language;
        this.homeAddress = homeAddress;
        this.photo = photo;
        this.cards = cards;
        this.slots = slots;
    }

    //
	// Methods
	//
	public void addCard(Card card) {
		this.cards.add(card);
	}
    public void deleteCard(Card card) { this.cards.remove(card); }

    public void addSlot(Slot slot) {
		this.slots.add(slot);
	}
    public void deleteSlot(Slot slot) { this.slots.remove(slot); }

	//
	// Setters & Getters
	//
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Location getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Location homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    //
    //
    //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id)
            && Objects.equals(firstName, student.firstName)
            && Objects.equals(middleName, student.middleName)
            && Objects.equals(lastName, student.lastName)
            && Objects.equals(birthdate, student.birthdate)
            && gender == student.gender
            && Objects.equals(email, student.email)
            && Objects.equals(phoneNumber, student.phoneNumber)
            && language == student.language
            && Objects.equals(homeAddress, student.homeAddress)
            && Arrays.equals(photo, student.photo);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, firstName, middleName, lastName, birthdate, gender,
                email, phoneNumber, language, homeAddress);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", language=" + language +
                ", homeAddress=" + homeAddress +
                '}';
    }
}