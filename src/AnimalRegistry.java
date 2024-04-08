import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AnimalRegistry {

   private List<Animal> animals;

   public AnimalRegistry() {
      this.animals = new ArrayList<>();
   }

   public void addAnimal(Animal animal) {
      this.animals.add(animal);

   }

   public Animal getAnimal(String name) {
      for (Animal animal : animals) {
         if (animal.getName().equals(name)) {
            return animal;
         }
      }
      return null;
   }

   public void printCommands(String animalName) {
      Animal animal = getAnimal(animalName);
      if (animal != null) {
         System.out.println("Список команд, которые выполняет " + animalName + ":");
         for (String command : animal.getCommands()) {
            System.out.println("- " + command);
         }
      } else {
         System.out.println("Животное с таким именем не найдено");
      }
   }

   public void teachAnimalCommand(String animalName, String command) {
      Animal animal = getAnimal(animalName);
      if (animal != null) {
         List<String> commands = animal.getCommands();
         commands.add(command);
         animal.setCommands(commands);
         System.out.println("Животное " + animalName + " теперь умеет выполнять команду " + command);
      } else {
         System.out.println("Животное с таким именем не найдено");
      }
   }

   public static void main(String[] args) {
      AnimalRegistry registry = new AnimalRegistry();
      try (Counter counter = new Counter()) {
         Scanner scanner = new Scanner(System.in);

         while (true) {
            System.out.println("----------------------------------------------------");
            System.out.println("Выберите действие:");
            System.out.println("1. Завести новое животное");
            System.out.println("2. Определить животное в правильный класс");
            System.out.println("3. Увидеть список команд, которое выполняет животное");
            System.out.println("4. Обучить животное новым командам");
            System.out.println("5. Посмотреть, как питомец выполняет команды");
            System.out.println("6. Выход");
            System.out.println("----------------------------------------------------");

            int choice = scanner.nextInt();

            switch (choice) {
               case 1:
                  System.out.println("Введите имя животного:");
                  String name = scanner.next();
                  System.out.println("Введите тип животного (dog, cat, bird):");
                  String type = scanner.next();
                  System.out.println("Введите дату рождения животного (ГГГГ-ММ-ДД):");
                  String birthdayString = scanner.next();
                  LocalDate birthday = LocalDate.parse(birthdayString);
                  System.out.println("Введите список команд, которые выполняет животное (через пробел):");
                  String commandsString = scanner.nextLine();
                  commandsString = scanner.nextLine();
                  List<String> commands = new ArrayList<>();
                  if (!commandsString.isEmpty()) {
                     commands.addAll(Arrays.asList(commandsString.split(" ")));
                  }
                  Animal animal = null;
                  switch (type) {
                     case "dog":
                        animal = new Dog(name, type, birthday, commands);
                        break;
                     case "cat":
                        animal = new Cat(name, type, birthday, commands);
                        break;
                     case "bird":
                        animal = new Bird(name, type, birthday, commands);
                        break;
                  }
                  if (animal != null) {
                     registry.addAnimal(animal);
                     System.out.println("Животное успешно добавлено в реестр");
                     counter.setCount(animal.getCommands().size());
                     System.out.println("Количество команд, которое занет ваш питомец: " + counter.getCount());
                  } else {
                     System.out.println("Не удалось добавить животное в реестр");
                  }
                  break;
               case 2:
                  System.out.println("Введите имя животного:");
                  String animalName = scanner.next();
                  Animal animalToClassify = registry.getAnimal(animalName);
                  if (animalToClassify != null) {
                     System.out.println("Животное " + animalName + " определено как " + animalToClassify.getType());
                  } else {
                     System.out.println("Животное с таким именем не найдено");
                  }
                  break;
               case 3:
                  System.out.println("Введите имя животного:");
                  String animalNameForCommands = scanner.next();
                  registry.printCommands(animalNameForCommands);
                  break;
               case 4:
                  System.out.println("Введите имя животного:");
                  String animalNameForTeaching = scanner.next();
                  System.out.println("Введите команду, которую нужно обучить животному:");
                  String command = scanner.next();
                  registry.teachAnimalCommand(animalNameForTeaching, command);
                  counter.add();
                  // Запуск исключения

                  // Если необходимо проверить некорректность программы, нужно снять
                  // комментирование строки ниже
                  // counter.setCount(-100);
                  System.out.println("Количество команд, которое занет ваш питомец: " + counter.getCount());
                  break;
               case 5:
                  System.out.println("Введите имя животного:");
                  String animalNameForShow = scanner.next();
                  registry.getAnimal(animalNameForShow).performCommands();
                  break;
               case 6:
                  System.out.println("Выход из программы");
                  return;
               default:
                  System.out.println("Неверный выбор");
            }
         }
      } catch (IllegalStateException e) {
         System.err.println("Исключение: " + e.getMessage());
         return;
      }
   }
}