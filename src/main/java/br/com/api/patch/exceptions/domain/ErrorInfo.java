package br.com.api.patch.exceptions.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@ApiModel(description = "API Response error")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ErrorInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "timestamp", notes = "Data e hora do erro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime timestamp;

    @ApiModelProperty(value = "code", notes = "Error code")
    public Integer code;

    @ApiModelProperty(value = "exception", notes = "Exception")
    public String exception;

    @ApiModelProperty(value = "message", notes = "Error message")
    public String message;

    @ApiModelProperty(value = "path", notes = "Path of error origin")
    public String path;

}
