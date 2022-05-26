package energSistem;

public class Baterija {
	int energija, kapacitet;

	public Baterija(int kapacitet) {
		energija = kapacitet;
		this.kapacitet = kapacitet;
	}
	
	public void napuni(int kolicina) {
		energija = (energija + kolicina) % kapacitet;
	}
	
	public void isprazni() {
		energija = 0;
	}
	
	public boolean puna() {
		return energija == kapacitet;
	}
}
