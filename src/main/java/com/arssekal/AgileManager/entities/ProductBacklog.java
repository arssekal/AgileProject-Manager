package com.arssekal.AgileManager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product_backlog")
public class ProductBacklog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 50)
    private String nom;
    @NotBlank
    @Size(min = 10, max = 100)
    private String description;
    @OneToMany(mappedBy = "productBacklog")
    private List<Epic> epics;
    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;
}