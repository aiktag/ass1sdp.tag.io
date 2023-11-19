class GamePatchFactory {
    public static GamePatches createPatch(int patchNumber) {
        switch (patchNumber) {
            case 1:
                return new Patch1();
            case 2:
                return new Patch2();
            default:
                throw new IllegalArgumentException("Invalid patch number: " + patchNumber);
        }
    }
}