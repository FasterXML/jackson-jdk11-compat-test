package com.fasterxml.jackson.compat11;

public class AllManualTests
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Start All tests....");
        System.out.println(" calling Format tests:");
        ManualFormatTest.main(args);
//        System.out.println(" calling Datatype tests:");
        System.out.println(" calling Other Module tests:");
        
        System.out.println("... All tests complete!");
    }
}
