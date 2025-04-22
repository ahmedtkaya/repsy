package com.repsy.hello.controller;

import com.repsy.hello.service.PackageService;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @PostMapping("/{packageName}/{version}")
    public ResponseEntity<?> uploadPackage(
            @PathVariable String packageName,
            @PathVariable String version,
            @RequestParam("package") MultipartFile repFile,
            @RequestParam("meta") MultipartFile metaFile) {
        try {
            packageService.handleUpload(packageName, version, repFile, metaFile);
            return ResponseEntity.ok("Package uploaded successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{packageName}/{version}/{fileName}")
    public ResponseEntity<?> downloadFile(
            @PathVariable String packageName,
            @PathVariable String version,
            @PathVariable String fileName) {
        try {
            byte[] file = packageService.getFile(packageName, version, fileName);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + fileName)
                    .body(file);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(404).body("File not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}
