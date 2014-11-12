import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.Font;

import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLabel;


public class OppgavetypeInput extends JFrame implements ActionListener {

	/*
	 * Klassevariablar - objektegenskapar
	 */
	private String oppgaveOrd;
	private String oppgaveFasit;
	private int nivaa;
	private boolean besvart; // True når spelar har besvart oppgåva (er ferdig)

	/* 
	 * Objektegenskapar - Grensesnitt 
	 */
	private JFormattedTextField inputSvar;
	private boolean inputSvarErEndra; // Held styr på om brukaren har skrive noko i input'en.
	private JPanel contentPane;
	private JButton btnLagre;
	private JLabel label;
	private JLabel lblOppgave;
	private JLabel lblSvar;
	private JLabel lblOppgaveOrdet;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OppgavetypeInput frame = new OppgavetypeInput();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.  Konstruktør! 
	 */
	public OppgavetypeInput() {
		this("test","A test",1); // Blank konstruktør. Just in case... :)
		System.out.println("Burde kanskje ikkje lage objekt utan parametrar?");
	}
	
	public OppgavetypeInput(String oppgaveOrd, String oppgaveSvar, int nivaa){

		this.oppgaveOrd = oppgaveOrd;
		this.oppgaveFasit = oppgaveSvar;
		this.nivaa = nivaa;
		this.setBesvart(true);
		
		lagGrensesnittet();
		
	}

	private void lagGrensesnittet() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 283);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/*
		 * Knapp - svar
		 */
		btnLagre = new JButton("Svar");
		btnLagre.addActionListener(this); // Sett klassa sjølv til eventListener for knappen
		btnLagre.setBounds(475, 156, 80, 53);
		contentPane.add(btnLagre);
		/*
		 * Input felt for svar
		 */
		inputSvar = new JFormattedTextField();
		inputSvar.setText("test");
		inputSvarErEndra = false; // I utgangspunktet er input-feltet ikkje endra. 
		inputSvar.setFont(new Font("Tahoma", Font.PLAIN, 30));
		inputSvar.setBounds(260, 156, 203, 53);
		contentPane.add(inputSvar);
		inputSvar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(inputSvarErEndra==false){
					inputSvarErEndra = true;  // Første gong nokon klikkar i input-feltet, vert det blankt og markert endra. 
					inputSvar.setText("");
				}
			}
		});
		
		/*
		 * Label - Heading for oppgåva
		 */
		JLabel lblHeadingOppgavetype = new JLabel("Oversett ord, niv\u00E5: ");
		lblHeadingOppgavetype.setFont(new Font("Dialog", Font.BOLD, 30));
		lblHeadingOppgavetype.setBounds(105, 12, 273, 47);
		contentPane.add(lblHeadingOppgavetype);
		/*
		 * Label - Oppgåveordet 
		 */
		label = new JLabel(Integer.toString(this.nivaa)); // Sett inn oppgåveordet.
		label.setFont(new Font("Dialog", Font.BOLD, 30));
		label.setBounds(390, 12, 27, 47);
		contentPane.add(label);
		/*
		 * Label - ordet "Oppgave" framfor oppgaveordet
		 */
		lblOppgave = new JLabel("Oppgave:");
		lblOppgave.setFont(new Font("Dialog", Font.BOLD, 30));
		lblOppgave.setBounds(105, 97, 153, 47);
		contentPane.add(lblOppgave);
		/*
		 * Label - ordet "Svar" framfor inputSvar
		 */
		lblSvar = new JLabel("Svar:");
		lblSvar.setFont(new Font("Dialog", Font.BOLD, 30));
		lblSvar.setBounds(105, 156, 153, 47);
		contentPane.add(lblSvar);
		/*
		 * Label - Oppgåveordet 
		 */
		lblOppgaveOrdet = new JLabel(this.oppgaveOrd);
		lblOppgaveOrdet.setFont(new Font("Dialog", Font.BOLD, 30));
		lblOppgaveOrdet.setBounds(260, 97, 153, 47);
		contentPane.add(lblOppgaveOrdet);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		/*
		 * Denne blir trigga av knappen. Den prøvar altså å sjekke svar. 
		 * Men vi tek først ein sjekk på om brukar har skrive noko, ellers gidd vi ikkje. 
		 */
		if(this.inputSvarErEndra){
			sjekkSvar();
		}else{
			JOptionPane.showMessageDialog(this,"Du bør kanskje skrive eit svar først?");
		}
	}

	private void sjekkSvar() {
		// TODO Auto-generated method stub
		//return true;
		String forslag = (String) this.inputSvar.getText();
		if(forslag.equals(this.oppgaveFasit)){  // HUSK altså at String ikkje kan samanliknast med ==, må bruke  equals-metoda, sikkert fordi det er eit objekt. 
			JOptionPane.showMessageDialog(this,"Hurra! You rule");
		}else{
			JOptionPane.showMessageDialog(this,"Meeeeh... Prøv igjen! Svaret er:\n '"+this.oppgaveFasit+"' \n Du skreiv:\n '"+forslag.toString()+"'");
			this.inputSvar.setText("");
			inputSvarErEndra=false; // Nullstill, slik at brukaren kan prøve igjen
		}
	}

	
	/*
	 * Set'ers og Get'ers
	 */
	private void setBesvart(boolean b) {
		this.besvart = b;		
	}
	
	public boolean getBesvart() {
		return besvart;
	}
}