package com.codean.remidiujian2.services.implement;

import com.codean.remidiujian2.mappers.CatatanParkirMapper;
import com.codean.remidiujian2.models.dtos.CatatanParkirDTO;
import com.codean.remidiujian2.models.dtos.CatatanParkirPostDTO;
import com.codean.remidiujian2.models.dtos.SlotParkirDTO;
import com.codean.remidiujian2.models.entity.CatatanParkir;
import com.codean.remidiujian2.services.CatatanParkirService;
import com.codean.remidiujian2.services.KendaraanService;
import com.codean.remidiujian2.services.SlotParkirService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CatatanParkirServiceImpl implements CatatanParkirService {

    private static final Logger log = LoggerFactory.getLogger(CatatanParkirServiceImpl.class);

    private final CatatanParkirMapper catatanParkirMapper;
    private final ModelMapper modelMapper = new ModelMapper();
    private final KendaraanService kendaraanService;
    private final SlotParkirService slotParkirService;


    @Override
    public List<CatatanParkirDTO> findAll(){
        return catatanParkirMapper.findAll().stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        catatanParkirMapper.delete(id);
    }

    @Override
    public CatatanParkirDTO findById(UUID id){
        CatatanParkir checkExisted = catatanParkirMapper.findById(id);

        return toDto(checkExisted);
    }

    @Override
    public void update(CatatanParkirDTO catatanParkirDTO){
        catatanParkirMapper.update(toEntity(catatanParkirDTO));
    }

    @Override
    public void save(CatatanParkirPostDTO catatanParkirPostDTO){
        ZonedDateTime masuk = ZonedDateTime.of(2024, 10, 15, 9, 30, 0, 0, ZoneId.of("Asia/Jakarta"));

        CatatanParkir catatanParkir = toEntity(catatanParkirPostDTO);
        catatanParkir.setWaktuMasuk(masuk);

//        catatanParkir.setWaktuMasuk(ZonedDateTime.now());

        SlotParkirDTO slotParkirDTO = slotParkirService.findById(catatanParkirPostDTO.getIdSlotParkir());
        if(!slotParkirDTO.getTerisi())
            slotParkirDTO.setTerisi(true);
        else{
            //throw exception
            log.warn("Tempat parkir sudah terisi!");
        }

        catatanParkirMapper.save(catatanParkir);

    }

    @Override
    public CatatanParkirDTO getTicket(UUID id){

        CatatanParkirDTO catatanParkirDTO = CalculateAndSaveParkingFee(findById(id));

        SlotParkirDTO slot = slotParkirService.findById(catatanParkirDTO.getIdSlotParkir());
        slot.setTerisi(false);
        slotParkirService.update(slot);

        return catatanParkirDTO;
    }

    @Override
    public CatatanParkirDTO CalculateAndSaveParkingFee(CatatanParkirDTO catatanParkirDTO){

        catatanParkirDTO.setWaktuKeluar(ZonedDateTime.now());
        Duration duration = Duration.between(catatanParkirDTO.getWaktuMasuk(), catatanParkirDTO.getWaktuKeluar());
        long hours = duration.toHours();
        double biaya;
        if (hours <= 2){
            biaya = 5000;
            catatanParkirDTO.setBiayaParkir(biaya);
        } else {
            biaya = 5000 + ((hours - 2) * 2000);
            catatanParkirDTO.setBiayaParkir(biaya);
        }


        catatanParkirMapper.update(toEntity(catatanParkirDTO));
        return catatanParkirDTO;
    }



    private CatatanParkirDTO toDto(CatatanParkir catatanParkir){
        return modelMapper.map(catatanParkir, CatatanParkirDTO.class);
    }

    private CatatanParkir toEntity(CatatanParkirDTO catatanParkirDTO){
        return modelMapper.map(catatanParkirDTO, CatatanParkir.class);
    }

    private CatatanParkir toEntity(CatatanParkirPostDTO catatanParkirPostDTO){
        return modelMapper.map(catatanParkirPostDTO, CatatanParkir.class);
    }

}
