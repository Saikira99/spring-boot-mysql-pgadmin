package com.example.spring_boot_mysql_pgadmin.controller;

import com.example.spring_boot_mysql_pgadmin.entity.Technology;
import com.example.spring_boot_mysql_pgadmin.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @PostMapping
    public Technology createTechnology(@RequestBody Technology technology, @RequestParam String dbType) {
        return technologyService.saveOrUpdate(technology, dbType);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technology> getTechnologyById(@PathVariable Long id, @RequestParam String dbType) {
        return technologyService.findById(id, dbType)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Technology> getAllTechnologies(@RequestParam String dbType) {
        return technologyService.findAll(dbType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Technology> updateTechnology(@PathVariable Long id, @RequestBody Technology updatedTechnology, @RequestParam String dbType) {
        return technologyService.findById(id, dbType)
                .map(existingTechnology -> {
                    existingTechnology.setName(updatedTechnology.getName());
                    return ResponseEntity.ok(technologyService.saveOrUpdate(existingTechnology, dbType));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable Long id, @RequestParam String dbType) {
        technologyService.deleteById(id, dbType);
        return ResponseEntity.noContent().build();
    }
}
