package patterns;

import java.util.concurrent.LinkedBlockingDeque;

public class Caretaker {
	private LinkedBlockingDeque<Memento> previousMementos;
	private LinkedBlockingDeque<Memento> nextMementos;
	
	private static Caretaker instance = null;
	
	private Caretaker(){
		previousMementos = new LinkedBlockingDeque<>();
		nextMementos = new LinkedBlockingDeque<>();
	}
	
	public static Caretaker getInstance() {
		if(instance == null) {
			instance = new Caretaker();
		}
		return instance;
	}

	public void addPreviousMemento(Memento memento) {
		if(checkMementosSize()) {
			replaceOldMementos(memento);
			return;
		}
		this.previousMementos.push(memento);
	}
	
	public void addNextMemento(Memento memento) {
		this.nextMementos.push(memento);
	}
	
	public Memento getPreviousMemento() {
		return this.previousMementos.pop();
	}
	
	public Memento getNextMemento() {
		return this.nextMementos.pop();
	}
	
	private boolean checkMementosSize() {
		if(this.previousMementos.size() >= 20) {
			return true;
		}else if(this.nextMementos.size() >= 20) {
			return true;
		}
		return false;
	}
	
	private void replaceOldMementos(Memento memento) {
		Memento reemplazo = this.previousMementos.pollLast();
		System.out.println("Replace memento: " + reemplazo.getTextState());
		this.previousMementos.push(memento);
	}
	
	public void restartStacks() {
		this.previousMementos.clear();
		this.nextMementos.clear();
	}
	
}
