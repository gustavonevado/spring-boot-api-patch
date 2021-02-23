package br.com.api.patch.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;
import java.util.Set;
import javax.json.JsonMergePatch;
import javax.json.JsonPatch;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.geo.GeoJsonModule;
import org.springframework.stereotype.Service;

@Service
public class PatchUtils {

    private ObjectMapper jsonMapper = objectMapper();

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private Validator validator;

    public PatchUtils(Validator validator) {
        this.validator = validator;
        this.jsonMapper.registerModule(new JSR353Module());
//        this.jsonMapper.registerModule(new GeoJsonModule()); //TODO remover
    }

    public <T> T mergePatch(JsonMergePatch mergePatch, T targetBean, Class<T> beanClass) {

        // Convert the Java bean to a JSON document
        JsonValue target = jsonMapper.convertValue(targetBean, JsonValue.class);

        // Apply the JSON Merge Patch to the JSON document
        JsonValue patched = mergePatch.apply(target);

        T beanPatched = jsonMapper.convertValue(patched, beanClass);

        // Validate the Java bean and throw an excetion if any constraint has been violated
        Set<ConstraintViolation<T>> violations = validator.validate(beanPatched);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return beanPatched;
    }

    public <T> T patch(JsonPatch patch, T targetBean, Class<T> beanClass) {

        // Convert the Java bean to a JSON document
        JsonStructure target = jsonMapper.convertValue(targetBean, JsonStructure.class);

        // Apply the JSON Patch to the JSON document
        JsonValue patched = patch.apply(target);

        // Convert the JSON document to a Java bean and return it
        return jsonMapper.convertValue(patched, beanClass);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .findAndRegisterModules();
    }
}
