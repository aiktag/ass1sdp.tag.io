public class PaymentManager {

    private static PaymentManager instance;

    private PaymentManager() {
    }

    public static PaymentManager getInstance() {
        if (instance == null) {
            instance = new PaymentManager();
        }
        return instance;
    }

    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount);
    }
}

