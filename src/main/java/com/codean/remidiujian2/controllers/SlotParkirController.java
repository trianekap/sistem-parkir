package com.codean.remidiujian2.controllers;

import com.codean.remidiujian2.models.dtos.SlotParkirDTO;
import com.codean.remidiujian2.models.entity.SlotParkir;
import com.codean.remidiujian2.services.SlotParkirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/slot-parkir")
public class SlotParkirController {

    @Autowired
    private SlotParkirService slotParkirService;

    @GetMapping
    public ResponseEntity<List<SlotParkirDTO>> findAll() {
        List<SlotParkirDTO> slotParkirDTOList = slotParkirService.findAll();
        return ResponseEntity.ok(slotParkirService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlotParkirDTO> findById(@PathVariable UUID id) {
        return  ResponseEntity.ok(slotParkirService.findById(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody SlotParkirDTO slotParkirDTO) {
        slotParkirService.save(slotParkirDTO);
        return new  ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> update(@Valid @RequestBody SlotParkirDTO slotParkirDTO) {
        slotParkirService.update(slotParkirDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable UUID id) {
        slotParkirService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

