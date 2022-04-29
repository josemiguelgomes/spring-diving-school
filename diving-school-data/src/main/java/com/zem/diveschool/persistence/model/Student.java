package com.zem.diveschool.persistence.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "students")
public class Student extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Language language;
    @Column(name = "photo")
    private Byte[] photo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location homeAddress;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
 	private Set<Card> cards = new HashSet<>();
    @ManyToMany(mappedBy = "students")
	private Set<Slot> slots = new HashSet<>();

    //
    // Constructors
    //
    public Student() {
        super();
    }

    public Student(Long id, String firstName, String middleName, String lastName, Date birthdate, Gender gender,
                   String email, String phoneNumber, Language language, Location homeAddress, Byte[] photo,
                   Set<Card> cards, Set<Slot> slots) {
        super(id);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthdate;
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
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
    public Set<Card> getCards() {
        return cards;
    }
    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }
    public Set<Slot> getSlots() {
        return slots;
    }
    public void setSlots(Set<Slot> slots) {
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
        return Objects.equals(getId(), student.getId())
            && Objects.equals(firstName, student.firstName)
            && Objects.equals(middleName, student.middleName)
            && Objects.equals(lastName, student.lastName)
            && Objects.equals(birthDate, student.birthDate)
            && gender == student.gender
            && Objects.equals(email, student.email)
            && Objects.equals(phoneNumber, student.phoneNumber)
            && language == student.language
            && Objects.equals(homeAddress, student.homeAddress)
            && Arrays.equals(photo, student.photo);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId(), firstName, middleName, lastName, birthDate, gender,
                email, phoneNumber, language, homeAddress);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthDate +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", language=" + language +
                ", homeAddress=" + homeAddress +
                '}';
    }
}