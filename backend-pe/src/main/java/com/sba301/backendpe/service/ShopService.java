package com.sba301.backendpe.service;

import com.sba301.backendpe.entity.Shop;
import com.sba301.backendpe.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;

    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    public Shop getById(Long id) {
        return shopRepository.findById(id).orElseThrow();
    }

    public Shop create(Shop shop) {
        if (shopRepository.existsByName(shop.getName()))
            throw new RuntimeException("Duplicate shop name");
        if (shop.getOpenTime() <= 0 || shop.getOpenTime() >= 12)
            throw new RuntimeException("Open time must be between 1 and 11");
        return shopRepository.save(shop);
    }

    public void delete(Long id) {
        shopRepository.deleteById(id);
    }

    public List<String> getTypes() {
        return List.of("Science", "Technology", "Education", "Novel", "Stationery");
    }
}