package commands;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import hsbibot.hsbibot;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter{
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		String message = event.getMessage().getContentDisplay();
/*
	    if (message.trim().isEmpty()) {
	        System.out.println("Die Nachricht ist leer oder besteht nur aus Leerzeichen." + event.getMessage().getContentRaw());
	        return;
	    }
	*/    
	 	if (event.getAuthor().isBot()) {
	 	    return;  // Ignoriere Nachrichten von Bots
	 	 }
	 		
	    
		
		 
	    if (event.isFromType(ChannelType.TEXT)) {
	        TextChannel channel = event.getChannel().asTextChannel();

	        if (message.startsWith("!")) {
	            String[] args = message.substring(1).split(" ");
	            
	            if (args.length > 0) {
	            	if(!hsbibot.INSTANCE.getCmdMan().perform(args[0], event.getMember() , channel, event.getMessage())) {
	            		channel.sendMessage("Unbekanntes Kommando").queue(); 
	            	}
	            }
	            /*
	            	channel.sendMessage("Hallo " + event.getMember().getAsMention() + "!").queue();
	            }
	            else if(args.length == 1 && args[0].equalsIgnoreCase("time")) {
	            	Calendar cal = Calendar.getInstance(); 
	            	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH-mm-ss"); 
	            	
	            	channel.sendMessage("Es ist " + sdf.format(cal.getTime()) + "!").queue();
	            } */
	        	
	        }	
	    }
	}
}
