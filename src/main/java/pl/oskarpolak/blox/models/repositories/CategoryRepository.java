package pl.oskarpolak.blox.models.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.oskarpolak.blox.models.CategoryEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
    Optional<CategoryEntity> findByName(String name);
    List<CategoryEntity> findAll();
}
