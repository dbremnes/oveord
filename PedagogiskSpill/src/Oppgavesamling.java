//Oppgavesamling
/* Denne klassa skal instansiere fleire objekt av typen â€œoppgaveâ€?, alts� eit sett med oppg�ver (eller ei samling om du vil). Desse kan s� testast og vil etterkvart innehalde sine poengsummar. Denne klassa vil dermed kunne innehalde aggregert poengsum og kommunisere med brukar-objektet om � oppgradere level, tilføre nye poeng (prosentar kanskje?)  */

import java.awt.Panel;
import java.awt.Window;
import java.util.Scanner;
import java.math.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;



import javax.swing.JOptionPane;

public class Oppgavesamling {

	private Scanner fil;
	private OppgavetypeInput[] oppgaver;
	private Panel grensesnitt;
	//private int spillerNivaa;
	private int aktivOppgaveNR;
	private Hovedklasse vindu;
	private int antallOppgaver;
	private int antallRiktigeSvar;
	private Spilleren spiller;
	private String riktigBesvart="";
	private String feilBesvart="";

	//private int oppgaver_sorteringsliste[]; // Innheld sorteringsrekkefølga, produsert av metode sorter() Ser ikke at denne trengs

	public Oppgavesamling(String filNavn, Hovedklasse vindu) {
		// Konstruktøren

		this.vindu = vindu; // Sett peikaren fr� konstruktør-parameteret til klassevariabel.
		this.spiller=vindu.spiller; // lag peikar til spiller-objektet fra hovudvinduet
		//spillerNivaa=spilleren.getNivaa();
		//DEBUG: JOptionPane.showMessageDialog(null,  "Niv�:"+this.spiller.getNivaa(), null, JOptionPane.PLAIN_MESSAGE );

		try {
	        this.fil=new Scanner(new File(filNavn));
			 // Hentar inn "laerersord.txt" (el.l.), som Kristina produserer i sin klasse, dette m� vel utvides og endres med varierende oppgaver
	        this.oppgaver = new OppgavetypeInput[100];
	        this.lesInnOppgaveObjekt();  // Lag objekt p� bakgrunn av fila
			this.sorterOppgaver("alfabetisk");  // Sorter dei


		 } // end try
	 	catch ( FileNotFoundException fileNotFoundException ){
				lagFil(filNavn,"");
	 			JOptionPane.showMessageDialog(null,  "Feil ved �pning av fila.", null, JOptionPane.PLAIN_MESSAGE );
	 			return;
		} // end catch
		this.aktivOppgaveNR=0;
		this.visAktivOppgave();

	}

	private void lesInnOppgaveObjekt() {
		int oppgaveTeller=0;
		while(this.fil.hasNext()){
		 // instansier eit oppgave-objekt, i oppg-matrisen. F�r objektet med data fr� input-fila.
			String oppgaveOrd = this.fil.next();
			String oppgaveFasit = this.fil.next();
			int nivaa = Integer.parseInt(this.fil.next());
			//oppgavene skal bare legges til om niv�et stemmer
			if(this.spiller.getNivaa()==nivaa){
				// This er lagt til, fordi oppg�va skal kunne trigge "neste oppg�ve" her i oppg�vesamlinga.
				this.oppgaver[oppgaveTeller] = new OppgavetypeInput(oppgaveOrd,oppgaveFasit,nivaa,this);
				oppgaveTeller++;
			}
		}
		//variabler for kontroll av andel korrekte svar
		this.antallOppgaver= oppgaveTeller-1;
	}

	private void sorterOppgaver(String type)
	{
		/*
		 * Endar med at arrayen oppgaver_sorteringsliste har ID'en til oppgave-objekta, "riktig" sortert inni seg (alfabetisk eller...)
		 */
		 int k;
		 int j;
		//switch(type){
			//case	"random":

				for(int i=1;i>this.oppgaver.length;i++)
				{
					// Loop og lag ei tilfeldig liste
					k = (int) Math.random()*this.oppgaver.length;
					oppgaver[this.oppgaver.length]=this.oppgaver[i];
					oppgaver[i]=oppgaver[k];
					oppgaver[k]=oppgaver[this.oppgaver.length];
				}
				aktivOppgaveNR=0;
		}

	public void visAktivOppgave(){
		/*
		 *  Viser oppgava som skal vere aktiv.
		 */
		this.vindu.setContentPane( oppgaver[this.aktivOppgaveNR].visOppgave() );
		this.vindu.validate();
	}

