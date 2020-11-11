package comp1110.exam;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q5AddressTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    public static final int HASH_ITERATIONS = 100;

    static Q5Address[] addresses = new Q5Address[200 * HASH_ITERATIONS];

    @Test
    public void testHashCodeDeterministic() {
        checkNotObjectHashCode();
        for (Q5Address addr : addresses) {
            int hash = addr.hashCode();
            for (int i = 0; i < 10; i++) {
                int hash2 = addr.hashCode();
                assertTrue("address " + addr + "  returned different values for hashCode(): " + hash + ", " + hash2, hash == hash2);
            }
        }
    }

    @Test
    public void testAllFields() {
        checkNotObjectHashCode();
        Q5Address addr1 = new Q5Address(108, "Main St", 2601);
        Q5Address addr2 = new Q5Address(108, "Main St", 2903);
        testDifferent(addr1, addr2);
        testDifferent(addr1, addr2);
        addr2 = new Q5Address(2601, "Main St", 108);
        testDifferent(addr1, addr2);
        testDifferent(addr1, addr2);
        addr2 = new Q5Address(109, "Main St", 2601);
        testDifferent(addr1, addr2);
        testDifferent(addr1, addr2);
        addr2 = new Q5Address(108, "North Road", 2601);
        testDifferent(addr1, addr2);
        testDifferent(addr1, addr2);
        addr2 = new Q5Address(108, "Stain M", 2601);
        testDifferent(addr1, addr2);
        testDifferent(addr1, addr2);
    }

    @Test
    public void testEquals() {
        Q5Address addr1 = new Q5Address(108, "Main St", 2601);
        assertTrue("addr1.equals(addr1) returned false", addr1.equals(addr1));
        Q5Address addr2 = new Q5Address(108, "Main St", 2903);
        assertFalse("addresses " + addr1 + " and " + addr2 + " are not equal, but addr1.equals(addr2) returned true", addr1.equals(addr2));
        addr2.streetNumber = 109;
        addr2.postCode = 2601;
        assertFalse("addresses " + addr1 + " and " + addr2 + " are not equal, but addr1.equals(addr2) returned true", addr1.equals(addr2));
        addr2.streetNumber = 108;
        addr2.streetName = "Stain M";
        assertFalse("addresses " + addr1 + " and " + addr2 + " are not equal, but addr1.equals(addr2) returned true", addr1.equals(addr2));
        addr2.streetName = null;
        assertFalse("addresses " + addr1 + " and " + addr2 + " are not equal, but addr1.equals(addr2) returned true", addr1.equals(addr2));
        addr1.streetName = null;
        assertTrue("addresses " + addr1 + " and " + addr2 + " are equal, but addr1.equals(addr2) returned false", addr1.equals(addr2));
        addr2.streetName = "Main St";
        assertFalse("addresses " + addr1 + " and " + addr2 + " are not equal, but addr1.equals(addr2) returned true", addr1.equals(addr2));
        addr1.streetName = "Main St";
        assertTrue("addresses " + addr1 + " and " + addr2 + " are equal, but addr1.equals(addr2) returned false", addr1.equals(addr2));
    }

    @Test
    public void testUniformA() {
        checkNotObjectHashCode();
        // chiSquared critical value (DOF=10-1, prob=0.999) ~= 27.88
        testUniformity(10, 27.88);
    }

    @Test
    public void testUniformB() {
        checkNotObjectHashCode();
        // chiSquared critical value (DOF=50-1, prob=0.999) ~= 85.35
        testUniformity(50, 85.35);
    }

    private void testUniformity(int buckets, double chiSqCriticalValue) {
        Random r = new Random();
        int[] count = new int[buckets];
        int samples = buckets * HASH_ITERATIONS;
        for (int i = 0; i < samples; i++) {
            Q5Address addr = addresses[r.nextInt(addresses.length)];
            int h = Math.abs(addr.hashCode()) % buckets;
            count[h]++;
        }

        double chiSq = chiSquaredUniform(samples, count);
        assertTrue("Distribution of hash function doesn't appear to be uniform over " + buckets + " buckets (chi squared value of " + chiSq + ").\nExpected " + samples / buckets + " elements per bucket, but got " + Arrays.toString(count), chiSq < chiSqCriticalValue);
    }

    private void checkNotObjectHashCode() {
        Random r = new Random();
        int range = 39;
        Consumer<Function<Q5Address, Integer>> checkHash = (Function<Q5Address, Integer> hashFunction) -> {
            Set<Q5Address>[] myBuckets = new Set[range];
            Set<Q5Address>[] defaultBuckets = new Set[range];
            for (int i = 0; i < range; i++) {
                myBuckets[i] = new HashSet<>();
                defaultBuckets[i] = new HashSet<>();
            }
            for (int i = 0; i < 98 * HASH_ITERATIONS; i++) {
                Q5Address addr = addresses[r.nextInt(addresses.length)];
                int m = Math.abs(addr.hashCode()) % range;
                myBuckets[m].add(addr);
                int n = Math.abs(hashFunction.apply(addr)) % range;
                defaultBuckets[n].add(addr);
            }
            for (Set<Q5Address> myBucket : myBuckets) {
                for (Set<Q5Address> defaultBucket : defaultBuckets) {
                    assertFalse("It looks like you're using Object.hashCode() or Objects.hash()!", myBucket.equals(defaultBucket));
                }
            }
        };

        checkHash.accept((Q5Address addr) -> addr.passThroughHash());
        checkHash.accept((Q5Address addr) -> Objects.hash(addr.streetNumber, addr.streetName, addr.postCode));
        checkHash.accept((Q5Address addr) -> Objects.hash(addr.streetNumber, addr.postCode, addr.streetName));
        checkHash.accept((Q5Address addr) -> Objects.hash(addr.streetName, addr.postCode, addr.streetNumber));
        checkHash.accept((Q5Address addr) -> Objects.hash(addr.streetName, addr.streetNumber, addr.postCode));
        checkHash.accept((Q5Address addr) -> Objects.hash(addr.postCode, addr.streetNumber, addr.streetName));
        checkHash.accept((Q5Address addr) -> Objects.hash(addr.postCode, addr.streetName, addr.streetNumber));

    }

    private void testDifferent(Q5Address addr1, Q5Address addr2) {
        int hash1 = addr1.hashCode();
        int hash2 = addr2.hashCode();
        assertTrue("addresses " + addr1 + " and " + addr2 + " returned same hashCode(): " + hash1 + ", " + hash2, hash1 != hash2);
    }

    @BeforeClass
    public static void generateAddresses() {
        Random r = new Random(0);
        for (int i = 0; i < addresses.length; i++) {
            char[] randomName = new char[r.nextInt(5) + 3];
            for (int c = 0; c < randomName.length; c++) {
                if (c == 0) {
                    randomName[c] = (char) (r.nextInt(90 - 65) + 'A');
                } else {
                    randomName[c] = (char) (r.nextInt(123 - 97) + 'a');
                }
            }

            addresses[i] = new Q5Address(r.nextInt(10), String.valueOf(randomName), r.nextInt(100));
            System.out.println(addresses[i]);
        }
    }

    private static double chiSquaredUniform(int samples, int[] counts) {
        double uniformProb = 1.0 / counts.length;
        double total = 0;
        for (int count : counts) {
            double mi = ((double) samples) * uniformProb;
            total += ((double) count - mi) * ((double) count - mi) / mi;
        }
        return total;
    }


}
