package com.fasterxml.jackson.compat11;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

public class ManualAfterburnerTest
{
    public static void main(String[] args) throws Exception
    {
        System.out.print("Testing: Afterburner...");
        final ObjectMapper mapper = JsonMapper.builder()
                .addModule(new AfterburnerModule())
                .build();
        testBasic(mapper);
        testBasic(mapper);
        System.out.println(" - COMPLETE");
    }

    static void testBasic(ObjectMapper mapper) throws Exception
    {
        FiveMinuteUser input = new FiveMinuteUser();
        System.out.print(" write");
        String json = mapper.writeValueAsString(input);
        System.out.print(" "+json.length()+" bytes, read");
        /*FiveMinuteUser out =*/ mapper.readValue(json, FiveMinuteUser.class);
        System.out.print(" back successfully");
    }
}