	public void nesteOppgave(){
		// Denne vert trigga av oppgave-objektet, n�r neste oppg�ve skal visast.
		//sjekker om alle oppgaver p� niv�et er løst
		if(this.aktivOppgaveNR==this.antallOppgaver){
			this.antallRiktigeSvar=0;
			for(int i=0; i<this.antallOppgaver; i++){
				if (this.oppgaver[i].getRiktigBesvart()){
					antallRiktigeSvar++;
					riktigBesvart+=this.oppgaver[i].getOppgaveOrd();
					JOptionPane.showMessageDialog(null, "Riktig:"+riktigBesvart, "", JOptionPane.PLAIN_MESSAGE );
				}else{
					feilBesvart+=this.oppgaver[i].getOppgaveOrd()+" "+this.oppgaver[i].getElevensSvar();
					JOptionPane.showMessageDialog(null, "Feil: "+feilBesvart, "", JOptionPane.PLAIN_MESSAGE );					
				}
			}
			//finner prosentandel riktige svar
			double andel=this.antallRiktigeSvar/antallOppgaver;
			//endrer niv�et og oppgave utvalg om niv�et er over 80%
			if (andel>0.8){
				int nyttNivaa = this.spiller.nivaaOpp();
				//hva skal skje i tillegg?
				//noe kult m� skje
				//sjekk om alt er riktig
				if(andel==1){
					//noe EKSTRA kult skal skje, vedkomande har full pott.
				}//end if
			}

			/*
			 * Skriv resultatet til fil, p� m�nsteret Resultat_Nivaa_Elevens_Navn.txt
			 */
				String filnavnet= "Resultat_"+Integer.toString(this.spiller.getNivaa())+"_"+this.spiller.getFornavn()+"_"+this.spiller.getEtternavn()+".txt";
				// Lag klar string til fila, alts� innhaldet. 
				String filInnholdet = "Antall riktige svar: "+antallRiktigeSvar+"\n";			
				filInnholdet += "Riktig besvart oppgaver er: "+riktigBesvart+"\n";
				filInnholdet += "Feil besvarte oppgaver er:"+feilBesvart+"\n";
				lagFil(filnavnet,filInnholdet);
				
				JOptionPane.showMessageDialog(null, filnavnet, "", JOptionPane.PLAIN_MESSAGE );

				// Kva skal skje n�r spelet er over.
				JOptionPane.showMessageDialog(null, "Takk for n�, velkommen igjen!", "Takk!", JOptionPane.PLAIN_MESSAGE );

			vindu.dispose();
	}else{
		//henter frem oppgavene igjen
			// this.lesInnOppgaveObjekt();
			// this.sorterOppgaver("alfabetisk");
			this.aktivOppgaveNR++;
			this.visAktivOppgave();
	}
}

	public static void lagFil(String filNavn,String innhold)
	{
			Formatter output = null;
			try
			{
			    //exception handling left as an exercise for the reader
				FileWriter fileWriter = new FileWriter(filNavn, true);
				output = new Formatter (fileWriter);//Ã‚pner fila for skriving, hvis den ikke finnes, opprettes den
			}
			catch ( SecurityException securityException )
			{
				JOptionPane.showMessageDialog(null, "", "Du har ikke skriverettigheter til denne fila", JOptionPane.PLAIN_MESSAGE );
				System.exit( 1 ); // avslutter programmet

			}
			catch ( FileNotFoundException fileNotFoundException )
			{
				JOptionPane.showMessageDialog(null, "", "Feil ved Ã‚pning av fila", JOptionPane.PLAIN_MESSAGE );
				System.exit( 1 ); // avslutter programmet
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			// opprett filen
			output.format("%s", innhold);


			if ( output != null)
				output.close();

	}

	public int getAntallRiktigeSvar() {
		return this.antallRiktigeSvar;
	}

	public void setAntallRiktigeSvar(int antallRiktigeSvar) {
		this.antallRiktigeSvar = antallRiktigeSvar;
	}

	public int getAktivOppgaveNR() {
		return aktivOppgaveNR;
	}

	public void setAktivOppgaveNR(int aktivOppgaveNR) {
		this.aktivOppgaveNR = aktivOppgaveNR;
	}

	public int getAntalOppgaver(){
	/*	int antallOppgaver=0; 
		for(int i=0; i<this.oppgaver.length; i++){
			if(this.oppgaver[i]!=null) antallOppgaver++;
		}
		JOptionPane.showMessageDialog(null, "", Integer.toString(antallOppgaver), JOptionPane.PLAIN_MESSAGE );
		*/	
		return this.antallOppgaver; 
	}
}
/*
 *	Ikkje tatt i bruk.
 *
 * 	public int countLines(String filename) throws IOException {

	    LineNumberReader reader  = new LineNumberReader(new FileReader(filename));
	int cnt = 0;
	String lineRead = "";
	while ((lineRead = reader.readLine()) != null) {}

	cnt = reader.getLineNumber();
	reader.close();
	return cnt;
	}
  */
