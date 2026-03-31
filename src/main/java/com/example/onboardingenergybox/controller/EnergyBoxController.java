package com.example.onboardingenergybox.controller;

import com.example.onboardingenergybox.dto.EnergyBoxDTO;
import com.example.onboardingenergybox.model.EnergyBox;
import com.example.onboardingenergybox.repository.EnergyBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/api/energy")
public class EnergyBoxController {

    @Autowired
    private EnergyBoxRepository repository;

    // LISTAR TODAS
    @GetMapping
    public ResponseEntity<Page<EnergyBoxDTO>> getAll(Pageable pageable) {
        Page<EnergyBoxDTO> dtoPage = repository.findAll(pageable)
                .map(entity -> new EnergyBoxDTO(
                        entity.getPackNumber(),
                        entity.getProductionOrder(),
                        entity.getPalletNumber(),
                        entity.getBoxNumber(),
                        entity.getQuantityMetersInBox(),
                        entity.getBoxWeight(),
                        entity.isClosed(),
                        entity.isPrinted(),
                        entity.getClosedByUserName(),
                        entity.getUserCreate()
                ));
        return ResponseEntity.ok(dtoPage);
    }

    // CRIAR NOVA CAIXA (POST)
    @PostMapping
    public ResponseEntity<EnergyBox> create(@RequestBody EnergyBoxDTO data) {
        EnergyBox entity = new EnergyBox();

        // Mapeando do DTO para a Entity
        entity.setPackNumber(data.packNumber());
        entity.setProductionOrder(data.productionOrder());
        entity.setPalletNumber(data.palletNumber());
        entity.setBoxNumber(data.boxNumber());
        entity.setQuantityMetersInBox(data.quantityMetersInBox());
        entity.setBoxWeight(data.boxWeight());
        entity.setClosed(data.closed());
        entity.setPrinted(data.printed());
        entity.setClosedByUserName(data.closedByUserName());
        entity.setUserCreate(data.userCreate());
        // A data você pode setar manualmente ou usar @PrePersist na Entity

        EnergyBox saved = repository.save(entity);
        return ResponseEntity.ok(saved);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<EnergyBox> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}