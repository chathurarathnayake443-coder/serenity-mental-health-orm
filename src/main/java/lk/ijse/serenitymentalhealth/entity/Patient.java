package lk.ijse.serenitymentalhealth.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "patient_age")
    private int age;

    @Column(name = "patient_address")
    private String address;

    @Column(name = "patient_phone")
    private String phone;

    @Column(name = "guardian_name")
    private String guardianName;

    @Column(name = "guardian_phone")
    private String guardianPhone;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Registration> registrations;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "patient")
    List<PatientSession> patientSessions;

}
