package com.arssekal.AgileManager.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("developer")
@SuperBuilder
@NoArgsConstructor
public class Developer extends User {

}
