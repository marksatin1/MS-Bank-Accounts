package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Schema(
    name="Error Response",
    description="Schema to hold error response data"
)
@Data @AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description="API path invoked by client"
    )
    private String apiPath;

    @Schema(
            description="Response error code"
    )
    private HttpStatus errorCode;

    @Schema(
            description="Response error message"
    )
    private String errorMessage;

    @Schema(
            description="Date and time of the response error"
    )
    private LocalDateTime errorTime;

}
