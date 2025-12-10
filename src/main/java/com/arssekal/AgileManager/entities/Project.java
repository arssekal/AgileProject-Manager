package com.arssekal.AgileManager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    @OneToOne
    @JoinColumn(name = "productOwner_id")
    private ProductOwner productOwner;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
    private ProductBacklog productBacklog;
}
