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
/*
 * Fyljande import skal brukast til å gi Tab-order, altså at input-feltet er første tab-stop, svar-knappen er nr.2 etc.
 * import org.eclipse.wb.swing.FocusTraversalOnArray;
 * Desverre funkar den ikkje!  Grrr...
 */


public class Hovedklasse extends JFrame {
	private Scanner elevFil;
	public Spilleren spiller;

    public static void main (String[] args){

    	//Programmet starter med å vente på input fra bruker om rolle
        String [] verdier = {"Elev", "Lårer"};
        String valgt;
        String filnavn = "laerersord.txt";
        valgt=(String)JOptionPane.showInputDialog(null, "Velg rolle", "Elev eller lærer?", JOptionPane.INFORMATION_MESSAGE, null, verdier, verdier[0] );

        if (valgt.equals("Elev")){
        	Hovedklasse vindu = new Hovedklasse();
            vindu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            vindu.setSize(670, 750);

            // Her kan vi vise vindu for å velge spiller.
            vindu.velgSpiller();

            Oppgavesamling oppgavesamlingen;
            oppgavesamlingen = new Oppgavesamling(filnavn, vindu);
            vindu.setVisible(true);
        } else {
        	// Kommentere, deklamere, reparere, programmere!
        	LaerersInnlegging laerer=new LaerersInnlegging();
        }
    } // end main

    public void velgSpiller(){

		//Variabler
		String filNavn = "elever.txt";
		String line;
		int teller=0;

		Spilleren[] elevlisten = new Spilleren[100];
		try {
	        this.elevFil=new Scanner(new File(filNavn));
			 // Hentar inn "innlesteOppgaver.txt" (el.l.), som Kristina produserer i sin klasse, dette mÅ vel utvides og endres med varierende oppgaver
		 } // end try
	 	catch ( FileNotFoundException fileNotFoundException ){
				Oppgavesamling.lagFil(filNavn,"");
	 			//JOptionPane.showMessageDialog(null,  "Feil ved åpning av fila.", null, JOptionPane.PLAIN_MESSAGE );
		} // end catch

		while(this.elevFil.hasNext()){
			 // instansier eit oppgave-objekt, i oppg-matrisen. FÅr objektet med data frÅ input-fila.
				String fornavn = this.elevFil.next();
				String etternavn = this.elevFil.next();
				int nivaa = Integer.parseInt(this.elevFil.next());
				// Puttar inn eit spelar-objekt i matrisen, som vi så brukar til å velje aktiv spelar.
				elevlisten[teller] = new Spilleren(nivaa,fornavn,etternavn);
				teller++;
		}
			//variabler for kontroll av andel korrekte svar
	    // Matrisen returnerer valgt spillar objekt, som vi dermed gir peikaren "this.spiller".
	    this.spiller = (Spilleren) JOptionPane.showInputDialog(null, "Elevliste", "Velg navnet ditt", JOptionPane.INFORMATION_MESSAGE, null, elevlisten, elevlisten[0] );
		
    } // End velgSpiller()
} // end Hovedklasse
