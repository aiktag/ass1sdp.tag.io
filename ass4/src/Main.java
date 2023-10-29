import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JobFactory jobFactory = new JobFactory();
        Job pythonVacancy = jobFactory.createJobWithInitialVacancies("junior", "middle");

        Subscriber subscriberOne = new Subscriber("Aikyn");
        Subscriber subscriberTwo = new Subscriber("Tagich");

        pythonVacancy.addObserver(subscriberOne);
        pythonVacancy.addObserver(subscriberTwo);

        pythonVacancy.addVacancy("senior");
        pythonVacancy.removeVacancy("senior");
    }
}
