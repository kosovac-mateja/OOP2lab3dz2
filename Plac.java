package energSistem;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Plac extends Panel {
	private int red, kolona;
	
	private Parcela kliknuta;
	
	private ArrayList<Parcela> parcele = new ArrayList<>();
	
	private ArrayList<Proizvodjac> proizvodjaci = new ArrayList<>();

	public Plac(int red, int kolona) {
		this.red = red;
		this.kolona = kolona;
		
		this.setLayout(new GridLayout(red, kolona, 2, 2));
				
		for(int i = 0; i < red; i++) {
			for(int j = 0; j < kolona; j++) {
				Parcela p;
				
				if(new Random().nextDouble(1) <= 0.7)
					p = new TravnataPovrs();
					
				else
					p = new VodenaPovrs();
				
				add(p);
				parcele.add(p);
			}
		}
		
		izaberiParcelu();
		
		setSize(300, 300);
		
		setVisible(true);
	}
	
	public Parcela izaberiParcelu() {
		this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
            	if(!(e.getSource() instanceof Parcela)) return;
                Parcela parcela=(Parcela)e.getSource();
                
                if(kliknuta != null)
                	kliknuta.setFont(new Font(Font.SERIF, Font.BOLD, 14));
                
                kliknuta = parcela;
                kliknuta.setFont(new Font(Font.SERIF, Font.BOLD, 20));
            }
        });
		return kliknuta;
	}
	
	public void dodajProizvodjaca(Proizvodjac proizv) {
		
		int i = 0;
		for (Iterator<Parcela> it = parcele.iterator(); it.hasNext();) {
		    Parcela parc = it.next();
		    
		    if(parc == kliknuta) {
		    		parcele.remove(i);
			    	parcele.add(i, proizv);
			    	
			    	this.remove(i);
			    	this.add(proizv, i);
			    	
			    	proizvodjaci.add(proizv);
			    	if(kliknuta instanceof Proizvodjac) {
			    		proizvodjaci.remove(kliknuta);
			    		((Proizvodjac) kliknuta).zavrsi();
			    	}
			    	
			    	revalidate();
			    	
			    	for (Iterator<Proizvodjac> iter = proizvodjaci.iterator(); iter.hasNext();) {
			    		Proizvodjac pr = iter.next();
			    		
			    		int br = brVodPovr(parcele.indexOf(pr));
			    		
				    	((Hidroelektrana)pr).postaviBrVodPovrs(br);
			    	}
			    	
		    	break;
		    }
		    
		    i++;
		    
		}
	}
	
	public void zaustaviProizvodjace() {
		for (Iterator<Proizvodjac> iter = proizvodjaci.iterator(); iter.hasNext();) {
    		Proizvodjac pr = iter.next();
    		
    		pr.zavrsi();
    	}
	}

	private int brVodPovr(int i) {
		int br = 0;
		
		boolean postojiGore = i >= kolona;
		boolean postojiDole = i < (red - 1) * kolona;
		boolean postojiLevo = i % red != 0;
		boolean postojiDesno = i % red != (kolona - 1);
		
		if(postojiGore) {
			Parcela gore = parcele.get(i - kolona);
			
			if(gore instanceof VodenaPovrs)
				br++;
		}
		
		if(postojiDole) {
			Parcela dole = parcele.get(i + kolona);
			
			if(dole instanceof VodenaPovrs)
				br++;
		}
		
		if(postojiLevo) {
			Parcela levo = parcele.get(i - 1);
			
			if(levo instanceof VodenaPovrs)
				br++;
		}

		if(postojiDesno) {
			Parcela desno = parcele.get(i + 1);
			
			if(desno instanceof VodenaPovrs)
				br++;
		}
		
		if(postojiGore && postojiLevo) {
			Parcela goreLevo = parcele.get(i - kolona - 1);
			
			if(goreLevo instanceof VodenaPovrs)
				br++;
		}
		
		if(postojiGore && postojiDesno) {
			Parcela goreDesno = parcele.get(i - kolona + 1);
			
			if(goreDesno instanceof VodenaPovrs)
				br++;
		}
		
		if(postojiDole && postojiLevo) {
			Parcela doleLevo = parcele.get(i + kolona - 1);
			
			if(doleLevo instanceof VodenaPovrs)
				br++;
		}
		
		if(postojiDole && postojiDesno) {
			Parcela doleDesno = parcele.get(i + kolona + 1);
			
			if(doleDesno instanceof VodenaPovrs)
				br++;
		}
		
		return br;
	}
	
	
}
