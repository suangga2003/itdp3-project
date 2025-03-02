package com.itdp.arnd;

import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.awt.*;
// import java.net.URI;
import org.springframework.core.env.Environment;


@SpringBootApplication
@EnableJpaAuditing
public class ArndApplication {

	public static void main(String[] args) {
		Environment env = SpringApplication.run(ArndApplication.class, args).getEnvironment();
		String port = env.getProperty("server.port", "4000"); // Default ke 4000 jika tidak ada
        // Buka browser
        openBrowser("http://localhost:" + port);
	}

	private static void openBrowser(String url) {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI(url)); // Buka browser di Windows/Mac
			} else {
				String os = System.getProperty("os.name").toLowerCase();
				if (os.contains("win")) {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url); // Windows
				} else if (os.contains("mac")) {
					Runtime.getRuntime().exec("open " + url); // macOS
				} else if (os.contains("nix") || os.contains("nux")) {
					Runtime.getRuntime().exec("xdg-open " + url); // Linux
				} else {
					System.out.println("Tidak bisa membuka browser otomatis, silakan akses: " + url);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
