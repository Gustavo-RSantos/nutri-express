package br.com.nutriexpress.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.nutriexpress.demo.service.PratoService;
import br.dtos.pratos.PratoResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pratos")
public class PratoController {
    
    @Autowired
    private PratoService pratoService;

    @GetMapping
    public ResponseEntity<List<PratoResponseDTO>> getAllPratos(@RequestParam(value = "categoria", required = false) String categoria) {
        List<PratoResponseDTO> pratos = pratoService.getAllPratos()
            .stream()
            .map(PratoResponseDTO::new)
            .toList();

        return ResponseEntity.ok(pratos);        
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<PratoResponseDTO> getPratoById(@PathVariable Long id){
//        PratoResponseDTO prato = new PratoResponseDTO(PratoService.getPratoById(id));
//        return ResponseEntity.ok(prato);
//    }

}
