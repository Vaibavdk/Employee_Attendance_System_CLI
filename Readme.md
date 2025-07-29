# Employee Attendance Management System - Kotlin CLI
## Overview
This project is a command-line-based Employee Check-In system built using Kotlin. It provides functionality for managing employee records and tracking their daily attendance with real-time date and time information.

## Data Classes
### 1. EmployeeData

This data class stores the basic information of each employee:

**empId:** A unique ID for the employee. This is auto-generated based on the first character of the first name, first character of the last name, and an incrementing counter (e.g., "vk0").

**firstName:** The first name of the employee (stored in lowercase).

**lastName:** The last name of the employee (stored in lowercase).

**empRole:** The role or job title of the employee.

**reportingTo:** The employee ID of the person to whom this employee reports.

### 2. AttendanceData

This class holds each attendance record:

**empId:** The ID of the employee who checked in.

**attendanceDate:** The date when the employee checked in (in YYYY-MM-DD format).

**attendanceTime:** The time of the check-in (system current time).

### 3.Functionalities
**addEmployee()**

This function allows the user to input new employee details. It then creates an employee ID by combining the first letter of the first name, the first letter of the last name, and the current counter value. After adding the employee to the list, it increments the counter for the next ID.

**isEmployeeExist(empId: String): Boolean**

Checks whether an employee with the given ID exists in the employee list. If not, a message is shown. Returns true if the employee is found, otherwise false.

**isCheckedIn(empId: String, checkDate: String): Boolean**

Checks whether the employee has already checked in on a specific date. This is done by scanning the attendance list. If already present, a message is printed, and the function returns true.

**attendanceValidation()**

This function captures the current system date and time. It then asks for the employee ID and checks whether the ID is valid and if attendance has already been marked for that day. If validations pass, it records the attendance.

**viewAllEmployee()**

Displays all the employee details in a structured format. Each employee's ID, full name, role, and reporting manager are shown. If the list is empty, a message is printed.

**viewAttendanceEntry()**

Prints all the attendance records stored so far. If no records are found, it prints an appropriate message.

### 4.Main Menu
In the main() function, a continuous loop presents the following menu to the user:

1.Add new employee

2.Mark attendance (current date and time)

3.Check attendance for a specific date

4.View all employees

5.View attendance log

6.Exit

The user's input is handled with basic error handling using try-catch to ensure only valid numeric options are accepted.
