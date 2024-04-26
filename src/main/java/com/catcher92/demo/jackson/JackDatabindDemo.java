package com.catcher92.demo.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class JackDatabindDemo {

    static class Tuple2<T0, T1> {
        public T0 f0;
        public T1 f1;

        public int getArity() {
            return 2;
        }

        public Tuple2() {}

        public Tuple2(T0 f0, T1 f1) {
            this.f0 = f0;
            this.f1 = f1;
        }

        @Override
        public String toString() {
            return "Tuple2{" +
                    "f0=" + f0 +
                    ", f1=" + f1 +
                    '}';
        }
    }

    static class QueueElement {
        private final String name;
        private final String nickName;

        /**
         * 需要无参构造方法，否则构造空list会报错
         */
        @SuppressWarnings("unused")
        public QueueElement() {
            name = null;
            nickName = null;
        }

        public QueueElement(String name, String nickName) {
            this.name = name;
            this.nickName = nickName;
        }

        public String getName() {
            return name;
        }

        public String getNickName() {
            return nickName;
        }

        @Override
        public String toString() {
            return "QueueElement{" +
                    "name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        // 可以防止因为Tuple2中的arity字段导致反序列化失败
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        TypeReference<LinkedList<Tuple2<QueueElement, QueueElement>>> typeReference = new TypeReference<LinkedList<Tuple2<QueueElement, QueueElement>>>() {};
        List<Tuple2<QueueElement, QueueElement>> elements1 = mapper.readValue("[]", typeReference);
        System.out.println(elements1);

        String value = mapper.writeValueAsString(Collections.singleton(new Tuple2<QueueElement, QueueElement>(new QueueElement("zs", "ls"), null)));
        System.out.println(value);
        List<Tuple2<QueueElement, QueueElement>> elements2 = mapper.readValue(value, typeReference);
        System.out.println(elements2);
    }
}
