package lk.ijse.serenitymentalhealth.dto;

import lk.ijse.serenitymentalhealth.entity.PatientSession;
import lk.ijse.serenitymentalhealth.enums.SessionStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TherapySessionDTO {
    private String programName;
    private String therapistName;
    private LocalDate therapyDate;
    private LocalTime therapyTime;
    private int duration;
    private SessionStatus status;
    private List<PatientSession> patientSessions;

    public TherapySessionDTO(){}

    public TherapySessionDTO(String programName, String therapistName, LocalDate therapyDate, LocalTime therapyTime, int duration, SessionStatus status, List<PatientSession> patientSessions) {
        this.programName = programName;
        this.therapistName = therapistName;
        this.therapyDate = therapyDate;
        this.therapyTime = therapyTime;
        this.duration = duration;
        this.status = status;
        this.patientSessions = patientSessions;
    }
    public String getProgramName() {
        return programName;
    }
    public void setProgramName(String programName) {
        this.programName = programName;
    }
    public String getTherapistName() {
        return therapistName;
    }
    public void setTherapistName(String therapistName) {
        this.therapistName = therapistName;
    }
    public LocalDate getTherapyDate() {
        return therapyDate;
    }
    public void setTherapyDate(LocalDate therapyDate) {
        this.therapyDate = therapyDate;
    }
    public LocalTime getTherapyTime() {
        return therapyTime;
    }
    public void setTherapyTime(LocalTime therapyTime) {
        this.therapyTime = therapyTime;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public SessionStatus getStatus() {
        return status;
    }
    public void setStatus(SessionStatus status) {
        this.status = status;
    }
    public List<PatientSession> getPatientSessions() {
        return patientSessions;
    }
    public void setPatientSessions(List<PatientSession> patientSessions) {
        this.patientSessions = patientSessions;
    }

}
