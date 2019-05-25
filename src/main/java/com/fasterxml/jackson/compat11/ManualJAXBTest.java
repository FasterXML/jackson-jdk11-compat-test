package com.fasterxml.jackson.compat11;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

public class ManualJAXBTest
{
    static class Annotated {
        @XmlElement(name = "value")
        public int _foo = 3;
    }
    
    public static void main(String[] args) throws Exception
    {
        System.out.print("JAXB Test:");
        final ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JaxbAnnotationModule())
                .build();
        testBasic(mapper);
        testWithJAXBAnnotations(mapper);
        System.out.println(" COMPLETE");
    }

    static void testBasic(ObjectMapper mapper) throws Exception
    {
        FiveMinuteUser input = new FiveMinuteUser();
        String json = mapper.writeValueAsString(input);
        /*FiveMinuteUser out =*/ mapper.readValue(json, FiveMinuteUser.class);
    }

    static void testWithJAXBAnnotations(ObjectMapper mapper) throws Exception
    {
        Annotated input = new Annotated();
        input._foo = 42;
        String json = mapper.writeValueAsString(input);
        Annotated out = mapper.readValue(json, Annotated.class);

        if (out._foo != 42) {
            throw new Error("Mismatch on reading back)");
        }
    }
}
