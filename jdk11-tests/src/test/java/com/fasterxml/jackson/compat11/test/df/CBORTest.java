package com.fasterxml.jackson.compat11.test.df;

import java.util.*;

import com.fasterxml.jackson.compat11.test.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class CBORTest extends BaseTest
{
    @SuppressWarnings("serial")
    @Test
    public void testSimple() throws Exception
    {
        ObjectMapper mapper = newMapper(new CBORFactory());
        byte[] data = mapper.writeValueAsBytes(new HashMap<String,String>() { });

        Map<?,?> output = mapper.readValue(data, Map.class);
        assertEquals(0, output.size());
    }
}
