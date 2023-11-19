class InputHandler {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void processInput() {

        command.execute();
    }
}