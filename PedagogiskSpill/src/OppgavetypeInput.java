/*
 * Klasse OppgavesamlingInput, av Torbj�rn Frantsen
 * Form�l: presentere gresesnitt, sjekke resultat og formidle denne infoen til Oppgavesamling.
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
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
	 * F�rst egenskapane som gjeld objektet sj�lv, utanom grensesnittet.
	 */
	private String oppgaveOrd;
	private String oppgaveFasit;
	private int nivaa;
	private boolean besvart; // True n�r spelar har besvart oppg�va (er ferdig)
	private Oppgavesamling oppgavesamlinga;
	private boolean riktigBesvart;
	private String svar;
	private int antalTilgjengeligeForsok; 
	private int forsokBrukt; 

	/*
	 * Objektegenskapar - Her alt som har med grensesnittet � gjere.
	 */
	private JFormattedTextField inputSvar; // Tekstfelt der brukaren skriv svaret
	private boolean inputSvarErEndra; // Held styr p� om brukaren har skrive noko i input'en.
	private JPanel contentPane;  // JPanelet, dette vert bygd opp og vert sendt over til Oppgavesamling, som vi tek det vidare til hovudvinduet.
	private JButton btnLagre; // Knapp for � svare.
	// Nokre labels for � vise informasjon i grensesnittet. Brukar prefiks lbl for � lettare kjenne dei igjen.
	private JLabel label;
	private JLabel lblOppgave;
	private JLabel lblSvar;
	private JLabel lblOppgaveOrdet;

	/**
	 * Lokal main-metode for � teste objektet, utan � m�tte ha heile programmet klart.
	 * Er naturligvis overfl�dig n�r objektet vert laga av Oppgavesamling.
	 */
	public static void main(String[] args) {
		/* Denne koda er generert av Windowbuilder-plugin'en til Eclipse.
		 * Den forenklar grensesnittbygging veldig.
		 * Form�let med � bruke EventQueue og lage ein Runnable er � lage ein tr�d som k�yrer programmet.
		 * Fleirtr�d-k�yring er kanskje ikkje p�krevd med v�rt vesle program, men det skadar jo heller ikkje...   :)
		 */

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OppgavetypeInput frame = new OppgavetypeInput();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); // S�rgar for at feilmeldingar kjem med i consoll-vinduet. S�rs praktisk!
				}
			}
		});
	}
	/*
	 * Voila le konstrukt�r!
	 */
	public OppgavetypeInput() {
		//this("test","pr�ve",1,); // Puttar inn parameter-verdiar n�r objektet vert testa.
		System.out.println("Burde kanskje ikkje lage objekt utan parametrar?");
	}

	public OppgavetypeInput(String oppgaveOrd, String oppgaveSvar, int nivaa, Oppgavesamling oppgavesamlinga){

		this.oppgaveOrd = oppgaveOrd;
		this.oppgaveFasit = oppgaveSvar;
		this.nivaa = nivaa;
		//er dette riktig?
		this.setBesvart(true);
		this.oppgavesamlinga = oppgavesamlinga;
		/* I staden for � legge alt med grensesnitt her, slik Windowbuilder gjer det, vil eg ha ein rein konstrukt�r.
		 * Har ikkje f�tt lest meg heilt opp p� MVC enno, men dette er vel eit lite steg i den retning, � f� View-element bort fr� program-logikken.
		 */
		lagGrensesnittet();
		this.antalTilgjengeligeForsok = 2; // Dette kan jo vere en parameter til konstrukt�ren, d� m� l�rar velge antal fors�k i sin input( ?)
		this.forsokBrukt = 0;
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
		btnLagre.addActionListener(this); // Sett klassa sj�lv til eventListener for knappen.
		btnLagre.setBounds(475, 156, 80, 53);
		contentPane.add(btnLagre); // Add metoda til eit panel, legg til andre objekt i panelet.
		// Input felt for svar, coming up
		inputSvar = new JFormattedTextField();
		inputSvar.setText("test");
		inputSvarErEndra = false; // I utgangspunktet er input-feltet ikkje endra.
		inputSvar.setFont(new Font("Tahoma", Font.PLAIN, 30));
		inputSvar.setBounds(260, 156, 203, 53);
		contentPane.add(inputSvar);
		/* La til ein focus-listener her, som gjer at feltet blir blanka ut n�r nokon set mark�ren der.
		 * Men berre f�rste gong, finn det ikkje vidare brukarvenlig � t�me feltet KVAR gong det f�r fokus.
		 */
		inputSvar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(inputSvarErEndra==false){
					inputSvarErEndra = true;  // F�rste gong nokon klikkar i input-feltet, vert det blankt og markert endra.
					inputSvar.setText("");
				}
			}
		});

		// Label - Heading for oppg�va
		String oppgNr = (String) Integer.toString( this.oppgavesamlinga.getAktivOppgaveNR() );
		JLabel lblHeadingOppgavetype = new JLabel("Oppgave: "+oppgNr+". Oversett ord, niv�: ");
		lblHeadingOppgavetype.setFont(new Font("Dialog", Font.BOLD, 20));
		lblHeadingOppgavetype.setBounds(105, 12, 273, 47);
		contentPane.add(lblHeadingOppgavetype);
		// Label - Oppg�veordet
		label = new JLabel(Integer.toString(this.nivaa)); // Sett inn oppg�veordet.
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
		// Label - Oppg�veordet
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
		/* Denne blir trigga av knappen. Den pr�var alts� � sjekke svar.
		 * Men vi tek f�rst ein sjekk p� om brukar har skrive noko, ellers er det vel greit � anta at svaret er feil...
		 */
		AbstractButton source = (AbstractButton) arg0.getSource(); // Hent ut source-objektet
		String knappText =  source.getText(); // Hent teksta fr� knappen
		if(knappText.equals("neste oppg")){
			this.nesteOppgave();
		}else{
			if(this.inputSvarErEndra){
				sjekkSvar();
			}else{
				// Ein liten heads-up n�r ein pr�var svare blankt, har sentrert infoboksa over frame'n.
				JOptionPane.showMessageDialog(this,"Du b�r kanskje skrive eit svar f�rst?");
			}
		}
	}

	private void nesteOppgave(){
		this.oppgavesamlinga.nesteOppgave();

	}

	/* Test - new commit! */

	private void sjekkSvar() {
		String forslag = (String) this.inputSvar.getText();
		this.forsokBrukt++;
		
		if(forslag.equals(this.oppgaveFasit)){  // String-samanlikning, m� bruke equals() fra String-klassa, istaden for ==
			JOptionPane.showMessageDialog(this,"Hurra! You rule");  // Sv�rt pedagogisk riktig tilbakemelding.
			this.riktigBesvart = true;
			//Dag fors�ker � samle opp svar og riktige verdier
			this.nesteOppgave();
		}else{
			JOptionPane.showMessageDialog(this,"Please try again!");
			this.inputSvar.setText("");
			this.riktigBesvart = false;
			this.svar=forslag;
			//gir kun et fors�k per

			if(this.forsokBrukt >= this.antalTilgjengeligeForsok){
				JOptionPane.showMessageDialog(this,"Du har brukt opp dine fors�k. Riktig svar var:'"+this.oppgaveFasit+"'");
				this.nesteOppgave();
			}
			//inputSvarErEndra=false; // Nullstill, slik at brukaren kan pr�ve igjen
		}
	}

	public JPanel visOppgave(){
		// Sender rett og slett contentPane'et, alts� grensesnittet. Skal brukast av Oppgavesamling.
		return this.contentPane;
	}

	/*
	 * Set'ers og Get'ers m�'n ha, om objektet skal fungere bra.
	 * Ikkje s� mykje kvalitetssikring av objektets innkapsla egenskapar enno, men det kan jo kome...
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
	// riktigBesvart? 
	public boolean getRiktigBesvart(){
		return this.riktigBesvart;
	}
	// Elevens svar. 
	public String getElevensSvar()
	{
		return this.svar;
	}
}
