public class WowPlayer implements Wow {
    GameAdapt gameAdapt;

    public void play(String side, String team) {

        if (side.equalsIgnoreCase("radiant")) {
            System.out.println("Playing radiant side, as " + team);
        } else if (side.equalsIgnoreCase("neutral") || side.equalsIgnoreCase("dire")) {
            gameAdapt = new GameAdapt(side);
            gameAdapt.play(side, team);
        } else {
            System.out.println("Invalid side");
        }
    }
}
