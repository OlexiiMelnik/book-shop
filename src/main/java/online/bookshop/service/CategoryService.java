package online.bookshop.service;

import java.util.List;
import online.bookshop.dto.category.CategoryDto;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);

    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto findById(Long id);

    CategoryDto update(Long id, CategoryDto categoryDto);

    void deleteById(Long id);
}
