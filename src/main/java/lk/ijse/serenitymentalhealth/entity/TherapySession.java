package lk.ijse.serenitymentalhealth.entity;

import lk.ijse.serenitymentalhealth.enums.SessionStatus;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "therapy_session")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TherapySession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "therapy_session_id")
    private int therapySessionId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;

    @Column(name = "time_duration")
    private int timeDuration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "therapy_program_id")
    private TherapyProgram therapyProgram;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "therapySession")
    List<PatientSession> patientSessions;
}
