package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name="CustomerDetails",
        description="Schema to hold Customer, Account, Cards, and Loans information"
)
public class CustomerDetailsDto {

    @Schema(
            description="Customer name",
            example="Mark Satin"
    )
    @NotEmpty(message= "Name cannot be null or empty.")
    @Size(min=5, max=30, message="The length of the customer name must be between 5 and 30 characters.")
    private String name;

    @Schema(
            description="Customer email address",
            example="mark@fakemail.com"
    )
    @NotEmpty(message="Email address cannot be null or empty.")
    @Email(message="Must be a valid email address.")
    private String email;

    @Schema(
            description="Customer mobile phone number",
            example="9175552620"
    )
    @Pattern(regexp="(^$|[0-9]{10})", message="Mobile phone number must be exactly 10 digits.")
    private String mobileNumber;

    @Schema(
            description="Account details of the Customer"
    )
    private AccountsDto accountsDto;

    @Schema(
            description="Loans details of the Customer"
    )
    private LoansDto loansDto;

    @Schema(
            description="Cards details of the Customer"
    )
    private CardsDto cardsDto;
}
