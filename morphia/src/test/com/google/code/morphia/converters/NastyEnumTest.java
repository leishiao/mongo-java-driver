/**
 *
 */
package com.google.code.morphia.converters;

import com.google.code.morphia.TestBase;
import com.google.code.morphia.testutil.TestEntity;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Uwe Schaefer, (us@thomas-daily.de)
 */
public class NastyEnumTest extends TestBase {
    public enum NastyEnum {
        A {
            @Override
            public String toString() {
                return "Never use toString for other purposes than debugging";
            }
        },
        B {
            public String toString() {
                return "Never use toString for other purposes than debugging ";
            }
        }
    }

    static class NastyEnumEntity extends TestEntity {
        private static final long serialVersionUID = 1L;
        private final NastyEnum e1 = NastyEnum.A;
        private final NastyEnum e2 = NastyEnum.B;
        private final NastyEnum e3 = null;
    }

    @Test
    public void testNastyEnumPerisistence() throws Exception {
        NastyEnumEntity n = new NastyEnumEntity();
        ds.save(n);
        n = ds.get(n);
        Assert.assertSame(NastyEnum.A, n.e1);
        Assert.assertSame(NastyEnum.B, n.e2);
        Assert.assertNull(n.e3);
    }
}
