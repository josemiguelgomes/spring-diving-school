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
@Table(name = "instructors")
public class Instructor extends BaseEntity<Instructor> {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_Name")
    private String lastName;
    @Column(name = "birth_Date")
    private Date birthDate = new Date();
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
    @Enumerated(EnumType.STRING)
    private StatusTeaching statusTeaching;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location homeAddress;

    @ManyToMany(mappedBy = "instructors")
    private Set<Slot> slots = new HashSet<>();


    //
    // Constructors
    //
    @Builder
    public Instructor(Long id, String firstName, String middleName, String lastName, Date birthDate, Gender gender,
                      String email, String phoneNumber, Language language, Byte[] photo, StatusTeaching statusTeaching,
                      Location homeAddress, Set<Slot> slots) {
        super(id);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.language = language;
        this.photo = photo;
        this.statusTeaching = statusTeaching;
        this.homeAddress = homeAddress;
        this.slots = slots;
    }

    //
    // Methods
    //
    public void add(Slot slot) {
        this.slots.add(slot);
    }
    public void delete(Slot slot) { this.slots.remove(slot); }
}
