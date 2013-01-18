/**
 *
 */
package com.google.code.morphia.mapping.validation.fieldrules;

import com.google.code.morphia.TestBase;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.annotations.Serialized;
import com.google.code.morphia.testutil.AssertedFailure;
import com.google.code.morphia.testutil.TestEntity;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Uwe Schaefer, (us@thomas-daily.de)
 */
@SuppressWarnings("unchecked")
public class MapNotSerializableTest extends TestBase {
    static class Map1 extends TestEntity {
        private static final long serialVersionUID = 1L;
        @Serialized
        private final Map<Integer, String> shouldBeOk = new HashMap();

    }

    static class Map2 extends TestEntity {
        private static final long serialVersionUID = 1L;
        @Reference
        private final Map<Integer, E1> shouldBeOk = new HashMap();

    }

    static class Map3 extends TestEntity {
        private static final long serialVersionUID = 1L;
        @Embedded
        private final Map<E2, Integer> shouldBeOk = new HashMap();

    }

    public static class E1 {

    }

    public static class E2 {

    }

    @Test
    public void testCheck() {
        morphia.map(Map1.class);

        new AssertedFailure() {
            public void thisMustFail() throws Throwable {
                morphia.map(Map2.class);
            }
        };

        new AssertedFailure() {
            public void thisMustFail() throws Throwable {
                morphia.map(Map3.class);
            }
        };
    }

}
