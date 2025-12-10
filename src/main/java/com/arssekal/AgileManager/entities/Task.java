package com.arssekal.AgileManager.entities;

import com.arssekal.AgileManager.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
//    @ColumnDefault("TO_DO")
    private Status statut;
    @ManyToOne
    @JoinColumn(name = "userStory_id")
    private UserStory userStory;
    @OneToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;
}
