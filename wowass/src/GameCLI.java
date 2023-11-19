import java.util.Scanner;

public class GameCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the WOW game");
        System.out.println("Type 'help' to see available commands.");

        while (true) {
            System.out.print("Enter a command: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the Game.");
                break;
            }

            processCommand(input);
        }

        scanner.close();
    }

    private static void processCommand(String command) {
        switch (command.toLowerCase()) {
            case "play":
                playGame();
                break;
            case "observe":
                observePatches();
                break;
            case "equip":
                equipHero();
                break;
            case "applypatch":
                applyGamePatch();
                break;
            case "processpayment":
                processPayment();
                break;
            case "help":
                displayHelp();
                break;
            case "command":
                execute();
                break;
            default:
                System.out.println("Invalid command. Type 'help' for available commands.");
        }
    }

    private static void playGame() {
        WowPlayer wowPlayer = new WowPlayer();
        System.out.println("Enter race: ");
        String race = new Scanner(System.in).nextLine();
        System.out.println("Enter class: ");
        String playerClass = new Scanner(System.in).nextLine();
        wowPlayer.play(race, playerClass);
    }

    private static void observePatches() {
        Scanner scanner = new Scanner(System.in);

        Subscriber subscriber = new Subscriber("Player");

        GamePatch wowPatches = PatchFactory.createPatchWithInitialVacancies("cataclysm", "invoke");
        wowPatches.addObserver(subscriber);

        while (true) {
            System.out.print("Enter a command (add/remove/exit): ");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the Game Patch.");
                break;
            }

            processCommand(command, wowPatches);
        }
        scanner.close();
    }

    private static void processCommand(String command, GamePatch wowPatches) {
        Scanner scanner = new Scanner(System.in);

        switch (command.toLowerCase()) {
            case "add":
                System.out.print("Enter a patch to add: ");
                String addPatch = scanner.nextLine();
                wowPatches.addPatch(addPatch);
                break;
            case "remove":
                System.out.print("Enter a patch to remove: ");
                String removePatch = scanner.nextLine();
                wowPatches.removePatch(removePatch);
                break;
            default:
                System.out.println("Invalid command. Please enter 'add', 'remove', or 'exit'.");
        }
    }

    private static void equipHero() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Hero Equip");
        System.out.println("Choose how to equip your hero:");

        Hero basicHero = new BasicHero();

        while (true) {
            System.out.println("1. Equip Armor");
            System.out.println("2. Equip Cloak");
            System.out.println("3. Equip Armor and Cloak");
            System.out.println("4. Exit");

            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Hero heroWithArmor = new ArmorDecorator(basicHero);
                    heroWithArmor.equip();
                    break;
                case 2:
                    Hero heroWithCloak = new CloakDecorator(basicHero);
                    heroWithCloak.equip();
                    break;
                case 3:
                    Hero heroWithArmorAndCloak = new CloakDecorator(new ArmorDecorator(basicHero));
                    heroWithArmorAndCloak.equip();
                    break;
                case 4:
                    System.out.println("Exiting the Hero Equip.");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }


    private static void applyGamePatch() {
        String[] args = new String[0];
        if (args.length == 0) {
            System.out.println("<Wraith of Lich King> <Battle on Azeroth>");
            return;
        }

        for (String arg : args) {
            try {
                int patchNumber = Integer.parseInt(arg);
                GamePatches patch = GamePatchFactory.createPatch(patchNumber);
                patch.applyPatch();
            } catch (NumberFormatException e) {
                System.out.println("Invalid patch number: " + arg);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void processPayment() {
        System.out.println("Enter the payment amount: ");
        double amount = new Scanner(System.in).nextDouble();
        PaymentManager paymentManager = PaymentManager.getInstance();
        paymentManager.processPayment(amount);
    }
    private static void execute() {
        Command moveCommand = new MoveCommand("go direct");
        Command attackCommand = new AttackCommand();

        InputHandler inputHandler = new InputHandler();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Move");
            System.out.println("2. Attack");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    inputHandler.setCommand(moveCommand);
                    inputHandler.processInput();
                    break;
                case 2:
                    inputHandler.setCommand(attackCommand);
                    inputHandler.processInput();
                    break;
                case 3:
                    System.out.println("Exiting game.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void displayHelp() {
        System.out.println("Available Commands:");
        System.out.println("- play: Play the game");
        System.out.println("- observe: Observe game patches");
        System.out.println("- equip: Equip hero");
        System.out.println("- command. play as hero");
        System.out.println("- applypatch: Apply game patch");
        System.out.println("- processpayment: Process payment");
        System.out.println("- help: Display available commands");
        System.out.println("- exit: Exit the Game");
    }
}
