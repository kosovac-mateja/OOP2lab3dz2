package energSistem;

import java.awt.Color;
import java.util.Random;

public abstract class Proizvodjac extends Parcela implements Runnable{
	protected int vreme;
	
	protected int ukupnoVreme;
	
	protected Baterija baterija;
	
	protected Thread nit;
	
	protected boolean radi = false;
	
	public Proizvodjac(char oznaka, Color bojaPozadine, int vreme, Baterija baterija) {
		super(oznaka, bojaPozadine);
		this.vreme = vreme;
		this.baterija = baterija;
		ukupnoVreme = vreme + vremeProizvodnje();
		
		pokreni();
	}
	
	public int vremeProizvodnje() {
		return vreme + new Random().nextInt(300);
	}
	
	protected abstract boolean uspesno();
	
	public abstract int kolicinaEnergije();
	
	protected synchronized void pokreni() {
		nit = new Thread(this);
		
		nit.start();
		
		radi = true;
	}

	public synchronized void zavrsi() {
		if(nit != null) {
			nit.interrupt();
					
			radi = false;
		}
	}
	
	@Override
	public void run() {
		while(!nit.interrupted() && radi) {
			
			setForeground(Color.WHITE);
			revalidate();
			
			try {
				nit.sleep(ukupnoVreme);
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
