package online.bookshop.specification.bookspec;

import jakarta.persistence.criteria.Predicate;
import online.bookshop.model.Book;
import online.bookshop.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    private static final String FILTER_KEY = "author";

    @Override
    public String getKey() {
        return FILTER_KEY;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.like(root.get(FILTER_KEY),
                    "%" + params[0] + "%");
            return criteriaBuilder.and(predicate);
        };
    }
}
