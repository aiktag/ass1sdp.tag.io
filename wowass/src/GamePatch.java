import java.util.ArrayList;
import java.util.List;

class GamePatch {
    private List<String> patches;
    private List<Observer> subscribers;

    public GamePatch() {
        this.patches = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public void addPatch(String vacancy) {
        patches.add(vacancy);
        notifyObservers();
    }

    public void removePatch(String patch) {
        patches.removeIf(v -> v.equals(patch));
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
            observer.handleEvent(patches);
        }
    }
}