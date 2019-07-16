package com.fasterxml.jackson.compat11.testutil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NamedPoint {
    final String name;

    public Point p;
    
    @JsonCreator
    public NamedPoint(@JsonProperty("name") String n) {
        name = n;
        p = new Point();
    }

    public String getName() {
        return name;
    }
}