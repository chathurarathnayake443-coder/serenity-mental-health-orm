package lk.ijse.serenitymentalhealth.dto;

public class TherapistDTO {
    private int therapistId;
    private String therapistName;
    private String therapistEmail;
    private String therapistPhone;

    TherapistDTO() {}

    public TherapistDTO(String therapistName, String therapistEmail, String therapistPhone) {
        this.therapistName = therapistName;
        this.therapistEmail = therapistEmail;
        this.therapistPhone = therapistPhone;
    }

    public TherapistDTO(int therapistId, String therapistName, String therapistEmail, String therapistPhone) {
        this.therapistId = therapistId;
        this.therapistName = therapistName;
        this.therapistEmail = therapistEmail;
        this.therapistPhone = therapistPhone;
    }
    public int getTherapistId() {
        return therapistId;
    }
    public void setTherapistId(int therapistId) {
        this.therapistId = therapistId;
    }
    public String getTherapistName() {
        return therapistName;
    }
    public void setTherapistName(String therapistName) {
        this.therapistName = therapistName;
    }
    public String getTherapistEmail() {
        return therapistEmail;
    }
    public void setTherapistEmail(String therapistEmail) {
        this.therapistEmail = therapistEmail;
    }
    public String getTherapistPhone() {
        return therapistPhone;
    }
    public void setTherapistPhone(String therapistPhone) {
        this.therapistPhone = therapistPhone;
    }
}
