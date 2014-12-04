/*
 * Klasse OppgavesamlingInput, av Torbjørn Frantsen
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
import java.awt.Color;
import java.awt.Window.Type;
// import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.ImageIcon;


public class OppgavetypeInput extends JFrame implements ActionListener {
	/*
	 * Klassevariablar - objektegenskapar
	 * Først egenskapane som gjeld objektet sjølv, utanom grensesnittet.
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
	private JLabel lblNivaa;
	private JLabel lblOppgave;
	private JLabel lblSvar;
	private JLabel lblOppgaveOrdet;
	private JButton btnNesteOppg;
	private JLabel lblOppgaveNr;


	public static void main(String[] args) {
		/* Denne koda er generert av Windowbuilder-plugin'en til Eclipse.
		 * Den forenklar grensesnittbygging veldig.
		 * Form�let med � bruke EventQueue og lage ein Runnable er � lage ein tr�d som køyrer programmet.
		 * Fleirtr�d-køyring er kanskje ikkje p�krevd med v�rt vesle program, men det skadar jo heller ikkje...   :)
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
	/*
	 * Voila le konstruktør!
	 */
	public OppgavetypeInput() {
	
		this("test","prøve",1,null); // Puttar inn parameter-verdiar n�r objektet vert testa.
		System.out.println("Burde kanskje ikkje lage objekt utan parametrar?");
	}

	public OppgavetypeInput(String oppgaveOrd, String oppgaveSvar, int nivaa, Oppgavesamling oppgavesamlinga){
		this.oppgaveOrd = oppgaveOrd;
		this.oppgaveFasit = oppgaveSvar;
		this.nivaa = nivaa;
		//er dette riktig?
		this.setBesvart(true);
		this.oppgavesamlinga = oppgavesamlinga;
		/* I staden for � legge alt med grensesnitt her, slik Windowbuilder gjer det, vil eg ha ein rein konstruktør.
		 * Har ikkje f�tt lest meg heilt opp p� MVC enno, men dette er vel eit lite steg i den retning, � f� View-element bort fr� program-logikken.
		 */
		lagGrensesnittet();
		this.antalTilgjengeligeForsok = 2; // Dette kan jo vere en parameter til konstruktøren, d� m� lærar velge antal forsøk i sin input( ?)
		this.forsokBrukt = 0;

	}

	private void lagGrensesnittet() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 704);
		this.contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/*
		 * Knapp - svar
		 */
		btnLagre = new JButton("Svar");
		btnLagre.setForeground(Color.BLACK);
		btnLagre.addActionListener(this); // Sett klassa sjølv til eventListener for knappen.
		// Label - Oppg�veordet
		lblOppgaveOrdet = new JLabel(this.oppgaveOrd);
		lblOppgaveOrdet.setBackground(Color.ORANGE);
		lblOppgaveOrdet.setFont(new Font("Dialog", Font.BOLD, 30));
		lblOppgaveOrdet.setBounds(206, 84, 256, 31);
		contentPane.add(lblOppgaveOrdet);
		btnLagre.setBounds(498, 122, 111, 37);
		contentPane.add(btnLagre); // Add metoda til eit panel, legg til andre objekt i panelet.
		// Input felt for svar, coming up
		inputSvar = new JFormattedTextField();
		inputSvar.setBackground(Color.WHITE);
		inputSvar.setText("");
		inputSvarErEndra = false; // I utgangspunktet er input-feltet ikkje endra.
		inputSvar.setFont(new Font("Tahoma", Font.PLAIN, 30));
		inputSvar.setBounds(206, 116, 260, 43);
		contentPane.add(inputSvar);
		/* La til ein focus-listener her, som gjer at feltet blir blanka ut n�r nokon set markøren der.
		 * Men berre første gong, finn det ikkje vidare brukarvenlig � tøme feltet KVAR gong det f�r fokus.
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

		// Label - Heading for oppg�va
		JLabel lblHeadingOppgavetype = new JLabel("Gloseposen");
		lblHeadingOppgavetype.setFont(new Font("Dialog", Font.BOLD, 30));
		lblHeadingOppgavetype.setBounds(38, 16, 312, 52);
		contentPane.add(lblHeadingOppgavetype);
		// Label - Oppg�veordet
		lblNivaa = new JLabel(Integer.toString(this.nivaa)); // Sett inn oppg�veordet.
		lblNivaa.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNivaa.setBounds(557, 27, 27, 20);
		contentPane.add(lblNivaa);
		// Label - ordet "Svar" framfor inputSvar
		lblSvar = new JLabel("Ditt svar:");
		lblSvar.setFont(new Font("Dialog", Font.BOLD, 22));
		lblSvar.setBounds(38, 117, 153, 47);
		contentPane.add(lblSvar);

		btnNesteOppg = new JButton("neste oppg");
		btnNesteOppg.addActionListener(this);
		btnNesteOppg.setBounds(507, 601, 111, 31);
		contentPane.add(btnNesteOppg);
		
		JLabel lblLevelellerNiv = new JLabel("Niv�:");
		lblLevelellerNiv.setBounds(498, 27, 47, 20);
		contentPane.add(lblLevelellerNiv);
		// Label - ordet "Oppgave" framfor oppgaveordet
		lblOppgave = new JLabel("Hva betyr");
		lblOppgave.setForeground(Color.BLACK);
		lblOppgave.setFont(new Font("Dialog", Font.BOLD, 22));
		lblOppgave.setBounds(38, 73, 127, 47);
		contentPane.add(lblOppgave);

		// Label for OppgaveNr / Antaloppgaer-feltet.
		String oppgNr = (String) Integer.toString( this.oppgavesamlinga.getAktivOppgaveNR()+1 );
		JOptionPane.showMessageDialog(this,oppgNr);
		String oppgTotalt = Integer.toString( this.oppgavesamlinga.getAntalOppgaver() );
		lblOppgaveNr = new JLabel(oppgNr+"/"+oppgTotalt);
		lblOppgaveNr.setFont(new Font("Dialog", Font.BOLD, 16));
		lblOppgaveNr.setBounds(557, 59, 47, 22);
		contentPane.add(lblOppgaveNr);
		
		JLabel lblOppg = new JLabel("Ord:");
		lblOppg.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblOppg.setBounds(498, 60, 50, 20);
		contentPane.add(lblOppg);
		
		JLabel bakgrunnsbilde = new JLabel("");
		bakgrunnsbilde.setIcon(new ImageIcon("bilete/sun-151763_640.png"));
		bakgrunnsbilde.setBounds(-4, 57, 640, 640);
		contentPane.add(bakgrunnsbilde);
		// contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{inputSvar, btnLagre, btnNesteOppg}));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		/* Denne blir trigga av knappen. Den prøvar alts� � sjekke svar.
		 * Men vi tek først ein sjekk p� om brukar har skrive noko, ellers er det vel greit � anta at svaret er feil...
		 */
		AbstractButton source = (AbstractButton) arg0.getSource(); // Hent ut source-objektet
		String knappText =  source.getText(); // Hent teksta fr� knappen
		if(knappText.equals("neste oppg")){
			this.nesteOppgave();
		}else{
			if(this.inputSvarErEndra){
				sjekkSvar();
			}else{
				// Ein liten heads-up n�r ein prøvar svare blankt, har sentrert infoboksa over frame'n.
				JOptionPane.showMessageDialog(this,"Du bør kanskje skrive eit svar først?");
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
			JOptionPane.showMessageDialog(this,"Hurra! You rule");  // Svært pedagogisk riktig tilbakemelding.
			this.riktigBesvart = true;
			//Dag forsøker � samle opp svar og riktige verdier
			this.nesteOppgave();
		}else{
			JOptionPane.showMessageDialog(this,"Please try again!");
			this.inputSvar.setText("");
			this.riktigBesvart = false;
			this.svar=forslag;
			//gir kun et forsøk per

			if(this.forsokBrukt >= this.antalTilgjengeligeForsok){
				JOptionPane.showMessageDialog(this,"Du har brukt opp dine forsøk. Riktig svar var:'"+this.oppgaveFasit+"'");
				this.nesteOppgave();
			}
			//inputSvarErEndra=false; // Nullstill, slik at brukaren kan prøve igjen
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
