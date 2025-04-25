// package com.repsy.hello.entity;

// import jakarta.persistence.Embeddable;
// import lombok.*;

// @Embeddable
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class Dependency {
//     private String packageName;
//     private String version;
// }

//without lombok

package com.repsy.hello.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Dependency {

    private String packageName;
    private String version;

    public Dependency() {
    }

    public Dependency(String packageName, String version) {
        this.packageName = packageName;
        this.version = version;
    }

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
