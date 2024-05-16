package com.catcher92.demo.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class JacksonSerializationDemo {

    private static final Logger log = LoggerFactory.getLogger(JacksonSerializationDemo.class);

    public static void main(String[] args) throws JsonProcessingException {
        Outer1 outer1 = new Outer1();
        outer1.setOuterField("value1");
        outer1.setAdditionalField("value4");

        Inner inner = new Inner();
        inner.setInnerField1("value2");
        inner.setInnerField2("value3");
        outer1.setValue(inner);

        Outer<Inner> outer2 = new Outer<>();
        outer2.setOuterField("value1");
        outer2.setValue(inner);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(outer1));
        System.out.println(mapper.writeValueAsString(outer2));
    }

    public static class OuterSerializer<T extends Serializable> extends JsonSerializer<Outer<T>> {
        private static final Map<String, Map<String, String>> jsonPropertyCache = new ConcurrentHashMap<>();
        private static final Set<String> IGNORE_FIELDS = Sets.newHashSet("value");

        @Override
        public void serialize(Outer<T> outer, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            Map<String, Object> map = serialObject(outer, IGNORE_FIELDS);
            Optional.ofNullable(outer.getValue()).ifPresent(v -> map.putAll(serialObject(v, null)));
            for (Map.Entry<String, Object>entry : map.entrySet()){
                gen.writeObjectField(entry.getKey(), entry.getValue());
            }
                gen.writeEndObject();
        }

        private Map<String, Object> serialObject(Object obj, Set<String> notIncludeFields) {
            Map<String, Object> result = new LinkedHashMap<>();
            Class<?> clz = obj.getClass();
            while (null != clz && Serializable.class.isAssignableFrom(clz)) {
                for (Field field : clz.getDeclaredFields()) {
                    if ((null != notIncludeFields && notIncludeFields.contains(field.getName())) || field.getName().equals("serialVersionUID")) {
                        continue;
                    }
                    field.setAccessible(true);
                    try {
                        result.put(getJsonPropertyMap(clz).get(field.getName()), field.get(obj));
                    } catch (IllegalAccessException e) {
                        log.error("Get value for {}.{} failed for {}", clz.getName(), field.getName(), obj);
                    }
                }
                clz = clz.getSuperclass();
            }
            return result;
        }

        private Map<String, String> getJsonPropertyMap(Class<?> clazz) {
            String className = clazz.getName();
            return jsonPropertyCache.computeIfAbsent(className, k -> {
                Map<String, String> fieldMap = new ConcurrentHashMap<>();
                for (Field field : clazz.getDeclaredFields()) {
                    JsonProperty annotation = field.getAnnotation(JsonProperty.class);
                    if (annotation != null && !annotation.value().isEmpty()) {
                        fieldMap.put(field.getName(), annotation.value());
                    } else {
                        fieldMap.put(field.getName(), field.getName());
                    }
                }
                return fieldMap;
            });
        }
    }

    @JsonSerialize(using = OuterSerializer.class)
    public static class Outer<T extends Serializable> implements Serializable {
        private static final long serialVersionUID = 2659822988250727175L;
        @JsonProperty("outer_field")
        private String outerField;
        private T value;

        public void setOuterField(String outerField) {
            this.outerField = outerField;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

    @JsonSerialize(using = OuterSerializer.class)
    public static class Outer1 extends Outer<Inner> {
        private static final long serialVersionUID = 8975398921707712395L;
        @JsonProperty("additional_field")
        private String additionalField;

        public void setAdditionalField(String additionalField) {
            this.additionalField = additionalField;
        }
    }

    public static class Inner implements Serializable {
        private static final long serialVersionUID = -5718784085727263744L;
        @JsonProperty("inner_field_1")
        private String innerField1;
        @JsonProperty("inner_field_2")
        private String innerField2;

        public void setInnerField1(String innerField1) {
            this.innerField1 = innerField1;
        }

        public void setInnerField2(String innerField2) {
            this.innerField2 = innerField2;
        }
    }
}
