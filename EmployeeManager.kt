import java.time.LocalDate
import java.time.LocalTime

class EmployeeManager {
    val employeeList=mutableListOf<EmployeeData>()
    var counter=0
    fun addEmployee(){
        println("Enter the details of the employee below:")

        println("Enter the First name:")
        val firstName=readln().toString().lowercase()

        println("Enter the Last name:")
        val lastName=readln().toString().lowercase()

        println("Enter the Role:")
        val empRole=readln().toString().lowercase()

        println("Enter the Reporting Manager ID:")
        val reportingTo=readln().toString().lowercase()

        val empId="${firstName[0]}${lastName[0]}$counter"

        val employee= EmployeeData(empId,firstName,lastName,empRole,reportingTo)
        employeeList.add(employee)
        println("Employee had been added successfully and the Employee Id is $empId")
        counter++

    }

    //Creating new Attendance Entry list
    val attendanceEntry=mutableListOf<AttendanceData>()

    //Checking for the employee exist in the Employee List
    fun isEmployeeExist(inputId:String):Boolean{

        val employeeComponet=employeeList.find { it.empId==inputId }
        if(employeeComponet==null){
            println("Employee is Not found ")
            return false
        }
        else{
            return true
        }
    }

    //Checking for the EmpId is present in the attendance list in the specific date
    fun isCheckedIn(inputId: String,checkDate:String):Boolean{
        val alreadyCheckedIn=attendanceEntry.any{
            it.empId==inputId && it.attendanceDate==checkDate}
        if(alreadyCheckedIn){
            println("Already checked inn!!")
            return true
        }
        else {
            return false
        }

    }

    fun attendanceValidation(){
        val currentDate= LocalDate.now().toString()
        val currentTime= LocalTime.now().toString()
        println("Enter the Employee ID to add the Attendance:")
        val inputId=readln().trim()
        if(isEmployeeExist(inputId)){
            if(!isCheckedIn(inputId,currentDate)){
                val newEntry=AttendanceData(inputId,currentDate, currentTime)
                attendanceEntry.add(newEntry)
                println("Attendance had marked Successfully!!")

            }
        }
    }

    fun viewAllEmployee(){
        if (employeeList.isEmpty()) {
            println("No employees found.")
            return
        }
5
        println("List of All Employees:")
        println("--------------------------------------------")
        employeeList.forEach { emp ->
            println("|ID: ${emp.empId.uppercase()} | Name: ${emp.firstName.capitalize()} ${emp.lastName.capitalize()} | Role: ${emp.empRole.capitalize()} | Reports to: ${emp.reportingTo.uppercase()}|")
        }
        println("--------------------------------------------")
    }
    fun viewAttendanceEntry(){
        if (attendanceEntry.isEmpty()) {
            println("No employees found.")
            return
        }

        println("List of All Employees:")
        println("--------------------------------------------")
        attendanceEntry.forEach { emp ->
            println("ID: ${emp.empId.uppercase()} | Date ${emp.attendanceDate} | Time ${emp.attendanceTime}")
        }
        println("--------------------------------------------")
    }
    }
