package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.UserBO;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.TherapistDAO;
import lk.ijse.serenitymentalhealth.dao.custom.UserDAO;
import lk.ijse.serenitymentalhealth.dto.PatientDTO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.dto.UserDTO;
import lk.ijse.serenitymentalhealth.entity.Patient;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import lk.ijse.serenitymentalhealth.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    public boolean saveUser(UserDTO userDTO) throws SQLException {

        User user = new User();
        user.setUserName(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setUserPassword(userDTO.getPassword());
        user.setUserContact(userDTO.getContact());
        user.setUserAddress(userDTO.getAddress());
        user.setUserType(userDTO.getUserType());

        return userDAO.save(user);
    }

    public List<UserDTO> loadUserTable() throws SQLException {
        List<User> userList = userDAO.getAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user :userList) {
            userDTOList.add(new UserDTO(user.getUserName(),user.getUserPassword(),user.getName(),user.getUserContact(),user.getUserAddress(),user.getUserType()));
        }
        return userDTOList;
    }
}
