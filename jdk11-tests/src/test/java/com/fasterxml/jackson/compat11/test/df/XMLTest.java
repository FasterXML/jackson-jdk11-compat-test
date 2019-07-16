package com.fasterxml.jackson.compat11.test.df;

import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.compat11.test.BaseTest;
import com.fasterxml.jackson.compat11.testutil.FiveMinuteUser;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XMLTest extends BaseTest
{
    public void testSimple() throws Exception
    {
        // NOTE: enforce use of Woodstox, just in case
        XmlMapper mapper = new XmlMapper(new WstxInputFactory(),
                new WstxOutputFactory());
        byte[] data = mapper.writeValueAsBytes(new FiveMinuteUser());

        FiveMinuteUser output = mapper.readValue(data, FiveMinuteUser.class);
        assertNotNull(output);
    }
}
