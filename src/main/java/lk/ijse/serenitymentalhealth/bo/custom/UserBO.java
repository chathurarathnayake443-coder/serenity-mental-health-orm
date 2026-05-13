package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;
import lk.ijse.serenitymentalhealth.dto.UserDTO;
import lk.ijse.serenitymentalhealth.entity.User;

import java.sql.SQLException;

public interface UserBO extends SuperBO {

    public boolean saveUser(UserDTO userDTO) throws SQLException;
}
