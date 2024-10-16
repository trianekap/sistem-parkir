package com.codean.remidiujian2.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatatanParkirDTO {
    private UUID id;
    private UUID idKendaraan;
    private UUID idSlotParkir;
    @JsonIgnore
    private ZonedDateTime waktuMasuk;
    @JsonIgnore
    private ZonedDateTime waktuKeluar;
    private Double biayaParkir;
}

