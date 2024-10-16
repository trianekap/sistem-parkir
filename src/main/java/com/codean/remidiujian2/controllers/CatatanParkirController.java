package com.codean.remidiujian2.controllers;

import com.codean.remidiujian2.models.dtos.CatatanParkirDTO;
import com.codean.remidiujian2.models.dtos.CatatanParkirPostDTO;
import com.codean.remidiujian2.services.CatatanParkirService;
import com.codean.remidiujian2.utility.ConvertToFile;
import com.itextpdf.text.DocumentException;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/catatan-parkir")
public class CatatanParkirController {

    @Autowired
    ConvertToFile convertToFile;

    @Autowired
    private CatatanParkirService catatanParkirService;

    @GetMapping("/getall")
    public ResponseEntity<List<CatatanParkirDTO>> getAllCatatanParkir() {
        return ResponseEntity.ok().body(catatanParkirService.findAll());
    }

    @GetMapping("/byid/{id}")
    public ResponseEntity<CatatanParkirDTO> getById(@PathVariable UUID id){
        return ResponseEntity.ok().body(catatanParkirService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody CatatanParkirPostDTO catatanParkirPostDTO){
        catatanParkirService.save(catatanParkirPostDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<HttpStatus> update(@Valid @RequestBody CatatanParkirDTO catatanParkirDTO){
        catatanParkirService.update(catatanParkirDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/print/{id}")
    ResponseEntity<CatatanParkirDTO> printTicket(@PathVariable UUID id) throws Docx4JException, DocumentException, IOException {
        CatatanParkirDTO catatanParkirDTO = catatanParkirService.getTicket(id);

        convertToFile.saveUserDtoListToDocx(catatanParkirDTO);
        convertToFile.saveUserDtoListToPdf(catatanParkirDTO);
        return ResponseEntity.ok().body(catatanParkirService.getTicket(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable UUID id){
        catatanParkirService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

