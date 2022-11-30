package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.CrudRepository;

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
    public List<Post> findAllOrderId() {
        return crudRepository.query("from Post post join fetch post.participates",
                Post.class);
    }

    /**
     * Найти пост по id.
     * @param postId
     * @return пост.
     */
    public Optional<Post> findById(int postId) {
        return crudRepository.optional("from Post post join fetch post.participates where id = :fId",
                Post.class, Map.of("fId", postId));
    }
}
