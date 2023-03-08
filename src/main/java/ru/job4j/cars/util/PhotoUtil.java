package ru.job4j.cars.util;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.config.LoadConfig;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.service.PhotoCrudService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class PhotoUtil {
    private final LoadConfig loadConfig;
    private final PhotoCrudService photos;

    /**
     * Сохраняет загруженные файлы на диск и хранилище
     * путь к файлам задаётся в переменной:
     * upload.path=classpath:static/images/
     * максимальный размер загружаемого файла:
     * spring.servlet.multipart.max-file-size=10MB
     *
     * @param ids массив идентификаторов фото
     * @param files массив файлов
     * @param post объявление
     * @return сообщение с ошибками, если пусто - без ошибок.
     * @throws IOException сохранить и удалить файлы
     */
    public String savePhotosFromPage(@NotNull int[] ids,
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
                    photo1.setPostId(post.getId());
                    photos.update(photo1);
                    photoList.add(photo1);
                } else {
                    err += "фото №"
                            + count
                            + " не найдено, id="
                            + id + "!"
                            + System.lineSeparator();
                }
            } else {
                MultipartFile file = files[count];
                Photo photo = create(post, file.getOriginalFilename(), file);
                if (photo == null) {
                    err += "фото №" + count + " не сохранено!<br>" + System.lineSeparator();
                }
            }
            count++;
        }
        return err;
    }

    /**
     * Создание одной фотографии на диске и в хранилище
     * @param post объявление
     * @param photoName имя фотографии
     * @param file файл из страницы браузера
     * @return фотографию, если ошибка то null.
     * @throws IOException запись файла
     */
    public Photo create(Post post,
                        String photoName,
                        MultipartFile file) throws IOException {
        Photo photo = null;
        if (file.getSize() <= loadConfig.getImageSizeMax()) {
            photo = new Photo();
            int lastId = photos.findAllOrderById().stream()
                    .max(Comparator.comparingInt(Photo::getId))
                    .orElse(null)
                    .getId() + 1;
            photo.setName("photo " + lastId
                    + " " + post.getCar().getMarc().getName()
                    + "-" + post.getCar().getModel().getName()
            );
            String fn = file.getOriginalFilename();
            String ext = fn.substring(fn.length() - 3);
            photo.setFileName("f" + lastId + "." + ext);
            photo.setPostId(post.getId());
            photo = photos.create(photo);
            byte[] data = file.getBytes();
            photo.setFileName("f" + photo.getId() + "." + ext);
            try {
                File outputFile = new File(loadConfig.getImagesPath() + photo.getFileName());
                try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                    byte[] decoded = Base64.getDecoder().decode(data);
                    outputStream.write(decoded);
                    outputStream.flush();
                    photos.update(photo);
                }
            } catch (Exception e) {
                photos.delete(photo.getId());
                photo = null;
            }
        }
        return photo;
    }

    /**
     * Удалить фотографию из хранилища и с диска.
     * @param photo - фотография
     */
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
