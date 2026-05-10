package lk.ijse.serenitymentalhealth.entity;

import lk.ijse.serenitymentalhealth.enums.PaymentStatus;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "patient_session")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PatientSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_session_id")
    private int patientSessionId;

    @Column(name = "session_fee")
    private double sessionFee;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "therapy_session_id")
    private TherapySession therapySession;
}
