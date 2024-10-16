package com.codean.remidiujian2.models.entity;


import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Kendaraan {

    private UUID id;
    @NotBlank(message = "Plat nomor tidak boleh kosong")
    private String platNomor;
    private String jenisKendaraan;
}
