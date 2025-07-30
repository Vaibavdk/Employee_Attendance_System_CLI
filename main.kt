import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class EmployeeData(
    val empId: String,
    val firstName: String,
    val lastName: String,
    val empRole: String,
    val reportingTo: String,
)

data class AttendanceData(
    val empId: String,
    val checkInDateTime: LocalDateTime,
    var checkOutDateTime: LocalDateTime?=null,
    var workingHours: Double?=null

)

fun start(employeeManager: EmployeeManager,attendanceManager: AttendanceManager){
    while (true) {
        println("-------------------------------------")
        println("Current date & time: ${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}")
        println("-------------------------------------")
        println("1. Add new Employee")
        println("2. Mark Check-in today")
        println("3. Manual Check-in with DateTime")
        println("4. Check Attendance at specific date")
        println("5. View all the Employee list")
        println("6. View all the Attendance list")
        println("7. Check-Out")
        println("8. Exit")
        println("-------------------------------------")

        val n = try {
            readln().toInt()
        } catch (e: NumberFormatException) {
            println("Invalid input. Please enter a number.")
            continue
        }

        when (n) {
            1 -> employeeManager.addEmployee()
            2 -> attendanceManager.todayCheckIn()
            3 -> attendanceManager.manualCheckIn()
            4 -> {
                print("Enter Employee ID: ")
                val inputId = readln().trim()
                print("Enter date and time (YYYY-MM-DD HH:MM): ")
                val dateInput = readln().trim()

                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                val dateTime = try {
                    LocalDateTime.parse(dateInput, formatter)
                } catch (e: Exception) {
                    println("Invalid date-time format.")
                    continue
                }

                if (!attendanceManager.isCheckedIn(inputId, dateTime)) {
                    println("Not present")
                }
            }
            5 -> employeeManager.viewAllEmployee()
            6 -> attendanceManager.viewAttendanceEntry()
            7 -> attendanceManager.checkOutEmployee()
            8->break
            else -> println("Invalid option. Try again.")
        }
    }

}

fun main() {
    val employeeManager = EmployeeManager()

    val attendanceManager=AttendanceManager(employeeManager)

    start(employeeManager,attendanceManager)

}
