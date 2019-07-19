package com.fasterxml.jackson.compat11.test.df;

import com.fasterxml.jackson.compat11.test.BaseTest;
import com.fasterxml.jackson.compat11.testutil.FiveMinuteUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class YAMLTest extends BaseTest
{
    public void testSimple() throws Exception
    {
        ObjectMapper mapper = new YAMLMapper();
        byte[] data = mapper.writeValueAsBytes(new FiveMinuteUser());

        FiveMinuteUser output = mapper.readValue(data, FiveMinuteUser.class);
        assertNotNull(output);
    }
}
