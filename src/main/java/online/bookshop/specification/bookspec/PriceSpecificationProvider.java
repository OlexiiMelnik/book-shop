package online.bookshop.specification.bookspec;

import jakarta.persistence.criteria.Predicate;
import online.bookshop.model.Book;
import online.bookshop.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceSpecificationProvider implements SpecificationProvider<Book> {
    private static final String FILTER_KEY = "price";

    @Override
    public String getKey() {
        return FILTER_KEY;
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.between(root.get(FILTER_KEY), params[0],
                    (params.length > 1) ? params[1] : params[0]);
            return criteriaBuilder.and(predicate);
        };
    }
}
