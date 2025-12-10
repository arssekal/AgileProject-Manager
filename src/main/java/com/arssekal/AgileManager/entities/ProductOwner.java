package com.arssekal.AgileManager.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("product_owner")
public class ProductOwner extends User {
}
