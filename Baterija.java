package energSistem;

public class Baterija {
	int energija, kapacitet;

	public Baterija(int kapacitet) {
		energija = kapacitet;
		this.kapacitet = kapacitet;
	}
	
	public synchronized void napuni(int kolicina) {
		energija = (energija + kolicina) % kapacitet;
	}
	
	public synchronized void isprazni() {
		energija = 0;
	}
	
	public synchronized boolean puna() {
		return energija == kapacitet;
	}
}
