import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCases {
    @Test
    public void BigIntegerLinkedListTest(){
        BigIntegerLinkedList IntLink = new BigIntegerLinkedList("4321");
        Node expected = new Node(1,new Node(2, new Node(3, new Node(4, null))));
        Assertions.assertEquals(expected, IntLink.getHead());
    }

    @Test
    public void BigIntegerLLAddTest(){
        BigIntegerLinkedList num1 = new BigIntegerLinkedList("213");
        BigIntegerLinkedList num2 = new BigIntegerLinkedList("412");
        Node expected = new Node(5, new Node(2, new Node(6, null)));
        BigIntegerLinkedList result = BigIntegerLinkedList.add(num1, num2);

        Assertions.assertEquals(expected,result.getHead());
    }
    @Test
    public void BigIntegerLLAddTest2(){
        BigIntegerLinkedList num1 = new BigIntegerLinkedList("13");
        BigIntegerLinkedList num2 = new BigIntegerLinkedList("412");
        Node expected = new Node(5, new Node(2, new Node(4, null)));
        BigIntegerLinkedList result = BigIntegerLinkedList.add(num1, num2);

        Assertions.assertEquals(expected,result.getHead());
    }
    @Test
    public void BigIntegerLLAddTest3(){
        BigIntegerLinkedList num1 = new BigIntegerLinkedList("413");
        BigIntegerLinkedList num2 = new BigIntegerLinkedList("41");
        Node expected = new Node(4, new Node(5, new Node(4, null)));
        BigIntegerLinkedList result = BigIntegerLinkedList.add(num1, num2);

        Assertions.assertEquals(expected,result.getHead());
    }

    @Test
    public void BigIntegerLLAddTest4Carry(){
        BigIntegerLinkedList num1 = new BigIntegerLinkedList("99");
        BigIntegerLinkedList num2 = new BigIntegerLinkedList("99");
        Node expected = new Node(8, new Node(9, new Node(1, null)));
        BigIntegerLinkedList result = BigIntegerLinkedList.add(num1, num2);

        Assertions.assertEquals(expected,result.getHead());
    }
    @Test
    public void BigIntegerLLAddTest5None(){
        BigIntegerLinkedList num1 = new BigIntegerLinkedList("0");
        BigIntegerLinkedList num2 = new BigIntegerLinkedList("0");
        Node expected = new Node(0,null);
        BigIntegerLinkedList result = BigIntegerLinkedList.add(num1, num2);

        Assertions.assertEquals(expected,result.getHead());
    }
    @Test
    public void BigIntegerLLMultiTest() {
        BigIntegerLinkedList num1 = new BigIntegerLinkedList("12");
        BigIntegerLinkedList num2 = new BigIntegerLinkedList("11");
        Node expected = new Node(2, new Node(3, new Node(1, null)));
        BigIntegerLinkedList result = BigIntegerLinkedList.multiplication(num1, num2);

        Assertions.assertEquals(expected, result.getHead());

    }
    @Test
    public void BigIntegerLLMultiTest2() {
        BigIntegerLinkedList num1 = new BigIntegerLinkedList("12");
        BigIntegerLinkedList num2 = new BigIntegerLinkedList("1");
        Node expected = new Node(2, new Node(1, null));
        BigIntegerLinkedList result = BigIntegerLinkedList.multiplication(num1, num2);

        Assertions.assertEquals(expected, result.getHead());

    }
    @Test
    public void BigIntegerLLMultiTest3() {
        BigIntegerLinkedList num1 = new BigIntegerLinkedList("1");
        BigIntegerLinkedList num2 = new BigIntegerLinkedList("13");
        Node expected = new Node(3, new Node(1, null));
        BigIntegerLinkedList result = BigIntegerLinkedList.multiplication(num1, num2);

        Assertions.assertEquals(expected, result.getHead());

    }

    @Test
    public void BigIntegerLLMultiTest4() {
        BigIntegerLinkedList num1 = new BigIntegerLinkedList("0");
        BigIntegerLinkedList num2 = new BigIntegerLinkedList("0");
        Node expected = new Node(0, null);
        BigIntegerLinkedList result = BigIntegerLinkedList.multiplication(num1, num2);

        Assertions.assertEquals(expected, result.getHead());

    }
    @Test
    public void BigIntegerLLEXPOTest() {
        BigIntegerLinkedList base = new BigIntegerLinkedList("2");
        int exponent = 4;
        BigIntegerLinkedList expected = new BigIntegerLinkedList("16");
        BigIntegerLinkedList result = BigIntegerLinkedList.exponentiation(base, exponent);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void BigIntegerLLEXPOTest2(){
        BigIntegerLinkedList base = new BigIntegerLinkedList("3");
        int exponent = 0;
        BigIntegerLinkedList expected = new BigIntegerLinkedList("1");
        BigIntegerLinkedList result = BigIntegerLinkedList.exponentiation(base, exponent);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void BigIntegerLLEXPOText3(){
        BigIntegerLinkedList base = new BigIntegerLinkedList("5");
        int exponent = 1;
        BigIntegerLinkedList expected = new BigIntegerLinkedList("5");
        BigIntegerLinkedList result = BigIntegerLinkedList.exponentiation(base, exponent);
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void BigIntegerLLEXPOText4(){
        BigIntegerLinkedList base = new BigIntegerLinkedList("10");
        int exponent = 3;
        BigIntegerLinkedList expected = new BigIntegerLinkedList("1000");
        BigIntegerLinkedList result = BigIntegerLinkedList.exponentiation(base, exponent);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void toStringTest(){
        BigIntegerLinkedList num = new BigIntegerLinkedList("929182");
        Assertions.assertEquals(num.toString(),"929182");
    }

    @Test
    public void toStringTest2(){
        BigIntegerLinkedList num = new BigIntegerLinkedList("2399");
        Assertions.assertEquals(num.toString(), "2399");
    }

    @Test
    public void toString3(){
        BigIntegerLinkedList num = new BigIntegerLinkedList("99");
        Assertions.assertEquals(num.toString(), "99");
    }


}
