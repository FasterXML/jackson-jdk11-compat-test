# Overview

Simple test suite, aim of which is to guarantee that Jackson components declare and use
Java Module info (added in JDK 9) properly.

## JLink / JMod compatible 
The JLink module tests and confirms if the module path can be built by the Java Modular System, and be used in the newer packaging techniques such as JMod, JLink and JPackage.

### Notes
In order to compile the libraries from guicedee.com have been used

## Not Compatible with JLink
The below items require the base dependencies to be updated in order to compile into JLink artifacts
* MrBean
* Joda

## Usage, setup

If you get an error like:

```
[INFO] --- maven-toolchains-plugin:1.1:toolchain (default) @ jackson-jdk11-compat-test ---
[INFO] Required toolchain: jdk [ vendor='oracle' version='1.11' ]
[ERROR] No toolchain found for type jdk
[ERROR] Cannot find matching toolchain definitions for the following toolchain types:
jdk [ vendor='oracle' version='1.11' ]
```

you will need to setup Maven Toolchain settings.
See f.ex: https://stackoverflow.com/questions/40354942/maven-build-error-after-setting-toolchain-right

## Status

[![Build Status](https://travis-ci.org/FasterXML/jackson-jdk11-compat-test.svg)](https://travis-ci.org/FasterXML/jackson-jdk11-compat-test)
