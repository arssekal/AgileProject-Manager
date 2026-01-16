package com.arssekal.AgileManager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@DiscriminatorValue("scrum_master")
@SuperBuilder
@NoArgsConstructor
public class ScrumMaster extends User {
    @JsonIgnore
    @OneToMany(mappedBy = "scrumMaster")
    private List<Sprint> sprints;
}
