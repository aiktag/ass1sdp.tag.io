public class WowPlayer implements Wow {
    GameAdapt gameAdapt;

    public WowPlayer() {
    }

    public void play(String side, String team) {
        if (side.equalsIgnoreCase("radiant")) {
            System.out.println("Playing radiant side, as " + team);
        } else if (!side.equalsIgnoreCase("neutral") && !side.equalsIgnoreCase("dire")) {
            System.out.println("Invalid side");
        } else {
            this.gameAdapt = new GameAdapt(side);
            this.gameAdapt.play(side, team);
        }

    }
}