# WoW game
### Group: SE-2213 
### Student: Tagirov Aikyn 
### Project Overview:
#### About project
In my WoW Game Java project, I've implemented several design patterns to enhance the overall structure, maintainability, and extensibility of the code. Patterns were used as a functionality of the game and performed basic functions in the game such as payment, choosing the side to play for and much more.
#### Idea
The WoW Game Java project is a text-based simulation inspired by World of Warcraft. Users can create characters by choosing a race and class, engage with game patches, customize heroes with equipment, apply patches to the game, and interact with gameplay commands through a command-line interface. The project incorporates key Java programming concepts, including object-oriented design, design patterns (e.g., Singleton, Observer, Decorator), and command-line input handling. It offers hands-on practice for coding, simulation of game development aspects, and a creative space for personalizing the in-game experience. Ultimately, the project serves as an educational tool for learning Java, software design patterns, and command-line interface concepts.
#### Purpose

The purpose of my project is multifaceted. The project demonstrates key principles of object-oriented design, modularity, and extensibility. By incorporating various design patterns, including Singleton, Observer, Decorator, Factory Method, Command, and Adapter, it provides a practical understanding of these patterns in a real-world context.

Additionally, the project serves as a hands-on exploration of command-line interface (CLI) concepts, offering users the opportunity to interact with the game through textual commands. Simulating game development, it introduces basic game mechanics such as character creation, patch management, and gameplay interactions. The project encourages pattern recognition and application, allowing participants to recognize scenarios where design patterns can be effectively employed.

#### Objective
The objectives of the WoW Game Java project are to enhance participants' Java programming skills through hands-on coding practice and to deepen their understanding of key design patterns such as Singleton, Observer, Decorator, Adapter, Factory and Command. The project aims to simulate basic game development aspects, including character creation, patch management, and gameplay interactions, fostering pattern recognition and application in practical scenarios. It provides a creative space for users to personalize their in-game experience through hero customization and equipment choices. The interactive command-line interface design ensures an engaging user experience while reinforcing modular and extensible software design principles.

### Main body:
We employed design patterns in the WoW Game Java project to enhance the project's overall structure, maintainability, and flexibility. By implementing the Singleton pattern, we ensured a single instance of the PaymentManager, streamlining payment processing throughout the game. The Observer and Adapter pattern facilitated the management of game patches, allowing subscribers to react dynamically to changes. Additionally, patterns such as Decorator, Factory Method, and Command were used to achieve modular and extensible design, enabling easier customization of heroes, creation of game patches, and execution of various in-game commands. Overall, the strategic use of design patterns contributed to a more robust and scalable architecture for the project.
#### 1)Adapter design pattern
```java
interface Wow {
    void play(String side, String team);
}
public interface Wowcataclysm {
    void playneutral(String team);

    void playdire(String team);
}
public class Wowdire implements Wowcataclysm {
    public Wowdire() {
    }

    public void playneutral(String team) {
    }

    public void playdire(String team) {
        System.out.println("Playing dire side, as " + team);
    }
}
public class Wowneutral implements Wowcataclysm {
    public Wowneutral() {
    }

    public void playneutral(String team) {
        System.out.println("Playing neutral side, as " + team);
    }

    public void playdire(String team) {
    }
}
public class WowPlayer implements Wow {
    GameAdapt gameAdapt;

    public WowPlayer() {
    }

    public void play(String side, String team) {
        if (side.equalsIgnoreCase("radiant")) {
            System.out.println("Playing radiant side, as " + team);
        } else if (!side.equalsIgnoreCase("neutral") && !side.equalsIgnoreCase("dire")) {
            System.out.println("Invalid side");
        } else {
            this.gameAdapt = new GameAdapt(side);
            this.gameAdapt.play(side, team);
        }

    }
}
public class GameAdapt implements Wow {
    Wowcataclysm wowcataclysm;

    public GameAdapt(String side) {
        if (side.equalsIgnoreCase("neutral")) {
            this.wowcataclysm = new Wowneutral();
        } else if (side.equalsIgnoreCase("dire")) {
            this.wowcataclysm = new Wowdire();
        }

    }

    public void play(String side, String team) {
        if (side.equalsIgnoreCase("neutral")) {
            this.wowcataclysm.playneutral(team);
        } else if (team.equalsIgnoreCase("dire")) {
            this.wowcataclysm.playdire(team);
        }

    }
}

```
The provided code illustrates the Adapter design pattern, allowing the WowPlayer class to seamlessly interact with different sides of the game (radiant, dire, or neutral) through the use of the GameAdapt adapter. The GameAdapt class adapts the Wowcataclysm interface, allowing it to work with both Wowneutral and Wowdire classes. This enables the WowPlayer to play the game regardless of the specific side chosen.

