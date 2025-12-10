package com.arssekal.AgileManager.entities;

import com.arssekal.AgileManager.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@DiscriminatorValue("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String password;

    @OneToOne(mappedBy = "scrumMaster")
    private Sprint sprint;
    @OneToOne(mappedBy = "productOwner")
    private Project project;
    @OneToOne(mappedBy = "developer")
    private Task task;
}