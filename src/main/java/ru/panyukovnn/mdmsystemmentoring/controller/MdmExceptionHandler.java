package ru.panyukovnn.mdmsystemmentoring.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.panyukovnn.mdmsystemmentoring.dto.common.CommonResponse;
import ru.panyukovnn.mdmsystemmentoring.dto.common.CommonResponseBody;
import ru.panyukovnn.mdmsystemmentoring.dto.common.ValidationError;

import java.util.List;
import java.util.UUID;

import static ru.panyukovnn.mdmsystemmentoring.util.Constants.ERROR_RESPONSE_STATUS;

@Slf4j
@RestControllerAdvice
public class MdmExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        List<ValidationError> validationErrors = fieldErrors.stream()
            .map(it -> ValidationError.builder()
                .field(it.getField())
                .message(it.getDefaultMessage())
                .build())
            .toList();

        log.error("Ошибка валидации: {}", validationErrors, e);

        return CommonResponse.builder()
            .body(CommonResponseBody.builder()
                .id(UUID.randomUUID())
                .errorMessage("Ошибка валидации")
                .validationErrors(validationErrors)
                .status(ERROR_RESPONSE_STATUS)
                .build())
            .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse handleInvalidFormatException(HttpMessageNotReadableException e) {
        if (e.getCause() instanceof InvalidFormatException ife) {
            String errMessage = "Ошибка валидации, указан некорректный формат для следующего значения: " + ife.getValue();

            log.error(errMessage, e);

            return CommonResponse.builder()
                .body(CommonResponseBody.builder()
                    .id(UUID.randomUUID())
                    .errorMessage(errMessage)
                    .status(ERROR_RESPONSE_STATUS)
                    .build())
                .build();
        }

        return handleUnexpectedException(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResponse handleUnexpectedException(Exception e) {
        log.error("Непредвиденная ошибка: {}", e.getMessage(), e);

        return CommonResponse.builder()
            .body(CommonResponseBody.builder()
                .id(UUID.randomUUID())
                .errorMessage(e.getMessage())
                .status(ERROR_RESPONSE_STATUS)
                .build())
            .build();
    }
}
