package com.arssekal.AgileManager.entities;

import com.arssekal.AgileManager.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @NotBlank
    @Size(min = 3, max = 30, message = "le nom ne dois étre entre 3 et 10")
    private String nom;
    @Email(message = "l'email n'est pas valid")
    private String email;
    private String password;

    @OneToMany(mappedBy = "scrumMaster")
    private List<Sprint> sprints;

    @OneToOne(mappedBy = "productOwner")
    private Project project;

    @OneToOne(mappedBy = "developer")
    private Task task;
}