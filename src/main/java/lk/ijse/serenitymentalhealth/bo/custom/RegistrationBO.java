package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;

import java.sql.SQLException;

public interface RegistrationBO extends SuperBO {

    public String showNextId() throws SQLException;
}
