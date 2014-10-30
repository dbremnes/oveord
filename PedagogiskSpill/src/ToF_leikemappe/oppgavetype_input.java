package ToF_leikemappe;

import java.awt.Container;
import java.awt.Panel;

public class oppgavetype_input {

	private String oppgaveOrd;
	private String oppgaveSvar;
	private int nivaa;
	private boolean besvart; // True når spelar har besvart oppgåva (er ferdig)
	private Panel grensesnitt;
	
	
	public oppgavetype_input(String oppgaveOrd, String oppgaveSvar, int nivaa) {
		/*
		 * Konstruktøren. Initialiserer egenskapane til objektet. 
		 */
		super();
		this.oppgaveOrd = oppgaveOrd;
		this.oppgaveSvar = oppgaveSvar;
		this.nivaa = nivaa;
		this.setBesvart(true);
	}
	
	public Panel visOppgave(){
		
		/*  Inspirert av: http://cs.nyu.edu/~yap/classes/visual/03s/lect/l7/
		 * Ideen her, som kanskje ikkje er smart, er at denne metoden returnerer eit panel. 
		 * ut frå tanken om at det er eit element som kan puttast inn i eit vindu. 
		 */ 
		grensesnitt.add( this.visOppgaveSporsmaalet() ); // Vi legg til spørsmåldelen.
		grensesnitt.add( this.visOppgaveSvarInputPanel() ); // Og svardelen...
		grensesnitt.add( this.visSvarPaaOppgavePanel() ); // Og send-svar-delen. 
		return grensesnitt; 
	}
	
	private Panel visOppgaveSporsmaalet(){
		Panel oppgaveSporsmaalPanelet = null; // Må finne riktig type panel. 
		/*
		 * Intern metode for å lage layouten for spørsmålet, slik det blir presentert.
		 * Her blir det laga fleire panel, som blir organisert via ulike layouts. 
		 */
		return oppgaveSporsmaalPanelet; 
	}
	
	private Panel visOppgaveSvarInputPanel(){
		Panel oppgaveSvarInputPanelet = null; // Må finne riktig type panel. 
		/*
		 * Intern metode for å lage layouten for SVAR-input.
		 * Her blir det laga fleire panel, som blir organisert via ulike layouts. 
		 *	Det vil nok i versjon 1 være ein JOptionPane.showInputDialog(), berre for å få inn data enkelt
		 */
		return oppgaveSvarInputPanelet;
		
	}
	private Panel visSvarPaaOppgavePanel(){
		Panel sendSvarPanelet = null; // Må finne riktig type panel. 
		/*
		 * Innheld ein "Send-svar" button, som har eventlistener på å setje oppgåva "besvart"
		 * Overflødig i versjon 1 om en brukar JOptionPane.showInputDialog() til svaret. 
		 */
		return sendSvarPanelet;
		
	}

	public boolean getBesvart() {
		return besvart;
	}
	
}
