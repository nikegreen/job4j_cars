package ru.job4j.cars.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import java.io.File;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class LoadConfig {
    private final ResourceLoader resourceLoader;

    @Value("${upload.path}")
    private String imagesPathCfg;

    @Value("${spring.servlet.multipart.max-file-size}")
    private String fileSizeMax;

    private String classPath;

    private long imageSizeMax = 0;

    private String imagesPath = null;

    /**
     * Настраивает путь к фотографиям
     * @throws IOException операции с файлами
     */
    private void config() throws IOException {
        String resourceFile = "application.properties";
        Resource resource = resourceLoader.getResource("classpath:" + resourceFile);
        String classPath = resource.getFile().getAbsolutePath();
        this.classPath = classPath.substring(0, classPath.length() -  resourceFile.length());
        if (imagesPathCfg.startsWith("classpath:")) {
            imagesPathCfg = imagesPathCfg.replace("classpath:", this.classPath);
        }
        if (File.separatorChar == '/') {
            imagesPath = imagesPathCfg.replace('\\', File.separatorChar);
        } else {
            imagesPath = imagesPathCfg.replace('/', File.separatorChar);
        }
        int k = 1;
        if (fileSizeMax.endsWith("MB")) {
            fileSizeMax = fileSizeMax.substring(0, fileSizeMax.length() - 2);
            k = 1024 * 1024;
        }
        if (fileSizeMax.endsWith("KB")) {
            fileSizeMax = fileSizeMax.substring(0, fileSizeMax.length() - 2);
            k = 1024;
        }
        imageSizeMax = Integer.valueOf(fileSizeMax).intValue() * k;
    }

    /**
     *
     * @return тип {@link java.lang.String} -
     */
    public String getImagesPath() {
        if (imagesPath == null) {
            try {
                config();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return imagesPath;
    }

    public long getImageSizeMax() {
        if (imageSizeMax == 0) {
            try {
                config();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return imageSizeMax;
    }
}
