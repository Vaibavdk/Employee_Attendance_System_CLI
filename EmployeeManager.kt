import java.time.LocalDateTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.Duration

class AttendanceManager(private val employeeManager: EmployeeManager) {
    private val attendanceEntry = mutableListOf<AttendanceData>()

    //Check whether the employee exist or not
    fun isEmployeeExist(inputId: String): Boolean {
        val employeeComponent = employeeManager.employeeList.any { it.empId == inputId }
        if(!employeeComponent){
            println("Employee doesn't Exist")
        }
        return employeeComponent
    }

    //Check whether the employee already checked in or not
    fun isCheckedIn(inputId: String, checkDateTime: LocalDateTime): Boolean {
        val alreadyCheckedIn = attendanceEntry.any {
            it.empId == inputId && it.checkInDateTime.toLocalDate() == checkDateTime.toLocalDate()
        }

        if (alreadyCheckedIn) {
            println("Already checked in!!")
            return true
        }
        return false
    }

    //Used mark the given employee id as checked in
    fun todayCheckIn() {
        print("Enter the Employee ID to add the Attendance: ")
        val inputId = readln().trim()

        val currentDateTime = LocalDateTime.now()

        if (isEmployeeExist(inputId)) {
            if (!isCheckedIn(inputId, currentDateTime)) {
                val newEntry = AttendanceData(inputId, currentDateTime)
                attendanceEntry.add(newEntry)
                println("Attendance has been marked successfully!!")
            }
        }

    }

    //Manual entry for the given date and the employee id
    fun manualCheckIn() {
        print("Enter the Employee ID: ")
        val empId = readln().trim()
        if (!isEmployeeExist(empId)) return

        print("Enter date and time (YYYY-MM-DD HH:MM):")
        val input = readln().trim()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val inputDateTime = if (input.isEmpty()) {
            LocalDateTime.now()
        } else {
            try {
                LocalDateTime.parse(input, formatter)
            } catch (e: Exception) {
                println("Invalid date-time format. Use YYYY-MM-DD HH:MM")
                return
            }
        }

        val today = LocalDate.now()
        val inputDate = inputDateTime.toLocalDate()

        if (inputDate.isAfter(today)) {
            println("You can't check in for a future date.")
            return
        }

        if (isCheckedIn(empId, inputDateTime)) return

        attendanceEntry.add(AttendanceData(empId, inputDateTime))
        println("Manual check-in added successfully.")
    }

    //To view attendance list
    fun viewAttendanceEntry() {
        if (attendanceEntry.isEmpty()) {
            println("No attendance records found.")
            return
        }

        println("List of All Attendance Entries:")
        println("--------------------------------------------")
        attendanceEntry.forEach { emp ->
            println("|ID: ${emp.empId.uppercase()} | Check-IN DateTime: ${emp.checkInDateTime}| Check-OUT DateTime: ${emp.checkOutDateTime}| Number Of Working Hours: ${emp.workingHours}|")
        }
        println("--------------------------------------------")
    }

    //Used to mark checkout time and calculate the working hour
    fun checkOutEmployee() {
        print("Enter the Employee ID for checkout: ")
        val empId = readln().trim()

        // Find today's check-in record for this employee
        val today = LocalDate.now()
        val record = attendanceEntry.find {
            it.empId == empId &&
                    it.checkInDateTime.toLocalDate() == today &&
                    it.checkOutDateTime == null
        }

        if (record == null) {
            println("No check-in record found for today or already checked out.")
            return
        }

        val checkOutTime = LocalDateTime.now()

        if (checkOutTime.isBefore(record.checkInDateTime)) {
            println("Invalid checkout time. Cannot be before check-in.")
            return
        }

        record.checkOutDateTime = checkOutTime

        val duration = Duration.between(record.checkInDateTime, checkOutTime)

        val totalMinutes = duration.toMinutes()
        val hours = totalMinutes / 60        // integer division
        val minutes = totalMinutes % 60      // remainder minutes

        println("Total working time: $hours hours and $minutes minutes")
        record.workingHours=totalMinutes/60.0




    }

}
