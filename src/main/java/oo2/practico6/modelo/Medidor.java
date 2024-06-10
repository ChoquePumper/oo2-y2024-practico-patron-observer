package oo2.practico6.modelo;

public class Medidor {
	private String temperatura;
	private ClimaOnline clima;

	public Medidor(ClimaOnline clima) {
		this.clima = clima;
	}

	public String leerTemperatura() {
		//leo la temperatura del servicio web
		var temp = this.clima.temperatura();
		//System.out.println("temp = " + temp);
		double celsius = temp.celsius().doubleValue();
		this.temperatura = String.format("%f c", celsius);

		return this.temperatura;
	}
}
