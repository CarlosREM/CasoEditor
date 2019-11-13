package patterns;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.text.StyledDocument;

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
	
	public boolean checkPreviousMementoStateChange(Memento memento) {
		if(!this.previousMementos.isEmpty()) {
			ArrayList<String> textState = this.previousMementos.peek().getTextState();
			for(int i=0; i<textState.size(); i++) {
				String text = textState.get(i);
				if(!text.equals(memento.getTextState().get(i)) || memento.getTextState().size() != textState.size()) {
					System.out.println("Estado anterior: " + text);
					System.out.println("Estado actual: " + memento.getTextState().get(i));	
					return true;
				}
			}
			return false;
		}
		return true;
	}
	
	public void savePreviousMemento(Memento memento) {
		if(checkPreviousMementoStateChange(memento)) {
			addPreviousMemento(memento);
		}
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
		if(!this.previousMementos.isEmpty()) {
			return this.previousMementos.pop();
			//Poner el statte en el next memento
		}
		return null;
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
