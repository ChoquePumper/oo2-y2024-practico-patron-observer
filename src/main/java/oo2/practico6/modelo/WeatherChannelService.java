package oo2.practico6.modelo;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class WeatherChannelService implements ClimaOnline {
	private static final String URL_SERVICE = "https://api.openweathermap.org/data/2.5/weather";
	private final String appID;
	private final Double lat;
	private final Double lon;

	public WeatherChannelService(String appID, double lat, double lon) {
		this.appID = Objects.requireNonNull(appID);
		this.lat = lat;
		this.lon = lon;
	}

	@Override
	public String temperatura() {
		// units = metric, para mostrar la temperatura en celsius
		Map<String, String> params = Map.of("lat", lat.toString(), "lon", lon.toString(),
				"APPID", appID, "units", "metric");
		String res = peticion(params);
		return res + " c";
	}

	private static URL armarURL(String direccion) throws MalformedURLException {
		return armarURL(direccion, Map.of());
	}

	private static URL armarURL(String direccion, Map<String, String> params) throws MalformedURLException {
		params = Objects.requireNonNullElseGet(params, Map::of);
		StringBuffer url = new StringBuffer(direccion);
		if (!params.isEmpty()) {
			url.append("?");
			url.append(urlParams(params));
		}
		return new URL(url.toString());
	}

	private static String peticion(Map<String, String> params) {
		try {
			URL url = armarURL(URL_SERVICE, params);
			//System.out.println(url);

			// Realizar la conexión
			url.openConnection();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");

			// Enviar peticion y obtener respuesta.
			int code = conn.getResponseCode();
			StringWriter sw = new StringWriter();
			obtenerRespuesta(conn, sw);

			// Del JSON obtenido, leer la temperatura.
			//System.out.println(sw.toString());
			JSONObject jsonObject = new JSONObject(new JSONTokener(new StringReader(sw.toString())));
			return jsonObject.getJSONObject("main").getBigDecimal("temp").toString();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void obtenerRespuesta(HttpURLConnection conn, StringWriter sw) throws IOException {
		AtomicReference<String> next = new AtomicReference<>(null);
		var buf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		Stream.iterate(buf, bufferedReader -> {
			try {
				next.set(bufferedReader.readLine());
				return next.get() != null;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}, bufferedReader -> {
			sw.append(next.get());
			return bufferedReader;
		}).count();
	}

	private static String urlParams(Map<String, String> mapa) {
		final String SEP = "&";
		return String.join(SEP, mapa.entrySet().stream().map((e) -> e.getKey() + "=" + e.getValue()).toList());
	}
}
