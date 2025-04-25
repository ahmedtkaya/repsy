
package com.repsy.hello.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PackageMetaDto {

    private String name;
    private String version;
    private String author;
    private List<DependencyDto> dependencies = new ArrayList<>();

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

    public List<DependencyDto> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<DependencyDto> dependencies) {
        this.dependencies = dependencies;
    }

    public static class DependencyDto {

        @JsonProperty("package")
        private String packageName;

        private String version;

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
