package oo2.practico6;

import oo2.practico6.modelo.Medidor;
import oo2.practico6.modelo.Temperatura;
import oo2.practico6.modelo.WeatherChannelService;
import oo2.practico6.observadores.RegistrarEnArchivo;
import oo2.practico6.observadores.VerEnConsola;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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
		//var medidor = new Medidor(() -> Temperatura.fromFahrenheit(BigDecimal.valueOf(40)));
		medidor.agregarObservador(new VerEnConsola());
		medidor.agregarObservador(new RegistrarEnArchivo(new File("mediciones.txt")));
		System.out.println("medidor.leerTemperatura() = " + medidor.leerTemperatura());
	}
}
