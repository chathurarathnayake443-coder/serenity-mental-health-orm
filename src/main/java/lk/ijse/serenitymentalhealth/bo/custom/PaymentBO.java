package lk.ijse.serenitymentalhealth.bo.custom;

import lk.ijse.serenitymentalhealth.bo.SuperBO;
import lk.ijse.serenitymentalhealth.dto.PaymentDTO;
import lk.ijse.serenitymentalhealth.entity.PatientSession;
import lk.ijse.serenitymentalhealth.entity.TherapySession;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {

    public List<Integer> loadTherapySessionIds() throws SQLException;

    public List<PaymentDTO> loadPaymentDTOs(int id) throws SQLException;
}
