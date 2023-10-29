import java.util.List;

class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void handleEvent(List<String> vacancies) {
        System.out.printf("Hello %s, we changed our vacancy list to %s\n\n", name, vacancies);
    }
}
