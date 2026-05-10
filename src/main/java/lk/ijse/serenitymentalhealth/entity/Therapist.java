package lk.ijse.serenitymentalhealth.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "therapist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Therapist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int therapistId;

    @Column(name = "therapist_name")
    private String therapistName;

    @Column(name = "therapist_email")
    private String therapistEmail;

    @Column(name = "therapist_phone")
    private String therapistPhone;

    @ManyToMany
    @JoinTable(name = "therapist_therapy_programs", joinColumns = @JoinColumn(name = "therapist_id"),
            inverseJoinColumns = @JoinColumn(name = "therapy_program_id"))
    private List<TherapyProgram> therapyPrograms;
}
