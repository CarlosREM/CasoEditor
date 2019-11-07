package patterns;

public class Originator {
	private Memento state;
	
	public Originator() {
		
	}

	public void setMemento(Memento memento) {
		this.state = memento;
	}
	
	public Memento createMemento(String text) {
		Memento memento = new Memento(text);
		setMemento(memento);
		return memento;
	}
}
