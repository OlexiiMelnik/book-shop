package online.bookshop.mapper;

import online.bookshop.config.MapperConfig;
import online.bookshop.dto.category.CategoryDto;
import online.bookshop.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);
}
