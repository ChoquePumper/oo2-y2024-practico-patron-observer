package oo2.practico6;

import oo2.practico6.modelo.Medidor;
import oo2.practico6.modelo.WeatherChannelService;

import java.io.IOException;
import java.util.Properties;

import static java.lang.Double.parseDouble;

public class Main {
	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

		String appID = prop.getProperty("wheatherservice.appid");
		double lat = parseDouble(prop.getProperty("wheatherservice.latitude", ""));
		double lon = parseDouble(prop.getProperty("wheatherservice.longitude", ""));
		var medidor = new Medidor(new WeatherChannelService(appID, lat, lon));
		System.out.println("medidor.leerTemperatura() = " + medidor.leerTemperatura());
	}
}
