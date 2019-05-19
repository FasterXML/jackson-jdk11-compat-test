module jackson.compat11 {
    // we get annotations, streaming, too, via databind:
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
}
