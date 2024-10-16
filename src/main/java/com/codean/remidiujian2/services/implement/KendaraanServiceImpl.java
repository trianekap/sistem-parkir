package com.codean.remidiujian2.services.implement;
import com.codean.remidiujian2.exceptions.ResourceAlreadyExistException;
import com.codean.remidiujian2.mappers.KendaraanMapper;
import com.codean.remidiujian2.models.dtos.KendaraanDTO;
import com.codean.remidiujian2.models.entity.Kendaraan;
import com.codean.remidiujian2.services.KendaraanService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KendaraanServiceImpl implements KendaraanService {

    private static final Logger log = LoggerFactory.getLogger(KendaraanServiceImpl.class);
    private final KendaraanMapper kendaraanMapper;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public void save(KendaraanDTO kendaraanDTO) {
        Kendaraan kendaraan = toEntity(kendaraanDTO);
        kendaraanMapper.save(kendaraan);
    }

    @Override
    public KendaraanDTO findById(UUID id) {
        Kendaraan kendaraan = kendaraanMapper.findById(id);
        return toDTO(kendaraan);
    }

    @Override
    public List<KendaraanDTO> findAll() {
        return kendaraanMapper.findAll().stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(KendaraanDTO kendaraanDTO) {
        checkDuplicate(kendaraanDTO.getId(), kendaraanDTO.getPlatNomor());
        kendaraanMapper.update(toEntity(kendaraanDTO));
    }

    @Override
    public void delete(UUID id) {
        kendaraanMapper.delete(id);
    }

    private void checkDuplicate(UUID id, String platNomor) {
        List<Kendaraan> kendaraanList = kendaraanMapper.findByPlatNomor(platNomor.toUpperCase());
        if (!kendaraanList.isEmpty() && !id.equals(kendaraanList.get(0).getId())) {
                log.warn("Plat number already exists");
                throw new ResourceAlreadyExistException("Plat number already exists");
            }
    }

    @Override
    public List<KendaraanDTO> findByPlatNomor(String platNomor) {
        List<Kendaraan> checkExist = kendaraanMapper.findByPlatNomor(platNomor.toUpperCase());
        return checkExist.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public KendaraanDTO toDTO(Kendaraan kendaraan){
        return modelMapper.map(kendaraan, KendaraanDTO.class);
    }

    public Kendaraan toEntity(KendaraanDTO kendaraanDTO){
        return modelMapper.map(kendaraanDTO, Kendaraan.class);
    }
}

