package commands;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.net.ssl.*;

import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class HsbiLoginCommand implements ServerCommand {
	 
	private static final String LOGIN_URL = ""; // URL zur Login-Seite
	private static final String TARGET_URL = ""; // Die Seite, die du überwachen möchtest
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
		  
		  
	@Override
	public void performCommand(Member m, TextChannel channel, Message message) {
		// TODO Auto-generated method stub
		
		disableSSLVerification(); 		
		checkWebsite();
		
		
	}
	public void checkWebsite() {
		
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.submit(() -> {
			try {
				Document loginDoc = Jsoup.connect(LOGIN_URL)
			            .get(); // GET-Anfrage an die Login-Seite
		
				
				Element usernameField = loginDoc.select("input[name=asdf]").first();
			    if (usernameField != null) {
			        System.out.println("Das Eingabefeld 'asdf' wurde gefunden.");
			    }
			    
			    
		        Connection.Response loginResponse = Jsoup.connect(LOGIN_URL)   
		                .data("asdf", USERNAME)
		                .data("fdsa", PASSWORD)
		                .method(org.jsoup.Connection.Method.POST)
		                .execute();
		
		        if (loginResponse.statusCode() == 200) {
		            System.out.println("Login erfolgreich. Benutzername wurde eingegeben: " + USERNAME);
		        } else {
		            System.out.println("Login fehlgeschlagen. Statuscode: " + loginResponse.statusCode());
		        }        
		        
		     // Überprüfen des Statuscodes und des Inhalts der Login-Antwort
		        System.out.println("Statuscode: " + loginResponse.statusCode());
		        //System.out.println("Login-Antwort: " + loginResponse.body());
		        
		        Document doc = Jsoup.connect(TARGET_URL)
		                .cookies(loginResponse.cookies()) // Verwende die Cookies von der Login-Antwort
		                .get();
		        		
		        
		        System.out.println("Cookies nach dem Login: " + loginResponse.cookies());
		
		        //public Map<String, String> resetCookies() throws IOException { 
		        
		        String redirectURL = "";
		        
		        Connection.Response redirectResponse1 = Jsoup.connect(redirectURL)
		                .cookies(loginResponse.cookies())
		                .method(Connection.Method.GET)
		                .execute();
		        		

		        String redirectURL2 = "";		      
		        Connection.Response redirectResponse2 = Jsoup.connect(redirectURL2)
		                .cookies(redirectResponse1.cookies())
		                .method(Connection.Method.GET)
		                .execute();
		        		

		        String redirectURL3 = "";		        
		        Connection.Response redirectResponse3 = Jsoup.connect(redirectURL3)
		                .cookies(redirectResponse2.cookies())
		                .method(Connection.Method.GET)
		                .execute();
		
		        Document redirectDoc3 = redirectResponse3.parse(); 
		        
		        System.out.println("Redirect3///////////////////"  + redirectDoc3.html()); 
		        		
		        //System.out.println("Login-Antwort: " + loginResponse.body());
						 
		        
		        
		        // Wähle den relevanten Teil der Seite aus
		        Element contentElement = doc.select(".divloginstatus").first(); // Ersetze mit dem richtigen Selektor
		        //System.out.println("/////////////////////// " +contentElement.html()); 
			} catch(IOException e) {
				e.printStackTrace(); 
			} finally {
				executor.shutdown(); 
			}
		});
	}
	 public static void disableSSLVerification() {
	        try {
	            // Einen TrustManager erstellen, der alle Zertifikate akzeptiert
	            TrustManager[] trustAllCerts = new TrustManager[]{
	                new X509TrustManager() {
	                    public X509Certificate[] getAcceptedIssuers() {
	                        return null;
	                    }

	                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
	                    }

	                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
	                    }
	                }
	            };

	            // Initialisiere den SSLContext mit dem TrustManager
	            SSLContext sc = SSLContext.getInstance("SSL");
	            sc.init(null, trustAllCerts, new java.security.SecureRandom());
	            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

	            // Deaktiviere die Hostname-Verifizierung
	            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
	                public boolean verify(String hostname, SSLSession session) {
	                    return true;
	                }
	            });
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } 
}
