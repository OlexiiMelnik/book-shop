package online.bookshop.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import online.bookshop.dto.category.CategoryDto;
import online.bookshop.exeption.EntityNotFoundException;
import online.bookshop.mapper.CategoryMapper;
import online.bookshop.model.Category;
import online.bookshop.repository.CategoryRepository;
import online.bookshop.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return categoryMapper.toDto(
                categoryRepository.save(
                        categoryMapper.toEntity(categoryDto)));
    }

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find category by id: " + id));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find category by id: " + id));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return categoryMapper.toDto(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
