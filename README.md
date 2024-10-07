Ich habe versucht ein Monitoring-Tool mit JSoup und Selenium zu erstellen um Web-Scraping durchzuführen, wobei das Projekt über die JDA läuft. 

Aktueller Stand (es wird nicht mehr weitergearbeitet): Zugriff mit Login auf Target-Page war möglich (Status-Code: 200 (erfolgreich) - Die Target-Page hat mit asi-Code die Session am laufen gehalten mit jedem Login wurden der asi-Code geändert. 

Bei Ausführung des Commands hat der HTML-Body ergeben, dass ein Time-Out vorlag - die Session war einzigartig, man konnte nicht mit dem asi-Code die Webseite aufrufen. Es waren genau die Cookies von nöten, die die Session zugeteilt hat. Der nächste Step könnte sein: 
Cookies auslesen und in den Bot einfügen. 

Weitere Probleme: 
Die Target-Page führt täglich nachts ein Update durch, man müsste täglich neue Cookies übergeben oder der Bot muss eine neue Session eröffnen. 

