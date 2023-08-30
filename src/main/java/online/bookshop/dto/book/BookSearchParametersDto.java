package online.bookshop.dto.book;

public record BookSearchParametersDto(String[] title, String[] author, String[] isbn,
                                      String[] price, String[] description) {
}
