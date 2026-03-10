# Plan

## Entity/Repository

### User

Represents any person using the System (student, staff or admin)

- id (Primary key)
- name
- email (UNIQUE)
- password (Encrypt)
- role_id (Foreign key - roles.id)
- created_at

### Role

Defines Permissions of users in System

- id (Primary Key)
- name

### Availability

Represents the weekly availability of a staff member

- id (Primary Key)
- staff_id (Foreign - users.id)
- day_of_week
- start_time
- end_time

### Timeslot

Represents a specific booking timeslot

- id (Primary key)
- staff_id (Foreign key - users.id)
- availability_id (Foreign key - availability.id)
- start_time
- end_time
- is_booked

### Meeting

Represents a scheduled meeting between students and staff,
occurs when student books a slot

- id (Primary Key)
- staff_id (Foreign Key - users.id)
- student_id (Foreing Key - users.id)
- timeslot_id (Foreign Key - timeslots.id)
- status
- notes
- created_at

#### Relationships

- roles - users (1 to many)
- users(staff) - availablity (1 to many)
- users (staff) - timeslot (1 to many)
- availability - timeslot (1 to many)
- timeslots - meeting (to to 1 or 0)
- users (student) - meeting (1 to many)
- users staff - meetings (1 to many)

## DTO

## Service

## Controller
