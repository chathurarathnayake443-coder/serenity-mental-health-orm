package lk.ijse.serenitymentalhealth.entity;

import lk.ijse.serenitymentalhealth.enums.PaymentStatus;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "registration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private int registrationId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "registered_date")
    private LocalDate registeredDate;

    @Column(name = "registration_fee")
    private double registrationFee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "therapy_program_id")
    private TherapyProgram therapyProgram;
}
