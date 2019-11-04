module jackson.compat11test {
    // we get annotations, streaming, too, via databind:
    requires transitive com.fasterxml.jackson.annotation;
    requires transitive com.fasterxml.jackson.core; 
    requires transitive com.fasterxml.jackson.databind; 

    // but formats require explicit inclusion
    requires com.fasterxml.jackson.dataformat.avro;
    requires com.fasterxml.jackson.dataformat.cbor;
    requires com.fasterxml.jackson.dataformat.csv;

    requires com.fasterxml.jackson.dataformat.javaprop;
    requires com.fasterxml.jackson.dataformat.xml;

    //requires compatible depedencies
	requires com.fasterxml.jackson.dataformat.yaml;
	requires com.fasterxml.jackson.dataformat.protobuf;
	requires com.fasterxml.jackson.dataformat.smile;
	//requires com.fasterxml.jackson.dataformat.ion;
	requires com.fasterxml.jackson.datatype.joda;

	requires static joda.time;

	requires com.fasterxml.jackson.module.afterburner;

	requires java.xml.bind;
   // and then some base modules as well

    requires com.fasterxml.jackson.module.jaxb;
    //requires com.fasterxml.jackson.module.mrbean;

    requires com.ctc.wstx;

    requires com.google.guice;
    requires com.google.common;

    //Todo Needs to go static guava, static com.google.common
	//requires com.fasterxml.jackson.datatype.guava;
	requires com.fasterxml.jackson.module.mrbean;

	// and finally open up types for tests
    exports com.fasterxml.jackson.compat11.testutil;
    opens com.fasterxml.jackson.compat11.testutil;
}