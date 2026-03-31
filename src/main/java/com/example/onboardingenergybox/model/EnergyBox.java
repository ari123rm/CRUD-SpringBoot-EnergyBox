package com.example.onboardingenergybox.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "box",schema = "energy")
@Getter @Setter @NoArgsConstructor
public class EnergyBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int packNumber;
    private String productionOrder;
    private int palletNumber;
    private int boxNumber;
    private int quantityMetersInBox;
    private float boxWeight;
    private boolean closed;
    private boolean printed;
    private String closedByUserName;
    private String userCreate;
    private String userEdit;
    private String dateCreate;
    private String dateEdit;
}
