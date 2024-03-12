package com.ncst.flatbuffers;

import com.google.flatbuffers.FlatBufferBuilder;

import java.nio.ByteBuffer;

// 导入生成的FlatBuffers类

public class FlatBuffersExample {

    public static void main(String[] args) {
        // 创建FlatBuffers Builder
        FlatBufferBuilder builder = new FlatBufferBuilder(1024);

        // 构建要序列化的Java对象
        int nameOffset = builder.createString("John Doe");
        MyObject.startMyObject(builder);
        MyObject.addId(builder, 123);
        MyObject.addName(builder, nameOffset);
        int myObjectOffset = MyObject.endMyObject(builder);

        // 结束FlatBuffer构建
        builder.finish(myObjectOffset);

        // 获取序列化后的数据
        ByteBuffer byteBuffer = builder.dataBuffer();

        // 反序列化FlatBuffers数据为Java对象
        MyObject myObject = MyObject.getRootAsMyObject(byteBuffer);

        // 访问反序列化后的对象属性
        int id = myObject.id();
        String name = myObject.name();

        // 打印结果
        System.out.println("Deserialized Object - Id: " + id + ", Name: " + name);
    }
}
