package lk.ijse.serenitymentalhealth.entity;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import java.util.List;

@Entity
@Table(name = "therapist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Therapist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "therapist_id")
    private int therapistId;

    @Column(name = "therapist_name")
    private String therapistName;

    @Column(name = "therapist_email")
    private String therapistEmail;

    @Column(name = "therapist_phone")
    private String therapistPhone;

    @Column(name = "therapist_address")
    private String therapistAddress;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "therapist_therapy_programs", joinColumns = @JoinColumn(name = "therapist_id"),
            inverseJoinColumns = @JoinColumn(name = "therapy_program_id"))
    private List<TherapyProgram> therapyPrograms;

    @OneToMany(mappedBy = "therapist")
    private List<TherapySession> therapySessions;
}
