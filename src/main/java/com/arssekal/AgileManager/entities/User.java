package com.arssekal.AgileManager.entities;

import com.arssekal.AgileManager.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@DiscriminatorValue("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}) }
)
@SuperBuilder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 30, message = "le nom ne dois étre entre 3 et 10")
    private String nom;
    @Size(min = 3, max = 30, message = "le nom ne dois étre entre 3 et 10")
    private String prenom;
    @Email(message = "l'email n'est pas valid")
    @Column(name = "email")
    private String email;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "scrumMaster")
    private List<Sprint> sprints;

    @JsonManagedReference // test
    @JsonIgnore
    @OneToMany(mappedBy = "productOwner")
    private List<Project> projects;

    @JsonIgnore
    @OneToOne(mappedBy = "developer")
    private Task task;

    @Transient
    public Role getRole() {
        return switch (this) {
            case ProductOwner productOwner -> Role.PRODUCT_OWNER;
            case ScrumMaster scrumMaster -> Role.SCRUM_MASTER;
            case Developer developer -> Role.DEVELOPER;
            default -> null;
        };
    }
}