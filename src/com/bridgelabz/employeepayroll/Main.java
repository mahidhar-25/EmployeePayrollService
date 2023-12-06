package com.bridgelabz.employeepayroll;

import java.util.Scanner;

public class Main {
    private static final int CREATE_NEW_EMPLOYEE_PAYROLL = 1;
    private static final int PRINT_ALL_ENTRIES_FROM_DB = 2;
    private static final int COUNT_NO_OF_ENTRIES_IN_DB = 3;
    private static final int EXIT = 4;

    public static void main(String[] args) {
        System.out.println("!!! welcome to employee pay roll service !!!");

        System.out.println("1. To Create a new employee payroll");
        System.out.println("2. To show all entries in Database file");
        System.out.println("3. To get the count of entries in database");
        System.out.println("4. To exit");

        Scanner input = new Scanner(System.in);
        System.out.println("Choose One option from above : ");
        int choice = input.nextInt();
        while(choice != EXIT){
            switch(choice){
                case CREATE_NEW_EMPLOYEE_PAYROLL -> {
                    EmployeePayrollService newEmployee = EmployeePayrollService.readEmployeePayrollData();
                }
                case PRINT_ALL_ENTRIES_FROM_DB->{
                    EmployeePayrollService.printAndReturnCountOfEntriesInFile();
                }
                case COUNT_NO_OF_ENTRIES_IN_DB ->{
                    System.out.println("Total entries are : " + EmployeePayrollService.countEntriesInFile());
                }
                default ->   System.out.println("Choose correct option from above");

            }
            System.out.println("Choose One option from above : ");
            choice = input.nextInt();
        }
    }
}
