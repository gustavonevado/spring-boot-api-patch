package br.com.api.patch.exceptions;

import br.com.api.patch.exceptions.domain.ErrorInfo;
import feign.FeignException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({FeignException.class})
    public @ResponseBody
    ErrorInfo handleFeignException(HttpServletRequest request, FeignException ex) {

        return buildErrorInfo(request, ex, ex.getMessage());
    }

    private ErrorInfo buildErrorInfo(HttpServletRequest request, Exception exceptionCdt, String message) {
        return ErrorInfo
                .builder()
                .timestamp(LocalDateTime.now()).message(message)
                .exception(exceptionCdt.getClass().getSimpleName()).path(request.getRequestURI()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public @ResponseBody
    ErrorInfo handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException ex) {
        return buildErrorInfo(request, ex, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody
    ErrorInfo handleConstraintViolation(HttpServletRequest request, ConstraintViolationException ex) {
        List<String> details = ex.getConstraintViolations()
                .parallelStream()
                .map(e -> e.getMessage())
                .collect(Collectors.toList());
        String errors = details.stream().reduce("", (a, e) -> a + e + " ");
        return buildErrorInfo(request, ex, errors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ NotFound.class })
    public @ResponseBody ErrorInfo handleNotFoundException(HttpServletRequest request, NotFound ex) {
        return buildErrorInfo(request, ex, ex.getMessage());
    }

}
