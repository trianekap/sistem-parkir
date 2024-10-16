package com.codean.remidiujian2.services;

import com.codean.remidiujian2.models.dtos.CatatanParkirDTO;
import com.codean.remidiujian2.models.dtos.CatatanParkirPostDTO;
import com.codean.remidiujian2.models.entity.CatatanParkir;
import org.apache.ibatis.annotations.Param;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface CatatanParkirService {

    void save(CatatanParkirPostDTO catatanParkirPostDTO);

    CatatanParkirDTO findById(UUID id);

    void update(CatatanParkirDTO catatanParkirDTO);

    CatatanParkirDTO getTicket(UUID id);

    List<CatatanParkirDTO> findAll();

    void delete(UUID id);

    CatatanParkirDTO CalculateAndSaveParkingFee(CatatanParkirDTO catatanParkirDTO);
}

