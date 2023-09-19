

package com.qf.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Json 工具类
 * 基于 Jackson 实现
 *
 * @author 千锋健哥
 */
public final class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE)
            .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, Boolean.FALSE)
            .serializationInclusion(JsonInclude.Include.NON_NULL).build();


    /**
     * 反序列化，将 Json 字符串解析为 Java 对象
     *
     * @param text      Json String
     * @param valueType Java 对象
     * @return Java 对象
     */
    public static <T> T parseObject(String text, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(text, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化，将 Json 字符串解析为 Java 对象
     *
     * @param bytes     Json Byte Array
     * @param valueType Java 对象
     * @return Java 对象
     */
    public static <T> T parseObject(byte[] bytes, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(bytes, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化，将 Json 字符串解析为 Java 对象
     *
     * @param jsonParser Json Parser
     * @param valueType  Java 对象
     * @return Java 对象
     */
    public static <T> T parseObject(JsonParser jsonParser, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(jsonParser, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化，将 Json 字符串解析为 Java 对象
     *
     * @param dataInput Json DataInput
     * @param valueType Java 对象
     * @return Java 对象
     */
    public static <T> T parseObject(DataInput dataInput, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(dataInput, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化，将 Json 字符串解析为 Java 对象
     *
     * @param inputStream Json InputStream
     * @param valueType   Java 对象
     * @return Java 对象
     */
    public static <T> T parseObject(InputStream inputStream, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化，将 Json 字符串解析为 Java 对象
     *
     * @param reader    Json Reader
     * @param valueType Java 对象
     * @return Java 对象
     */
    public static <T> T parseObject(Reader reader, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(reader, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化，将 Json 字符串解析为 Java 对象
     *
     * @param file      Json File
     * @param valueType Java 对象
     * @return Java 对象
     */
    public static <T> T parseObject(File file, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(file, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化，将Json 字符串转化为指定对象,并将指定对象中的泛型转为指定的T类型
     *
     * @param text          Json String
     * @param typeReference TypeReference，T 为指定要转化为的数据类型
     * @return Java 对象
     */
    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(text, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化，将 Json 字符串解析为 Java 对象集合
     *
     * @param text      Json String
     * @param valueType Java 对象
     * @return Java 对象集合
     */
    public static <T> List<T> parseArray(String text, Class<T> valueType) {
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, valueType);
            return OBJECT_MAPPER.readValue(text, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化，将 Json 字符串解析为 Java 对象集合
     *
     * @param bytes     Json Byte Array
     * @param valueType Java 对象
     * @return Java 对象集合
     */
    public static <T> List<T> parseArray(byte[] bytes, Class<T> valueType) {
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, valueType);
            return OBJECT_MAPPER.readValue(bytes, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化，将 Json 字符串解析为 Java 对象集合
     *
     * @param jsonParser Json JsonParser
     * @param valueType  Java 对象
     * @return Java 对象集合
     */
    public static <T> List<T> parseArray(JsonParser jsonParser, Class<T> valueType) {
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, valueType);
            return OBJECT_MAPPER.readValue(jsonParser, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化，将 Json 字符串解析为 Java 对象集合
     *
     * @param dataInput Json DataInput
     * @param valueType Java 对象
     * @return Java 对象集合
     */
    public static <T> List<T> parseArray(DataInput dataInput, Class<T> valueType) {
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, valueType);
            return OBJECT_MAPPER.readValue(dataInput, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化，将 Json 字符串解析为 Java 对象集合
     *
     * @param inputStream Json InputStream
     * @param valueType   Java 对象
     * @return Java 对象集合
     */
    public static <T> List<T> parseArray(InputStream inputStream, Class<T> valueType) {
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, valueType);
            return OBJECT_MAPPER.readValue(inputStream, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化，将 Json 字符串解析为 Java 对象集合
     *
     * @param reader    Json Reader
     * @param valueType Java 对象
     * @return Java 对象集合
     */
    public static <T> List<T> parseArray(Reader reader, Class<T> valueType) {
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, valueType);
            return OBJECT_MAPPER.readValue(reader, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化，将 Json 字符串解析为 Java 对象集合
     *
     * @param file      Json File
     * @param valueType Java 对象
     * @return Java 对象集合
     */
    public static <T> List<T> parseArray(File file, Class<T> valueType) {
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, valueType);
            return OBJECT_MAPPER.readValue(file, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 序列化，将一个 Java 对象或 Java 对象集合转化为 Json 字符串
     *
     * @param type Java 对象引用
     * @return Json 字符串
     */
    public static <T> String toJsonString(T type) {
        try {
            return OBJECT_MAPPER.writeValueAsString(type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 序列化，将一个 Java 对象转化为 Json 字符串，并定制 Json 展示内容
     *
     * @param type              Java 对象引用
     * @param serializationView 定制现实内容的 Java 对象
     * @return Json 字符串
     */
    public static <T> String toJsonString(T type, Class<?> serializationView) {
        try {
            return OBJECT_MAPPER.writerWithView(serializationView).writeValueAsString(type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 美化序列化，将一个 Java 对象或 Java 对象集合转化为 Json 字符串
     *
     * @param type Java 对象引用
     * @return Json 字符串
     */
    public static <T> String toPrettyJsonString(T type) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 美化序列化，将一个Java 对象转化为 Json 字符串，并定制 Json 展示内容
     *
     * @param type              Java 对象引用
     * @param serializationView 定制现实内容的Java 对象
     * @return Json 字符串
     */
    public static <T> String toPrettyJsonString(T type, Class<?> serializationView) {
        try {
            return OBJECT_MAPPER.writerWithView(serializationView).withDefaultPrettyPrinter().writeValueAsString(type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 序列化，将一个 Java 对象或 Java 对象集合转化为 Json Byte Array
     *
     * @param type Java 对象引用
     * @return Json 字符串
     */
    public static <T> byte[] toJsonBytes(T type) {
        try {
            return OBJECT_MAPPER.writeValueAsString(type).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回一个ObjectMapper对象
     *
     * @return ObjectMapper
     */
    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}