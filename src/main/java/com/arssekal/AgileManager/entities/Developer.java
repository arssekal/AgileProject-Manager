package com.arssekal.AgileManager.entities;

import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("developer")
public class Developer extends User {
}
