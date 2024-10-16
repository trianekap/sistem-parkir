package com.codean.remidiujian2.services;

import com.codean.remidiujian2.models.dtos.KendaraanDTO;

import java.util.List;

import java.util.UUID;

public interface KendaraanService {
    void save(KendaraanDTO kendaraan);
    KendaraanDTO findById(UUID id);
    List<KendaraanDTO> findByPlatNomor(String platNomor);
    List<KendaraanDTO> findAll();
    void update(KendaraanDTO kendaraan);
    void delete(UUID id);

}

