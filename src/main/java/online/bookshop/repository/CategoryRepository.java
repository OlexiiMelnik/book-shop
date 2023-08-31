package online.bookshop.repository;

import java.util.Set;
import online.bookshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<Category> findByIdIn(Set<Long> categoryIds);
}
