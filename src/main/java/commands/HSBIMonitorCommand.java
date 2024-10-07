package commands;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class HSBIMonitorCommand implements ServerCommand {

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		// TODO Auto-generated method stub
		
		
		Map<String, String> cookies = new HashMap<>();
		cookies.put("JSESSIONID", "B79693612E3C538F67F4A7BCEF95F84A.node1");
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.submit(() -> {
		try {
			Document doc1 = Jsoup.connect("")											
						.cookies(cookies)
						.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")					
						.get();
			
			int statusCode = Jsoup.connect("")
				    .cookies(cookies)
				    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
				    .execute()
				    .statusCode();
					
			System.out.println("////////////////////Status Code: " + statusCode);


			Element table = doc1.select("table#notenTabelle").first(); 
			System.out.println(doc1.body().html());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		});
		
		
	}
}
