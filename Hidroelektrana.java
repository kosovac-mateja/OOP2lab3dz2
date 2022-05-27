package energSistem;

import java.awt.Color;

public class Hidroelektrana extends Proizvodjac {

	private int brVodPovrs = 0;
	
	public Hidroelektrana(Baterija baterija) {
		super('H', Color.BLUE, 1500, baterija);
	}
	
	public void postaviBrVodPovrs(int brVodPovrs) {
		this.brVodPovrs = brVodPovrs;
	}

	@Override
	public boolean uspesno() {
		return brVodPovrs > 0;
	}

	@Override
	public int kolicinaEnergije() {
		return brVodPovrs;
	}

}
