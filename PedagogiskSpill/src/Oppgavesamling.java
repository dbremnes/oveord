//Oppgavesamling
/* Denne klassa skal instansiere fleire objekt av typen â€œoppgaveâ€?, altså eit sett med oppgåver (eller ei samling om du vil). Desse kan så testast og vil etterkvart innehalde sine poengsummar. Denne klassa vil dermed kunne innehalde aggregert poengsum og kommunisere med brukar-objektet om å oppgradere level, tilføre nye poeng (prosentar kanskje?)  */

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
	private String riktigBesvart="Riktig besvart oppgaver er: ";
	private String feilBesvart="Feil besvarte oppgaver er ";

	//private int oppgaver_sorteringsliste[]; // Innheld sorteringsrekkefølga, produsert av metode sorter() Ser ikke at denne trengs

	public Oppgavesamling(String filNavn, Hovedklasse vindu) {
		// Konstruktøren

		this.vindu = vindu; // Sett peikaren frå konstruktør-parameteret til klassevariabel.
		this.spiller=vindu.spiller; // lag peikar til spiller-objektet fra hovudvinduet
		//spillerNivaa=spilleren.getNivaa();
			JOptionPane.showMessageDialog(null,  "Nivå:"+this.spiller.getNivaa(), null, JOptionPane.PLAIN_MESSAGE );

		try {
	        this.fil=new Scanner(new File(filNavn));
			 // Hentar inn "laerersord.txt" (el.l.), som Kristina produserer i sin klasse, dette må vel utvides og endres med varierende oppgaver
	        this.oppgaver = new OppgavetypeInput[100];
	        this.lesInnOppgaveObjekt();  // Lag objekt på bakgrunn av fila
			this.sorterOppgaver("alfabetisk");  // Sorter dei


		 } // end try
	 	catch ( FileNotFoundException fileNotFoundException ){
				lagFil(filNavn,"");
	 			JOptionPane.showMessageDialog(null,  "Feil ved åpning av fila.", null, JOptionPane.PLAIN_MESSAGE );
	 			return;
		} // end catch
		this.aktivOppgaveNR=0;
		this.visAktivOppgave();

	}

	private void lesInnOppgaveObjekt() {
		int oppgaveTeller=0;
		while(this.fil.hasNext()){
		 // instansier eit oppgave-objekt, i oppg-matrisen. Får objektet med data frå input-fila.
			String oppgaveOrd = this.fil.next();
			String oppgaveFasit = this.fil.next();
			int nivaa = Integer.parseInt(this.fil.next());
			//oppgavene skal bare legges til om nivået stemmer
			if(this.spiller.getNivaa()==nivaa){
				// This er lagt til, fordi oppgåva skal kunne trigge "neste oppgåve" her i oppgåvesamlinga.
				this.oppgaver[oppgaveTeller] = new OppgavetypeInput(oppgaveOrd,oppgaveFasit,nivaa,this);
				oppgaveTeller++;
			}
		}
		//variabler for kontroll av andel korrekte svar
		antallOppgaver=oppgaveTeller;
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
		 *  Viser oppgåva som skal vere aktiv.
		 */
		this.vindu.setContentPane( oppgaver[this.aktivOppgaveNR].visOppgave() );
		this.vindu.validate();
	}

	public void nesteOppgave(){
		// Denne vert trigga av oppgave-objektet, når neste oppgåve skal visast.
		//sjekker om alle oppgaver på nivået er løst
		if(this.aktivOppgaveNR==this.antallOppgaver-1){
			this.antallRiktigeSvar=0;
			for(int i=1;i>this.oppgaver.length;i++){
				if(this.oppgaver[i].getRiktigBesvart()) this.antallRiktigeSvar++;
			}
			//finner prosentandel riktige svar
			double andel=this.antallRiktigeSvar/antallOppgaver;
			//endrer nivået og oppgave utvalg om nivået er over 80%
			if (andel>0.8){
				int nyttNivaa = this.spiller.nivaaOpp();
				//hva skal skje i tillegg?
				//noe kult må skje

				//sjekk om alt er riktig
				if(andel==1){
					//noe ekstra kult skal skje
				}//end if

			}
			// Kva skal skje når spelet er over.
			JOptionPane.showMessageDialog(null, "", "Takk for alt!", JOptionPane.PLAIN_MESSAGE );

			// Køyre metode for å loope gjennom oppgavene og skriv ut i resultat-fil .
			for (int i=0; i<this.oppgaver.length; i++)
			{
				if (this.oppgaver[i].getRiktigBesvart()){
					antallRiktigeSvar++;
					riktigBesvart+=this.oppgaver[i].getOppgaveOrd();
				}else{
					feilBesvart+=this.oppgaver[i].getOppgaveOrd()+" "+this.oppgaver[i].getElevensSvar();
				}
			}
				String filnavnet=this.spiller.getNavn()+".txt";
				riktigBesvart+="antall riktig besvarte oppgaver er "+antallRiktigeSvar+"\n";
				String skrivInformasjon=riktigBesvart+"\n"+feilBesvart;
				lagFil(filnavnet,skrivInformasjon);
				JOptionPane.showMessageDialog(null, "", filnavnet, JOptionPane.PLAIN_MESSAGE );


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

}
/*
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
