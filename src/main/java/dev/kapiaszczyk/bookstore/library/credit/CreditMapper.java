package dev.kapiaszczyk.bookstore.library.credit;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CreditMapper {

    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "book.id", target = "bookId")
    CreditDTO creditToCreditDTO(Credit credit);
}
