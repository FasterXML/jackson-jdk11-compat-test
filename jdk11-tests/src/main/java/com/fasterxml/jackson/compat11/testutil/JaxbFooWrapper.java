package com.fasterxml.jackson.compat11.testutil;

import jakarta.xml.bind.annotation.XmlElement;

public class JaxbFooWrapper {
    @XmlElement(name = "value")
    public int _foo = 3;
}