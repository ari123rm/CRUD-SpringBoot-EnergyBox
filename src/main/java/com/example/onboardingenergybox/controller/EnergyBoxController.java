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

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/energy")
public class EnergyBoxController {

    @Autowired
    private EnergyBoxRepository repository;

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
                        entity.getUserCreate(),
                        entity.getUserEdit()
                ));
        return ResponseEntity.ok(dtoPage);
    }

    @PostMapping
    public ResponseEntity<EnergyBox> create(@RequestBody EnergyBoxDTO data) {
        EnergyBox entity = new EnergyBox();

        ZonedDateTime nowBrazil = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
        entity.setUserEdit(data.userEdit());
        entity.setDateCreate(nowBrazil.format(formatter));
        entity.setDateEdit(nowBrazil.format(formatter));

        EnergyBox saved = repository.save(entity);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnergyBox> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnergyBox> putById(@PathVariable long id,@RequestBody EnergyBoxDTO data){
        EnergyBox entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado: " + id));

        ZonedDateTime nowBrazil = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        entity.setPackNumber(data.packNumber());
        entity.setProductionOrder(data.productionOrder());
        entity.setPalletNumber(data.palletNumber());
        entity.setBoxNumber(data.boxNumber());
        entity.setQuantityMetersInBox(data.quantityMetersInBox());
        entity.setBoxWeight(data.boxWeight());
        entity.setClosed(data.closed());
        entity.setPrinted(data.printed());
        entity.setClosedByUserName(data.closedByUserName());
        entity.setUserCreate(entity.getUserCreate());
        entity.setUserEdit(data.userEdit());
        entity.setDateCreate(entity.getDateCreate());
        entity.setDateEdit(nowBrazil.format(formatter));

        EnergyBox boxAtualizada = repository.save(entity);

        return ResponseEntity.ok(boxAtualizada);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<EnergyBox> patchById(@PathVariable long id, @RequestBody EnergyBoxDTO data) {
        EnergyBox entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado: " + id));

        if (data.packNumber() != null) {
            entity.setPackNumber(data.packNumber());
        }
        if (data.productionOrder() != null) {
            entity.setProductionOrder(data.productionOrder());
        }
        if (data.palletNumber() != null) {
            entity.setPalletNumber(data.palletNumber());
        }
        if (data.boxNumber() != null) {
            entity.setBoxNumber(data.boxNumber());
        }
        if (data.quantityMetersInBox() != null) {
            entity.setQuantityMetersInBox(data.quantityMetersInBox());
        }
        if (data.boxWeight() != null) {
            entity.setBoxWeight(data.boxWeight());
        }
        if (data.closed() != null) {
            entity.setClosed(data.closed());
        }
        if (data.printed() != null) {
            entity.setPrinted(data.printed());
        }
        if (data.closedByUserName() != null) {
            entity.setClosedByUserName(data.closedByUserName());
        }
        if (data.userEdit() != null) {
            entity.setUserEdit(data.userEdit());
        }

        ZonedDateTime nowBrazil = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        entity.setDateEdit(nowBrazil.format(formatter));

        EnergyBox boxAtualizada = repository.save(entity);

        return ResponseEntity.ok(boxAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}