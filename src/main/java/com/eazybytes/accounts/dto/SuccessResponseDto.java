package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
@Schema(
        name="Success Response",
        description="Schema to hold successful response data"
)
@Data @AllArgsConstructor
public class SuccessResponseDto {

    @Schema(
            description="Status code of a successful response"
    )
    private String statusCode;

    @Schema(
            description="Status message of a successful response"
    )
    private String statusMsg;

}
