package com.fasterxml.jackson.compat11;

public class AllManualTests
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Start All tests....");

        // formats
        ManualFormatTest.main(args);

        // datatypes
        ManualDatatypesTest.main(args);

        // other modules
        ManualJAXBTest.main(args);

        System.out.println("... All tests complete!");
    }
}
