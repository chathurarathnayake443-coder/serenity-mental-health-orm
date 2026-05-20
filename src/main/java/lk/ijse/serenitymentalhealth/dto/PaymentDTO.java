package lk.ijse.serenitymentalhealth.dto;

import lk.ijse.serenitymentalhealth.enums.PaymentStatus;

public class PaymentDTO {
    private int sessionId;
    private String therapistName;
    private String patientName;
    private int therapistId;
    private PaymentStatus paymentStatus;
    private int patientId;
    private double sessionFee;

    public PaymentDTO() {}

    public PaymentDTO(int sessionId, int patientId, int therapistId,String therapistName, String patientName, double sessionFee, PaymentStatus paymentStatus) {
        this.sessionId = sessionId;
        this.patientId = patientId;
        this.therapistId = therapistId;
        this.therapistName = therapistName;
        this.patientName = patientName;
        this.sessionFee = sessionFee;
        this.paymentStatus = paymentStatus;
    }
    public int getSessionId() {
        return sessionId;
    }
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
    public String getTherapistName() {
        return therapistName;
    }
    public void setTherapistName(String therapistName) {
        this.therapistName = therapistName;
    }
    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    public double getSessionFee() {
        return sessionFee;
    }
    public void setSessionFee(double sessionFee) {
        this.sessionFee = sessionFee;
    }
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public int getTherapistId() {
        return therapistId;
    }
    public void setTherapistId(int therapistId) {
        this.therapistId = therapistId;
    }
}
