package br.com.nutriexpress.demo.controller;

import br.dtos.pratos.PratoRequestDTO;
import br.dtos.pratos.PratoUpdateRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<PratoResponseDTO>> getAllPratos(@RequestParam(required = false) String categoria) {
        List<PratoResponseDTO> pratos = (categoria != null) ?  // Condicional para caso venha o parametro com o nome da categoria ou não
                pratoService.getAllPratosByCategoria(categoria) // Chama a função de busca pela categoria no Services
                : pratoService.getAllPratos(); // Chama a função de busca de todos os pratos
        return ResponseEntity.status(HttpStatus.OK).body(pratos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> getPratoById(@PathVariable Long id){
        PratoResponseDTO prato = pratoService.getPratoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(prato);
    }

    @GetMapping("/calorias")
    public ResponseEntity<List<PratoResponseDTO>> getAllPratosByMaxCalorias(@RequestParam Double max){
        List<PratoResponseDTO> pratos = pratoService.getAllPratosByMaxCalorias(max);
        return ResponseEntity.status(HttpStatus.OK).body(pratos);
    }

    @PostMapping
    public ResponseEntity<PratoResponseDTO> createNewPrato(@Valid @RequestBody PratoRequestDTO prato){
        PratoResponseDTO newPrato = pratoService.createNewPrato(prato);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPrato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> updatePrato(@PathVariable Long id, @RequestBody @Valid PratoUpdateRequestDTO updatedDataPrato){
        PratoResponseDTO updatedPrato = pratoService.updatePrato(id, updatedDataPrato);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPrato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrato(@PathVariable Long id){
        pratoService.deletePrato(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
