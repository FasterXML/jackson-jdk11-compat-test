package com.fasterxml.jackson.compat11;

import com.fasterxml.jackson.compat11.FiveMinuteUser.Gender;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.cbor.databind.CBORMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.ion.IonObjectMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.dataformat.protobuf.ProtobufMapper;
import com.fasterxml.jackson.dataformat.smile.databind.SmileMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Manually run tests, separate from unit tests, just so we can exercise Module loading
 * outside of IDE, tests.
 */
public class ManualFormatTest
{
    static <T> void testSimpleFormat(T input,
            ObjectMapper mapper) throws Exception
    {
        testFormat(input, mapper.readerFor(input.getClass()),
                mapper.writerFor(input.getClass()));
    }

    static <T> void testSchemaFormat(T input,
            ObjectMapper mapper, FormatSchema schema) throws Exception
    {
        testFormat(input,
                mapper.readerFor(input.getClass()).with(schema),
                mapper.writerFor(input.getClass()).with(schema));
    }
    
    static <T> void testFormat(T input,
            ObjectReader r, ObjectWriter w) throws Exception
    {
        System.out.println("Testing: format '"+r.getFactory().getFormatName()+"'");

        byte[] ser = w.writeValueAsBytes(input);
        T result = r.readValue(ser);

        if (!input.equals(result)) {
            throw new Error("Mismatch for reader of type "+r.getFactory().getFormatName());
        }
    }
    
    public static void main(String[] args) throws Exception
    {
        System.out.println("Start tests....");
        
        // start with plain old JSON
        final FiveMinuteUser.Name name = new FiveMinuteUser.Name("Billy-Bob", "Smith");
        final FiveMinuteUser input = new FiveMinuteUser(name.getFirst(), name.getLast(),
                true, Gender.MALE, new byte[] { 1, 2, 3, 4, 5 });

        // Text formats, non-schema:

        testSimpleFormat(input, new JsonMapper());
        testSimpleFormat(input, new JavaPropsMapper());
//        testSimpleFormat(input, new YAMLMapper());
        testSimpleFormat(input, new XmlMapper());

        // Binary formats, non-schema:
        testSimpleFormat(input, new CBORMapper());
        testSimpleFormat(input, new SmileMapper());
        testSimpleFormat(input, new IonObjectMapper());

        // Text formats, schema:
        { // bit special for CSV, as we need flat content 
            final CsvMapper mapper = new CsvMapper();
            testSchemaFormat(name, mapper, mapper.schemaFor(name.getClass()));
        }

        // Binary formats, schema:
        {
            final AvroMapper mapper = new AvroMapper();
            testSchemaFormat(input, mapper, mapper.schemaFor(input.getClass()));
        }
        {
            final ProtobufMapper mapper = new ProtobufMapper();
            testSchemaFormat(input, mapper, mapper.generateSchemaFor(input.getClass()));
        }
 
        System.out.println("Tests complete!");
    }
    
}
