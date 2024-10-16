package com.codean.remidiujian2.models.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KendaraanDTO {
    private UUID id;
    @NotBlank(message = "Plat nomor tidak boleh kosong")
    private String platNomor;
    private String jenisKendaraan;
}

