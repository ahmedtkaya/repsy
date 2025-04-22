package com.repsy.hello.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Entity
@Table(name = "packages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String version;
    private String author;

    @ElementCollection
    @CollectionTable(name = "package_dependencies", joinColumns = @JoinColumn(name = "package_id"))
    private List<Dependency> dependencies;

    private LocalDateTime uploadedAt;

    private String storageType; // "file-system" or "object-storage"
}
