package com.repsy.hello.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PackageMetaDto {

    private String name;
    private String version;
    private String author;

    private List<DependencyDto> dependencies;

    @Data
    public static class DependencyDto {
        @JsonProperty("package")
        private String packageName;
        private String version;
    }
}
