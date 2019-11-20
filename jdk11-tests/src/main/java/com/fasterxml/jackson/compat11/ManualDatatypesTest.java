package com.fasterxml.jackson.compat11;

import com.fasterxml.jackson.datatype.guava.GuavaModule;
import org.joda.time.DateTime;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.collect.ImmutableSortedSet;

public class ManualDatatypesTest
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Datatypes Test: start");
        System.out.print(" Guava test...");
        testGuava();
        System.out.println(" COMPLETE!");
        System.out.print(" Joda test...");
        testJoda();
        System.out.println(" COMPLETE!");
        System.out.println("Datatypes Test: COMPLETE");
    }

    public static class TimeWrapper {
        public DateTime time;
    }

    static void testJoda() throws Exception
    {
        final ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JodaModule());
        TimeWrapper input = new TimeWrapper();
        input.time = DateTime.now();
        String json = mapper.writeValueAsString(input);
        TimeWrapper out = mapper.readValue(json, TimeWrapper.class);
        // no guarantee timezone remains the same, but underlying timestamp should so:
        if (input.time.getMillis() != out.time.getMillis()) {
            throw new Error("Mismatch on read!");
        }
    }

    static void testGuava() throws Exception
    {
        final ObjectMapper mapper = new ObjectMapper()
                .registerModule(new GuavaModule())
		        ;
        ImmutableSortedSet<Integer> set = mapper.readValue("[5,1,2]",
                new TypeReference<ImmutableSortedSet<Integer>>() { });

        String json = mapper.writeValueAsString(set);
        if (!"[1,2,5]".equals(json)) {
            throw new Error("Mismatch on read!");
        }
    }
}
