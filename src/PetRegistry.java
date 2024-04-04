
// import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
// import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PetRegistry {
   private static final Scanner scanner = new Scanner(System.in);

   private static Map<String, Animal> animals = new HashMap<>();

   // сделать другой класс
   public static void main(String[] args) {
      while (true) {
         System.out.println("---- Pet Registry ----"); // приветствие
         System.out.println("1. Add new pet"); // добавление животного
         System.out.println("2. Get pet details"); // добавлени деталей
         System.out.println("3. List all pets"); // вывод списка животных
         System.out.println("4. Teach pet new command"); // добавление новой команды, которое животное изучило
         System.out.println("5. Quit"); // выход
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

      System.out.println("Введите имя животного:");
      String name = scanner.nextLine();
      name = scanner.nextLine();

      if (isNameExists(name)) {
         System.out.println("Животное с таким именем уже существует. Попробуйте другое имя.");
         return;
      }

      System.out.println("Введите дату рождения животного в формате ГГГГ-ММ-ДД:");
      String birthday = scanner.nextLine();

      System.out.println("Введите тип животного (cat, dog, bird, hamster):");
      String type = scanner.nextLine();

      if (!type.equals("cat") && !type.equals("dog") && !type.equals("bird") && !type.equals("hamster")) {
         System.out.println("Недопустимый тип животного. Допустимые значения: cat, dog, bird, hamster.");
         return;
      }

      System.out.println("Введите команды, которые знает животное:");
      String commands = scanner.nextLine();

      String newAnimal = name + ";" + birthday + ";" + type + ";" + commands;

      try (FileWriter file = new FileWriter("animals.txt", true)) {
         file.write(newAnimal + System.lineSeparator());
         System.out.println("Животное успешно добавлено в файл.");
      } catch (IOException e) {
         System.out.println("Ошибка при добавлении животного в файл.");
         e.printStackTrace();
      }
   }

   private static boolean isNameExists(String name) {
      try (Scanner fileScanner = new Scanner(new File("animals.txt"))) {
         while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(";");
            if (parts[0].equals(name)) {
               return true;
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
      return false;
   }

   // System.out.println("Enter the pet's name:");
   // String name = scanner.nextLine();

   // System.out.println("Enter the pet's type (cat, dog, bird, humster):");
   // String type = scanner.nextLine();

   // System.out.println("Enter the pet's age:");
   // int age = Integer.parseInt(scanner.nextLine());

   // // Создаем JSON объект для нового питомца
   // JSONObject newPet = new JSONObject();
   // newPet.put("name", name);
   // newPet.put("type", type);
   // newPet.put("age", age);

   // // Пытаемся прочесть существующие данные из файла
   // JSONArray petsArray;
   // try {
   // JSONParser parser = new JSONParser();
   // FileReader fileReader = new FileReader("pets.json");
   // petsArray = (JSONArray) parser.parse(fileReader);
   // } catch (IOException | ParseException e) {
   // // Если файл не существует или пустой, создаем новый пустой массив
   // petsArray = new JSONArray();
   // }

   // // Добавляем нового питомца в массив
   // petsArray.add(newPet);

   // // Записываем обновленные данные в файл
   // try (FileWriter file = new FileWriter("pets.json")) {
   // file.write(petsArray.toJSONString());
   // System.out.println("Pet added successfully!");
   // } catch (IOException e) {
   // System.out.println("An error occurred while adding the pet.");
   // e.printStackTrace();
   // }
   // Animal animal = null;
   // switch (type) {
   // case "cat":
   // animal = new Cat(name);
   // break;
   // case "dog":
   // animal = new Dog(name);
   // break;
   // case "bird":
   // animal = new Bird(name);
   // break;
   // case "humster":
   // animal = new Humster(name);
   // break;
   // default:
   // System.out.println("Invalid pet type");
   // return;
   // }

   // animals.put(name, animal);
   // System.out.println("Pet successfully added");
   // }

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
      try (BufferedReader reader = new BufferedReader(new FileReader("animals.txt"))) {
         String line;
         while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
               String[] fields = line.split(";");
               System.out.println("_______________");
               System.out.println("name - " + fields[0]);
               System.out.println("birthday - " + fields[1]);
               System.out.println("type - " + fields[2]);
               System.out.println("commands - " + fields[3]);
            }
         }
         System.out.println("Список окончен");
      } catch (IOException e) {
         e.printStackTrace();
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

}
