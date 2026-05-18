package lk.ijse.serenitymentalhealth.entity;

import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="therapy_program")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TherapyProgram {
    @Id
    @Column(name = "therapy_program_id")
    private String therapyProgramId;

    @Column(name = "therapy_program_name")
    private String therapyProgramName;

    @Column(name = "therapy_program_duration")
    private int duration;

    @Column(name = "therapy_program_cost")
    private double cost;

    @Column(name = "therapy_program_description")
    private String description;

    @ManyToMany(mappedBy = "therapyPrograms")
    private List<Therapist> therapists;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "therapyProgram",cascade = CascadeType.ALL)
    private List<Registration> registrations;

    @OneToMany(mappedBy = "therapyProgram",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TherapySession> therapySessions;
}
