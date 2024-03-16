public class WaterTurtle extends PhotosynthesizingCreature implements Protector {

    @Override
    public void doAction() {
        System.out.println("The turtle sprayed a burst of bubbles.");
    }
    @Override
    public void protect() {
        System.out.println("The turtle withdrew into its shell for protection.");
    }
}
