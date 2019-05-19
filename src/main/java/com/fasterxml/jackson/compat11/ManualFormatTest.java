package com.fasterxml.jackson.compat11;

import com.fasterxml.jackson.compat11.FiveMinuteUser.Gender;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.cbor.databind.CBORMapper;
import com.fasterxml.jackson.dataformat.ion.IonObjectMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.dataformat.protobuf.ProtobufMapper;
import com.fasterxml.jackson.dataformat.smile.databind.SmileMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

/**
 * Manually run tests, separate from unit tests, just so we can exercise Module loading
 * outside of IDE, tests.
 */
public class ManualFormatTest
{
    static void testSimpleFormat(FiveMinuteUser input,
            ObjectMapper mapper) throws Exception
    {
        testFormat(input, mapper.readerFor(FiveMinuteUser.class),
                mapper.writerFor(FiveMinuteUser.class));
    }

    static void testSchemaFormat(FiveMinuteUser input,
            ObjectMapper mapper, FormatSchema schema) throws Exception
    {
        testFormat(input,
                mapper.readerFor(FiveMinuteUser.class).with(schema),
                mapper.writerFor(FiveMinuteUser.class).with(schema));
    }
    
    static void testFormat(FiveMinuteUser input,
            ObjectReader r, ObjectWriter w) throws Exception
    {
        System.out.println("Testing: format '"+r.getFactory().getFormatName()+"'");

        byte[] ser = w.writeValueAsBytes(input);
        FiveMinuteUser result = r.readValue(ser);

        if (!input.equals(result)) {
            throw new Error("Mismatch for reader of type "+r.getFactory().getFormatName());
        }
    }
    
    public static void main(String[] args) throws Exception
    {
        System.out.println("Start tests....");
        
        // start with plain old JSON
        final FiveMinuteUser input = new FiveMinuteUser("Billy-Bob", "Smith", true,
                Gender.MALE, new byte[] { 1, 2, 3, 4, 5 });

        // Text formats, non-schema:

        testSimpleFormat(input, new JsonMapper());
        testSimpleFormat(input, new JavaPropsMapper());
        testSimpleFormat(input, new YAMLMapper());
        testSimpleFormat(input, new XmlMapper());

        // Binary formats, non-schema:
        testSimpleFormat(input, new CBORMapper());
        testSimpleFormat(input, new SmileMapper());
        testSimpleFormat(input, new IonObjectMapper());

        // Text formats, schema:
        /*
        {
            final CsvMapper mapper = new CsvMapper();
            testSchemaFormat(input, mapper, mapper.schemaFor(input.getClass()));
        }
        */

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
