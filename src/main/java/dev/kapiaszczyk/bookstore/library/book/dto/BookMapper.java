package dev.kapiaszczyk.bookstore.library.book.dto;

import dev.kapiaszczyk.bookstore.library.book.Book;
import dev.kapiaszczyk.bookstore.library.credit.Credit;
import dev.kapiaszczyk.bookstore.library.credit.dto.CreditDTO;
import dev.kapiaszczyk.bookstore.library.loan.Loan;
import dev.kapiaszczyk.bookstore.library.loan.dto.LoanDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "isbn.id", target = "isbnId")
    @Mapping(source = "inventory.id", target = "inventoryId")
    @Mapping(source = "credits", target = "credits") // Explicitly map the credits field
    @Mapping(source = "loan", target = "loan") // Explicitly map the loan field
    BookDTO mapToDTO(Book book);

    @Named("mapToDTOList")
    List<BookDTO> mapToDTOList(List<Book> books);

    @Named("mapCreditListToDTOList")
    List<CreditDTO> mapCreditListToDTOList(List<Credit> credits); // Define the mapping for List<Credit> to List<CreditDTO>

    @Named("mapLoanToDTO")
    LoanDTO loanToDTO(Loan loan); // Define the mapping for Loan to LoanDTO

}
