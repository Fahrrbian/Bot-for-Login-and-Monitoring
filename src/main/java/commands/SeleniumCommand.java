package commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumCommand implements ServerCommand{

	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		// TODO Auto-generated method stub
        System.setProperty("webdriver.chrome.driver", "Pfad/zu/chromedriver");

        WebDriver driver = new ChromeDriver();

        // Cookies aus der aktuellen Browser-Session
        Cookie sessionCookie = new Cookie("JSESSIONID", "DEIN_JSESSIONID_WERT");
        driver.manage().addCookie(sessionCookie);

        // Öffne die Seite
        driver.get("DEIN_ZIEL_LINK");

        // Weitere Automatisierungsmöglichkeiten
        System.out.println(driver.getPageSource());

        driver.quit();
	}

}
