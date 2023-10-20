package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.SuccessResponseDto;
import com.eazybytes.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD endpoints for the Accounts microservice",
        description = "CRUD REST API endpoints for the Accounts microservice in personal banking application"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    @Operation(
          summary = "Create Account endpoint",
          description = "REST API endpoint to create a new Account and a new Customer"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "HTTP Status CREATED"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status INTERNAL SERVER ERROR",
                content = @Content(
                        schema = @Schema(implementation= ErrorResponseDto.class)
                )
        )
    })
    @PostMapping("/create")
    public ResponseEntity<SuccessResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SuccessResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
    @Operation(
            summary = "Fetch Account Details endpoint",
            description = "REST API endpoint to fetch Account and Customer details"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status INTERNAL SERVER ERROR",
                content = @Content(
                        schema = @Schema(implementation= ErrorResponseDto.class)
                )
        )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})", message="Mobile number must be 10 digits.")
                                                               String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }
    @Operation(
            summary = "Update Account Details endpoint",
            description = "REST API endpoint to update Account and Customer details using a Customer DTO"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "417",
                description = "HTTP Status EXPECTATION FAILED"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status INTERNAL SERVER ERROR",
                content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
                )
        )
    })
    @PutMapping("/update")
    public ResponseEntity<SuccessResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SuccessResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new SuccessResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }
    @Operation(
            summary = "Delete Account/Customer endpoint",
            description = "REST API endpoint to delete an existing Account/Customer using a mobile phone number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status EXPECTATION FAILED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
            )
            )

    })
    @DeleteMapping("/delete")
    public ResponseEntity<SuccessResponseDto> deleteAccount(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
                                                                String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new SuccessResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SuccessResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }
}
