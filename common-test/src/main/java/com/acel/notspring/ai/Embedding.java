package com.acel.notspring.ai;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.primitives.Floats;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.pinecone.PineconeClient;
import io.pinecone.PineconeClientConfig;
import io.pinecone.PineconeConnection;
import io.pinecone.PineconeConnectionConfig;
import io.pinecone.proto.*;

import java.util.ArrayList;
import java.util.List;

public class Embedding {

//    public static void main(String[] args) throws Exception{
//        int i = 1;
//        for (String text : arr) {
//            JSONArray jsonArray = embeddingData(text);
//            System.out.println(text + ":" +jsonArray);
//            insert2(jsonArray, "b"+i, text);
//            i++;
//        }
//    }

    public static void main(String[] args) throws Exception{

            JSONArray jsonArray = embeddingData("失眠");
            query(jsonArray);
    }

    private static JSONArray embeddingData(String text) throws Exception{
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("https://api.openai.com/v1/embeddings")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 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")
                .body("{\n    \"input\": \"" + text + "\",\n    \"model\": \"text-embedding-ada-002\"\n  }")
                .asString();

        return JSON.parseObject(response.getBody()).getJSONArray("data").getJSONObject(0)
                .getJSONArray("embedding");
    }
    static String[] arr = new String[]{
            "玫瑰亲亲小王子：“我爱你，但你总要离开……”小王子：“我那个时候太小，不懂得如何爱你。",
            "我一直都过得不好，每晚哭着入睡，做梦的时候你一次次地离去，我次次抓不住，亲眼见你一次次死去……我已经什么都感觉不到了，没有快乐，没有恐惧，感觉不到饥饿，困苦，酸甜苦辣，闻不到，尝不出，眼睛也开始花了，像是一直蒙着雾",
            "如果有一天，剑还在我却输了，那是因为我想输",
            "世界那么大，我们依旧错过",
            "柔和的阳光斜挂在苍松翠柏不凋的枝叶上，显得那么安静肃穆，绿色的草坪和白色的水泥道貌岸然上，脚步是那么轻起轻落，大家的心中却是那么的激动与思绪波涌"
    };


    public static void insert2(JSONArray jsonArray, String id, String text) throws UnirestException {
        HttpResponse<String> response = Unirest.post("https://test2-bbfa7aa.svc.gcp-starter.pinecone.io/vectors/upsert")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("Api-Key", "0d76805b-ee67-45c5-b8d3-b31a07821c3f")
                .body("{\"vectors\":[{\"metadata\":{\"origin_text\":\"" + text+"\"},\"id\":\" " + id +" \",\"values\":" +jsonArray.toJSONString()+"}]}")
                .asString();

        System.out.println(text + "=======" +response.getBody());
    }

    public static void query(JSONArray jsonArray) throws UnirestException {
        HttpResponse<String> response = Unirest.post("https://test2-bbfa7aa.svc.gcp-starter.pinecone.io/query")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("Api-Key", "0d76805b-ee67-45c5-b8d3-b31a07821c3f")
                .body("{\"includeValues\":false,\"includeMetadata\":true,\"topK\":3,\"vector\":" +jsonArray.toJSONString()+"}")
                .asString();
        System.out.println(response.getBody());


    }
}
