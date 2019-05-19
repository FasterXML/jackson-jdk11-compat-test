package com.fasterxml.jackson.test.misc;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.fasterxml.jackson.test.BaseTest;

public class JAXBTest extends BaseTest
{
    static class Annotated {
        @XmlElement(name = "value")
        public int _foo = 3;
    }

    final ObjectMapper MAPPER = JsonMapper.builder()
            .addModule(new JaxbAnnotationModule())
            .build();
    
    public void testBasic() throws Exception
    {
        FiveMinuteUser input = new FiveMinuteUser();
        String json = MAPPER.writeValueAsString(input);
        FiveMinuteUser out = MAPPER.readValue(json, FiveMinuteUser.class);
        assertNotNull(out);
    }

    public void testWithJAXBAnnotations() throws Exception
    {
        Annotated input = new Annotated();
        input._foo = 42;
        String json = MAPPER.writeValueAsString(input);
        Annotated out = MAPPER.readValue(json, Annotated.class);
        assertNotNull(out);
        assertEquals(42, out._foo);

        // also verify name was used
        JsonNode root = MAPPER.readTree(json);
        assertTrue(root.has("value"));
        assertEquals(42, root.path("value").asInt());
    }
}
