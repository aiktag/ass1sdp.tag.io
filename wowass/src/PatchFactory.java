class PatchFactory {
    public static GamePatch createPatchWithInitialVacancies(String... initialVacancies) {
        GamePatch gamePatch = new GamePatch();
        for (String vacancy : initialVacancies) {
            gamePatch.addPatch(vacancy);
        }
        return gamePatch;
    }
}