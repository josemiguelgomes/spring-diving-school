package com.zem.diveschool.persistence.bootstrap;

import com.zem.diveschool.persistence.model.*;
import com.zem.diveschool.persistence.services.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Profile("demobyservices")
public class DataLoaderByService implements ApplicationListener<ContextRefreshedEvent> {

    // Services Beans
    private final CardService cardService;
    private final CourseService courseService;
    private final InstructorService instructorService;
    private final LocationService locationService;
    private final SlotLanguageService slotLanguageService;
    private final SlotService slotService;
    private final StudentService studentService;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    //
    // Constructor with DI
    //


    public DataLoaderByService(CardService cardService, CourseService courseService,
                               InstructorService instructorService, LocationService locationService,
                               SlotLanguageService slotLanguageService, SlotService slotService,
                               StudentService studentService) {
        this.cardService = cardService;
        this.courseService = courseService;
        this.instructorService = instructorService;
        this.locationService = locationService;
        this.slotLanguageService = slotLanguageService;
        this.slotService = slotService;
        this.studentService = studentService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        instructorService.saveAll(getInstructors());
        courseService.saveAll(getCourses());
        studentService.saveAll(getStudents());
        slotService.saveAll(getSlots());
        cardService.saveAll(getCards());
    }

    private List<Instructor> getInstructors() {

        List<Instructor> instructors = new ArrayList<>(3);

        //
        // Instructor 1
        //
        Instructor instructor1 = new Instructor();
        instructor1.setFirstName("Jose");
        instructor1.setMiddleName("Miguel");
        instructor1.setLastName("Gomes");
        try {
            instructor1.setBirthDate(sdf.parse("1972-01-21"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        instructor1.setGender(Gender.MALE);
        instructor1.setEmail("jose_miguel_gomes@hotmail.com");
        instructor1.setPhoneNumber("+351 96 555 81 85");
        instructor1.setLanguage(Language.PORTUGUESE);
        instructor1.setPhoto(null);
        instructor1.setStatusTeaching(StatusTeaching.TEACHING);

        Location homeAddress1 = new Location();
        homeAddress1.setStreetAddress("Rua de Santa Teresinha 60A");
        homeAddress1.setPostalCode("2775-283");
        homeAddress1.setCity("Parede");
        homeAddress1.setStateProvince("Lisboa");
        homeAddress1.setCountry(Country.PORTUGAL);

        instructor1.setHomeAddress(homeAddress1);

        instructors.add(instructor1);

        //
        // Instructor 2
        //
        Instructor instructor2 = new Instructor();
        instructor2.setFirstName("Antonio");
        instructor2.setMiddleName("Couvert");
        instructor2.setLastName("Legumes");
        try {
            instructor2.setBirthDate(sdf.parse("1980-04-30"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        instructor2.setGender(Gender.MALE);
        instructor2.setEmail("antonio_legumes@hotmail.com");
        instructor2.setPhoneNumber("+351 98 658 75 45");
        instructor2.setLanguage(Language.PORTUGUESE);
        instructor2.setPhoto(null);
        instructor2.setStatusTeaching(StatusTeaching.TEACHING);

        Location homeAddress2 = new Location();
        homeAddress2.setStreetAddress("Rua dos Legumes 2F");
        homeAddress2.setPostalCode("1000-001");
        homeAddress2.setCity("Lisboa");
        homeAddress2.setStateProvince("Lisboa");
        homeAddress2.setCountry(Country.PORTUGAL);

        instructor2.setHomeAddress(homeAddress2);

        instructors.add(instructor2);

        //
        // Instructor 3
        //
        Instructor instructor3 = new Instructor();
        instructor3.setFirstName("Cruzeta");
        instructor3.setMiddleName("Silva");
        instructor3.setLastName("Morgadinha");
        try {
            instructor3.setBirthDate(sdf.parse("2001-07-14"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        instructor3.setGender(Gender.FEMALE);
        instructor3.setEmail("cruzeta_morgadinha@hotmail.com");
        instructor3.setPhoneNumber("+351 91 587 33 45");
        instructor3.setLanguage(Language.UKRANIAN);
        instructor3.setPhoto(null);
        instructor3.setStatusTeaching(StatusTeaching.NOT_TEACHING);

        Location homeAddress3 = new Location();
        homeAddress3.setStreetAddress("Rua Esburacada");
        homeAddress3.setPostalCode("2000-001");
        homeAddress3.setCity("Kiev");
        homeAddress3.setStateProvince("Kiev");
        homeAddress3.setCountry(Country.UKRAINE);

        instructor3.setHomeAddress(homeAddress3);

        instructors.add(instructor3);


        //
        return instructors;
    }

    private List<Course> getCourses() {

        List<Course> courses = new ArrayList<>(5);

        //
        // Course 1
        //
        Course course1 = new Course();
        course1.setName("GUE Rec1");
        course1.setLevel(Level.EASY);

        courses.add(course1);

        //
        // Course 2
        //
        Course course2 = new Course();
        course2.setName("GUE Rec2");
        course2.setLevel(Level.EASY);

        courses.add(course2);

        //
        // Course 3
        //
        Course course3 = new Course();
        course3.setName("GUE Rec3");
        course3.setLevel(Level.MEDIUM);

        courses.add(course3);

        //
        // Course 4
        //
        Course course4 = new Course();
        course4.setName("GUE Fundamentals");
        course4.setLevel(Level.MEDIUM);

        courses.add(course4);

        //
        // Course 1
        //
        Course course5 = new Course();
        course5.setName("GUE Tech 1");
        course5.setLevel(Level.DIFFICULT);

        courses.add(course5);

        //
        return courses;

    }
    private List<Student> getStudents() {

        List<Student> students = new ArrayList<>(2);

        //
        // Student 1
        //
        Student student1 = new Student();
        student1.setFirstName("Joaquina");
        student1.setMiddleName("Ortacula");
        student1.setLastName("Brunheta");
        try {
            student1.setBirthDate(sdf.parse("2001-07-21"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        student1.setGender(Gender.FEMALE);
        student1.setEmail("joaquina_brunheta@hotmail.com");
        student1.setPhoneNumber("+351 98 4334 56 12");
        student1.setLanguage(Language.ENGLISH);
        student1.setPhoto(null);

        Location homeAddress1 = new Location();
        homeAddress1.setStreetAddress("Rock Street");
        homeAddress1.setPostalCode("180WSS");
        homeAddress1.setCity("London");
        homeAddress1.setStateProvince("London");
        homeAddress1.setCountry(Country.UNITED_KINGDOM);

        student1.setHomeAddress(homeAddress1);

        //
        students.add(student1);

        //
        // Student 2
        //
        Student student2 = new Student();
        student2.setFirstName("Grody");
        student2.setMiddleName("Mein");
        student2.setLastName("Fagor");
        try {
            student2.setBirthDate(sdf.parse("1955-02-12"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        student2.setGender(Gender.OTHER);
        student2.setEmail("grody1055@hotmail.com");
        student2.setPhoneNumber("+45 45 499934 006 12");
        student2.setLanguage(Language.ENGLISH);
        student2.setPhoto(null);

        Location homeAddress2 = new Location();
        homeAddress2.setStreetAddress("Crooked Road");
        homeAddress2.setPostalCode("1435WSA");
        homeAddress2.setCity("Southern");
        homeAddress2.setStateProvince("London");
        homeAddress2.setCountry(Country.UNITED_KINGDOM);

        student2.setHomeAddress(homeAddress2);

        //
        students.add(student2);

        //
        return students;
    }
    private List<Slot> getSlots() {

        List<Slot> slots = new ArrayList<>(2);

        //
        // Slot 1
        //
        Slot slot1 = new Slot();
        slot1.setTitle("GUE Rec1");
        try {
            slot1.setStartDate(sdf.parse("2023-05-01"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            slot1.setEndDate(sdf.parse("2023-05-07"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        slot1.setStatus(SlotStatus.ON);

        Location location1 = new Location();
        location1.setStreetAddress("");
        location1.setPostalCode("");
        location1.setCity("");
        location1.setStateProvince("");
        location1.setCountry(Country.UNITED_KINGDOM);
        slot1.setLocation(location1);
        Optional<Course> course1 = courseService.findByName("GUE Rec1");
        if (course1.isEmpty()) {
            throw new RuntimeException("Please provide a course that exists");
        }
        slot1.setCourse(course1.get());

        // Associate Language(s) to the slot
        List<SlotLanguage> slotLanguages1 = new ArrayList<>(2);
        SlotLanguage slotLanguage1A = new SlotLanguage();
        slotLanguage1A.setLanguage(Language.ENGLISH);
        slotLanguage1A.setSlot(slot1);
        slotLanguages1.add(slotLanguage1A);

        SlotLanguage slotLanguage1B = new SlotLanguage();
        slotLanguage1B.setLanguage(Language.UKRANIAN);
        slotLanguage1B.setSlot(slot1);
        slotLanguages1.add(slotLanguage1B);

        slot1.setLanguages(slotLanguages1);

        // Associate instructor(s) to the slot
        List<Instructor> instructors1 = new ArrayList<>();

        Optional<Instructor> instructor1A = instructorService.findByFirstName("Jose");
        if (instructor1A.isEmpty()) {
            throw new RuntimeException("Please provide an instructor that exists");
        }
        instructors1.add(instructor1A.get());

        Optional<Instructor> instructor1B = instructorService.findByFirstName("Antonio");
        if (instructor1B.isEmpty()) {
            throw new RuntimeException("Please provide an instructor that exists");
        }
        instructors1.add(instructor1B.get());

        slot1.setInstructors(instructors1);

        // Associate student(s) to the slot
        List<Student> student1 = new ArrayList<>();
        Optional<Student> student1A = studentService.findByFirstName("Grody");
        if (student1A.isEmpty()) {
            throw new RuntimeException("Please provide an student that exists");
        }
        student1.add(student1A.get());

        slot1.setStudents(student1);

        //
        slots.add(slot1);

        //
        // Slot 2
        //
        Slot slot2 = new Slot();
        slot2.setTitle("GUE Fundamentals");
        try {
            slot2.setStartDate(sdf.parse("2024-01-01"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            slot2.setEndDate(sdf.parse("2024-01-05"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        slot2.setStatus(SlotStatus.ON);

        Location location2 = new Location();
        location2.setStreetAddress("");
        location2.setPostalCode("");
        location2.setCity("");
        location2.setStateProvince("");
        location2.setCountry(Country.PORTUGAL);
        slot2.setLocation(location2);
        Optional<Course> course2 = courseService.findByName("GUE Fundamentals");
        if (course2.isEmpty()) {
            throw new RuntimeException("Please provide a course that exists");
        }
        slot2.setCourse(course2.get());

        List<SlotLanguage> slotLanguages2 = new ArrayList<>(1);
        SlotLanguage slotLanguage2A = new SlotLanguage();
        slotLanguage2A.setLanguage(Language.ENGLISH);
        slotLanguage2A.setSlot(slot2);
        slotLanguages2.add(slotLanguage2A);

        slot2.setLanguages(slotLanguages2);

        // Associate instructor(s) to the slot
        List<Instructor> instructors2 = new ArrayList<>();

        Optional<Instructor> instructor2A = instructorService.findByFirstName("Cruzeta");
        if (instructor2A.isEmpty()) {
            throw new RuntimeException("Please provide an instructor that exists");
        }
        instructors2.add(instructor2A.get());

        slot2.setInstructors(instructors2);

        //
        slots.add(slot2);
        //
        return slots;
    }
    private List<Card> getCards() {

        List<Card> allCards = new ArrayList<>(2);

        //
        // Student 1
        //
        Optional<Student> student1 = studentService.findByFirstName("Joaquina");
        if (student1.isEmpty()) {
            throw new RuntimeException("Please provide a student that exists");
        }
        List<Card> cards = new ArrayList<>(2);
        Card card1 = new Card();
        card1.setCourse("GUE Fundamentals (old style)");
        card1.setStudentName("Joaquinita Fundies");
        try {
            card1.setStartDate(sdf.parse("2021-05-20"));
            card1.setEndDate(sdf.parse("2021-05-30"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        card1.setCountry(Country.PORTUGAL);
        card1.setInstructorName("El Grand Instructor");
        card1.setStudent(student1.get());
        cards.add(card1);

        allCards.add(card1);

        Card card2 = new Card();
        card2.setCourse("GUE Cave 1 (very old style)");
        card2.setStudentName("Joaquina Caver");
        try {
            card2.setStartDate(sdf.parse("2021-07-30"));
            card2.setEndDate(sdf.parse("2021-08-15"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        card2.setCountry(Country.FRANCE);
        card2.setInstructorName("El Grand Instructor Caver");
        card2.setStudent(student1.get());
        cards.add(card2);

        allCards.add(card2);

        //
        return allCards;
    }


}
