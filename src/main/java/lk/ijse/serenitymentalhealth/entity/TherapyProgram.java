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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "therapy_program_id")
    private int therapyProgramId;

    @Column(name = "therapy_program_name")
    private String therapyProgramName;

    @Column(name = "therapy_program_duration")
    private int duration;

    @Column(name = "therapy_program_cost")
    private double cost;

    @Column(name = "therapy_program_description")
    private String description;

    @ManyToMany(mappedBy = "therapist_therapy_programs")
    private List<Therapist> therapists;
}
