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
import java.util.*;

/* */    
public class Hovedklasse extends JFrame {
    public static void main (String[] args)
    {
    	//Programmet starter med � vente p� input fra bruker om rolle
        String [] verdier = {"Elev", "Lærer"};
        String valgt;
        String fil = "laerersord.txt";
        valgt=(String)JOptionPane.showInputDialog(null, "Velg rolle", "Elev eller lærer?", JOptionPane.INFORMATION_MESSAGE, null, verdier, verdier[0] );
        
        if (valgt.equals("Elev")){
        	Hovedklasse vindu = new Hovedklasse();
            vindu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            vindu.setSize(1000, 800);
            
            // Her kan vi vise vindu for � velge spiller.
            Spilleren spiller = velgSpiller();
            
            Oppgavesamling oppgavesamlingen;
            oppgavesamlingen = new Oppgavesamling(fil, spiller, vindu);
            vindu.setVisible(true);
        } else {
        	// Kommentere, deklamere, reparere
        	LaerersInnlegging laerer=new LaerersInnlegging();       
        }
    }
    
    public static Spilleren velgSpiller(){
    	
    	/*  Alternativ 1 (valgt bort)
    	 * Les inn filer, med filnavn som starter med ElevResultat_
    	 * Pluss en knapp med "Ny spiller" som sp�r etter fornavn/etternavn.
      	 *
    	 * ALTERNATIV 2 (Yes we can!)
    	 * 
    	 * Vi les inn elev-navna fr� "elever.txt"
    	 * *  Elever.txt skal bli oppretta av Laerersinnlegging-klassa.
    	 * * L�rer blir spurt etter niv�.  
    	 */
//    	public void getElevListe<fra LærersInnlegg(){
	   		
	    }
     
    	/* FELLES 
    	 * Vis nedtrekksliste med valg av spiller (navn)
    	 * lag spiller-objekt og return spiller-objektet
    	 */
		//Variabler
		String filNavn = "elever.txt";
		String line;
		ArrayList elevlisten = new ArrayList();
	    
		//Opprette nedtrekksliste fra filen elever.txt
		String [] verdier = filNavn /*<putte inn elever.txt som parameter i arraylisten verdier>*/;
	    String elevNavn;
	    elevNavn = (String)JOptionPane.showInputDialog(null, "Elevliste", "Velg navnet ditt", JOptionPane.INFORMATION_MESSAGE, null, verdier, verdier[0] );
		
	    //variabel "elevnavn" knyttes opp mot spilleren - hvor putter jeg inn elevNavn-parameteren?
    	int nivaa = 1;
    	String fornavn = ""; // Hent fornavn fra elever.txt
    	String etternavn = "";  // Hent etternavn fra elever.txt
    	Spilleren theGamerDude = new Spilleren(nivaa,fornavn,etternavn);
    	return theGamerDude;
    }
    
    
	private void velgSpillerGrensesnitt() {

		/* this.contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		*/ 
		
		/*
		 * Legg til felt for innskriving av fornavn, etternavn og niv�
		 * 
		 * Eller drit i dette og lag enkle input-felt ala laerersInnlegging...
		 */
	}

}
