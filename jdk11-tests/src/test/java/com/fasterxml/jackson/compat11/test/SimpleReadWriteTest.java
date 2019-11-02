package com.fasterxml.jackson.compat11.test;

import com.fasterxml.jackson.compat11.testutil.NamedPoint;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleReadWriteTest extends BaseTest
{
    /*
    /**********************************************************
    /* Test methods
    /**********************************************************
     */

    private final ObjectMapper MAPPER = newMapper();

    public void testReadWrite() throws Exception
    {
        NamedPoint np = new NamedPoint("Ziggy");
        np.p.x = 3;
        np.p.setY(72);
        
        String json = MAPPER.writeValueAsString(np);
        byte[] jsonB = MAPPER.writeValueAsBytes(np);

        NamedPoint npStr = MAPPER.readValue(json, NamedPoint.class);
        NamedPoint npB = MAPPER.readValue(jsonB, NamedPoint.class);

        assertEquals(3, npStr.p.x);
        assertEquals(3, npB.p.x);
    }

//    public void testElse() { throw new Error(); }
}
