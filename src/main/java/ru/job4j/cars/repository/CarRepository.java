package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarRepository {
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     * @param car
     * @return машина с id.
     */
    public Car create(Car car) {
        crudRepository.run(session ->
                session.persist(car));
        return car;
    }

    /**
     * Обновить в базе car.
     * @param car
     */
    public void update(Car car) {
        crudRepository.run(session ->
                session.merge(car));

    }

    /**
     * Удалить по id.
     * @param carId
     */
    public void delete(int carId) {
        crudRepository.run("delete from Car where id = :fId",
                Map.of("fId", carId)
        );
    }

    /**
     * Список машин отсортированных по id.
     * @return список машин.
     */
    public List<Car> findAllOrderById() {
        return crudRepository.query("from Car car join fetch car.drivers", Car.class);
    }

    /**
     * Найти машину по id.
     * @param carId
     * @return carId машины.
     */
    public Optional<Car> findById(int carId) {
        return crudRepository.optional(
                "from Car car join fetch car.drivers where id = :fId", Car.class,
        Map.of("fId", carId)
        );
   }
}
