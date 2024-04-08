import java.time.LocalDate;
import java.util.List;

class Bird extends Animal {

   public Bird(String name, String type, LocalDate birthday, List<String> commands) {
      super(name, type, birthday, commands);
   }

   @Override
   public void performCommands() {
      System.out.println("Птичка выполняет команды и поет");
      System.out.println("чик-чирик \n чик-чирик \n  чик-чирик");
   }
}