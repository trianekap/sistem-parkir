package com.codean.remidiujian2.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatatanParkirPostDTO {

    private UUID idKendaraan;
    private UUID idSlotParkir;

}
