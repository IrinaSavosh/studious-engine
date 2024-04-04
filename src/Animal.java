import java.util.ArrayList;
import java.util.List;

abstract class Animal {

   private String name;
   protected String type;
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
