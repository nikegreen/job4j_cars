package ru.job4j.cars.model.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.PriceHistory;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@RequiredArgsConstructor
public class PriceHistoryMemRepository implements PriceHistoryAbstractRepository {
    private final PostMemRepository posts;
    private final AtomicInteger count = new AtomicInteger();

    /**
     * Сохранить в базе изменение цены.
     * @param priceHistory запись с измением цены.
     * @return запись с измением цены с id.
     */
    @Override
    public PriceHistory create(PriceHistory priceHistory) {
        if (priceHistory == null) {
            return null;
        }
        if (priceHistory.getAutoPostId() > 0) {
            Optional<Post> post = posts.findById(priceHistory.getAutoPostId());
            if (post.isEmpty()) {
                return null;
            }
            List<PriceHistory> histories = new ArrayList<>(post.get().getPriceHistories());
            priceHistory.setId(count.incrementAndGet());
            histories.add(priceHistory);
            post.get().setPriceHistories(histories);
        }
        return priceHistory;
    }

    /**
     * Обновить в базе запись с измением цены.
     * @param priceHistory запись с измением цены.
     */
    @Override
    public void update(PriceHistory priceHistory) {
        if (priceHistory == null) {
            return;
        }
        if (priceHistory.getAutoPostId() > 0) {
            Optional<Post> post = posts.findById(priceHistory.getAutoPostId());
            if (post.isEmpty()) {
                priceHistory = null;
                return;
            }
            List<PriceHistory> histories = post.get().getPriceHistories();
            final PriceHistory ph1 = priceHistory;
            histories.forEach(ps -> {
                if(ps.getId() == ph1.getId()) {
                    ps.setAfter(ph1.getAfter());
                    ps.setBefore(ph1.getBefore());
                    ps.setCreated(ph1.getCreated());
                }
            });
            post.get().setPriceHistories(histories);
        }
    }

    /**
     * Удалить запись с измением цены по id.
     * @param id ID запись с измением цены.
     */
    @Override
    public void delete(int id) {
        posts.findAllOrderById().forEach(post -> {
            post.setPriceHistories(
                    post.getPriceHistories()
                            .stream()
                            .filter(priceHistory -> priceHistory.getId() != id)
                            .toList()
            );
        });
    }

    /**
     * Список записей с измением цены отсортированных по id.
     * @return {@link java.util.List<ru.job4j.cars.model.PriceHistory>} список записей с измением цены.
     */
    @Override
    public List<PriceHistory> findAllOrderById() {
        return posts.findAllOrderById()
                .stream()
                .flatMap(post -> post.getPriceHistories().stream())
                .sorted(Comparator.comparing(PriceHistory::getId))
                .toList();
    }

    /**
     * Список запись с измением цены отфильтрованных по id поста.
     * @param postId - id объявления
     * @return {@link java.util.List<java.lang.Integer>} список записей с измением цены.
     */
    @Override
    public List<PriceHistory> findAllWherePost(int postId) {
        Optional<Post> post = posts.findById(postId);
        if (post.isPresent()) {
            return post.get().getPriceHistories();
        }
        return new ArrayList<>();
    }

    /**
     * Найти запись с измением цены по ID
     * @return запись с измением цены.
     */
    @Override
    public Optional<PriceHistory> findById(int id) {
        return posts.findAllOrderById()
                .stream()
                .flatMap(post -> post.getPriceHistories().stream())
                .filter(priceHistory -> priceHistory.getId() == id)
                .findFirst();
    }
}
