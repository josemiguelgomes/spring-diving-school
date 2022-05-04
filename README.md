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




|**Mapping**| **Endpoint**                                     | **Controller**              |  **HTML file**                                   | **Status** |
|:----------|:-------------------------------------------------|:----------------------------|--------------------------------------------------|------------|
| Request   |/                                                 | IndexController.java        |                                                  |<g>DONE</g> |
|           |                                                  |                             |                                                  |            |
| Request   |/cards                                            | CardController.java         | cards/index                                      |<r>TODO</r> |
| Request   |/cards/{cardId}/show                              | CardController.java         | cards/show                                       |<r>TODO</r> |
| GET       |/cards/new                                        | CardController.java         | cards/cardform                                   |<r>TODO</r> |
| GET       |/cards/{cardId}/update                            | CardController.java         | cards/cardform                                   |<r>TODO</r> |
| POST      |/cards                                            | CardController.java         | redirect:/cards/{cardIdSaved}/show               |<r>TODO</r> |
| GET       |/cards/{cardId}/delete                            | CardController.java         | redirect:/cards                                  |<r>TODO</r> |
| Request   |/students/{studentId}/cards                       | CardController.java         | students/cards/list                              |<r>TODO</r> |
| Request   |/students/{studentId}/cards/{cardId}/show         | CardController.java         | students/cards/show                              |<r>TODO</r> |
|           |                                                  |                             |                                                  |            |
| Request   |/courses                                          | CourseController.java       | courses/index                                    |<r>TODO</r> |
| Request   |/courses/{courseId}/show                          | CourseController.java       | courses/show                                     |<r>TODO</r> |
| GET       |/courses/new                                      | CourseController.java       | courses/courseform                               |<r>TODO</r> |
| GET       |/courses/{courseID}/update                        | CourseController.java       | courses/courseform                               |<r>TODO</r> |
| POST      |/courses                                          | CourseController.java       | redirect:/courses/{courseIdSaved}/show           |<r>TODO</r> |
| GET       |/courses/{id}/delete                              | CourseController.java       | redirect:/courses                                |<r>TODO</r> |
| Request   |/slots/{slotId}/courses                           | CourseController.java       | slots/courses/list                               |<r>TODO</r> |
| Request   |/slots/{slotId}/courses/{courseId}                | CourseController.java       | slots/courses/show                               |<r>TODO</r> |
|           |                                                  |                             |                                                  |            |
| Request   |/instructors                                      | InstructorController.java   | instructors/index                                |<r>TODO</r> |
| Request   |/instructors/{instructorId}/show                  | InstructorController.java   | instructors/show                                 |<r>TODO</r> |
| GET       |/instructors/new                                  | InstructorController.java   | instructors/instructorform                       |<r>TODO</r> |
| GET       |/instructors/{instructorId}/update                | InstructorController.java   | instructors/instructorform                       |<r>TODO</r> |
| POST      |/instructors                                      | InstructorController.java   | redirect:/instructors/{instructorIdSaved}/show   |<r>TODO</r> | 
| GET       |/instructors/{instructorId}/delete                | InstructorController.java   | redirect:/instructors                            |<r>TODO</r> |
| Request   |/slots/{slotId}/instructors                       | InstructorController.java   | slots/instructor/list                            |<r>TODO</r> |
| Request   |/slots/{slotId}/instructors/{instructorId}        | InstructorController.java   | slots/instructor/show                            |<r>TODO</r> |
|           |                                                  |                             |                                                  |            | 
| Request   |/locations                                        | LocationController.java     | locations/index                                  |<r>TODO</r> |
| Request   |/locations/{locationId}/show                      | LocationController.java     | locations/show                                   |<r>TODO</r> |
| GET       |/locations/new                                    | LocationController.java     | locations/locationform                           |<r>TODO</r> |
| GET       |/locations/{locationId}/update                    | LocationController.java     | locations/locationform                           |<r>TODO</r> |
| POST      |/locations                                        | LocationController.java     | redirect:/locations/{locationIdSaved}/show       |<r>TODO</r> |
| GET       |/locations{locationId}/delete                     | LocationController.java     | redirect:/locations                              |<r>TODO</r> |
| Request   |/students/{studentId}/locations                   | LocationController.java     | students/locations/list                          |<r>TODO</r> |
| Request   |/students/{studentId}/locations/{locationId}      | LocationController.java     | students/locations/show                          |<r>TODO</r> |
| Request   |/instructors/{instructorId}/locations             | LocationController.java     | instructors/locations/list                       |<r>TODO</r> |
| Request   |/instructors/{instructorId}/locations/{locationId}/show| LocationController.java     | instructors/locations/show                       |<r>TODO</r> |
| Request   |/slots/{slotId}/locations                         | LocationController.java     | slots/locations/list                             |<r>TODO</r> |
| Request   |/slots/{slotId}/locations/{locationId}/show       | LocationController.java     | slots/locations/show                              |<r>TODO</r> |
|           |                                                  |                             |                                        |            |
|           |/slots                                            | SlotController.java         |                                        |<r>TODO</r> |
|           |/slots/{slotId}                                   | SlotController.java         |                                        |<r>TODO</r> |
|           |/students/{studentId}/slots                       | SlotController.java         |                                        |<r>TODO</r> |
|           |/students/{studentId}/slots/{slotId}              | SlotController.java         |                                        |<r>TODO</r> |
|           |/instructors/{instructorId}/slots                 | SlotController.java         |                                        |<r>TODO</r> |
|           |/instructors/{instructorId}/slots/{slotId}        | SlotController.java         |                                        |<r>TODO</r> |
|           |/courses/{courseId}/slots                         | SlotController.java         |                                        |<r>TODO</r> |
|           |/courses/{courseId}/slots/{slotId}                | SlotController.java         |                                        |<r>TODO</r> |
|           |                                                  |                             |                                        |            |
|           |/slotLanguages                                    | SlotLanguageController.java |                                        |<r>TODO</r> |
|           |/slotLanguages/{slotLanguageId}                   | SlotLanguageController.java |                                        |<r>TODO</r> |
|           |/slots/{slotID}/slotLanguages                     | SlotLanguageController.java |                                        |<r>TODO</r> |
|           |/slots/{slotID}/slotLanguages/{slotLanguageId}    | SlotLanguageController.java |                                        |<r>TODO</r> |
|           |                                                  |                             |                                        |            |
|           |/students                                         | StudentController.java      |                                        |<r>TODO</r> |
|           |/students/{studentId}                             | StudentController.java      |                                        |<r>TODO</r> |
|           |/slots/{slotId}/students/{studentId}              | StudentController.java      |                                        |<r>TODO</r> |







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