package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;
import lk.ijse.serenitymentalhealth.dto.TherapyProgramDTO;
import lk.ijse.serenitymentalhealth.entity.TherapyProgram;

import java.sql.SQLException;

public interface TherapyProgramBO extends SuperBO {

    public boolean saveTherapyProgram(TherapyProgramDTO therapyProgramDTO) throws SQLException;
}
