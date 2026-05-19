package lk.ijse.serenitymentalhealth.dto;

import java.time.LocalDate;

public class PatientSessionHistoryDTO {
    private int sessionId;
    private String therapistName;
    private LocalDate date;
    private String programName;

    public PatientSessionHistoryDTO() {}

    public PatientSessionHistoryDTO(int sessionId, String therapistName, LocalDate date, String programName) {
        this.sessionId = sessionId;
        this.therapistName = therapistName;
        this.date = date;
        this.programName = programName;
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
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getProgramName() {
        return programName;
    }
    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
