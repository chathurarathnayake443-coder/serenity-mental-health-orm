package lk.ijse.serenitymentalhealth.bo.custom.impl;

import lk.ijse.serenitymentalhealth.bo.custom.UserBO;
import lk.ijse.serenitymentalhealth.dao.DAOFactory;
import lk.ijse.serenitymentalhealth.dao.custom.TherapistDAO;
import lk.ijse.serenitymentalhealth.dao.custom.UserDAO;
import lk.ijse.serenitymentalhealth.dto.TherapistDTO;
import lk.ijse.serenitymentalhealth.dto.UserDTO;
import lk.ijse.serenitymentalhealth.entity.Therapist;
import lk.ijse.serenitymentalhealth.entity.User;

import java.sql.SQLException;

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
}
