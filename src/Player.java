public class Player {
    private String name;
    private chips chip;

    public Player(String name, String colour){
        this.name = name;
        chip = new chips(colour);
    };

    public String getName(){
      return name;
    };
    public chips getChip() {
        return chip;
    }
}
