package com.example.onboardingenergybox.dto;

public record EnergyBoxDTO(
        int packNumber,
        String productionOrder,
        int palletNumber,
        int boxNumber,
        int quantityMetersInBox,
        float boxWeight,
        boolean closed,
        boolean printed,
        String closedByUserName,
        String userCreate
) {}
