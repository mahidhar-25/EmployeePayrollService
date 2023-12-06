package com.bridgelabz.employeepayroll;

import java.util.Scanner;

public class EmployeePayrollService {
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

    /*
    @desc : it takes all the required inputs for employee payroll
    @params : no params
    @return : EmployeePayrollService
     */
    public static EmployeePayrollService  readEmployeePayrollData(){
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
        return new EmployeePayrollService(employeeId , employeeName , employeeSalary);
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
