module jackson.compat11 {
    // we get annotations, streaming, too, via databind:
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind; 

    // but formats require explicit inclusion
    requires com.fasterxml.jackson.dataformat.avro;
    requires com.fasterxml.jackson.dataformat.cbor;
    requires com.fasterxml.jackson.dataformat.csv;
    requires com.fasterxml.jackson.dataformat.ion;
    requires com.fasterxml.jackson.dataformat.javaprop;
    requires com.fasterxml.jackson.dataformat.protobuf;
    requires com.fasterxml.jackson.dataformat.smile;
    requires com.fasterxml.jackson.dataformat.xml;
    requires com.fasterxml.jackson.dataformat.yaml;

   // and then some base modules as well
    requires java.xml.bind;
    requires com.fasterxml.jackson.module.jaxb;
    requires com.fasterxml.jackson.module.mrbean;

    // and datatypes
    requires com.fasterxml.jackson.datatype.joda;
    requires com.fasterxml.jackson.datatype.guava;

    // and finally open up types for tests
    //    export com.fasterxml.jackson.compat11;
    //    opens com.fasterxml.jackson.compat11;
}
