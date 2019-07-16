package com.fasterxml.jackson.compat11.test.dt;

import org.joda.time.DateTime;

import com.fasterxml.jackson.compat11.test.BaseTest;
import com.fasterxml.jackson.compat11.testutil.JodaDateTimeWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Simple tests to see that Joda module works too.
 */
public class JodaTest extends BaseTest
{
    final ObjectMapper MAPPER = newMapper();

    public void testSimpleJoda() throws Exception
    {
        JodaDateTimeWrapper input = new JodaDateTimeWrapper();
        input.time = DateTime.now();
        String json = MAPPER.writeValueAsString(input);
        JodaDateTimeWrapper out = MAPPER.readValue(json, JodaDateTimeWrapper.class);
        // no guarantee timezone remains the same, but underlying timestamp should so:
        assertEquals(input.time.getMillis(), out.time.getMillis());
    }
}
