public class GrassDinosaur extends PhotosynthesizingCreature implements Protector {
    protected String flowerType;
    public GrassDinosaur(String flowerType){
        this.flowerType = flowerType;

    }
    @Override
    public void doAction() {
        System.out.println("The dinosaur's flower release a pleasing aroma.");
    }
    @Override
    public void protect() {
            System.out.println("The dinosaur's flower released a protective spore!");
    }
}



