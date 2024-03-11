package game24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalTest {

    private Rational r1 = new Rational(1, 2);
    private Rational r2 = new Rational(5, 10);
    private Rational r3 = new Rational(5, 2);
    private Rational r4 = new Rational(3, 7);

    @Test
    void add() {
        assertEquals(r1.add(r2), new Rational(1, 1));
        assertEquals(r3.add(r4), new Rational(41, 14));
    }

    @Test
    void sub() {
        assertEquals(r1.sub(r2), new Rational(0, 1));
        assertEquals(r3.sub(r4), new Rational(29, 14));
    }

    @Test
    void mul() {
        Rational r = r1.mul(r2);
        System.out.println(r.getNumerator());
        System.out.println(r.getDenominator());
        assertEquals(r1.mul(r2), new Rational(1, 4));
        assertEquals(r3.mul(r4), new Rational(15, 14));
    }

    @Test
    void div() {
        assertEquals(r1.div(r2), new Rational(1, 1));
        assertEquals(r3.div(r4), new Rational(35, 6));
    }

    @Test
    void getReciprocal() {
        assertEquals(r1.getReciprocal(), new Rational(2, 1));
        assertEquals(r2.getReciprocal(), new Rational(10, 5));
    }

    @Test
    void equalsInt() {
        assertTrue(new Rational(10, 5).equalsInt(2));
    }
}