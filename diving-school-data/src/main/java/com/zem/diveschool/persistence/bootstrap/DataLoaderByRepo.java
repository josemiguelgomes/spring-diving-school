package com.zem.diveschool.persistence.bootstrap;

import com.zem.diveschool.persistence.model.*;
import com.zem.diveschool.persistence.repositories.*;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Profile("demobyrepo")
@Component
public class DataLoaderByRepo implements ApplicationListener<ContextRefreshedEvent> {

    // Repositories Beans
    private final CardRepository cardPointer;
    private final CourseRepository coursePointer;
    private final InstructorRepository instructorPointer;
    private final LocationRepository locationPointer;
    private final SlotLanguageRepository slotLanguagePointer;
    private final SlotRepository slotPointer;
    private final StudentRepository studentPointer;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    //
    // Constructor with DI
    //
    public DataLoaderByRepo(CardRepository cardRepository,
                            CourseRepository courseRepository,
                            InstructorRepository instructorRepository,
                            LocationRepository locationRepository,
                            SlotLanguageRepository slotLanguageRepository,
                            SlotRepository slotRepository,
                            StudentRepository studentRepository) {
        this.cardPointer = cardRepository;
        this.coursePointer = courseRepository;
        this.instructorPointer = instructorRepository;
        this.locationPointer = locationRepository;
        this.slotLanguagePointer = slotLanguageRepository;
        this.slotPointer = slotRepository;
        this.studentPointer = studentRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            instructorPointer.saveAll(getInstructors());
            coursePointer.saveAll(getCourses());
            studentPointer.saveAll(getStudents());
            slotPointer.saveAll(getSlots());
            cardPointer.saveAll(getCards());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private Set<Instructor> getInstructors() throws ParseException {
        Set<Instructor> instructors = new HashSet<>(3);

        instructors.add(Instructor.builder()
                .firstName("Jose")
                .middleName("Miguel")
                .lastName("Gomes")
                .birthDate(sdf.parse("1972-01-21"))
                .gender(Gender.MALE)
                .email("jose_miguel_gomes@hotmail.com")
                .phoneNumber("+351 96 555 81 85")
                .language(Language.PORTUGUESE)
                .photo(null)
                .statusTeaching(StatusTeaching.TEACHING)
                .homeAddress(Location.builder()
                        .streetAddress("Rua de Santa Teresinha 60A")
                        .postalCode("2775-283")
                        .city("Parede")
                        .stateProvince("Lisboa")
                        .country(Country.builder().country("PORTUGAL").build())
                        .build())
                .build()
        );

        instructors.add(Instructor.builder()
                .firstName("Cruzeta")
                .middleName("Moguli")
                .lastName("Gomes")
                .birthDate(sdf.parse("1992-01-21"))
                .gender(Gender.MALE)
                .email("oink@hotmail.com")
                .phoneNumber("+351 96 555 81 85")
                .language(Language.ENGLISH)
                .photo(null)
                .statusTeaching(StatusTeaching.TEACHING)
                .homeAddress(Location.builder()
                        .streetAddress("Rua das Maragaridas 1-B")
                        .postalCode("2775-223")
                        .city("Cascais")
                        .stateProvince("Lisboa")
                        .country(Country.builder().country("PORTUGAL").build())
                        .build())
                .build()
        );

        instructors.add(Instructor.builder()
                .firstName("Pinhada")
                .middleName("Moguli")
                .lastName("Gomes")
                .birthDate(sdf.parse("1992-01-21"))
                .gender(Gender.MALE)
                .email("muleka@hotmail.com")
                .phoneNumber("+351 96 555 81 85")
                .language(Language.ENGLISH)
                .photo(null)
                .statusTeaching(StatusTeaching.TEACHING)
                .homeAddress(Location.builder()
                        .streetAddress("Rua dos Buracos 15")
                        .postalCode("1215-223")
                        .city("Massamá")
                        .stateProvince("Lisboa")
                        .country(Country.builder().country("PORTUGAL").build())
                        .build())
                .build()
        );

        //
        return instructors;
    }

    private Set<Course> getCourses() {
        Set<Course> courses = new HashSet<>(5);
        //
        // Courses
        //
        courses.add(Course.builder()
                .name("GUE Rec1")
                .level(Level.EASY)
                .build());
        courses.add(Course.builder()
                .name("GUE Rec2")
                .level(Level.EASY)
                .build());
        courses.add(Course.builder()
                .name("GUE Rec3")
                .level(Level.EASY)
                .build());
        courses.add(Course.builder()
                .name("GUE Fundamentals")
                .level(Level.MEDIUM)
                .build());
        courses.add(Course.builder()
                .name("GUE Tech 1")
                .level(Level.MEDIUM)
                .build());
        //
        return courses;
    }

    private Set<Student> getStudents() throws ParseException {
        Set<Student> students = new HashSet<>(2);

        students.add(Student.builder()
                .firstName("Joaquina")
                .middleName("Ortacula")
                .lastName("Brunheta")
                .birthdate(sdf.parse("2001-07-21"))
                .gender(Gender.FEMALE)
                .email("joaquina_brunheta@hotmail.com")
                .phoneNumber("+351 98 4334 56 12")
                .language(Language.ENGLISH)
                .photo(null)
                .homeAddress(Location.builder()
                        .streetAddress("Rock Street")
                        .postalCode("180WSS")
                        .city("London")
                        .stateProvince("London")
                        .country(Country.builder().country("UNITED KINGDOM").build())
                        .build())
                .build());

        students.add(Student.builder()
                .firstName("Grody")
                .middleName("Mein")
                .lastName("Fagor")
                .birthdate(sdf.parse("1955-02-12"))
                .gender(Gender.OTHER)
                .email("grody1055@hotmail.com")
                .phoneNumber("+45 45 499934 006 12")
                .language(Language.ENGLISH)
                .photo(null)
                .homeAddress(Location.builder()
                        .streetAddress("Crooked Road")
                        .postalCode("180W-A-ASS")
                        .city("Southern")
                        .stateProvince("London")
                        .country(Country.builder().country("UNITED KINGDOM").build())
                        .build())
                .build());
        //
        return students;
    }

    private Set<Slot> getSlots() throws ParseException {
        Set<Slot> slots = new HashSet<>(2);

        Slot slot1 =  Slot.builder()
                .title("GUE Rec1")
                .startDate(sdf.parse("2023-05-01"))
                .endDate(sdf.parse("2023-05-07"))
                .status(SlotStatus.ON)
                .location(Location.builder()
                        .streetAddress("")
                        .postalCode("")
                        .city("")
                        .stateProvince("London")
                        .country(Country.builder().country("UNITED KINGDOM").build())
                        .build())
                .course(coursePointer.findByName("GUE Rec1").orElse(null))
                .languages(new HashSet<>())
                .instructors(new HashSet<>(Arrays.asList(
                        instructorPointer.findByFirstName("Jose").orElse(null),
                        instructorPointer.findByFirstName("Cruzeta").orElse(null),
                        instructorPointer.findByFirstName("Pinhada").orElse(null)
                )))
                .build();
        slot1.getLanguages().add(SlotLanguage.builder()
                .language(Language.ENGLISH)
                .slot(slot1)
                .build());
        slot1.getLanguages().add(SlotLanguage.builder()
                .language(Language.UKRANIAN)
                .slot(slot1)
                .build());
        slots.add(slot1);

        Slot slot2 = Slot.builder()
                .title("GUE Fundamentals")
                .startDate(sdf.parse("2024-02-05"))
                .endDate(sdf.parse("2024-03-07"))
                .status(SlotStatus.ON)
                .location(Location.builder()
                        .streetAddress("")
                        .postalCode("")
                        .city("")
                        .stateProvince("lisbon")
                        .country(Country.builder().country("UNITED KINGDOM").build())
                        .build())
                .course(coursePointer.findByName("GUE Fundamentals").orElse(null))
                .languages(new HashSet<>())
                .instructors(new HashSet<>(Arrays.asList(
                        instructorPointer.findByFirstName("Jose").orElse(null),
                        instructorPointer.findByFirstName("").orElse(null)
                )))
                .build();
        slot2.getLanguages().add(SlotLanguage.builder()
                .language(Language.ENGLISH)
                .slot(slot2)
                .build());
        slot2.getLanguages().add(SlotLanguage.builder()
                .language(Language.PORTUGUESE)
                .slot(slot2)
                .build());
        slots.add(slot2);

        //
        return slots;
    }

    private Set<Card> getCards() throws ParseException {
        Set<Card> allCards = new HashSet<>(2);

        allCards.add(Card.builder()
                .student(studentPointer.findByFirstName("Joaquina").orElse(null))
                .course("GUE Fundamentals (old style)")
                .studentName("Joaquinita Fundies")
                .startDate(sdf.parse("2021-05-20"))
                .endDate(sdf.parse("2021-05-30"))
                .country(Country.builder().country("UNITED KINGDOM").build())
                .instructorName("El Grand Instructor")
                .build()
        );

        allCards.add(Card.builder()
                .student(studentPointer.findByFirstName("Joaquina").orElse(null))
                .course("GUE Cave 1 (old style)")
                .studentName("Joaquina Caver")
                .startDate(sdf.parse("2021-07-30"))
                .endDate(sdf.parse("2021-08-15"))
                .country(Country.builder().country("FRANCE").build())
                .instructorName("El Grand Instructor Caver")
                .build()
        );

        //
        return allCards;
    }

}
