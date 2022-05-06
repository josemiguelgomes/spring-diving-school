<style>
r { color: Red }
o { color: Orange }
g { color: Green }
</style>

# Dive School App
[![Build Status](https://circleci.com/gh/josemiguelgomes/spring-diving-school/tree/main.svg?style=svg)](https://circleci.com/gh/josemiguelgomes/spring-diving-school/tree/main)
[![Coverage Status](https://coveralls.io/repos/github/josemiguelgomes/spring-diving-school/badge.svg?branch=main)](https://coveralls.io/github/josemiguelgomes/spring-diving-school?branch=main)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/josemiguelgomes/spring-diving-school/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/josemiguelgomes/spring-diving-school)


# spring-diving-school
Dive School App & API

# Endpoints 
Status: <r>TODO</r> <o>NOT TESTED</o> <g>DONE</g>


Status: <r>TODO</r> <o>NOT TESTED</o> <g>DONE</g>

|**Mapping**| **Endpoint**                                           | **Controller**              |  **HTML file**                                     | **Status**       | **Method**               |
|:----------|:-------------------------------------------------------|:----------------------------|----------------------------------------------------|------------------|--------------------------|
| Request   |/                                                       | IndexController.java        | index                                              |<g>DONE</g>       | getIndexPage()           |
| Request   |/oups                                                   | IndexController.java        | notimplemented                                     |<g>DONE</g>       | oupsHandler()            |
|           |                                                        |                             |                                                    |                  |                          |
| Request   |/cards                                                  | CardController.java         | cards/index                                        |<o>NOT TESTED</o> | listCards()              |
| Request   |/cards/{cardId}/show                                    | CardController.java         | cards/show                                         |<o>NOT TESTED</o> | showById()               |
| GET       |/cards/new                                              | CardController.java         | cards/cardform                                     |<o>NOT TESTED</o> | newCard()                | 
| GET       |/cards/{cardId}/update                                  | CardController.java         | cards/cardform                                     |<o>NOT TESTED</o> | updateCard()             |
| POST      |/cards                                                  | CardController.java         | redirect:/cards/{cardIdSaved}/show                 |<o>NOT TESTED</o> | saveOrUpdate()           |
| GET       |/cards/{cardId}/delete                                  | CardController.java         | redirect:/cards                                    |<o>NOT TESTED</o> | deleteById()             |
|           |                                                        |                             |                                                    |                  |                          |
| Request   |/courses                                                | CourseController.java       | courses/index                                      |<o>NOT TESTED</o> | listCourses()            |
| Request   |/courses/{courseId}/show                                | CourseController.java       | courses/show                                       |<o>NOT TESTED</o> | showById()               |
| GET       |/courses/new                                            | CourseController.java       | courses/courseform                                 |<o>NOT TESTED</o> | newCourse()              |
| GET       |/courses/{courseID}/update                              | CourseController.java       | courses/courseform                                 |<o>NOT TESTED</o> | updateCourse()           |
| POST      |/courses                                                | CourseController.java       | redirect:/courses/{courseIdSaved}/show             |<o>NOT TESTED</o> | saveOrUpdate()           |
| GET       |/courses/{id}/delete                                    | CourseController.java       | redirect:/courses                                  |<o>NOT TESTED</o> | deleteById()             |
| Request   |/courses/{courseId}/slots                               | CourseController.java       | courses/slots/list                                 |<o>NOT TESTED</o> | listCourseSlots()        |
| Request   |/courses/{courseId}/slots/{slotId}/show                 | CourseController.java       | courses/slots/show                                 |<o>NOT TESTED</o> | showCourseSlot()         |
|           |                                                        |                             |                                                    |                  |                          |
| Request   |/instructors                                            | InstructorController.java   | instructors/index                                  |<o>NOT TESTED</o> | listInstructors()        |
| Request   |/instructors/{instructorId}/show                        | InstructorController.java   | instructors/show                                   |<o>NOT TESTED</o> | showById()               |
| GET       |/instructors/new                                        | InstructorController.java   | instructors/instructorform                         |<o>NOT TESTED</o> | newInstructor()          |
| GET       |/instructors/{instructorId}/update                      | InstructorController.java   | instructors/instructorform                         |<o>NOT TESTED</o> | updateInstructor()       |
| POST      |/instructors                                            | InstructorController.java   | redirect:/instructors/{instructorIdSaved}/show     |<o>NOT TESTED</o> | saveOrUpdate()           | 
| GET       |/instructors/{instructorId}/delete                      | InstructorController.java   | redirect:/instructors                              |<o>NOT TESTED</o> | deleteById()             |
| Request   |/instructors/{instructorId}/locations                   | InstructorController.java   | instructors/locations/list                         |<o>NOT TESTED</o> | listInstructorLocations()|
| Request   |/instructors/{instructorId}/locations/{locationId}/show | InstructorController.java   | instructors/locations/show                         |<o>NOT TESTED</o> | showInstructorLocation() |
| Request   |/instructors/{instructorId}/slots                       | InstructorController.java   | instructors/slots/list                             |<o>NOT TESTED</o> | listInstructorSlots()    |
| Request   |/instructors/{instructorId}/slots/{slotId}/show         | InstructorController.java   | instructors/slots/show                             |<o>NOT TESTED</o> | showInstructorSlot()     |
|           |                                                        |                             |                                                    |                  |                          |
| Request   |/locations                                              | LocationController.java     | locations/index                                    |<o>NOT TESTED</o> | listLocations()          |
| Request   |/locations/{locationId}/show                            | LocationController.java     | locations/show                                     |<o>NOT TESTED</o> | showById()               |
| GET       |/locations/new                                          | LocationController.java     | locations/locationform                             |<o>NOT TESTED</o> | newLocation()            |
| GET       |/locations/{locationId}/update                          | LocationController.java     | locations/locationform                             |<o>NOT TESTED</o> | updateLocation()         |
| POST      |/locations                                              | LocationController.java     | redirect:/locations/{locationIdSaved}/show         |<o>NOT TESTED</o> | saveOrUpdate()           |
| GET       |/locations{locationId}/delete                           | LocationController.java     | redirect:/locations                                |<o>NOT TESTED</o> | deleteById()             |
|           |                                                        |                             |                                                    |                  |                          |
| Request   |/slots                                                  | SlotController.java         | slots/index                                        |<o>NOT TESTED</o> | listSlots()              |
| Request   |/slots/{slotId}/show                                    | SlotController.java         | slots/show                                         |<o>NOT TESTED</o> | showById()               |
| Request   |/slots/new                                              | SlotController.java         | slots/slotform                                     |<o>NOT TESTED</o> | newSlot()                |
| Request   |/slots/{slotId}/update                                  | SlotController.java         | slots/slotform                                     |<o>NOT TESTED</o> | updateSlot()             |
| POST      |/slots                                                  | SlotController.java         | redirect:/slots/{slotIdSaved}/show                 |<o>NOT TESTED</o> | saveOrUpdate()           |
| GET       |/slots{slotId}/delete                                   | SlotController.java         | redirect:/slots                                    |<o>NOT TESTED</o> | deleteById()             |
| Request   |/slots/{slotId}/courses                                 | SlotController.java         | slots/courses/list                                 |<o>NOT TESTED</o> | listSlotCourses()        |
| Request   |/slots/{slotId}/courses/{courseId}/show                 | SlotController.java         | slots/courses/show                                 |<o>NOT TESTED</o> | showSlotCourse()         |
| Request   |/slots/{slotId}/instructors                             | SlotController.java         | slots/instructors/list                             |<o>NOT TESTED</o> | listSlotInstructors()    |
| Request   |/slots/{slotId}/instructors/{instructorId}/show         | SlotController.java         | slots/instructors/show                             |<o>NOT TESTED</o> | showSlotInstructor()     |
| Request   |/slots/{slotId}/slotlanguages                           | SlotLanguageController.java | slots/slotLanguages/list                           |<o>NOT TESTED</o> | listSlotSlotLanguages()  |
| Request   |/slots/{slotId}/slotlanguages/{slotLanguagesId}/show    | SlotLanguageController.java | slots/slotLanguages/show                           |<o>NOT TESTED</o> | showSlotSlotLanguage()   |
| Request   |/slots/{slotId}/students                                | SlotController.java         | slots/students/list                                |<o>NOT TESTED</o> | listSlotStudents()       |
| Request   |/slots/{slotId}/students/{studentId}/show               | SlotController.java         | slots/students/show                                |<o>NOT TESTED</o> | showSlotStudent()        |
|           |                                                        |                             |                                                    |                  |                          |
| Request   |/slotLanguages                                          | SlotLanguageController.java | slotLanguages/index                                |<o>NOT TESTED</o> | listSlotLanguages()      |
| Request   |/slotLanguages/{slotLanguageId}/show                    | SlotLanguageController.java | slots/show                                         |<o>NOT TESTED</o> | showById()               |
| Request   |/slotLanguages/new                                      | SlotLanguageController.java | slots/slotLanguageform                             |<o>NOT TESTED</o> | newSlot()                |
| Request   |/slotLanguages/{slotLanguageId}/update                  | SlotLanguageController.java | slots/slotLanguageform                             |<o>NOT TESTED</o> | updateSlotLanguage()     |
| POST      |/slotLanguages                                          | SlotLanguageController.java | redirect:/slotLanguages/{slotLanguageIdSaved}/show |<o>NOT TESTED</o> | saveOrUpdate()           |
| GET       |/slotLanguages{slotLanguageId}/delete                   | SlotLanguageController.java | redirect:/slotLanguages                            |<o>NOT TESTED</o> | deleteById()             |
|           |                                                        |                             |                                                    |                  |                          |
| Request   |/students                                               | StudentController.java      | students/index                                     |<o>NOT TESTED</o> | listStudents()           |
| Request   |/students/{studentId}/show                              | StudentController.java      | students/show                                      |<o>NOT TESTED</o> | showById()               |
| GET       |/students/new                                           | StudentController.java      | students/studentform                               |<o>NOT TESTED</o> | newStudent()             | 
| GET       |/students/{studentId}/update                            | StudentController.java      | students/studentform                               |<o>NOT TESTED</o> | updateStudent()          |
| POST      |/students                                               | StudentController.java      | redirect:/students/{studentIdSaved}/show           |<o>NOT TESTED</o> | saveOrUpdate()           |
| GET       |/students/{studentId}/delete                            | StudentController.java      | redirect:/students                                 |<o>NOT TESTED</o> | deleteById()             |
| Request   |/students/{studentId}/cards                             | StudentController.java      | students/cards/list                                |<o>NOT TESTED</o> | listStudentCards()       | 
| Request   |/students/{studentId}/cards/{cardId}/show               | StudentController.java      | students/cards/show                                |<o>NOT TESTED</o> | showStudentCard()        |
| Request   |/students/{studentId}/locations                         | StudentController.java      | students/locations/list                            |<o>NOT TESTED</o> | listStudentLocations()   |
| Request   |/students/{studentId}/locations/{locationId}/show       | StudentController.java      | students/locations/show                            |<o>NOT TESTED</o> | showStudentLocation()    |
| Request   |/students/{studentId}/slots                             | StudentController.java      | students/slots/list                                |<o>NOT TESTED</o> | listStudentSlots()       |
| Request   |/students/{studentId}/slots/{slotId}/show               | StudentController.java      | students/slots/show                                |<o>NOT TESTED</o> | showStudentSlot()        |








# Access to H2
http://localhost:8080/h2-console

# Access to Actuators
http://localhost:8080/actuator \
http://localhost:8080/actuator/health/{component}/{instance} \
http://localhost:8080/actuator/info \


# Dive School Database Model

![Dive School Model](diving-school-model.png?raw=true "Dive School Model")


# Dive School Architecture

![Dive School Architecture](architecture.png?raw=true "Dive School Architecture")