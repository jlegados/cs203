import java.util.Objects;

public class BigIntegerLinkedList {
    private Node head;

    public BigIntegerLinkedList(Node node) {
        head = node;
    }
    public BigIntegerLinkedList(String number) {
        for (char digit : number.toCharArray()) {
            head = new Node(Character.getNumericValue(digit), head);
        }
    }

    public Node getHead() {
        return head;
    }

    public static BigIntegerLinkedList add(BigIntegerLinkedList num1, BigIntegerLinkedList num2) {
        Node result = null;
        Node curresult = null;
        Node curnum1 = num1.head;
        Node curnum2 = num2.head;
        int carrying = 0;

        while (curnum1 != null || curnum2 != null) {
            if (curnum1 != null && curnum2 != null) {
                int[] singledigit = addSingleDigit(curnum1.getValue(), curnum2.getValue(), carrying);
                curnum1 = curnum1.getNext();
                curnum2 = curnum2.getNext();
                carrying = singledigit[1];
                Node temp = new Node(singledigit[0], null);
                if (result == null) {
                    result = temp;
                    curresult = temp;

                } else {
                    curresult.setNext(temp);
                    curresult = curresult.getNext();
                }
            } else if (curnum1 == null) {
                int[] singledigit = addSingleDigit(0, curnum2.getValue(), carrying);
                curnum2 = curnum2.getNext();
                carrying = singledigit[1];
                Node temp = new Node(singledigit[0], null);
                if (result == null) {
                    result = temp;
                    curresult = temp;

                } else {
                    curresult.setNext(temp);
                    curresult = curresult.getNext();
                }
            } else {
                int[] singledigit = addSingleDigit(curnum1.getValue(), 0, carrying);
                curnum1 = curnum1.getNext();
                carrying = singledigit[1];
                Node temp = new Node(singledigit[0], null);
                if (result == null) {
                    result = temp;
                    curresult = temp;

                } else {
                    curresult.setNext(temp);
                    curresult = curresult.getNext();
                }
            }
        }

        if (carrying != 0) {
            curresult.setNext(new Node(1, null));

        }

        return new BigIntegerLinkedList(result);
    }

    public static int[] addSingleDigit(int num1, int num2, int carrying) {
        int sum = num1 + num2 + carrying;
        int result = sum % 10;
        int newCarrying = sum / 10;

        int[] resultArray = {result, newCarrying};
        return resultArray;

    }

    public static BigIntegerLinkedList multiplication(BigIntegerLinkedList num1, BigIntegerLinkedList num2) {
        Node curnum2 = num2.head;
        BigIntegerLinkedList result = new BigIntegerLinkedList("0");
        int counter = 0;
        while (curnum2 != null) {
            Node onedigit = multiplyOneDigit(num1.head, curnum2.getValue());
            curnum2 = curnum2.getNext();
            for (int i = 0; i < counter; i++) {
                onedigit = new Node(0, onedigit);
            }
            result = add(result, new BigIntegerLinkedList(onedigit));
            counter++;


        }
        return result;
    }

    public static Node multiplyOneDigit(Node num1, int num2) {
        Node current = num1;
        Node result = null;
        Node curresult = null;
        int carrying = 0;
        while (current != null) {
            int temp_result = current.getValue() * num2 + carrying;
            int result2 = temp_result % 10;
            carrying = temp_result / 10;
            Node temp = new Node(result2, null);
            current = current.getNext();
            if (result == null) {
                result = temp;
                curresult = temp;
            } else {
                curresult.setNext(temp);
                curresult = curresult.getNext();

            }

        } if (carrying != 0){
            curresult.setNext(new Node(carrying, null));
        }
        return result;
    }

    public static BigIntegerLinkedList exponentiation(BigIntegerLinkedList base, int exponent) {
        if (exponent == 0) {
            return new BigIntegerLinkedList("1");
        }
        if (exponent == 1) {
            return base;
        }
        BigIntegerLinkedList squared = multiplication(base, base);

        if (exponent % 2 == 1) {
            int newExpo = (exponent - 1) / 2;
            BigIntegerLinkedList result = multiplication(base, exponentiation(squared, newExpo));
            return result;
        } else {
            int newExpo = exponent / 2;
            BigIntegerLinkedList result = exponentiation(squared, newExpo);
            return result;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BigIntegerLinkedList that)) return false;
        return Objects.equals(getHead(), that.getHead());
    }

    public String toString(){
        Node current = head;
        String result = "";
        while(current != null){
            result = current.getValue()+result;
            current = current.getNext();


        }
        return result;
    }


}




