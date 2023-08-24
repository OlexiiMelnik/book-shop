package online.bookshop.specification.bookspec;

import lombok.RequiredArgsConstructor;
import online.bookshop.dto.BookSearchParametersDto;
import online.bookshop.model.Book;
import online.bookshop.specification.SpecificationBuilder;
import online.bookshop.specification.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto bookSearchParametersDto) {
        Specification<Book> spec = Specification.where(null);
        if (bookSearchParametersDto.title() != null
                && bookSearchParametersDto.title().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("title")
                    .getSpecification(bookSearchParametersDto.title()));
        }
        if (bookSearchParametersDto.author() != null
                && bookSearchParametersDto.author().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("author")
                    .getSpecification(bookSearchParametersDto.author()));
        }
        if (bookSearchParametersDto.isbn() != null
                && bookSearchParametersDto.isbn().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("isbn")
                    .getSpecification(bookSearchParametersDto.isbn()));
        }
        if (bookSearchParametersDto.price() != null
                && bookSearchParametersDto.price().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("price")
                    .getSpecification(bookSearchParametersDto.price()));
        }
        if (bookSearchParametersDto.description() != null
                && bookSearchParametersDto.description().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("description")
                    .getSpecification(bookSearchParametersDto.description()));
        }
        return spec;
    }
}
