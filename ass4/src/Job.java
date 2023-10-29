import java.util.ArrayList;
import java.util.List;

public class Job {
    private List<String> vacancies = new ArrayList<>();
    private List<Observer> subscribers = new ArrayList<>();

    public void addVacancy(String vacancy) {
        vacancies.add(vacancy);
        notifyObservers();
    }

    public void removeVacancy(String vacancy) {
        vacancies.removeIf(v -> v.equals(vacancy));
        notifyObservers();
    }

    public void addObserver(Observer observer) {
        subscribers.add(observer);
    }

    public void removeObserver(Observer observer) {
        subscribers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : subscribers) {
            observer.handleEvent(vacancies);
        }
    }
}
