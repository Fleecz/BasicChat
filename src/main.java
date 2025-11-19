import command.CLI;
import command.HelpCommand;
import command.ICommand;

import java.util.HashMap;
public class main {
    public static void main(String[] args) {
        HashMap<String, ICommand> commands = new HashMap<String, ICommand>();
        commands.put("/help",new HelpCommand());
        for(String command : args){
            ICommand com = commands.get(command);
            if(com!=null){
                com.execute();
            }
            else
                System.out.println("Unknown command");
        }
        CLI cli = new CLI();
        cli.run();
    }
}
