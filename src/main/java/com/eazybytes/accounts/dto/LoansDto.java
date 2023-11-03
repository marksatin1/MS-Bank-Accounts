package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Loans",
        description = "Schema to hold Loans information"
)
@Data
public class LoansDto {

    @Schema(
            description = "Mobile Number of the Loan holder",
            example = "2126449370"
    )
    @NotEmpty(message = "Mobile Number cannot be null or empty.")
    @Pattern(regexp="(^$|[0-9]{10})", message="Mobile Number must be 10 digits.")
    private String mobileNumber;

    @Schema(
            description = "ID Number of the Loan",
            example = "944271603827"
    )
    @NotEmpty(message = "Loan Number cannot be null or empty.")
    @Pattern(regexp="(^$|[0-9]{12})", message="AccountNumber must be 12 digits.")
    private String loanNumber;

    @Schema(
            description = "Type of Loan",
            example = "Home Loan"
    )
    @NotEmpty(message = "Loan Type cannot be null or empty.")
    private String loanType;

    @Schema(
            description = "Total Amount of Loan (USD)",
            example = "30000"
    )
    @Positive(message = "Total Loan Amount must be greater than zero.")
    private int totalLoan;

    @Schema(
            description = "Total Amount paid back on Loan (USD)",
            example = "10500"
    )
    @PositiveOrZero(message = "Total Amount Paid must be greater than or equal to zero.")
    private int amountPaid;

    @Schema(
            description = "Amount remaining to be paid back on Loan (USD)",
            example = "19500"
    )
    @PositiveOrZero(message = "Total Outstanding Amount must be greater than or equal to zero.")
    private int outstandingAmount;
}
