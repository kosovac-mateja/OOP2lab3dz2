package energSistem;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Parcela extends Label {
	protected char oznaka;
	
	protected Color bojaPozadine;
	
	public Parcela(char oznaka, Color bojaPozadine) {
		super("" + oznaka, Label.CENTER);
		
		setBackground(bojaPozadine);
		
		this.oznaka = oznaka;
		this.bojaPozadine = bojaPozadine;
		
		setForeground(Color.WHITE);
		setFont(new Font(Font.SERIF, Font.BOLD, 14));
		
		addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
            	getParent().dispatchEvent(e);
            }
        });
	}
	
	

	public void bojaPozadine(Color boja) {
		this.setBackground(boja);
	}
	
	
	
}
