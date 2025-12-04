package com.arssekal.AgileManager.entities;

import com.arssekal.AgileManager.enums.Role;
import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("scrum_master")
public class ScrumMaster extends User {
}
