// package com.repsy.hello.entity;

// import jakarta.persistence.*;
// import lombok.*;
// import java.time.LocalDateTime;
// import java.util.List;

// @Builder
// @Entity
// @Table(name = "packages")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class Package {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String name;
//     private String version;
//     private String author;

//     @ElementCollection
//     @CollectionTable(name = "package_dependencies", joinColumns = @JoinColumn(name = "package_id"))
//     private List<Dependency> dependencies;

//     private LocalDateTime uploadedAt;

//     private String storageType; // "file-system" or "object-storage"
// }

//without lombok
package com.repsy.hello.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "packages")
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

    private String storageType;

    public Package() {
    }

    // Builder (manuel)
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Package pkg = new Package();

        public Builder name(String name) {
            pkg.setName(name);
            return this;
        }

        public Builder version(String version) {
            pkg.setVersion(version);
            return this;
        }

        public Builder author(String author) {
            pkg.setAuthor(author);
            return this;
        }

        public Builder dependencies(List<Dependency> deps) {
            pkg.setDependencies(deps);
            return this;
        }

        public Builder uploadedAt(LocalDateTime time) {
            pkg.setUploadedAt(time);
            return this;
        }

        public Builder storageType(String type) {
            pkg.setStorageType(type);
            return this;
        }

        public Package build() {
            return pkg;
        }
    }

    // Getter/setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }
}
