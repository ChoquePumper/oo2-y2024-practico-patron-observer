package oo2.practico6.observadores;

import oo2.practico6.modelo.Observer;
import oo2.practico6.modelo.Temperatura;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class RegistrarEnArchivo implements Observer {

	private final File file;

	public RegistrarEnArchivo(File file) {
		this.file = Objects.requireNonNull(file);
	}

	@Override
	public void actualizar(Temperatura temperatura) {
		try (var writer = new FileWriter(file, true)) {
			// Armar la linea
			final String SEP = "\t", EOL = "\n";
			String[] valores = {LocalDateTime.now().toString(), temperatura.celsius().toString()};
			// Escribir
			writer.write(String.join(SEP, valores));
			writer.write(EOL);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
