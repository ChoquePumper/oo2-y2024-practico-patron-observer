package oo2.practico6.modelo;

public class Medidor {
	private String temperatura;
	private ClimaOnline clima;

	public Medidor(ClimaOnline clima) {
		this.clima = clima;
	}

	public String leerTemperatura() {
		//leo la temperatura del servicio web
		this.temperatura = this.clima.temperatura();

		return this.temperatura;
	}
}
