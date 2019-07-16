package com.fasterxml.jackson.compat11.testutil;

import javax.xml.bind.annotation.XmlElement;

public class JaxbFooWrapper {
    @XmlElement(name = "value")
    public int _foo = 3;
}