package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EngineRepository {

    private CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     * @param engine
     * @return двигатель с id
     */
    public Engine create(Engine engine) {
        crudRepository.run(session ->
                session.persist(engine));
        return engine;
    }

    /**
     * Обновить в базе engine.
     * @param engine
     */
    public void update(Engine engine) {
        crudRepository.run(session ->
                session.merge(engine));
    }

    /**
     * Удалить по id.
     * @param engineId
     */
    public void delete(int engineId) {
        crudRepository.run("delete from Engine where id = :fId",
                Map.of("fId", engineId));
    }

    /**
     * Список двигателей по id.
     * @return список двигателей.
     */
    public List<Engine> findAllOrderById() {
        return crudRepository.query("from Engine", Engine.class);
    }

    /**
     * Найти двигатель по id.
     * @param engineId
     * @return id двигателя.
     */
    public Optional<Engine> findById(int engineId) {
        return crudRepository.optional("from Engine where id = :fid", Engine.class,
                Map.of("fId", engineId));
    }
}
