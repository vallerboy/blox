package pl.oskarpolak.blox.models.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.oskarpolak.blox.models.CategoryEntity;
import pl.oskarpolak.blox.models.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryEntity> getCategories(){
        return categoryRepository.findAll();
    }

}
