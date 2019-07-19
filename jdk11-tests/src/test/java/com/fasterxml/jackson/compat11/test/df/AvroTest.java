package com.fasterxml.jackson.compat11.test.df;

import com.fasterxml.jackson.compat11.test.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;

public class AvroTest extends BaseTest
{
    @SuppressWarnings("serial")
    public void testSimple() throws Exception
    {
        ObjectMapper mapper = newMapper(new AvroFactory());

        // !!! 23-Feb-2016, tatu: Trivial to avoid using any Schema
        assertNotNull(mapper);
    }
}
