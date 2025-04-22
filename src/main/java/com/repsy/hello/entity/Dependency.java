package com.repsy.hello.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dependency {
    private String packageName;
    private String version;
}
