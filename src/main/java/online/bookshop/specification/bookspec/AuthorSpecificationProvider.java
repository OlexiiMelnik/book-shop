package online.bookshop.specification.bookspec;

import java.util.Arrays;
import online.bookshop.model.Book;
import online.bookshop.specification.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "author";
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder)
                -> root.get("author").in(Arrays.stream(params).toArray());
    }
}
