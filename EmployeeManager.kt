

class EmployeeManager {
    val employeeList = mutableListOf<EmployeeData>()
    var counter = 0
    init{
    val predefinedManagers = mutableListOf(
        EmployeeData("m1", "Alice", "Johnson", "Sales Manager", "ceo"),
        EmployeeData("m2", "Bob", "Smith", "Tech Lead", "ceo"),
        EmployeeData("m3", "Catherine", "Brown", "HR Manager", "ceo"),
        EmployeeData("m4", "David", "Lee", "Finance Manager", "ceo"),
        EmployeeData("m5", "Eva", "Garcia", "Marketing Head", "ceo"),
        EmployeeData("m6", "Frank", "Taylor", "Product Manager", "ceo"),
        EmployeeData("m7", "Grace", "Walker", "Operations Manager", "ceo"),
        EmployeeData("m8", "Henry", "Davis", "Design Lead", "ceo"),
        EmployeeData("m9", "Isabella", "Martinez", "Support Manager", "ceo"),
        EmployeeData("m10", "Jack", "Wilson", "QA Lead", "ceo")
    )
    employeeList.addAll(predefinedManagers)

    }

    //To check whether the given manager id os valid or not
    fun validateAndSetManagerId(): String {
        val managerIdList = listOf("m1", "m2", "m3", "m4", "m5", "m6", "m7", "m8", "m9", "m10")

        while (true) {
            try {
                println("Please enter a valid Manager ID from the list below:")
                println(managerIdList)
                print("Enter the Reporting Manager ID: ")
                val reportingTo = readln().trim().lowercase()

                if (!managerIdList.contains(reportingTo)) {
                    throw IllegalArgumentException("Invalid Manager ID.")
                }

                // Valid manager ID
                return reportingTo

            } catch (e: Exception) {

                println("Please enter a valid Manager ID from the list below:")
                println(managerIdList)
            }
        }
    }



    //To add new employee
    fun addEmployee() {
        println("Enter the details of the employee below:")

        print("Enter the First name: ")
        val firstName = readln().trim().lowercase()

        print("Enter the Last name: ")
        val lastName = readln().trim().lowercase()

        print("Enter the Role: ")
        val empRole = readln().trim().lowercase()


        val reportingTo = validateAndSetManagerId()

        val empId = "${firstName[0]}${lastName[0]}$counter"

        val employee = EmployeeData(empId, firstName, lastName, empRole, reportingTo)
        employeeList.add(employee)
    println("----------------------------------------------------------------------")

    println("Employee has been added successfully and the Employee ID is "+"\"${empId}\"".uppercase())
        counter++
    }



    //To view the Employee list
    fun viewAllEmployee() {
        if (employeeList.isEmpty()) {
            println("No employees found.")
            return
        }
        println("List of All Employees:")
        println("--------------------------------------------")
        employeeList.forEach { emp ->
            println("| ID: ${emp.empId.uppercase()} | Name: ${emp.firstName.uppercase()} ${emp.lastName.uppercase()} | Role: ${emp.empRole.uppercase()} | Reports to: ${emp.reportingTo.uppercase()} |")
        }
        println("--------------------------------------------")
    }


}
