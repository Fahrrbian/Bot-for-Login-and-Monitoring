package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.awt.Color;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class GetArtikelCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		// TODO Auto-generated method stub
		try {
			Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Discord_(software)").get();
			String title = doc.select("#firstHeading").text(); 
			Element firstParagraph = doc.select(".mw-parser-output > p").first();
		    Element secondParagraph = doc.select(".mw-parser-output > p").get(1);			
		    
			EmbedBuilder embed = new EmbedBuilder(); 
			embed.setTitle("Wikipedia: " + title, "https://en.wikipedia.org/wiki/Discord_(software)");
			embed.addField("Einführung", firstParagraph.text(), false);
		    embed.addField("Weiterführende Details", secondParagraph.text(), false);
		    embed.setColor(Color.BLUE);
		    embed.setFooter("Abgerufen von Wikipedia", "https://upload.wikimedia.org/wikipedia/commons/6/63/Wikipedia-logo.png");

			channel.sendMessageEmbeds(embed.build()).queue(); 
			
		} catch(IOException e) {
			channel.sendMessage("Fehler beim Abrufen des Artikels."); 
		}
	}
	
}
