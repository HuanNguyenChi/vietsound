package com.huannguyen.vietsound.service.impl;

import com.huannguyen.vietsound.config.StorageException;
import com.huannguyen.vietsound.config.StorageFileNotFoundException;
import com.huannguyen.vietsound.config.StorageProperties;
import com.huannguyen.vietsound.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootLocation;

    public FileSystemStorageService() {
//        this.rootLocation = Paths.get("/static/img/bg-img/") ;
        this.rootLocation = Paths.get("C:\\Users\\nchih\\OneDrive\\Máy tính\\vietsound\\src\\main\\resources\\static\\img\\bg-img");
    }

    @Override
    public void store(MultipartFile file) {
        try {

            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
