package dev.kapiaszczyk.bookstore.library.author.dto;

import dev.kapiaszczyk.bookstore.library.author.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    AuthorDTO mapToAuthorDTO(Author author);

    @Named("mapToAuthorDTOList")
    List<AuthorDTO> mapToAuthorDTOList(List<Author> authors);
}
