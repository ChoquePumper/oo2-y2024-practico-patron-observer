package oo2.practico6.observadores;

import oo2.practico6.modelo.Observer;
import oo2.practico6.modelo.Temperatura;

public class VerEnConsola implements Observer {
	private static final String MSJ_HACE_FRIO = "Hace frio, se encenderá la caldera";
	private static final String MSJ_HACE_CALOR = "Hace calor, se encenderá el aire acondicionado";

	@Override
	public void actualizar(Temperatura temperatura) {
		var celsius = temperatura.celsius().doubleValue();

		if (haceFrio(celsius))
			System.out.println(MSJ_HACE_FRIO);
		if (haceCalor(celsius))
			System.out.println(MSJ_HACE_CALOR);
	}

	private boolean haceFrio(double tempCelsius) {
		// Temperatura menor a 12 grados celsius?
		return tempCelsius < 12;
	}

	private boolean haceCalor(double tempCelsius) {
		// Temperatura mayor a 17 grados celsius?
		return tempCelsius > 17;
	}
}
