package com.sam.velusamy_samundeeswari_peacocknestdaycare.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    // Path where files will be stored
    private final Path fileStorageLocation;

    /**
     * Initializes the file storage location by creating the necessary directories.
     *
     * @throws IOException if an I/O error occurs while creating the directories
     */
    public FileStorageService() throws IOException {
        // Set the file storage location to the 'uploads' directory, normalized to an absolute path
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        // Create the directory if it does not exist
        Files.createDirectories(this.fileStorageLocation);
    }

    /**
     * Stores a file in the file storage location.
     *
     * @param file the MultipartFile to be stored
     * @return the name of the stored file
     * @throws IOException if an I/O error occurs while storing the file
     */
    public String storeFile(MultipartFile file) throws IOException {
        // Get the original file name
        String fileName = file.getOriginalFilename();
        // Resolve the target location for the file
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        // Copy the file to the target location, replacing any existing file with the same name
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        // Return the name of the stored file
        return fileName;
    }
}
