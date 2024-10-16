package com.codean.remidiujian2.controllers;

import com.codean.remidiujian2.exceptions.ValueTooLongException;
import com.codean.remidiujian2.models.dtos.KendaraanDTO;
import com.codean.remidiujian2.services.KendaraanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/kendaraan")
public class KendaraanController {
    private final KendaraanService kendaraanService;

    @GetMapping
    public ResponseEntity<List<KendaraanDTO>> findAll() {
        List<KendaraanDTO> kendaraanList = kendaraanService.findAll();
        return ResponseEntity.ok(kendaraanService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KendaraanDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(kendaraanService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody KendaraanDTO kendaraanDTO){
        if (kendaraanDTO.getPlatNomor().length() > 10) {
            throw new ValueTooLongException("Plat nomor cannot then 10 characters");
        }
        kendaraanService.save(kendaraanDTO);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> update(@Valid @RequestBody KendaraanDTO kendaraanDTO){
        if (kendaraanDTO.getPlatNomor().length() > 10) {
            throw new ValueTooLongException("Plat nomor cannot then 10 characters");
        }
        kendaraanService.update(kendaraanDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable UUID id){
        kendaraanService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}