package energSistem;

import java.awt.Color;
import java.util.Random;

public abstract class Proizvodjac extends Parcela implements Runnable{
	protected int vreme;
	
	protected Baterija baterija;
	
	protected Thread nit;
	
	public Proizvodjac(char oznaka, Color bojaPozadine, int vreme, Baterija baterija) {
		super(oznaka, bojaPozadine);
		pokreni();
		this.vreme = vreme;
		this.baterija = baterija;
	}
	
	public int vremeProizvodnje() {
		return vreme + new Random().nextInt(300);
	}
	
	public abstract boolean uspesno();
	
	public abstract int kolicinaEnergije();
	
	public synchronized void pokreni() {
		nit = new Thread(this);
		
		nit.start();
	}

	public synchronized void zavrsi() {
		if(nit != null) {
			nit.interrupt();
		}
	}
	
	@Override
	public void run() {
		while(!nit.interrupted()) {
			setForeground(Color.WHITE);
			revalidate();
			try {
				nit.sleep(vreme + vremeProizvodnje());
			} catch (InterruptedException e) {}
			
			if(uspesno()) {
				setForeground(Color.RED);
				baterija.napuni(kolicinaEnergije());
				revalidate();
			}
			try {
				nit.sleep(300);
			} catch (InterruptedException e) {}
		}
		
		
		synchronized (this) {
			nit = null;
			notify(); 
		}
	}

}
