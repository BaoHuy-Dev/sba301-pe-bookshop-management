package com.sba301.backendpe.repository;

import com.sba301.backendpe.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    boolean existsByName(String name);
}