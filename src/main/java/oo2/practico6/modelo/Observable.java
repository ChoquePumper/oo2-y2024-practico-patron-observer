package oo2.practico6.modelo;

import java.util.LinkedList;
import java.util.List;

public abstract class Observable {
	private List<Observer> observadores = new LinkedList<>();

	public void agregarObservador(Observer observador) {
		observadores.add(observador);
	}

	protected void notificar(Temperatura temperatura) {
		for (Observer observador : observadores)
			observador.actualizar(temperatura);
	}
}
