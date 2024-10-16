package com.codean.remidiujian2.models.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatatanParkir {

    private UUID id;
    private UUID idKendaraan;
    private UUID idSlotParkir;
    @JsonIgnore
    private ZonedDateTime waktuMasuk;
    @JsonIgnore
    private ZonedDateTime waktuKeluar;
    private Double biayaParkir;
}
