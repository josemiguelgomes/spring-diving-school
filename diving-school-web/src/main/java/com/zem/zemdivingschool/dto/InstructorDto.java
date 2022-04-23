package com.zem.zemdivingschool.dto;

import com.zem.zemdivingschool.persistence.model.*;

import java.util.Date;

public class InstructorDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private Date birthDate;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private Language language;
    private Byte[] photo;
    private StatusTeaching statusTeaching;

    public InstructorDto() {
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
}
