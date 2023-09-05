public class chips {
//    private Player player;
    private String colour;
    public chips(String colour)
    {
//        this.player = player;
        this.colour = colour;
    }
//
//    public Player getPlayer() {
//        return player;
//    }

    public String getColour() {
        return colour;
    }


    public static class  miniChips{
        int value;

        public miniChips(int value) {
            this.value = value;
        }
        
    }

}
