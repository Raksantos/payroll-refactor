package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Predicate;

import models.services.payment.PaymentData;
import models.services.payment.PayCheck;
import models.services.ServiceTax;

import static java.util.stream.Collectors.toCollection;

import java.io.Serializable;

public abstract class Employee implements Serializable{
    private UUID id;
    private String name;
    private String address;
    private Double salary;
    private PaymentData paymentData;
    private ArrayList<ServiceTax> serviceTax;
    private Syndicate employeeSyndicate;
    
    public Employee(){

    }

    public Employee(String name, String address, Double salary, PaymentData paymentData){
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.paymentData = paymentData;
        this.employeeSyndicate = null;
        this.serviceTax = new ArrayList<ServiceTax>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Syndicate getEmployeeSyndicate() {
        return employeeSyndicate;
    }

    public void setEmployeeSyndicate(Syndicate employeeSyndicate) {
        this.employeeSyndicate = employeeSyndicate;
    }

    public PaymentData getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(PaymentData paymentData) {
        this.paymentData = paymentData;
    }

    @Override
    public String toString(){
        String data = "\n{\n\tUser id: " + getId();
        data += "\n\tName: " + getName();
        data += "\n\tAddress: " + getAddress();
        data += "\n\tSalary: " + getSalary();
        data += "\n\tService Tax: " + getServiceTax();
        data += "\n\tPayment Data: {" + getPaymentData();

        if(this.employeeSyndicate.getIsAffiliated()){
            data += "\n\tSyndicate: { ";
            data += this.employeeSyndicate.toString();
            data += "\n\t}";
        }

        data += "\n}\n";
        return data;
    }

    public ArrayList<ServiceTax> getServiceTax() {
        return serviceTax;
    }

    public void setServiceTax(ArrayList<ServiceTax> serviceTax) {
        this.serviceTax = serviceTax;
    }

    public abstract PayCheck makePayment(LocalDate date);

    public Double calculateServiceTaxes(){
        double taxes = 0.0;

        ArrayList<ServiceTax> serviceTaxes;
        ArrayList<PayCheck> payChecks = this.getPaymentData().getPayChecks();

        if(!payChecks.isEmpty()){
            LocalDate lastDate = payChecks.get(payChecks.size() - 1).getDate();
            Predicate<ServiceTax> dateFilter = tax -> tax.getDate().isAfter(lastDate);

            serviceTaxes = this.getServiceTax().stream().filter(dateFilter).collect(toCollection(ArrayList::new));
        } else {
            serviceTaxes = this.getServiceTax();
        }

        for(ServiceTax tax : serviceTaxes){
            taxes += tax.getValue();
        }
                
        taxes += this.getEmployeeSyndicate().getTax();

        return taxes;
    }
}
