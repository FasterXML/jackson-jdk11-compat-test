package com.fasterxml.jackson.compat11;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;

public class ManualMrBeanTest
{
    public interface Point {
        public int getX();
        public int getY();
    }

    public static void main(String[] args) throws Exception
    {
        System.out.print("MrBean Test:");
        ObjectMapper mapper = new ObjectMapper()
            .registerModule(new MrBeanModule())
            ;
        Point p = mapper.readValue("{\"x\":1, \"y\":2}", Point.class);
        if ((1 != p.getX()) || (2 != p.getY())) {
            throw new Error("Error in reading back");
        }
        System.out.println(" COMPLETE");
    }
}
