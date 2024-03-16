import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class ClassTests {
    @Test
    public void exampleTest() {

        Assertions.assertEquals(2, 1 + 1);

    }

    @Test
    public void GetNameTest(){
        Person person = new Person("Park Chanyeol", 30);
        Assertions.assertEquals("Park Chanyeol", person.getName());
    }
    @Test
    public void GetAgeTest(){
        Person person = new Person("Park Chanyeol", 30);
        Assertions.assertEquals(30, person.getAge());
    }

    @Test
    public void addPreviousAddressTest(){
        Person person = new Person("Park Chanyeol", 30);
        person.addPreviousAddress("SM Entertainment 83-21, Wangsimni-ro, Seongdong-gu, Seoul, Republic of Korea");
        person.addPreviousAddress("14 12 Gagil Yangpyeong-ro, Yeongdeungpo-gu, Seoul, Korea");

        List<String> expectedADD = Arrays.asList("SM Entertainment 83-21, Wangsimni-ro, Seongdong-gu, Seoul, " +
                        "Republic of Korea", "14 12 Gagil Yangpyeong-ro, Yeongdeungpo-gu, Seoul, Korea");

        Assertions.assertEquals(expectedADD,person.getPreviousAddresses());

    }

    @Test
    public void BankAccBalTest(){
        Person person = new Person("Park Chanyeol", 30);

        Assertions.assertEquals(0.0, person.getBankAccBal(), 0.000);
    }

    @Test
    public void WorkTest(){
        Person person = new Person("Park Chanyeol", 30);
        person.work(5000.0);
        Assertions.assertEquals(5000.0, person.getBankAccBal(), 0.000);

    }
    @Test
    public void cents2dollarsTest(){
        int cents = 3000;
        double expectedDollars = 30.0;

        double actualDollars = Person.cent2dollars(cents);

        Assertions.assertEquals(expectedDollars, actualDollars, 0.001);

    }
}
