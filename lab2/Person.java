import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int age;
    private List<String> previousAddresses;

    private double bankAccBal;


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.previousAddresses = new ArrayList<>();
        this.bankAccBal = 0.0;
    }


    public void addPreviousAddress(String address) {
        previousAddresses.add(address);

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getPreviousAddresses() {
        return previousAddresses;
    }

    public double getBankAccBal() {
        return bankAccBal;
    }

    public void work(double income) {
        bankAccBal += income;
    }

    public static double cent2dollars(int cents){
        return cents / 100.0;
    }
}

