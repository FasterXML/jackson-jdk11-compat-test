package com.fasterxml.jackson.compat11.test.blackbird;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.blackbird.BlackbirdModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlackbirdCompatTest {

    private final String resourceName =
            (BlackbirdCompatTest.class.getName() + "$Data")
                .replace('.', '/').concat(".class");

    @Test
    public void loadInChildClassloader() throws Exception {
        TestLoader loader = new TestLoader(getClass().getClassLoader());
        Class<?> clazz = Class.forName(Data.class.getName(), true, loader);
        ObjectMapper mapper = new ObjectMapper().registerModule(new BlackbirdModule());
        Constructor<?> constructor = clazz.getConstructor(int.class);
        Object data = constructor.newInstance(42);
        assertEquals("{\"field\":42}", mapper.writeValueAsString(data));
    }

    public static class Data {
        private int field;

        public Data(int field) {
            this.field = field;
        }

        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }
    }

    public class TestLoader extends ClassLoader {
        public TestLoader(ClassLoader parent) {
            super(parent);
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                try {
                    Class<?> clazz;
                    if (Data.class.getName().equals(name)) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        InputStream in = getResource(resourceName).openStream();
                        int i;
                        while ((i = in.read()) != -1) {
                            baos.write(i);
                        }
                        byte[] bytes = baos.toByteArray();
                        clazz = defineClass(name, bytes, 0, bytes.length);
                    } else {
                        clazz = super.loadClass(name, resolve);
                    }
                    if (resolve) {
                        resolveClass(clazz);
                    }
                    return clazz;
                } catch (Exception e) {
                    throw new ClassNotFoundException("Unable to load class", e);
                }
            }
        }
    }
}
