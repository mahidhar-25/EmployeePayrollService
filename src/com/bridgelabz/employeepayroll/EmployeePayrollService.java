package com.bridgelabz.employeepayroll;

import java.io.*;
import java.util.Scanner;

public class EmployeePayrollService {
    private static String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static String EMPLOYEE_PAYROLL_DB_FOLDER_NAME = "database";
    private static String EMPLOYEE_PAYROLL_DB_NAME = "employeePayrollData.txt";
    private String employeeId;
    private String employeeName;
    private long employeeSalary;

/*
@desc : EmployeePayrollService it is a parametrized constructor
@params : String - employeeId
          String - employeeName
          String - employeeSalary
@return : no return;
 */
    public EmployeePayrollService(String employeeId, String employeeName, long employeeSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        try {
            writeEmployeePayrollToDataBase(this);
            System.out.println("Employee data appended to the file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    @desc : getEmployeeId is a getter to get employeeId
    @params : no params
    @return : String - employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /*
    @desc : setEmployeeId is a setter to set employeeId
    @param : String - employeeId
    @return : no return
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    /*
    @desc : getEmployeeName is a getter to get employeeName
    @params : no params
    @return : String - employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }
    /*
    @desc : setEmployeeName is a setter to set employeeName
    @param : String - employeeName
    @return : no return
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    /*
    @desc : getEmployeeName is a getter to get employeeSalary
    @params : no params
    @return : Long - employeeSalary
     */
    public long getEmployeeSalary() {
        return employeeSalary;
    }
    /*
    @desc : setEmployeeSalary is a setter to set employeeSalary
    @param : Long - employeeSalary
    @return : no return
     */
    public void setEmployeeSalary(long employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public static void writeEmployeePayrollToDataBase(EmployeePayrollService employee) throws IOException {
        // Check if the folder exists, create if not
        File folder = new File(CURRENT_DIRECTORY, EMPLOYEE_PAYROLL_DB_FOLDER_NAME);
        //if folder exist ignored if not directory will create;
        if (!folder.exists()) {
            folder.mkdir();
        }

        // Check if the file exists, create if not
        File file = new File(folder, EMPLOYEE_PAYROLL_DB_NAME);
        //if file exist ignored if not directory will create;
        if (!file.exists()) {
            file.createNewFile();
        }

        // Append employee data to the file
        try (PrintWriter saveEmployeePayrollToDataBase = new PrintWriter(new FileWriter(file, true))) {
            saveEmployeePayrollToDataBase.println(employee.saveEmployeePayrollToDataBase());
        }
    }

    /*
    @desc : it takes all the required inputs for employee payroll
    @params : no params
    @return : EmployeePayrollService
     */
    public static EmployeePayrollService  readEmployeePayrollData()  {
        Scanner input = new Scanner(System.in);
        String employeeId , employeeName;
        long employeeSalary;
        System.out.println("Enter employee id : ");
        employeeId = input.next();
        System.out.println("Enter employee name in a single line : ");
        input.nextLine();
        employeeName = input.nextLine();
        System.out.println("Enter employee salary : ");
        employeeSalary = input.nextLong();
        EmployeePayrollService newEmployeePayrollService = new EmployeePayrollService(employeeId , employeeName , employeeSalary);



        return newEmployeePayrollService;
    }

    /*
    @desc : countEntriesInFile as we store employee payroll entry in a new line everytime we will keep a count on no lines
    @parms : no params
    @return : int - no of lines
     */

    public static int countEntriesInFile() {
        int entryCount = 0;
        // Updated location of the database file
        File folder = new File(CURRENT_DIRECTORY, EMPLOYEE_PAYROLL_DB_FOLDER_NAME);
        File file = new File(folder, EMPLOYEE_PAYROLL_DB_NAME);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                entryCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entryCount;
    }
    /*
@desc : saveEmployeePayrollToDataBase is a method to print all the details of the employee payroll service
@params : no params
@return : string
 */

    public String saveEmployeePayrollToDataBase() {
        return "{" + "employeeId=" + employeeId + "," + "employeeName=" + employeeName + "," + "employeeSalary=" + employeeSalary + "," +'}';
    }

/*
@desc : toString is a method to print all the details of the employee payroll service
@params : no params
@return : string
 */
    @Override
    public String toString() {
        return "EmployeePayrollService = {" +
                "employeeId=" + employeeId + ",\n" +
                "employeeName=" + employeeName + ",\n" +
                "employeeSalary=" + employeeSalary + ",\n" +
                '}';
    }
}
