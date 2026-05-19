package lk.ijse.serenitymentalhealth.dto;

public class PatientDTO {
    private int patientId;
    private String patientName;
    private int patientAge;
    private String patientAddress;
    private String patientPhone;
    private String guardianName;
    private String guardianPhone;

    public PatientDTO(){
    }

    public PatientDTO(int patientId){
        this.patientId = patientId;
    }

    public PatientDTO(String patientName){
        this.patientName = patientName;
    }

    public PatientDTO(String patientName, int patientAge,String patientAddress, String patientPhone, String guardianName, String guardianPhone){
        this.patientName = patientName;
        this.patientAddress = patientAddress;
        this.patientPhone = patientPhone;
        this.guardianName = guardianName;
        this.guardianPhone = guardianPhone;
        this.patientAge = patientAge;
    }

    public PatientDTO(int patientId,String patientName, int patientAge,String patientAddress, String patientPhone, String guardianName, String guardianPhone){
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientAddress = patientAddress;
        this.patientPhone = patientPhone;
        this.guardianName = guardianName;
        this.guardianPhone = guardianPhone;
        this.patientAge = patientAge;
    }

    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public String getPatientName() {
        return patientName;
    }
    public int getPatientAge() {
        return patientAge;
    }
    public String getPatientAddress() {
        return patientAddress;
    }
    public String getPatientPhone() {
        return patientPhone;
    }
    public String getGuardianName() {
        return guardianName;
    }
    public String getGuardianPhone() {
        return guardianPhone;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }
    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }
    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }
    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }
    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }
}
