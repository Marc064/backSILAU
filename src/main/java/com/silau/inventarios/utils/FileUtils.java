package com.silau.inventarios.utils;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static MultipartFile convertFileToMultipartFile(String relativePath) throws IOException {
        // Ruta absoluta del archivo
        Path filePath = Path.of(relativePath);

        // Leer el contenido del archivo como bytes
        byte[] content = Files.readAllBytes(filePath);

        // Crear un MultipartFile usando MockMultipartFile
        return new MockMultipartFile(
                filePath.getFileName().toString(),  // Nombre del archivo
                filePath.getFileName().toString(),  // Nombre original del archivo
                Files.probeContentType(filePath),   // Tipo MIME del archivo
                content                             // Contenido del archivo
        );
    }

}
