package com.sba301.backendpe.controller;

import com.sba301.backendpe.entity.Shop;
import com.sba301.backendpe.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myapp/shops")
@CrossOrigin(origins = "http://localhost:5173")
public class ShopController {
    private final ShopService shopService;

    @GetMapping
    public ResponseEntity<List<Shop>> getAll() {
        return ResponseEntity.ok(shopService.getAll());
    }

    @PostMapping
    public ResponseEntity<Shop> create(@RequestBody Shop shop) {
        return ResponseEntity.ok(shopService.create(shop));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getById(@PathVariable Long id) {
        return ResponseEntity.ok(shopService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        shopService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/types")
    public List<String> getTypes() {
        return shopService.getTypes();
    }

}