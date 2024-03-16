public class Spaghetti implements Stringifiable {
    private double noodleDiameter;
    private String sauce;

    public Spaghetti(double noodleDiameter, String sauce){
        this.noodleDiameter = noodleDiameter;
        this.sauce = sauce;
    }

    public String stringify(){
        return noodleDiameter +", " + sauce;
    }

    @Override
    public String toString() {
        return noodleDiameter + ", " + sauce;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // if the objects are the same
        if (obj == null) return false; // if the other object is null
        if (obj.getClass() != this.getClass()) {
            return false;
        } // if the other object is not an instance of Spaghetti
        Spaghetti spaghetti = (Spaghetti) obj;
        return Double.compare(this.noodleDiameter, spaghetti.noodleDiameter) == 0 &&
                this.sauce.equals(spaghetti.sauce);
    }

    @Override
    public int hashCode() {
        int nodiahash = Double.hashCode(noodleDiameter); //noodle diameter hash
        int sauhash = sauce.hashCode(); // sauce hash

        int result = 31 * nodiahash + sauhash; //multiplying 31 b/c prime numbers don't
        // divide evenly by anything so distribution will be random.
        return result;

    }
}
