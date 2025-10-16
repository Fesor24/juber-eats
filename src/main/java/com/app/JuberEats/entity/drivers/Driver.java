package com.app.JuberEats.entity.drivers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Driver {
    private Long id;
    private String licenseNo;
    private String imageUrl;
    private DriverStatus status = DriverStatus.Offline;
    private Instant createdAtUtc;
    private DriverAddress address;
}
