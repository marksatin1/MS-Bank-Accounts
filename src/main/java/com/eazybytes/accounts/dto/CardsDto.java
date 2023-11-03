package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Cards",
        description = "Schema to hold bank card information"
)
@Data
public class CardsDto {

    @Schema(
            description = "Mobile phone number associated with bank card",
            example = "6469529026"
    )
    @NotEmpty(message = "Mobile phone number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})", message="Mobile phone number must be 10 digits.")
    private String mobileNumber;

    @Schema(
            description = "ID number of bank card",
            example = "422378884160"
    )
    @Pattern(regexp = "(^$|[0-9]{12})", message="Card number must be 12 digits.")
    @NotEmpty(message = "Card number cannot be null or empty.")
    private String cardNumber;

    @Schema(
            description = "Type of bank card",
            example = "Credit"
    )
    @NotEmpty(message = "Card type cannot be null or empty.")
    private String cardType;

    @Schema(
            description = "Card's spending limit (USD)",
            example = "50000"
    )
    @Positive(message = "Total card limit (USD) must be greater than zero.")
    private int totalLimit;

    @Schema(
            description = "Amount of card limit (USD) used",
            example = "20000"
    )
    @PositiveOrZero(message = "Total amount of limit used (USD) must be greater than or equal to zero.")
    private int amountUsed;

    @Schema(
            description = "Remaining amount of card limit (USD) remaining",
            example = "30000"
    )
    @PositiveOrZero(message = "Total amount of limit used (USD) must be greater than or equal to zero.")
    private int availableAmount;
}
