package oo2.practico6.modelo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

import static java.math.BigDecimal.valueOf;

public final class Temperatura {
	private static final MathContext MC = new MathContext(10);
	private final BigDecimal celsius;
	private final BigDecimal fahrenheit;
	private final BigDecimal kelvin;


	private Temperatura(BigDecimal celsius, BigDecimal fahrenheit, BigDecimal kelvin) {
		this.celsius = celsius;
		this.fahrenheit = fahrenheit;
		this.kelvin = kelvin;
	}

	private static BigDecimal celsiusToFahrenheit(BigDecimal celsius) {
		return celsius.multiply(valueOf(9)).divide(valueOf(5), MC)
				.add(valueOf(32));
	}

	private static BigDecimal fahrenheitToCelsius(BigDecimal fahrenheit) {
		return fahrenheit.subtract(valueOf(32))
				.multiply(valueOf(5)).divide(valueOf(9), MC);
	}

	private static BigDecimal celsiusToKelvin(BigDecimal celsius) {
		return celsius.add(valueOf(273));
	}

	private static BigDecimal kelvinToCelsius(BigDecimal kelvin) {
		return kelvin.subtract(valueOf(273));
	}

	public static Temperatura fromCelsius(BigDecimal celsius) {
		return new Temperatura(celsius, celsiusToFahrenheit(celsius), celsiusToKelvin(celsius));
	}

	public static Temperatura fromFahrenheit(BigDecimal fahrenheit) {
		BigDecimal celsius = fahrenheitToCelsius(fahrenheit);
		return new Temperatura(celsius, fahrenheit, celsiusToKelvin(celsius));
	}

	public static Temperatura fromKelvin(BigDecimal kelvin) {
		BigDecimal celsius = kelvinToCelsius(kelvin);
		return new Temperatura(celsius, celsiusToFahrenheit(celsius), kelvin);
	}

	public BigDecimal celsius() {
		return celsius;
	}

	public BigDecimal fahrenheit() {
		return fahrenheit;
	}

	public BigDecimal kelvin() {
		return kelvin;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (Temperatura) obj;
		return Objects.equals(this.celsius, that.celsius) &&
				Objects.equals(this.fahrenheit, that.fahrenheit) &&
				Objects.equals(this.kelvin, that.kelvin);
	}

	@Override
	public int hashCode() {
		return Objects.hash(celsius, fahrenheit, kelvin);
	}

	@Override
	public String toString() {
		return "Temperatura[" +
				"celsius=" + celsius + ", " +
				"fahrenheit=" + fahrenheit + ", " +
				"kelvin=" + kelvin + ']';
	}

}
