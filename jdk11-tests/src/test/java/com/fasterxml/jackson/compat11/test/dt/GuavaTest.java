package com.fasterxml.jackson.compat11.test.dt;

import java.util.Iterator;

import com.fasterxml.jackson.compat11.test.BaseTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableSortedSet;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class GuavaTest extends BaseTest
{
    final ObjectMapper MAPPER = newMapper();
	@Test
    public void testImmutableSortedSet() throws Exception
    {
	    //TODO how does this work
        ImmutableSortedSet<Integer> set = MAPPER.readValue("[5,1,2]",
                new TypeReference<ImmutableSortedSet<Integer>>() { });
        assertEquals(3, set.size());
        Iterator<Integer> it = set.iterator();
        assertEquals(Integer.valueOf(1), it.next());
        assertEquals(Integer.valueOf(2), it.next());
        assertEquals(Integer.valueOf(5), it.next());

        String json = MAPPER.writeValueAsString(set);
        assertEquals("[1,2,5]", json);
    }
}
