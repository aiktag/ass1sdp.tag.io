class MoveCommand implements Command {
    private String direction;

    public MoveCommand(String direction) {
        this.direction = direction;
    }

    @Override
    public void execute() {
        // Logic for moving the player in the specified direction
        System.out.println("Moving player " + direction);
    }
}