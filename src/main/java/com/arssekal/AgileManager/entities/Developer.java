package com.arssekal.AgileManager.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("developer")
public class Developer extends User {

}
