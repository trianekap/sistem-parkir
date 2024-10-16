package com.codean.remidiujian2.mappers;

import com.codean.remidiujian2.models.entity.SlotParkir;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface SlotParkirMapper {
    List<SlotParkir> findAll();
    SlotParkir findById(@Param("id") UUID id);
    void save(SlotParkir slotParkir);
    void update(SlotParkir slotParkir);
    void delete(UUID slotParkir);
    List<SlotParkir> getLimitedItem(int pageSize, int offset, String order);
    int countItems();

}
