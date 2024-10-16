package com.codean.remidiujian2.models.entity;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlotParkir {

    private UUID id;
    @NotBlank(message = "Nomor slot tidak boleh kosong")
    private String nomorSlot;
    private Boolean terisi;
}
