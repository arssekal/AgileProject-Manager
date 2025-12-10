package com.arssekal.AgileManager.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("scrum_master")
public class ScrumMaster extends User {

}
