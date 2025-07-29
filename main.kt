import java.time.LocalDate

data class EmployeeData(
    val empId: String,
    val firstName:String,
    val lastName:String,
    val empRole:String,
    val reportingTo:String,
)

data class AttendanceData(
    val empId: String,
    val attendanceDate: String,
    val attendanceTime: String)



fun main(){
    val manager= EmployeeManager()


    while(true){ println("-------------------------------")
    println("Current date : ${LocalDate.now().toString()}")
        println("-------------------------------")
    println("1. Add new Employee")
    println("2. Add Attendance")
    println("3. Check Attendance at specific date")
    println("4. View all the Employee list")
    println("5. View all the Attendance list" )
    println("6. Exit")
        println("-------------------------------")


        val n = try {
            readln().toInt()
        } catch (e: NumberFormatException) {
            println("Invalid input. Please enter a number.")
            continue
        }


    when(n){
    1->manager.addEmployee()
    2->manager.attendanceValidation()
    3->{println("Enter Employee Id to check:")
        val inputId=readln()
        println("Enter the date formate in YYYY-MM-DD")
        val checkDate=readln()
        if(!manager.isCheckedIn(inputId,checkDate)){
            println("Not present")
        }
    }
    4->manager.viewAllEmployee()
    5->manager.viewAttendanceEntry()
    6->break
    }
    }
}
