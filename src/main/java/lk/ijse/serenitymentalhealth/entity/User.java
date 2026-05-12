package lk.ijse.serenitymentalhealth.entity;

import lk.ijse.serenitymentalhealth.enums.PaymentStatus;
import lk.ijse.serenitymentalhealth.enums.UserType;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_contact")
    private String userContact;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
