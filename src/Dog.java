import java.time.LocalDate;
import java.util.List;

class Dog extends Animal {


   public Dog(String name, String type, LocalDate birthday, List<String> commands) {
      super(name, type, birthday, commands);
   }

   @Override
   public void performCommands() {
      System.out.println("Собака выполняет команды и лает");
      System.out.println("гав-гав-гав \n гав-гав-гав \n  гав-гав-гав");
   }

}