# Overview

Simple test suite, aim of which is to guarantee that Jackson components declare and use
Java Module info (added in JDK 9) properly.

## JLink / JMod compatible 
The JLink profile tests and confirms if the module path can be built by the Java Modular System, and be used in the newer packaging techniques such as JMod, JLink and JPackage.

### Notes
In order to compile the libraries from guicedee.com have been used

## TODO in Jackson Modules
* Guava
  * requires static guava
  * requires static com.google.common

## Not Compatible with JLink
The below items require the base dependencies to be updated in order to compile into JLink artifacts
* MrBean
* Joda

## Status

[![Build Status](https://travis-ci.org/FasterXML/jackson-jdk11-compat-test.svg)](https://travis-ci.org/FasterXML/jackson-jdk11-compat-test)
