import java.util.List;

interface Observer {
    void handleEvent(List<String> patches);
}