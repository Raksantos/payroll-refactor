package models;
import java.util.ArrayList;

import models.services.payment.PaymentList;
import models.services.payment.PaymentSchedule;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import java.io.Serializable;

public class Company implements Serializable{
    private ArrayList<Employee> employees;

    private ArrayList<PaymentList> paymentLists;

    private ArrayList<PaymentSchedule> paymentSchedules;     

    public Company(){
        this.employees = new ArrayList<Employee>();
        this.paymentLists = new ArrayList<PaymentList>();
        this.paymentSchedules = new ArrayList<PaymentSchedule>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<PaymentList> getPaymentLists() {
        return paymentLists;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void setPaymentLists(ArrayList<PaymentList> paymentLists) {
        this.paymentLists = paymentLists;
    }

    public ArrayList<PaymentSchedule> getPaymentSchedules() {
        return paymentSchedules;
    }

    public void setPaymentSchedules(ArrayList<PaymentSchedule> paymentSchedules) {
        this.paymentSchedules = paymentSchedules;
    }

    public String backup(Company company){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(company);
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        }catch(Exception err){
            err.printStackTrace();
            System.out.println("\n\nCouldn't save the state.\n\n");
            return "";
        }
    }

    public Company restore(String state){
        try{
            byte[] data = Base64.getDecoder().decode(state);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            return (Company) ois.readObject();
        }catch(Exception err){
            err.printStackTrace();
            System.out.println("\n\nCouldn't restore the state.\n\n");
            return null;
        }
    }
}
