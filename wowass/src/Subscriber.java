import java.util.List;

class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void handleEvent(List<String> patches) {
        System.out.printf("Hello %s, we changed our patch list to %s\n\n", name, patches);
    }
}