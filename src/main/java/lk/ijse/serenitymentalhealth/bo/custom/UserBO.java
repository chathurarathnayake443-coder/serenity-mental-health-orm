package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;
import lk.ijse.serenitymentalhealth.dto.UserDTO;
import lk.ijse.serenitymentalhealth.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserBO extends SuperBO {

    public boolean saveUser(UserDTO userDTO) throws SQLException;

    public boolean updateUser(UserDTO userDTO) throws SQLException;

    public boolean deleteUser(String username) throws SQLException;

    public List<UserDTO> loadUserTable() throws SQLException;
}
