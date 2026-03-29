# plan

## Entity / Repository

### User

any person using the system (student, staff, or admin).

| Field | Type | Notes |
|-------|------|-------|
| id | Primary Key | |
| name | String | |
| email | String |  |
| password | String | hashed |
| role | Enum| STAFF, STUDENT, ADMIN|
| created_at | Timestamp | |

---

### student

Extends User. Student-specific profile data.

| Field | Type | Notes |
|-------|------|-------|
| id | Foreign Key |  users.id |
| student_number | String | UNIQUE |
| course | String | |
| year | Integer | |
| department | String | |

---

### Staff

Extends User. Staff-specific profile data.

| Field | Type | Notes |
|-------|------|-------|
| id | Foreign Key |  users.id |
| department | String | |
| title | String | like Lecturer, Tutor, Advisor |
| office_location | String | Optional |

---

### availability

the weekly recurring availability of a staff member.

| Field | Type | Notes |
|-------|------|-------|
| id | Primary Key | |
| staff_id | Foreign Key |  users.id |
| day_of_week | Enum | |
| start_time | Time | |
| end_time | Time | |

---

### timeslot

a specific bookable time slot generated from an availability window.

| Field | Type | Notes |
|-------|------|-------|
| id | Primary Key | |
| availability_id | Foreign Key |  availability.id (staff gotten throguh from here) |
| start_time | DateTime | |
| end_time | DateTime | |
| is_booked | Boolean | |

 `staff_id` is left out because you can get it  by doing
 `availability_id - availability.staff_id` and so a second foreign key isnt needed

---

### Meeting

a scheduled meeting between a student and a staff member, created when a student books a timeslot.

| Field | Type | Notes |
|-------|------|-------|
| id | Primary Key | |
| staff_id | Foreign Key |  users.id |
| student_id | Foreign Key |  users.id |
| timeslot_id | Foreign Key |  timeslots.id (UNIQUE) |
| status | Enum | SCHEDULED, CANCELLED, COMPLETED |
| notes | Text | Optional |
| created_at | Timestamp | |

---

### Relationships

| Relationship | x to y |
|---|---|
| roles - users | 1 to many |
| users (staff) - availability | 1 to many |
| availability - timeslots | 1 to many |
| timeslots - meeting | 1 to 0..1 |
| users (student) - meetings | 1 to many |
| users (staff) - meetings | 1 to many |

---

## DTOs

### CreateUserRequestDTO

Used when registering a new user.

- name
- email
- password

### UserResponseDTO

 When sending user data back to the client.

- id
- name
- email
- role
- created_at

### StaffProfileResponseDTO

Extends UserResponseDTO, returned when viewing a staff member's full profile.

- id
- name
- email
- role
- created_at
- department
- title
- office_location

### StudentProfileResponseDTO

Extends UserResponseDTO. returned when viewing a student's profile.

- id
- name
- email
- role
- created_at
- student_number
- course
- year
- department

---

### CreateAvailabilityRequestDTO

Staff sets their weekly recurring availability.

`staff_id` must be gotten from the authenticated JWT token server-side, not client.

- day_of_week
- start_time
- end_time

### AvailabilityResponseDTO

availability info to the frontend.

- id
- staff_id
- day_of_week
- start_time
- end_time

---

### TimeslotResponseDTO

Sent to students when viewing available booking slots.

- id
- staff_id (from availability)
- start_time
- end_time
- is_booked

---

### BookMeetingRequestDTO

Used when a student books a timeslot.

 `student_id` has to be gotten from the authenticated
 JWT token server-side, not supplied by the client.

- timeslot_id
- notes (optional)

### UpdateMeetingStatusDTO

Used for updating meeting status

- status (SCHEDULED | CANCELLED | COMPLETED)

### MeetingResponseDTO

Returns meeting details to staff or student.

`start_time` and `end_time` are gotten from the timeslot using
join, they aren't stored on the Meeting directly.
This is to stop duplicated data

- id
- staff_id
- student_id
- timeslot_id
- start_time (from timeslot)
- end_time (from timeslot)
- status
- notes
- created_at

---

## Services

### UserService

- `createUser`
- `getUserById`
- `getAllUsers`
- `assignRole`

### StaffService

- `getStaffById`
- `getStaffByDepartment`
- `getAllStaff`

### AvailabilityService

- `createAvailability` (calls generateTimeslotForWindow)
- `getAvailabilityByStaff`
- `updateAvailability`
- `deleteAvailability`

### TimeslotService

- `getAvailableTimeslots`
- `markSlotBooked`
- `markSlotFree`
- `generateTimeslotForWindow` generates timeslots from availability
- `extentTimeSlotWindow` scheduled job that pushes forward timeslots so they don't run out

### MeetingService

- `bookMeeting`
- `updateMeetingStatus`
- `getMeetingsForStudent`
- `getMeetingsForStaff`

### AuthenticationService

Handles JWT authentication using Spring Security. password hashing etc

- `login`
- `validateToken`
- `extractUserFromToken`

---

## Controllers

```
/api
  /auth
    POST   /login                          # Authenticate user, return JWT
    POST   /register/student
    POST   /register/staff               
    POST   /register/admin

  /users
    GET    /                               # Get all users        [ADMIN]
    GET    /{id}                           # Get user by ID       [ADMIN, SELF]

  /staff
    GET    /                               # Get all staff                      [AUTHENTICATED]
    GET    /?department={dept}             # Filter staff by department         [AUTHENTICATED]
    GET    /{staffId}                      # Get staff profile                  [AUTHENTICATED]
    GET    /my-department                  # Get staff matching student's dept  [STUDENT]

  /availability
    POST   /                               # Create availability + timeslots [STAFF]
    GET    /staff/{staffId}                # Get staff availability [AUTHENTICATED]
    PUT    /{id}                           # Update availability  [STAFF, ADMIN]
    DELETE /{id}                           # Delete availability  [STAFF, ADMIN]

  /timeslots
    GET    /staff/{staffId}                # Get available timeslots for staff  [AUTHENTICATED]

  /meetings
    POST   /                               # Book a meeting       [STUDENT]
    GET    /{id}                           # Get meeting by ID    [STAFF, STUDENT (own)]
    PUT    /{id}/status                    # Update meeting status (cancel/complete) [STAFF, STUDENT]
    GET    /student/{studentId}            # Get meetings for student [STUDENT (own), STAFF, ADMIN]
    GET    /staff/{staffId}                # Get meetings for staff   [STAFF (own), ADMIN]
```
