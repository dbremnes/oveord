import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

/* */    
public class Hovedklasse extends JFrame {
	private Scanner elevFil;
	public Spilleren spiller;
	
    public static void main (String[] args){
  
    	//Programmet starter med � vente p� input fra bruker om rolle
        String [] verdier = {"Elev", "L�rer"};
        String valgt;
        String filnavn = "laerersord.txt";
        valgt=(String)JOptionPane.showInputDialog(null, "Velg rolle", "Elev eller l�rer?", JOptionPane.INFORMATION_MESSAGE, null, verdier, verdier[0] );
        
        if (valgt.equals("Elev")){
        	Hovedklasse vindu = new Hovedklasse();
            vindu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            vindu.setSize(1000, 800);
            
            // Her kan vi vise vindu for � velge spiller.
            vindu.velgSpiller();
            
            Oppgavesamling oppgavesamlingen;
            oppgavesamlingen = new Oppgavesamling(filnavn, vindu);
            vindu.setVisible(true);
        } else {
        	// Kommentere, deklamere, reparere
        	LaerersInnlegging laerer=new LaerersInnlegging();       
        }	
    }
    
    public void velgSpiller(){
  
		//Variabler
		String filNavn = "elever.txt";
		String line;
		int teller=0;

		Spilleren[] elevlisten = new Spilleren[100];		
		try {
	        this.elevFil=new Scanner(new File(filNavn));
			 // Hentar inn "innlesteOppgaver.txt" (el.l.), som Kristina produserer i sin klasse, dette må vel utvides og endres med varierende oppgaver
		 } // end try
	 	catch ( FileNotFoundException fileNotFoundException ){
				Oppgavesamling.lagFil(filNavn);
	 			//JOptionPane.showMessageDialog(null,  "Feil ved åpning av fila.", null, JOptionPane.PLAIN_MESSAGE );
		} // end catch
		
		while(this.elevFil.hasNext()){
			 // instansier eit oppgave-objekt, i oppg-matrisen. Får objektet med data frå input-fila.
				String fornavn = this.elevFil.next();
				String etternavn = this.elevFil.next();
				int nivaa = Integer.parseInt(this.elevFil.next());	
				
				elevlisten[teller] = new Spilleren(nivaa,fornavn,etternavn);
		}
			//variabler for kontroll av andel korrekte svar
	    
	    this.spiller = (Spilleren) JOptionPane.showInputDialog(null, "Elevliste", "Velg navnet ditt", JOptionPane.INFORMATION_MESSAGE, null, elevlisten, elevlisten[0] ); 
		JOptionPane.showMessageDialog(null,  "Niv�:"+this.spiller.getNivaa(), null, JOptionPane.PLAIN_MESSAGE );
    }

}
