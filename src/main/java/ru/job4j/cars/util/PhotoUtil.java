package ru.job4j.cars.util;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.config.LoadConfig;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.service.PhotoAbstractService;
import ru.job4j.cars.service.PhotoCrudService;
import ru.job4j.cars.service.PhotoMemService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PhotoUtil {
    private final LoadConfig loadConfig;
    private final PhotoCrudService photos;

    public String SavePhotosFromPage(@NotNull Model model,
                                      @NotNull int[] ids,
                                      @NotNull MultipartFile[] files,
                                      @NotNull Post post
                                      ) throws IOException {
        List<Photo> photoList = new ArrayList<>();
        String err = "";
        int count = 0;
        for (int id: ids) {
            if (id > 0) {
                Optional<Photo> photo = photos.findById(id);
                if (photo.isPresent()) {
                    Photo photo1 =  photo.get();
                    photo1.setPost(post);
                    photos.update(photo1);
                    photoList.add(photo1);
                } else {
                    err += "фото №" + count + " не найдено, id=" + id + "!" + System.lineSeparator();
                }
            } else {
                MultipartFile file = files[count];
                Photo photo = create(photos, post, file.getOriginalFilename(), file);
                if (photo == null) {
                    err += "фото №" + count + " не сохранено!<br>" + System.lineSeparator();
                }
            }
            count++;
        }
        return err;
    }

    public Photo create(PhotoAbstractService photos,
                        Post post,
                        String photoName,
                        MultipartFile file) throws IOException {
        Photo photo = null;
        if (file.getSize() <= loadConfig.getImageSizeMax()) {
            photo = new Photo();
            photo.setName("");
            photo.setFileName("f");
            photo.setPost(post);
            photo = photos.create(photo);
            byte[] data = file.getBytes();
            //String type = file.getContentType();
            String fn = file.getOriginalFilename();
            if (fn != null) {
                photo.setName(fn);
            }
            String ext = fn.substring(fn.length() - 3);
            photo.setFileName("f" + photo.getId() + "." + ext);
            try {
                File outputFile = new File(loadConfig.getImagesPath() + photo.getFileName());
                try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                    byte[] decoded = Base64.getDecoder().decode(data);
                    outputStream.write(decoded);
                    outputStream.flush();
                }
            } catch (Exception e) {
                photos.delete(photo.getId());
                photo = null;
            }
        }
        return photo;
    }

    public void delete(Photo photo) {
        try {
            File outputFile = new File(loadConfig.getImagesPath() + photo.getFileName());
            outputFile.delete();
            photos.delete(photo.getId());
            photo = null;
        } catch (Exception e) {
        }
    }
}
