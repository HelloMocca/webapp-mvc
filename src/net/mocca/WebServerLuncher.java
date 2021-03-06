package net.mocca;

import java.io.File;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServerLuncher {
	private static final Logger logger = LoggerFactory
			.getLogger(WebServerLuncher.class);

	public static void main(String[] args) throws Exception {
		String webappDirLocation = "webapp/";
		Tomcat tomcat = new Tomcat();

		// The port that we should run on can be set into an environment
		// variable
		// Look for that variable and default to 8080 if it isn't there.
		// String webPort = System.getenv("PORT");
		// if(webPort == null || webPort.isEmpty()) {
		// webPort = "8080";
		// }

		tomcat.setPort(Integer.valueOf("8080"));

		tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
		logger.info("configuring app with basedir: "
				+ new File("./" + webappDirLocation).getAbsolutePath());

		tomcat.start();
		tomcat.getServer().await();
	}
}
