package com.ensa.AgileManager.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("product_owner")
@SuperBuilder
@NoArgsConstructor
public class ProductOwner extends User {
}
