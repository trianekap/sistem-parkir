package com.codean.remidiujian2.services;

import com.codean.remidiujian2.models.dtos.SlotParkirDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface SlotParkirService {

    List<SlotParkirDTO> findAll();
    SlotParkirDTO findById(UUID id);
    Map<String, Object> getSlotParkirPage(int pageNum, int pageSize, String orderColumn, String orderType);
    void save(SlotParkirDTO slotParkirDTO);
    void update(SlotParkirDTO slotParkirDTO);
    void delete(UUID id);

}

