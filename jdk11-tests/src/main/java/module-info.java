module jackson.compat11test {
    // we get annotations, streaming, too, via databind:
    requires transitive com.fasterxml.jackson.annotation;
    requires transitive com.fasterxml.jackson.core; 
    requires transitive com.fasterxml.jackson.databind; 

    // but formats require explicit inclusion
    requires com.fasterxml.jackson.dataformat.avro;
    requires com.fasterxml.jackson.dataformat.cbor;
    requires com.fasterxml.jackson.dataformat.csv;
    //    requires com.fasterxml.jackson.dataformat.ion;
    requires com.fasterxml.jackson.dataformat.javaprop;
    requires com.fasterxml.jackson.dataformat.protobuf;
    requires com.fasterxml.jackson.dataformat.smile;
    requires com.fasterxml.jackson.dataformat.xml;
    requires com.fasterxml.jackson.dataformat.yaml;

   // and then some base modules as well
    requires java.xml.bind;
    requires com.fasterxml.jackson.module.afterburner;
    requires com.fasterxml.jackson.module.jaxb;
    requires com.fasterxml.jackson.module.mrbean;
    //TODO use named guice from com.jwebmp.jpms.guice - https://mvnrepository.com/artifact/com.jwebmp.inject/guice/0.67.0.12
	//Make provided so a different implementation can be used if need be
//    requires com.fasterxml.jackson.module.guice;

    // and datatypes
    requires com.fasterxml.jackson.datatype.joda;
    requires com.fasterxml.jackson.datatype.guava;
    requires transitive joda.time;

    // and finally open up types for tests
    exports com.fasterxml.jackson.compat11.testutil;
    opens com.fasterxml.jackson.compat11.testutil;
}
