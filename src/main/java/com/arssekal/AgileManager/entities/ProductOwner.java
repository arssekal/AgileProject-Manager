package com.arssekal.AgileManager.entities;

import com.arssekal.AgileManager.enums.Role;
import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("product_owner")
public class ProductOwner extends User {
}
