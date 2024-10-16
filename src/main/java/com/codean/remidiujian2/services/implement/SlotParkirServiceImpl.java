package com.codean.remidiujian2.services.implement;

import com.codean.remidiujian2.mappers.SlotParkirMapper;
import com.codean.remidiujian2.models.dtos.SlotParkirDTO;
import com.codean.remidiujian2.models.entity.SlotParkir;
import com.codean.remidiujian2.services.SlotParkirService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SlotParkirServiceImpl implements SlotParkirService {


    private ModelMapper modelMapper = new ModelMapper();
    private final SlotParkirMapper slotParkirMapper;

    @Override
    public List<SlotParkirDTO> findAll(){
        return slotParkirMapper.findAll().stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SlotParkirDTO findById(UUID id){
        SlotParkir checkSlotParkir = slotParkirMapper.findById(id);
        return toDto(checkSlotParkir);
    }

    @Override
    public void update(SlotParkirDTO slotParkirDTO){
        slotParkirMapper.update(toEntity(slotParkirDTO));
    }

    @Override
    public void delete(UUID id){
        slotParkirMapper.delete(id);
    }

    @Override
    public void save(SlotParkirDTO slotParkirDTO){
        SlotParkir slotParkir = toEntity(slotParkirDTO);
        slotParkirMapper.save(slotParkir);
    }

    @Override
    public Map<String, Object> getSlotParkirPage(int pageNum, int pageSize, String orderColumn, String orderType){

        orderColumn = orderColumn.equalsIgnoreCase("terisi") ? "terisi" : "nomor_slot";
        orderType = orderType.equalsIgnoreCase("DESC") ? "DESC" : "ASC";
        String orderFormat = orderColumn.concat(" " + orderType);
        int offset = pageNum * pageSize;
        int itemCount = slotParkirMapper.countItems();

        List<SlotParkirDTO> contentDTO = slotParkirMapper.getLimitedItem(pageSize, offset, orderFormat)
                .stream().map(this::toDto).collect(Collectors.toList());


        //Map response
        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("Page Number", pageNum);
        responseMap.put("Page Size", pageSize);
        responseMap.put("Total Element", itemCount);
        responseMap.put("Total Page", itemCount%pageSize == 0 ? itemCount/pageSize: itemCount/pageSize+1);
        responseMap.put("Content", contentDTO);


        return responseMap;

    }


    private SlotParkirDTO toDto(SlotParkir slotParkir){
        return modelMapper.map(slotParkir, SlotParkirDTO.class);
    }
    private SlotParkir toEntity(SlotParkirDTO slotParkirDTO){
        return modelMapper.map(slotParkirDTO, SlotParkir.class);
    }

}
