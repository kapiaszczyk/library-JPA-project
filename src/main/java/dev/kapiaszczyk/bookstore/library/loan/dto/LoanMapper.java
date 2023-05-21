package dev.kapiaszczyk.bookstore.library.loan.dto;

import dev.kapiaszczyk.bookstore.library.loan.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "status", source = "status")
    LoanDTO mapLoanToDTO(Loan loan);

}
