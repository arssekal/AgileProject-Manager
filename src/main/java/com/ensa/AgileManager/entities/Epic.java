package com.ensa.AgileManager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "epic")
public class Epic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            nullable = false
    )
    @NotEmpty
    @Size(min = 3, max = 50)
    private String titre;
    private String description;
    @ManyToOne
    @JoinColumn(name = "product_backlog_id")
    private ProductBacklog productBacklog;

    @OneToMany(mappedBy = "epic", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<UserStory> userStories;
}
