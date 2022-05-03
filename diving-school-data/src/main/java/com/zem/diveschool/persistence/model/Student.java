package com.zem.diveschool.persistence.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student extends BaseEntity<Student> {
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
    @Builder
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
	public void add(Card card) {
		this.cards.add(card);
	}
    public void delete(Card card) { this.cards.remove(card); }

    public void add(Slot slot) {
		this.slots.add(slot);
	}
    public void delete(Slot slot) { this.slots.remove(slot); }
}