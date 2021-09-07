package controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import models.Company;
import models.Employee;
import models.services.payment.PayCheck;
import models.services.payment.PaymentList;
import models.services.payment.PaymentSchedule;
import utils.EmployeeUtils;
import utils.GeneralUtils;

public class PaymentController {
    public static void LaunchPayroll(Scanner input, Company company){

        if(EmployeeUtils.warningEmptyEmployeesList(company.getEmployees())) return;

        int count, week = -1;

        String stringDate;
        PayCheck payCheck;
        PaymentList paymentList = null;
        ArrayList<PayCheck> payCheckList = new ArrayList<PayCheck>();

        System.out.print("Inform the first date of the month(YYYY-MM-DD): ");
        stringDate = input.nextLine();

        ArrayList<Integer> firstDate = GeneralUtils.convertDateToArray(stringDate);

        System.out.print("Inform the last date of the month(YYYY-MM-DD): ");
        stringDate = input.nextLine();

        ArrayList<Integer> lastDate = GeneralUtils.convertDateToArray(stringDate);

        LocalDate first = LocalDate.of(firstDate.get(0), firstDate.get(1), firstDate.get(2));

        LocalDate last = LocalDate.of(lastDate.get(0), lastDate.get(1), lastDate.get(2));

        long size = ChronoUnit.DAYS.between(first, last.plusDays(1));

        for(count = 0; count < size; count++){
            LocalDate current = first.plusDays(count);

            if(current.getDayOfWeek() == first.getDayOfWeek()){
                week++; 
            }

            for(Employee employee : company.getEmployees()){
                if(verifyPayDate(employee, week, current)){
                    if(employee.getPaymentData().getPaymentSchedule().getStrategy().getMethodDiv() == 2){//Verifying if an employee is biWeek
                        if(week%2 != 0){//If is a pay week
                            payCheck = employee.makePayment(current);
                            payCheckList.add(payCheck);
                        }
                    }else{
                        payCheck = employee.makePayment(current);
                        payCheckList.add(payCheck);
                    }
                }
            }   
        }


        paymentList = new PaymentList(payCheckList, last);
        ArrayList<PaymentList> paymentLists = company.getPaymentLists();

        paymentLists.add(paymentList);

        company.setPaymentLists(paymentLists);

        System.out.println(payCheckList);
    }

    public static int getMethodDiv(Employee employee){
        return employee.getPaymentData().getPaymentSchedule().getStrategy().getMethodDiv();
    }

    public static boolean verifyPayDate(Employee employee, int week, LocalDate current){
        boolean dateInSchedule = false;
        PaymentSchedule employeeSchedule = employee.getPaymentData().getPaymentSchedule();

        dateInSchedule = employee.getPaymentData().getPaymentSchedule().getStrategy().getDateInSchedule(employeeSchedule, week, current);
        
        return dateInSchedule;
    }

}
