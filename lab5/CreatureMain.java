public class CreatureMain {
    public static void main(String[] args) {
        // Change to constructor calls
        Creature dinosaur = new GrassDinosaur("Roses"); // Typecast as needed
        Creature lizard = new FireLizard(400.0); // Typecast as needed
        Creature turtle = new WaterTurtle(); // Typecast as needed

        dinosaur.doAction();
        lizard.doAction();
        turtle.doAction();

        ((Protector) dinosaur).protect();
        ((Protector) turtle).protect();

        ((PhotosynthesizingCreature) dinosaur).photosynthesize(); // Typecast as needed
        ((PhotosynthesizingCreature) lizard).photosynthesize(); // Typecast as needed
        // turtle.photosynthesize(); // Comment out
    }
}
