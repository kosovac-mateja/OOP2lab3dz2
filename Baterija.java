package energSistem;

public class Baterija {
	private int energija, kapacitet;

	public Baterija(int kapacitet) {
		energija = kapacitet;
		this.kapacitet = kapacitet;
	}
	
	public synchronized void napuni(int kolicina) {
		energija = energija + kolicina;
		
		if(energija > kapacitet)
			energija = kapacitet;
	}
	
	public synchronized void isprazni() {
		energija = 0;
	}
	
	public synchronized boolean puna() {
		return energija == kapacitet;
	}
}
