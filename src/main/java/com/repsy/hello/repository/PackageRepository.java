package com.repsy.hello.repository;

import com.repsy.hello.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package, Long> {
    Optional<Package> findByNameAndVersion(String name, String version);
}
