import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PetRegistry {

    private static final Scanner scanner = new Scanner(System.in);

    private static Map<String, Animal> animals = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("---- Pet Registry ----");
            System.out.println("1. Add new pet");
            System.out.println("2. Get pet details");
            System.out.println("3. List all pets");
            System.out.println("4. Teach pet new command");
            System.out.println("5. Quit");
            System.out.println("-----------------------");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addPet();
                    break;
                case 2:
                    getPetDetails();
                    break;
                case 3:
                    listAllPets();
                    break;
                case 4:
                    teachPetNewCommand();
                    break;
                case 5:
                    quit();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void addPet() {
        System.out.println("Enter the pet's name:");
        String name = scanner.nextLine();

        System.out.println("Enter the pet's type (cat, dog, bird):");
        String type = scanner.nextLine();

        Animal animal = null;
        switch (type) {
            case "cat":
                animal = new Cat(name);
                break;
            case "dog":
                animal = new Dog(name);
                break;
            case "bird":
                animal = new Bird(name);
                break;
            default:
                System.out.println("Invalid pet type");
                return;
        }

        animals.put(name, animal);
        System.out.println("Pet successfully added");
    }

    private static void getPetDetails() {
        System.out.println("Enter the pet's name:");
        String name = scanner.nextLine();

        if (!animals.containsKey(name)) {
            System.out.println("Pet not found");
            return;
        }

        Animal animal = animals.get(name);
        System.out.println("Pet details:");
        System.out.println("Name: " + animal.getName());
        System.out.println("Type: " + animal.getType());
        System.out.println("Commands: " + animal.getCommands());
    }

    private static void listAllPets() {
        System.out.println("List of all pets:");
        for (Animal animal : animals.values()) {
            System.out.println(animal.getName() + " (" + animal.getType() + ")");
        }
    }

    private static void teachPetNewCommand() {
        System.out.println("Enter the pet's name:");
        String name = scanner.nextLine();

        if (!animals.containsKey(name)) {
            System.out.println("Pet not found");
            return;
        }

        Animal animal = animals.get(name);
        System.out.println("Enter the new command:");
        String command = scanner.nextLine();

        animal.addCommand(command);
        System.out.println("Command successfully added");
    }

    private static void quit() {
        System.out.println("Thank you for using the Pet Registry");
        scanner.close();
        System.exit(0);
    }

    private static abstract class Animal {

        private String name;
        private String type;
        private List<String> commands;

        public Animal(String name) {
            this.name = name;
            this.commands = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public List<String> getCommands() {
            return commands;
        }

        public void addCommand(String command) {
            commands.add(command);
        }

    }

    private static class Cat extends Animal {

        public Cat(String name) {
            super(name);
            this.type = "cat";
        }

    }

    private static class Dog extends Animal {

        public Dog(String name) {
            super(name);
            this.type = "dog";
        }

    }

    private static class Bird extends Animal {

        public Bird(String name) {
            super(name);
            this.type = "bird";
        }

    }

}
