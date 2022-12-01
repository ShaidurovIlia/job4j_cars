package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostRepository {
    private CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     * @param post
     * @return пост с id.
     */
    public Post create(Post post) {
        crudRepository.run(session ->
                session.persist(post));
        return post;
    }

    /**
     * Обновить в базе
     * @param post
     */
    public void update(Post post) {
        crudRepository.run(session ->
                session.merge(post));
    }

    /**
     * Удалить пост по id.
     * @param postId
     */
    public void delete(int postId) {
       crudRepository.run("delete from Post where id = :fId",
                Map.of("fId", postId));
    }

    /**
     * Список постов отсортированных по id.
     * @return список постов.
     */
    public List<Post> findAllOrderById() {
        return crudRepository.query(
                "from Post post join fetch post.participates join fetch post.history",
                Post.class);
    }

    /**
     * Найти объявление определенной марки.
     * @param car
     * @return список постов.
     */
    public List<Post> findBySpecificBrand(Car car) {
        return crudRepository.query(
                "from Post post join fetch post.participates join fetch post.history"
                        + "where post.car.brand like :fKey",
                Post.class, Map.of("fKey", "%" + car.getBrand() + "%")
        );
    }

    /**
     * Найти объявление с фото.
     * @param post
     * @return список постов.
     */
    public List<Post> findByPhoto(Post post) {
        return crudRepository.query(
                "from Post post join fetch post.participates join fetch post.history"
                        + "where photo like :fKey",
                Post.class, Map.of("fKey", Arrays.toString(post.getPhoto()))
        );
    }

    /**
     * Найти объявление за последний день.
     * @param post
     * @return список объявлений.
     */
    public List<Post> findByLastDay(Post post) {
        return crudRepository.query(
                "from Post post join fetch post.participates join fetch post.history where("
                        + "extract (day from created))"
                        + "= (extract(day from current_date)) :fKey", Post.class,
                Map.of("fKey", post.getCreated())
        );
    }

    /**
     * Найти пост по id.
     * @param postId
     * @return пост.
     */
    public Optional<Post> findById(int postId) {
        return crudRepository.optional(
                "from Post post join fetch post.participates where id = :fId",
                Post.class, Map.of("fId", postId));
    }
}
