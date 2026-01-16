package com.arssekal.AgileManager.entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("scrum_master")
@SuperBuilder
@NoArgsConstructor
public class ScrumMaster extends User {
}
