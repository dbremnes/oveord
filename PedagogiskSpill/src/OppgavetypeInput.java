/*
 * Klasse OppgavesamlingInput, av Torbjørn Frantsen
 * Formål: presentere gresesnitt, sjekke resultat og formidle denne infoen til Oppgavesamling.
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class OppgavetypeInput extends JFrame implements ActionListener {
	/*
	 * Klassevariablar - objektegenskapar
	 * Først egenskapane som gjeld objektet sjølv, utanom grensesnittet. 
	 */
	private String oppgaveOrd;
	private String oppgaveFasit;
	private int nivaa;
	private boolean besvart; // True når spelar har besvart oppgåva (er ferdig)
	private Oppgavesamling oppgavesamlinga;
	/* 
	 * Objektegenskapar - Her alt som har med grensesnittet å gjere. 
	 */
	private JFormattedTextField inputSvar; // Tekstfelt der brukaren skriv svaret
	private boolean inputSvarErEndra; // Held styr på om brukaren har skrive noko i input'en.
	private JPanel contentPane;  // JPanelet, dette vert bygd opp og vert sendt over til Oppgavesamling, som vi tek det vidare til hovudvinduet. 
	private JButton btnLagre; // Knapp for å svare. 
	// Nokre labels for å vise informasjon i grensesnittet. Brukar prefiks lbl for å lettare kjenne dei igjen. 
	private JLabel label;
	private JLabel lblOppgave;
	private JLabel lblSvar;
	private JLabel lblOppgaveOrdet;
	
	/**
	 * Lokal main-metode for å teste objektet, utan å måtte ha heile programmet klart. 
	 * Er naturligvis overflødig når objektet vert laga av Oppgavesamling. 
	 */
	public static void main(String[] args) {
		/* Denne koda er generert av Windowbuilder-plugin'en til Eclipse.
		 * Den forenklar grensesnittbygging veldig.
		 * Formålet med å bruke EventQueue og lage ein Runnable er å lage ein tråd som køyrer programmet. 
		 * Fleirtråd-køyring er kanskje ikkje påkrevd med vårt vesle program, men det skadar jo heller ikkje...   :)
		 */
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OppgavetypeInput frame = new OppgavetypeInput();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); // Sørgar for at feilmeldingar kjem med i consoll-vinduet. Særs praktisk! 
				}
			}
		});
	}
	/**
	 * Voila le konstruktør! 
	 */
	public OppgavetypeInput() {
		this("test","prøve",1); // Puttar inn parameter-verdiar når objektet vert testa. 
		System.out.println("Burde kanskje ikkje lage objekt utan parametrar?");
	}
	
	public OppgavetypeInput(String oppgaveOrd, String oppgaveSvar, int nivaa, Oppgavesamling oppgavesamlinga){

		this.oppgaveOrd = oppgaveOrd;
		this.oppgaveFasit = oppgaveSvar;
		this.nivaa = nivaa;
		this.setBesvart(true);
		/* I staden for å legge alt med grensesnitt her, slik Windowbuilder gjer det, vil eg ha ein rein konstruktør. 
		 * Har ikkje fått lest meg heilt opp på MVC enno, men dette er vel eit lite steg i den retning, å få View-element bort frå program-logikken. 
		 */
		lagGrensesnittet();		
	}

	private void lagGrensesnittet() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 283);
		this.contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/*
		 * Knapp - svar
		 */
		btnLagre = new JButton("Svar");
		btnLagre.addActionListener(this); // Sett klassa sjølv til eventListener for knappen.
		btnLagre.setBounds(475, 156, 80, 53);
		contentPane.add(btnLagre); // Add metoda til eit panel, legg til andre objekt i panelet. 
		// Input felt for svar, coming up
		inputSvar = new JFormattedTextField();
		inputSvar.setText("test");
		inputSvarErEndra = false; // I utgangspunktet er input-feltet ikkje endra. 
		inputSvar.setFont(new Font("Tahoma", Font.PLAIN, 30));
		inputSvar.setBounds(260, 156, 203, 53);
		contentPane.add(inputSvar);
		/* La til ein focus-listener her, som gjer at feltet blir blanka ut når nokon set markøren der.
		 * Men berre første gong, finn det ikkje vidare brukarvenlig å tøme feltet KVAR gong det får fokus.  
		 */
		inputSvar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(inputSvarErEndra==false){
					inputSvarErEndra = true;  // Første gong nokon klikkar i input-feltet, vert det blankt og markert endra. 
					inputSvar.setText("");
				}
			}
		});
		
		// Label - Heading for oppgåva
		JLabel lblHeadingOppgavetype = new JLabel("Oversett ord, niv\u00E5: ");
		lblHeadingOppgavetype.setFont(new Font("Dialog", Font.BOLD, 30));
		lblHeadingOppgavetype.setBounds(105, 12, 273, 47);
		contentPane.add(lblHeadingOppgavetype);
		// Label - Oppgåveordet
		label = new JLabel(Integer.toString(this.nivaa)); // Sett inn oppgåveordet.
		label.setFont(new Font("Dialog", Font.BOLD, 30));
		label.setBounds(390, 12, 27, 47);
		contentPane.add(label);
		// Label - ordet "Oppgave" framfor oppgaveordet
		lblOppgave = new JLabel("Oppgave:");
		lblOppgave.setFont(new Font("Dialog", Font.BOLD, 30));
		lblOppgave.setBounds(105, 97, 153, 47);
		contentPane.add(lblOppgave);
		// Label - ordet "Svar" framfor inputSvar
		lblSvar = new JLabel("Svar:");
		lblSvar.setFont(new Font("Dialog", Font.BOLD, 30));
		lblSvar.setBounds(105, 156, 153, 47);
		contentPane.add(lblSvar);
		// Label - Oppgåveordet
		lblOppgaveOrdet = new JLabel(this.oppgaveOrd);
		lblOppgaveOrdet.setFont(new Font("Dialog", Font.BOLD, 30));
		lblOppgaveOrdet.setBounds(260, 97, 153, 47);
		contentPane.add(lblOppgaveOrdet);
		
		JButton btnNesteOppg = new JButton("neste oppg");
		btnNesteOppg.addActionListener(this);
		btnNesteOppg.setBounds(509, 27, 115, 29);
		contentPane.add(btnNesteOppg);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		/* Denne blir trigga av knappen. Den prøvar altså å sjekke svar. 
		 * Men vi tek først ein sjekk på om brukar har skrive noko, ellers er det vel greit å anta at svaret er feil... 
		 */
		if(arg0.getSource()=="neste oppg"){
			/* Dette er uferdig, poenget er at knappen "neste oppg" skal trigge oppgavesamling. 
			 * Det bør også skje når riktig svar er avgitt. 
			 */
			this.nesteOppgave();
		}else{
			if(this.inputSvarErEndra){
				sjekkSvar();
			}else{
				// Ein liten heads-up når ein prøvar svare blankt, har sentrert infoboksa over frame'n. 
				JOptionPane.showMessageDialog(this,"Du bør kanskje skrive eit svar først?");
			}
		}
	}

	private void nesteOppgave(){
		this.oppgavesamlinga.nesteOppgave();
		
	}
	private void sjekkSvar() {
		String forslag = (String) this.inputSvar.getText();
		if(forslag.equals(this.oppgaveFasit)){  // String-samanlikning, må bruke equals() fra String-klassa, istaden for == 
			JOptionPane.showMessageDialog(this,"Hurra! You rule");  // Svært pedagogisk riktig tilbakemelding. 
			this.nesteOppgave();
		}else{
			JOptionPane.showMessageDialog(this,"Meeeeh... Prøv igjen! Svaret er:\n '"+this.oppgaveFasit+"' \n Du skreiv:\n '"+forslag.toString()+"'");
			this.inputSvar.setText("");
			inputSvarErEndra=false; // Nullstill, slik at brukaren kan prøve igjen
		}
	}
	
	public JPanel visOppgave(){
		// Sender rett og slett contentPane'et, altså grensesnittet. Skal brukast av Oppgavesamling.
		return this.contentPane;
	}
	
	/*
	 * Set'ers og Get'ers må'n ha, om objektet skal fungere bra. 
	 * Ikkje så mykje kvalitetssikring av objektets innkapsla egenskapar enno, men det kan jo kome...
	 */
	private void setBesvart(boolean b) {
		this.besvart = b;		
	}
	
	public boolean getBesvart() {
		return besvart;
	}
	//  nivaa
	private void setNivaa(int n) {
		this.nivaa = n;		
	}	
	public int getNivaa() {
		return this.nivaa;
	}
	// oppgaveOrd
	private void setOppgaveOrd(String o) {
		this.oppgaveOrd= o;		
	}	
	public String getOppgaveOrd() {
		return this.oppgaveOrd;
	}
	// oppgaveFasit  
	private void setOppgaveFasit(String f) {
		this.oppgaveFasit = f;		
	}	
	public String getOppgaveFasit() {
		return this.oppgaveFasit;
	}
}