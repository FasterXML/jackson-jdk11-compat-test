package com.fasterxml.jackson.test.misc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;
import com.fasterxml.jackson.test.BaseTest;

public class MrBeanTest extends BaseTest
{
    public interface Point {
        public int getX();
        public int getY();
    }

    public void testBasic() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper()
            .registerModule(new MrBeanModule())
            ;
        Point p = mapper.readValue("{\"x\":1, \"y\":2}", Point.class);
        assertEquals(1, p.getX());
        assertEquals(2, p.getY());
    }
}
