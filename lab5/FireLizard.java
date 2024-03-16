public class FireLizard extends PhotosynthesizingCreature {
    protected double fireTemperatureInCelsius;
    public FireLizard(double fireTemperatureInCelsius) {
        this.fireTemperatureInCelsius = fireTemperatureInCelsius;
    }

    @Override
    public void doAction() {
        System.out.println("The lizard breathed fire!");
    }
}
