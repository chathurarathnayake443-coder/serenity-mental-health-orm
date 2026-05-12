package lk.ijse.serenitymentalhealth.dto;

public class TherapyProgramDTO {
    private String therapyProgramId;
    private String therapyProgramName;
    private String therapyProgramDescription;
    private String therapyProgramDuration;
    private double therapyProgramCost;

    public TherapyProgramDTO() {}

    public TherapyProgramDTO(String therapyProgramId,String therapyProgramName, String therapyProgramDescription, String therapyProgramDuration, double therapyProgramCost) {
        this.therapyProgramId = therapyProgramId;
        this.therapyProgramName = therapyProgramName;
        this.therapyProgramDescription = therapyProgramDescription;
        this.therapyProgramDuration = therapyProgramDuration;
        this.therapyProgramCost = therapyProgramCost;
    }

    public TherapyProgramDTO(String therapyProgramName, String therapyProgramDescription, String therapyProgramDuration, double therapyProgramCost) {
        this.therapyProgramName = therapyProgramName;
        this.therapyProgramDescription = therapyProgramDescription;
        this.therapyProgramDuration = therapyProgramDuration;
        this.therapyProgramCost = therapyProgramCost;
    }

    public String getTherapyProgramId() {
        return therapyProgramId;
    }
    public void setTherapyProgramId(String therapyProgramId) {
        this.therapyProgramId = therapyProgramId;
    }
    public String getTherapyProgramName() {
        return therapyProgramName;
    }
    public void setTherapyProgramName(String therapyProgramName) {
        this.therapyProgramName = therapyProgramName;
    }
    public String getTherapyProgramDescription() {
        return therapyProgramDescription;
    }
    public void setTherapyProgramDescription(String therapyProgramDescription) {
        this.therapyProgramDescription = therapyProgramDescription;
    }
    public String getTherapyProgramDuration() {
        return therapyProgramDuration;
    }
    public void setTherapyProgramDuration(String therapyProgramDuration) {
        this.therapyProgramDuration = therapyProgramDuration;
    }
    public double getTherapyProgramCost() {
        return therapyProgramCost;
    }
    public void setTherapyProgramCost(double therapyProgramCost) {
        this.therapyProgramCost = therapyProgramCost;
    }
}
