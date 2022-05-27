package energSistem;

import java.awt.*;
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
		
		this.setLayout(new GridLayout(red, kolona, 5, 5));
				
		for(int i = 0; i < red; i++) {
			for(int j = 0; j < kolona; j++) {
				Parcela p;
				if(new Random().nextDouble(1) <= 0.7) {
					p = new TravnataPovrs();
					add(p);
					parcele.add(p);
				}
					
				else {
					p = new VodenaPovrs();
					add(p);
					parcele.add(p);
				}
			}
		}
		
		this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Parcela parcela=(Parcela)e.getSource();
                if(kliknuta != null)
                	kliknuta.setFont(new Font(Font.SERIF, Font.BOLD, 14));
                kliknuta = parcela;
                kliknuta.setFont(new Font(Font.SERIF, Font.BOLD, 20));
            }
        });
		
		setSize(300, 300);
		
		setVisible(true);
	}
	
	public void dodajProizvodjaca(Proizvodjac proizv) {
		
		int i = 0;
		for (Iterator<Parcela> it = parcele.iterator(); it.hasNext();) {
		    Parcela parc = it.next();
		    
		    if(parc == kliknuta) {
		    	if(!(kliknuta instanceof Proizvodjac)) {
		    		parcele.remove(i);
			    	parcele.add(i, proizv);
			    	this.remove(i);
			    	this.add(proizv, i);
			    	proizvodjaci.add(proizv);
			    	revalidate();
			    	for(Proizvodjac p : proizvodjaci) {
			    		int ind = parcele.indexOf(p);
			    		int br = brVodPovr(ind);
				    	((Hidroelektrana)p).postaviBrVodPovrs(br);
			    	}
		    	}
		    	break;
		    }
		    
		    i++;
		    
		}
	}

	private int brVodPovr(int i) {
		int br = 0;
		if(i >= kolona && (parcele.get(i - kolona) instanceof VodenaPovrs))
			br++;
		if((i < (red - 1) * kolona) && (parcele.get(i + kolona) instanceof VodenaPovrs))
			br++;
		if(i % red != 0 && (parcele.get(i - 1) instanceof VodenaPovrs))
			br++;
		if(i % red != (kolona - 1) && (parcele.get(i + 1) instanceof VodenaPovrs))
			br++;
		
		return br;
	}
	
	
}
