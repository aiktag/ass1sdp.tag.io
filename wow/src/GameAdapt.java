public class GameAdapt implements Wow {

    Wowcataclysm wowcataclysm;

    public GameAdapt(String side) {

        if (side.equalsIgnoreCase("neutral")) {
            wowcataclysm = new Wowneutral();

        } else if (side.equalsIgnoreCase("dire")) {
            wowcataclysm = new Wowdire();
        }
    }

    public void play(String side, String team) {

        if (side.equalsIgnoreCase("neutral")) {
            wowcataclysm.playneutral(team);
        } else if (team.equalsIgnoreCase("dire")) {
            wowcataclysm.playdire(team);
        }
    }
}
