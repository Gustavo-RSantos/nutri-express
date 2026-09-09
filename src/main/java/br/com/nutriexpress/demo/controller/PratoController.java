package br.com.nutriexpress.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriexpress.demo.service.PratoService;
import org.springframework.web.bind.annotation.GetMapping;
import br.dtos.pratos.PratoResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pratos")
public class PratoController {
    
    @Autowired
    private PratoService pratoService;

    public PratoController(PratoService pratoService){
        this.pratoService = pratoService;
    }

    @GetMapping
    public ResponseEntity<List<PratoResponseDTO>> listarPratos() {
        List<PratoResponseDTO> pratos = pratoService.listarPratos()
            .stream()
            .map(PratoResponseDTO::new)
            .toList();

        return ResponseEntity.ok(pratos);        
    }
    

}
