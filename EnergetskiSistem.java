package energSistem;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergetskiSistem extends Frame {
	
	private Plac plac;
	
	private Baterija baterija;
	
	private Hidroelektrana h;
	
	private Button dodajDugme = new Button("Dodaj!");
	
	private void popuniProzor() {
		add(plac, BorderLayout.CENTER);
		
		dodajDugme.addActionListener((ae) -> {
			plac.dodajProizvodjaca(new Hidroelektrana(baterija));
			plac.revalidate();
		});
		
		Panel gornjiPanel = new Panel();
		
		gornjiPanel.add(dodajDugme);
		
		add(gornjiPanel, BorderLayout.NORTH);
		
	}
	
	public EnergetskiSistem(int red, int kol, int kap) {
		plac = new Plac(red, kol);
		baterija = new Baterija(kap);
		
		setBounds(700, 200, 500, 500);
		setResizable(false);
		
		popuniProzor();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new EnergetskiSistem(5, 5, 5);
	}

}
