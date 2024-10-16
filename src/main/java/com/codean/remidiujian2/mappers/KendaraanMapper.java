package com.codean.remidiujian2.mappers;

import com.codean.remidiujian2.models.entity.Kendaraan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface KendaraanMapper {

    Kendaraan findById(@Param("id") UUID id);

    List<Kendaraan> findAll();

    List<Kendaraan> findByPlatNomor(String platNomor);

    void update(Kendaraan kendaraan);

    void delete(@Param("id") UUID id);

    void save(Kendaraan kendaraan);

}
