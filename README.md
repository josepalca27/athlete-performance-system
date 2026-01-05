# Athlete Performance Management System

The Athlete Performance Management System is a web application built using Spring Boot and Thymeleaf that helps manage athletes, record workouts and training sessions, and monitor performance statistics over time.

I created this project because I wanted to combine my love of sports, fitness, and performance tracking with my expertise in software engineering to produce a useful, real-world application.

Full-stack development features, such as MVC architecture, server-side rendering, data persistence, and user-friendly UX design, are demonstrated in this project.

## Project Overview
The Athlete Performance Management System allows users to:

- Develop and oversee athletes
- Keep track of your soccer workouts and training sessions.
- Verify the inputs (dates, length, intensity, etc.)
- View comprehensive athlete profiles that include training history
- Use CSV files to store all data; no database is needed.

The project's objective was to create a clear, practical, and user-friendly system that resembled what an athlete or coach may actually utilize.

## Why I Built this
I wanted to create a project that felt practical and personal rather than merely academic because I like being active and keeping track of my workouts.
I was able to practice with this system:

- Java-based backend logic
- Spring Boot-based MVC architecture
- Using Thymeleaf for server-side rendering
- Validation of input and error management
- A tidy Git workflow and project structure

## Features

### Athletes

- Add new athletes along with their physical and biographical details
- See every athlete in a spotless table
- To see an athlete's complete profile, click on their name.
- Improved country field with flags and acronyms

### Athlete Detail Page

- View details about the athlete, including age, sport, position, height, weight, and nation.
- View the recorded soccer matches
- View the workouts that have been tracked
- Buttons for quick access to record new workouts or sessions

### Soccer Session & Workout

* Log sessions/workouts with:

    - Date (validated format)
    - Duration (must be positive)
    - Intensity (1–10 scale)
    - Optional notes

* Friendly validation messages for invalid inputs

### UI & UX 

- Reusable footer and navbar components
- Uniform spacing and arrangement
- Empty-state messages in the absence of data
- Easy, polished styling (clean design, few colors)

## Tech Stack

- Java 21
- Spring Boot
- Thymeleaf
- Maven
- HTML / CSS
- CSV file persistence
- Git & GitHub

## Project Structure 

athlete-performance-system
├── java-app/         
├── web-app/           
│   ├── controllers
│   ├── services
│   ├── models
│   ├── templates
│   │   ├── fragments  
│   │   └── pages
│   └── static
│       └── style.css
├── data/              
├── docs/              
└── README.md

## How to Run the Project
 ### Prerequisites

 - Java 17+ (Java 21 recommended)
 - Maven

  ### Run the app 

  cd web-app/athlete-web
./mvnw spring-boot:run

Then open your brower and go to: http://localhost:8080

## Sample Data

For demonstration purposes, this repository contains sample athletes, sessions, and routines.
When someone runs the project, they may add more and view realistic data right away.

## Feature Improvements 

Some ideas I may explore later:

- Edit or delete athletes
- Search and filtering
- Charts for performance trends
- Database persistence (JPA / PostgreSQL)
- Authentication for different user roles

## Author

Jose Palma Cadenas Computer Science student
Passionate about sports, fitness, and building meaningful software

