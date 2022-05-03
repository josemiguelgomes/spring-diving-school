# Dive School App
[![Build Status](https://circleci.com/gh/josemiguelgomes/spring-diving-school/tree/main.svg?style=svg)](https://circleci.com/gh/josemiguelgomes/spring-diving-school/tree/main)
[![Coverage Status](https://coveralls.io/repos/github/josemiguelgomes/spring-diving-school/badge.svg?branch=main)](https://coveralls.io/github/josemiguelgomes/spring-diving-school?branch=main)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/josemiguelgomes/spring-diving-school/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/josemiguelgomes/spring-diving-school)


# spring-diving-school
Dive School

# Endpoints

## Courses
/courses/ \
/courses/{id}

## Students
/students/ \
/students/{id}

## Student Addresses
/students/ \
/students/{id}/locations \
/students/{id}/locations/{id}

## Student Cards
/students/{id}/cards \
/students/{id}/cards/{id}

## Student Slots
/students/{id}/slots \
/students/{id}/slots/{id}

## Cards (Students)
/cards \
/cards/{id}

## Addresses (Instructors and Students)
/locations \
/locations/{id}

## Slots (Instructors and Students)
/slots \
/slots/{id}
/slots/{id}/courses

## Instructors
/instructors \
/instructors/{id}

## Instructor Addresses
/instructors/{id}/locations \
/instructors/{id}/locations/{id}

## Instructor Slots
/instructors/{id}/slots \
/instructors/{id}/slots/{id}


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