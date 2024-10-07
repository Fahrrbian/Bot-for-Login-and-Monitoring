package listener;

import java.util.concurrent.ConcurrentHashMap;

import commands.GetArtikelCommand;
import commands.HSBIMonitorCommand;
import commands.HsbiLoginCommand;
import commands.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class CommandManager {

public ConcurrentHashMap<String, ServerCommand> commands; 
	
	public CommandManager() {
		this.commands = new ConcurrentHashMap<>(); 
		
		this.commands.put("artikel", new GetArtikelCommand()); 
		this.commands.put("hsbilogin", new HsbiLoginCommand()); 
		this.commands.put("monitor", new HSBIMonitorCommand());  
	}
	public boolean perform(String command, Member m, TextChannel channel, Message message) {
		
		ServerCommand cmd; 
		if((cmd = this.commands.get(command.toLowerCase())) != null) {
			cmd.performCommand(m, channel, message);
		}
		
		return false; 
	}
}
