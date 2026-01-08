package com.arssekal.AgileManager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("scrum_master")
public class ScrumMaster extends User {
    @JsonIgnore
    @OneToMany(mappedBy = "scrumMaster")
    private List<Sprint> sprints;
}
