public class GameAdapt implements Wow {
    Wowcataclysm wowcataclysm;

    public GameAdapt(String side) {
        if (side.equalsIgnoreCase("neutral")) {
            this.wowcataclysm = new Wowneutral();
        } else if (side.equalsIgnoreCase("dire")) {
            this.wowcataclysm = new Wowdire();
        }

    }

    public void play(String side, String team) {
        if (side.equalsIgnoreCase("neutral")) {
            this.wowcataclysm.playneutral(team);
        } else if (team.equalsIgnoreCase("dire")) {
            this.wowcataclysm.playdire(team);
        }

    }
}
