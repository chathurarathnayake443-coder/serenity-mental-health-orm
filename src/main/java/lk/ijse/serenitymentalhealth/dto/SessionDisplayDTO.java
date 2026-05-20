package lk.ijse.serenitymentalhealth.dto;

public class SessionDisplayDTO {
    private String programName;
    private String therapistName;
    private String patientName;

    public SessionDisplayDTO() {}

    public SessionDisplayDTO(String programName, String therapistName, String patientName) {
        this.programName = programName;
        this.therapistName = therapistName;
        this.patientName = patientName;
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
    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