#### Wow (Target Interface)
- `play(String side, String team)`

#### Wowcataclysm (Adaptee Interface)
- `playneutral(String team)`
- `playdire(String team)`

#### Wowdire and Wowneutral(Concrete Adaptee Classes) 
- `playneutral(String team)`
- `playdire(String team)`

#### WowPlayer (Client Class)
- `gameAdapt: GameAdapt`
- `play(String side, String team)`

#### GameAdapt (Adapter Class) 
- `wowcataclysm: Wowcataclysm`
- `GameAdapt(String side)`
- `play(String side, String team)`

![adapter](https://github.com/aiktag/ass1sdp.tag.io/blob/main/UML/AdapterUML.png)

### 2) Observer Design Pattern
```java
import java.util.List;

interface Observer {
    void handleEvent(List<String> patches);
}
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
class PatchFactory {
    public static GamePatch createPatchWithInitialVacancies(String... initialVacancies) {
        GamePatch gamePatch = new GamePatch();
        for (String vacancy : initialVacancies) {
            gamePatch.addPatch(vacancy);
        }
        return gamePatch;
    }
}
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
```
This code implements the Observer design pattern, allowing objects of the Observer type, such as Subscriber, to be notified of changes in the GamePatch class. The PatchFactory class provides a convenient method for creating a GamePatch with initial vacancies.

#### Observer (Observer Interface)
- `handleEvent(patches: List<String>)`
#### GamePatch class (Concrete Subject)
- `patches: List<String>`
- `subscribers: List<Observer>`
- `addPatch(vacancy: String)`
- `removePatch(patch: String) `
- `addObserver(observer: Observer)`
- `removeObserver(observer: Observer)`
- `notifyObservers()` 
#### Subscriber class (Concrete Observer)
- `name: String`
- `handleEvent(patches: List<String>)`
#### Patch Factory (Client Code)
- `createPatchWithInitialVacancies(initialVacancies: String...)`

![observer](https://github.com/aiktag/ass1sdp.tag.io/blob/main/UML/ObserverUML.png)

### 3) Decorator Design Pattern 
```java
interface Hero {
    void equip();
}
class BasicHero implements Hero {
    @Override
    public void equip() {
        System.out.println("Basic hero equipped");
    }
}
class ArmorDecorator extends HeroDecorator {
    public ArmorDecorator(Hero decoratedHero) {
        super(decoratedHero);
    }

    @Override
    public void equip() {
        super.equip();
        System.out.println("Equipping Armor");
    }
}
class CloakDecorator extends HeroDecorator {
    public CloakDecorator(Hero decoratedHero) {
        super(decoratedHero);
    }

    @Override
    public void equip() {
        super.equip();
        System.out.println("Equipping Cloak");
    }
}
abstract class HeroDecorator implements Hero {
    protected Hero decoratedHero;

    public HeroDecorator(Hero decoratedHero) {
        this.decoratedHero = decoratedHero;
    }

    public void equip() {
        decoratedHero.equip();
    }
}
```
This Java code implements the Decorator design pattern for a Hero interface and its concrete implementation (BasicHero). There are two decorator classes (ArmorDecorator and CloakDecorator) extending an abstract decorator class (HeroDecorator). 

#### Hero Interface (Interface)
- `equip()`
#### Basic Hero Class (Concrete Component Class)
- `equip()`
#### Hero Decorator (Abstract Decorator Class)
- `decoratedHero`
- `equip()`
#### ArmorDecorator and CloakDecorator classes (Concrete Decorator Classes)
- `equip()`

![decorator](https://github.com/aiktag/ass1sdp.tag.io/blob/main/UML/DecoratorUML.png)

### 4) Command Design Pattern
```java
interface Command {
    void execute();
}
class AttackCommand implements Command {
    @Override
    public void execute() {
        // Logic for player attacking
        System.out.println("Player attacking");
    }
}
class InputHandler {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void processInput() {

        command.execute();
    }
}
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

```
Code defines a Command interface with an execute method, and then provides two implementations: AttackCommand and MoveCommand.
#### Command (Command Interface)
- `execute()`
#### AttackCommand and MoveCommand classes (Concrete Command Classes)
- `execute()`
#### InputHandler Class (Invoker Class)
- `command:`
- `setCommand(command: Command)`
- `processInput()`

![command](https://github.com/aiktag/ass1sdp.tag.io/blob/main/UML/CommandUML.png)

### 5) Singleton Design Pattern
```java
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
```
The provided Java code represents a Singleton pattern for a PaymentManager class.
#### PaymentManager (Singleton Class)
- `instance:`
- `getInstance()`
- `processPayment(amount: double)`

![singleton](https://github.com/aiktag/ass1sdp.tag.io/blob/main/UML/SingletonUML.png)

### 6) Factory Design Pattern 
```java
interface GamePatches {
    void applyPatch();
}
class GamePatchFactory {
    public static GamePatches createPatch(int patchNumber) {
        switch (patchNumber) {
            case 1:
                return new Patch1();
            case 2:
                return new Patch2();
            default:
                throw new IllegalArgumentException("Invalid patch number: " + patchNumber);
        }
    }
}
class Patch1 implements GamePatches {
    @Override
    public void applyPatch() {
        System.out.println("Wraith of Lich King");
    }
}
class Patch2 implements GamePatches {
    @Override
    public void applyPatch() {
        System.out.println("Battle on Azeroth");
    }
}
```
The provided Java code implements a simple Factory Method pattern for creating instances of GamePatches. 
#### GamePatches interface (interface)
- `applyPatch()`
#### GamePatchFactory class (Factory Class)
- `createPatch(patchNumber: int)`
#### Patch1 and Patch2 classes (Concrete classes)
- `applyPatch()`

![factory](https://github.com/aiktag/ass1sdp.tag.io/blob/main/UML/FactoryUML.png)

### 7) Command Line Interface to execute
```java
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
```
The code represents a simple text-based game command-line interface (CLI).
### Conclusion:
The project is a text-based command-line interface (CLI) implementation of a WoW game.
It allows users to play the game, observe game patches, equip heroes, apply game patches, process payments, and execute various commands.
### Design Patterns:
#### Adapter Pattern:
- `Utilized the Adapter pattern to adapt the WowPlayer class to the GameAdapt class, enabling compatibility with different sides (neutral, dire).`
- `GameAdapt acts as an adapter, adapting the WowPlayer class to the Wowcataclysm interface.`
#### Observer Pattern:
- `Implemented the Observer pattern for observing game patches.`
- `The GamePatch class serves as the subject, and the Subscriber class acts as the observer.`
- `Subscribers are notified when game patches are added or removed.`
#### Decorator Pattern:
- `Applied the Decorator pattern for equipping heroes with different enhancements.`
- `Hero is the base component interface, while ArmorDecorator and CloakDecorator are concrete decorators.`
- `Decorators add specific functionalities (equipping armor, cloak) to the basic hero.`
#### Command Pattern:
- `Employed the Command pattern for handling player commands in the game.`
- `Command is the command interface, and MoveCommand and AttackCommand are concrete command implementations.`
- `InputHandler acts as the invoker, allowing the player to choose and execute commands.`
#### Singleton Pattern:
- `Implemented the Singleton pattern for the PaymentManager class.`
- `Ensures a single instance of the PaymentManager is created and provides a global point of access to it.`
#### Factory Pattern: 
- `Implemented the Factory Design Pattern for creating different types of game patches.`
- `Introduces a GamePatchFactory interface and a concrete implementation, ConcreteGamePatchFactory, that centralizes the creation of game patches.`
- `Concrete classes like CataclysmPatch and InvokePatch implement the GamePatch interface and are created by the factory based on the specified type.`
### Project Outcomes. Challanges faced.
Successfully implemented a modular and extensible WoW game in Java, incorporating key design patterns such as Factory, Adapter, Observer, Decorator, Command, and Singleton.
Achieved a well-organized code structure that enhances maintainability and flexibility, allowing for the addition of new features or modifications without major code changes. Adapting to and implementing multiple design patterns posed a challenge in terms of understanding the intricate interactions between different pattern implementations. Ensuring seamless integration between various components while maintaining the overall cohesion of the system required careful consideration of design choices. Debugging and resolving issues related to the adaptation of the WoW player to different sides (neutral, dire) in the Adapter pattern presented a specific challenge.
### Future improvements.
Future improvements could focus on improving the user interface and interaction within the command-line interface (CLI), providing a more intuitive and engaging experience for players. ntroducing additional game features, quests, or character customization options could enhance gameplay depth and provide players with more diverse and immersive experiences in the WoW game. Implementing a data storage solution, such as a database, would allow for the persistence of player progress, achievements, and in-game data, enabling a more comprehensive and personalized gaming experience.
