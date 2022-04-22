package com.zem.zemdivingschool.persistence.model;

import javax.persistence.*;

import java.util.*;

@Entity
@Table(name = "instructors")
public class Instructor extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_Name")
    private String lastName;
    @Column(name = "birthdate")
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Language language;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location homeAddress;
    @Column(name = "photo")
    private Byte[] photo;
    @Enumerated(EnumType.STRING)
    private StatusTeaching statusTeaching;
    @ManyToMany(mappedBy = "instructors")
    private List<Slot> slots = new ArrayList<>();


    //
    // Constructors
    //

    public Instructor() {
        super();
    }

    public Instructor(Long id, String firstName, String middleName, String lastName, Date birthDate, Gender gender,
                      String email, String phoneNumber, Language language, Location homeAddress,
                      Byte[] photo, StatusTeaching statusTeaching) {
        super(id);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.language = language;
        this.homeAddress = homeAddress;
        this.photo = photo;
        this.statusTeaching = statusTeaching;
    }

    //
    // Methods
    //

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

    public StatusTeaching getStatusTeaching() {
        return statusTeaching;
    }

    public void setStatusTeaching(StatusTeaching statusTeaching) {
        this.statusTeaching = statusTeaching;
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
        Instructor that = (Instructor) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(middleName, that.middleName)
                && Objects.equals(lastName, that.lastName) && Objects.equals(birthDate, that.birthDate)
                && gender == that.gender && Objects.equals(email, that.email)
                && Objects.equals(phoneNumber, that.phoneNumber)
                && language == that.language
                && Objects.equals(homeAddress, that.homeAddress)
                && Arrays.equals(photo, that.photo)
                && statusTeaching == that.statusTeaching;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(firstName, middleName, lastName, birthDate, gender, email, phoneNumber, language, homeAddress, statusTeaching);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", language=" + language +
                ", homeAddress=" + homeAddress +
                ", statusTeaching=" + statusTeaching +
                '}';
    }
}
