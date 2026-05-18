package lk.ijse.serenitymentalhealth.dto;

import lk.ijse.serenitymentalhealth.enums.PaymentStatus;

import java.time.LocalDate;

public class RegistrationDTO {
    private int registrationId;
    private int PatientId;
    private String ProgramId;
    private double fee;
    private LocalDate date;
    private PaymentStatus paymentStatus;

    public RegistrationDTO() {}

    public RegistrationDTO(int patientId, String programId, double fee, LocalDate date) {
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

    public RegistrationDTO(int registrationId, int patientId, String programId, double fee, LocalDate date) {
        this.registrationId = registrationId;
        this.PatientId = patientId;
        this.ProgramId = programId;
        this.fee = fee;
        this.date = date;
    }

    public RegistrationDTO(int registrationId, int patientId, String programId, double fee, LocalDate date, PaymentStatus paymentStatus) {
        this.registrationId = registrationId;
        this.PatientId = patientId;
        this.ProgramId = programId;
        this.fee = fee;
        this.date = date;
        this.paymentStatus = paymentStatus;
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
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
