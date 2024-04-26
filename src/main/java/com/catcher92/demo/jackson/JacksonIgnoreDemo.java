package com.catcher92.demo.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import java.io.Serializable;
import java.lang.annotation.*;

public class JacksonIgnoreDemo {

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface LogIgnore {
    }

    private static final ObjectMapper mapper1 = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    private static final ObjectMapper mapper2 = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean hasIgnoreMarker(AnnotatedMember m) {
                    return _hasAnnotation(m, LogIgnore.class) || super.hasIgnoreMarker(m);
                }
            });

    static class IndexOut implements Serializable {
        private final String notIgnore;
        @JsonIgnore
        private final String jacksonIgnore;
        @LogIgnore
        private final String logIgnore;

        public IndexOut(String notIgnore, String jacksonIgnore, String logIgnore) {
            this.notIgnore = notIgnore;
            this.jacksonIgnore = jacksonIgnore;
            this.logIgnore = logIgnore;
        }

        public String getNotIgnore() {
            return notIgnore;
        }

        public String getJacksonIgnore() {
            return jacksonIgnore;
        }

        public String getLogIgnore() {
            return logIgnore;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        IndexOut indexOut = new IndexOut("a", "b", "c");
        System.out.println(mapper1.writeValueAsString(indexOut));
        System.out.println(mapper2.writeValueAsString(indexOut));
    }
}
