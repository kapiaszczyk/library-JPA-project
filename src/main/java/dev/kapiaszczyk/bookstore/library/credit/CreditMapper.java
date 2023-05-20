package dev.kapiaszczyk.bookstore.library.credit;

import dev.kapiaszczyk.bookstore.library.author.Author;
import dev.kapiaszczyk.bookstore.library.author.AuthorDTO;
import dev.kapiaszczyk.bookstore.library.author.AuthorMapper;
import dev.kapiaszczyk.bookstore.library.book.Book;
import dev.kapiaszczyk.bookstore.library.book.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public interface CreditMapper {

    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "author", target = "author")
    CreditDTO creditToCreditDTO(Credit credit);
    @Named("mapAuthorListToDTOList")
    List<AuthorDTO> mapAuthorListToDTOList(List<Author> authors);
}