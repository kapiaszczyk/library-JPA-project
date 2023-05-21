package dev.kapiaszczyk.bookstore.library.credit.dto;

import dev.kapiaszczyk.bookstore.library.author.Author;
import dev.kapiaszczyk.bookstore.library.author.dto.AuthorDTO;
import dev.kapiaszczyk.bookstore.library.author.dto.AuthorMapper;
import dev.kapiaszczyk.bookstore.library.credit.Credit;
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