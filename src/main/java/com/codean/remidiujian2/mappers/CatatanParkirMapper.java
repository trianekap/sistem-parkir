package com.codean.remidiujian2.mappers;

import com.codean.remidiujian2.models.dtos.CatatanParkirDTO;
import com.codean.remidiujian2.models.entity.CatatanParkir;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CatatanParkirMapper {

    void save(CatatanParkir catatanParkir);

    CatatanParkir findById(@Param("id") UUID id);

    List<CatatanParkir> findAll();

    void update(CatatanParkir catatanParkir);

    void update(CatatanParkirDTO catatanParkir);

    void delete(@Param("id") UUID id);
}
