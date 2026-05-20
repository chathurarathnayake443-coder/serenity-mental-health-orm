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
import org.mindrot.jbcrypt.BCrypt;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    public boolean saveUser(UserDTO userDTO) throws SQLException {

        User user = new User();
        user.setUserName(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setUserPassword(BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt()));
        user.setUserContact(userDTO.getContact());
        user.setUserAddress(userDTO.getAddress());
        user.setUserType(userDTO.getUserType());

        return userDAO.save(user);
    }

    public boolean updateUser(UserDTO userDTO) throws SQLException {
        User user = new User();
        user.setUserName(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setUserPassword(BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt()));
        user.setUserContact(userDTO.getContact());
        user.setUserAddress(userDTO.getAddress());
        user.setUserType(userDTO.getUserType());

        return userDAO.update(user);
    }

    public boolean deleteUser(String username) throws SQLException {
        return userDAO.delete(username);
    }

    public List<UserDTO> loadUserTable() throws SQLException {
        List<User> userList = userDAO.getAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user :userList) {
            userDTOList.add(new UserDTO(user.getUserName(),user.getName(),user.getUserPassword(),user.getUserContact(),user.getUserAddress(),user.getUserType()));
        }
        return userDTOList;
    }

    public UserDTO findUser(String name) throws SQLException {
        User user = userDAO.find(name);
        if (user!=null){
            return new UserDTO(user.getUserName(), user.getName(), user.getUserPassword(), user.getUserContact(),user.getUserAddress(),   user.getUserType());
        }
        return null;
    }
}
