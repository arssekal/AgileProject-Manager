package com.ensa.AgileManager.entities;

import com.ensa.AgileManager.enums.OtherStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "project",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_project_name",
                columnNames = "nom"
        )
)
public class Project {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "project_sequence",
            sequenceName = "project_sequence",
            allocationSize = 10

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "project_sequence"
    )
    private Long id;
    @Column(name = "nom", nullable = false)
    @NotBlank
    @Size(min = 3, max = 50, message = "la longueur de nom du projet dois etre entre 3 et 50")
    private String nom;
    @NotBlank
    @Size(min = 10, max = 100, message = "la longueur de description du projet dois etre entre 10 et 100")
    private String description;
    private OtherStatus status;
    @CreatedDate
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "productOwner_id")
    @JsonBackReference
    private ProductOwner productOwner;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL,orphanRemoval = true)
    private ProductBacklog productBacklog;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sprint> sprints;
}
