package com.example.onboardingenergybox.dto;

public record EnergyBoxDTO(
        Integer packNumber,
        String productionOrder,
        Integer palletNumber,
        Integer boxNumber,
        Integer quantityMetersInBox,
        Float boxWeight,
        Boolean closed,
        Boolean printed,
        String closedByUserName,
        String userCreate,
        String userEdit
) {}
