package com.example.sev_user.final_weekone.model;

/**
 * Created by toan on 16-Sep-16.
 */
public class Customer {
    private int idCustomer;
    private String nameCustomer;
    private int ageCustomer;
    private String emailCustomer;
    private String addressCustomer;
    private String phoneCustomer;
    private int creditCustomer;

    public Customer(int idCustomer, String nameCustomer, int ageCustomer, String emailCustomer, String addressCustomer,
                    String phoneCustomer, int creditCustomer) {
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.ageCustomer = ageCustomer;
        this.emailCustomer = emailCustomer;
        this.addressCustomer = addressCustomer;
        this.phoneCustomer = phoneCustomer;
        this.creditCustomer = creditCustomer;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public int getAgeCustomer() {
        return ageCustomer;
    }

    public void setAgeCustomer(int ageCustomer) {
        this.ageCustomer = ageCustomer;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public int getCreditCustomer() {
        return creditCustomer;
    }

    public void setCreditCustomer(int creditCustomer) {
        this.creditCustomer = creditCustomer;
    }
}
