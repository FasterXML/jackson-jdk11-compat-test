package com.fasterxml.jackson.compat11.test.misc;

import com.fasterxml.jackson.compat11.test.BaseTest;
import com.fasterxml.jackson.compat11.testutil.FiveMinuteUser;
import com.fasterxml.jackson.compat11.testutil.JaxbFooWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JAXBTest extends BaseTest
{
    final ObjectMapper MAPPER = JsonMapper.builder()
            .addModule(new JaxbAnnotationModule())
            .build();

	@Test
    public void testBasic() throws Exception
    {
        FiveMinuteUser input = new FiveMinuteUser();
        String json = MAPPER.writeValueAsString(input);
        FiveMinuteUser out = MAPPER.readValue(json, FiveMinuteUser.class);
        assertNotNull(out);
    }
    @Test
    public void testWithJAXBAnnotations() throws Exception
    {
        JaxbFooWrapper input = new JaxbFooWrapper();
        input._foo = 42;
        String json = MAPPER.writeValueAsString(input);
        JaxbFooWrapper out = MAPPER.readValue(json, JaxbFooWrapper.class);
        assertNotNull(out);
        assertEquals(42, out._foo);

        // also verify name was used
        JsonNode root = MAPPER.readTree(json);
        assertTrue(root.has("value"));
        assertEquals(42, root.path("value").asInt());
    }
}
