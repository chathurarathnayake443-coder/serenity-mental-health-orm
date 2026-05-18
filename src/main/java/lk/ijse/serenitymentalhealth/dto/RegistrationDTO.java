package lk.ijse.serenitymentalhealth.dto;

import lk.ijse.serenitymentalhealth.enums.PaymentStatus;

public class RegistrationDTO {
    private int registrationId;
    private int PatientId;
    private String ProgramId;
    private double fee;
    private String date;
    private PaymentStatus paymentStatus;

    public RegistrationDTO() {}

    public RegistrationDTO(int patientId, String programId, double fee, String date) {
        this.PatientId = patientId;
        this.ProgramId = programId;
        this.fee = fee;
        this.date = date;
    }

    public RegistrationDTO(int patientId, double fee, PaymentStatus paymentStatus) {
        this.PatientId = patientId;
        this.fee = fee;
        this.paymentStatus = paymentStatus;
    }

    public RegistrationDTO(int registrationId, int patientId, String programId, double fee, String date) {
        this.registrationId = registrationId;
        this.PatientId = patientId;
        this.ProgramId = programId;
        this.fee = fee;
        this.date = date;
    }
    public int getRegistrationId() {
        return registrationId;
    }
    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }
    public int getPatientId() {
        return PatientId;
    }
    public void setPatientId(int patientId) {
        this.PatientId = patientId;
    }
    public String getProgramId() {
        return ProgramId;
    }
    public void setProgramId(String programId) {
        this.ProgramId = programId;
    }
    public double getFee() {
        return fee;
    }
    public void setFee(double fee) {
        this.fee = fee;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
