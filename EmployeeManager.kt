import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EmployeeManager {
    val employeeList = mutableListOf<EmployeeData>()
    var counter = 0
    val attendanceEntry = mutableListOf<AttendanceData>()

    fun addEmployee() {
        println("Enter the details of the employee below:")

        print("Enter the First name: ")
        val firstName = readln().trim().lowercase()

        print("Enter the Last name: ")
        val lastName = readln().trim().lowercase()

        print("Enter the Role: ")
        val empRole = readln().trim().lowercase()

        print("Enter the Reporting Manager ID: ")
        val reportingTo = readln().trim().lowercase()

        val empId = "${firstName[0]}${lastName[0]}$counter"

        val employee = EmployeeData(empId, firstName, lastName, empRole, reportingTo)
        employeeList.add(employee)
        println("Employee has been added successfully and the Employee ID is $empId")
        counter++
    }

    fun isEmployeeExist(inputId: String): Boolean {
        val employeeComponent = employeeList.find { it.empId == inputId }
        if (employeeComponent == null) {
            println("Employee not found.")
            return false
        }
        return true
    }

    fun isCheckedIn(inputId: String, checkDateTime: LocalDateTime): Boolean {
        val alreadyCheckedIn = attendanceEntry.any {
            it.empId == inputId && it.attendanceDateTime.toLocalDate() == checkDateTime.toLocalDate()
        }
        if (alreadyCheckedIn) {
            println("Already checked in!!")
            return true
        }
        return false
    }

    fun attendanceValidation() {
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

    fun manualCheckIn() {
        print("Enter the Employee ID: ")
        val empId = readln().trim()
        if (!isEmployeeExist(empId)) return

        print("Enter date and time (YYYY-MM-DD HH:MM), or leave blank to use current: ")
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


        if (isCheckedIn(empId, inputDateTime)) return

        attendanceEntry.add(AttendanceData(empId, inputDateTime))
        println("Manual check-in added successfully.")
    }

    fun viewAllEmployee() {
        if (employeeList.isEmpty()) {
            println("No employees found.")
            return
        }
        println("List of All Employees:")
        println("--------------------------------------------")
        employeeList.forEach { emp ->
            println("| ID: ${emp.empId.uppercase()} | Name: ${emp.firstName.replaceFirstChar(Char::uppercase)} ${emp.lastName.replaceFirstChar(Char::uppercase)} | Role: ${emp.empRole.replaceFirstChar(Char::uppercase)} | Reports to: ${emp.reportingTo.uppercase()} |")
        }
        println("--------------------------------------------")
    }

    fun viewAttendanceEntry() {
        if (attendanceEntry.isEmpty()) {
            println("No attendance records found.")
            return
        }
        println("List of All Attendance Entries:")
        println("--------------------------------------------")
        attendanceEntry.forEach { emp ->
            val formatted = emp.attendanceDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            println("|ID: ${emp.empId.uppercase()} | DateTime: $formatted|")
        }
        println("--------------------------------------------")
    }
}
