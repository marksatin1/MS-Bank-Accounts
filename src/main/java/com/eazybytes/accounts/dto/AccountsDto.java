package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Accounts",
        description = "Schema to hold bank account data"
)
@Data
public class AccountsDto {

    @Schema(
            description = "Bank account number",
            example = "1287916226"
    )
    @NotEmpty(message = "Account Number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})", message="AccountNumber must be 10 digits.")
    private Long accountNumber;

    @Schema(
            description = "Type of bank account",
            example = "Savings"
    )
    @NotEmpty(message = "Account Type cannot be null or empty.")
    private String accountType;

    @Schema(
            description = "Branch Address of bank account",
            example = "123 New York Ave"
    )
    @NotEmpty(message = "Branch Address cannot be null or empty.")
    private String branchAddress;
}
