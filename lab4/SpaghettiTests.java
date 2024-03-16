import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpaghettiTests {
    // Insert your tests below

    @Test
    public void equalsWithNonSpaghettiObjectTest() {
        Spaghetti spaghetti = new Spaghetti(4.5, "Carbonara");
        String nonSpaghetti = "Non Spaghetti";
        Assertions.assertFalse(spaghetti.equals(nonSpaghetti));
    }

    @Test
    public void equalsWithNonSpaghettiObjectTest2() {
        Spaghetti spaghetti = new Spaghetti(4.5, "Carbonara");
        String nonSpaghetti = "4.5, Carbonara";
        Assertions.assertFalse(spaghetti.equals(nonSpaghetti));
    }

    @Test
    public void equalsWithNonSpaghettiObjectTest3(){
        Spaghetti spaghetti = new Spaghetti(4.5, "Carbonara");
        int nonSpaghetti = 2;
        Assertions.assertFalse(spaghetti.equals(nonSpaghetti));
    }
    @Test
    public void EqualsMethodtest() {
        Spaghetti spaghetti = new Spaghetti(4.5, "Carbonara");
        Spaghetti spaghetti2 = new Spaghetti(4.5, "Carbonara");
        Spaghetti spaghetti3 = new Spaghetti(5.3, "Marinara");
        Assertions.assertTrue(spaghetti.equals(spaghetti2));
        Assertions.assertFalse(spaghetti.equals(spaghetti3));
    }

    @Test
    public void EqualsMethodtest2() {
        Spaghetti spaghetti = new Spaghetti(6.5, "Pesto");
        Spaghetti spaghetti2 = new Spaghetti(6.5, "Pesto");
        Spaghetti spaghetti3 = new Spaghetti(6.5, "Pesto");
        Assertions.assertTrue(spaghetti.equals(spaghetti2));
        Assertions.assertTrue(spaghetti.equals(spaghetti3));
    }

    @Test
    public void hashCodeTest(){
        Spaghetti spaghetti = new Spaghetti(5.5, "Pesto");
        Spaghetti spaghetti2 = new Spaghetti(5.5, "Pesto");
        Spaghetti spaghetti3 = new Spaghetti(4.5, "Marinara");
        Assertions.assertTrue(spaghetti.hashCode() == spaghetti2.hashCode());
        Assertions.assertFalse(spaghetti3.hashCode() == spaghetti.hashCode());

    }

    @Test
    public void hashCodeTest2(){
        Spaghetti spaghetti = new Spaghetti(5.5, "Pesto");
        Spaghetti spaghetti2 = new Spaghetti(7.5, "Carbonara");
        Spaghetti spaghetti3 = new Spaghetti(4.5, "Marinara");
        Assertions.assertFalse(spaghetti.hashCode() == spaghetti2.hashCode());
        Assertions.assertFalse(spaghetti3.hashCode() == spaghetti.hashCode());

    }
    @Test
    public void toStringTest(){
        Spaghetti spaghetti = new Spaghetti(6.5, "Marinara");
        Spaghetti spaghetti2 = new Spaghetti(5.4, "Pesto");
        Assertions.assertTrue(spaghetti.toString().equals("6.5, Marinara"));
        Assertions.assertTrue(spaghetti2.toString().equals("5.4, Pesto"));

    }

    @Test
    public void toStringTest2(){
        Spaghetti spaghetti = new Spaghetti(6.5, "Marinara");
        Assertions.assertTrue(spaghetti.toString().equals("6.5, Marinara"));
        Assertions.assertFalse(spaghetti.toString().equals("5.4, Pesto"));

    }
    @Test
    public void StringifiableTest() {
        Spaghetti spaghetti1 = new Spaghetti(5.5, "Marinara");
        Spaghetti spaghetti2 = new Spaghetti(4.5, "Pesto");
        Assertions.assertTrue(spaghetti1.stringify().equals("5.5, Marinara"));
        Assertions.assertTrue(spaghetti2.stringify().equals("4.5, Pesto"));

    }

    @Test
    public void StringifiableTest2() {
        Spaghetti spaghetti1 = new Spaghetti(5.5, "Marinara");
        Spaghetti spaghetti2 = new Spaghetti(4.5, "Pesto");
        Assertions.assertTrue(spaghetti1.stringify().equals("5.5, Marinara"));
        Assertions.assertFalse(spaghetti2.stringify().equals("5.5, Pesto"));

    }


    // Insert your tests above

    @Test
    void testSpaghettiInstantiation() {
        try {
            Class<?> clazz = Class.forName("Spaghetti");
            Constructor<?> constructor = clazz.getConstructor(double.class, String.class);
            Object obj = constructor.newInstance(1, "");
            assertNotNull(obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            fail("Class not found or cannot be instantiated: " + e.getClass());
        }
    }
}