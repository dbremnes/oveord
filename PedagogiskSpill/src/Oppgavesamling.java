//Oppgavesamling
/* Denne klassa skal instansiere fleire objekt av typen â€œoppgaveâ€�, altsÃ¥ eit sett med oppgÃ¥ver (eller ei samling om du vil). Desse kan sÃ¥ testast og vil etterkvart innehalde sine poengsummar. Denne klassa vil dermed kunne innehalde aggregert poengsum og kommunisere med brukar-objektet om Ã¥ oppgradere level, tilfÃ¸re nye poeng (prosentar kanskje?)  */

import java.awt.Panel;
import java.awt.Window;
import java.util.Scanner;
import java.math.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;


import javax.swing.JOptionPane;

public class Oppgavesamling {

	private Scanner fil;
	private OppgavetypeInput[] oppgaver = new OppgavetypeInput[100];
	private Panel grensesnitt;
	int spillerNummer;
	int spillerNivaa=1;
	int aktivoppgave;
	private Hovedklasse vindu;

	//private int oppgaver_sorteringsliste[]; // Innheld sorteringsrekkefÃ¸lga, produsert av metode sorter() Ser ikke at denne trengs


	public Oppgavesamling(String filen, int spillerNR, Hovedklasse vindu) {
		/*
		 * Konstruktøren
		 */
		this.vindu = vindu; // Sett peikaren frå konstruktør-parameteret til klassevariabel. 
		 //spillerNivaa=spiller.getNivaa(spillerNR);
		//super(); //hva er dette?
		// Muligens redundant, kan sikker hente ut filnavnet frÃ¥ this.fil, men tek med inntil vidare:
		try
		{
        this.fil=new Scanner(new File(filen));
		 // Hentar inn "innlesteOppgaver.txt" (el.l.), som Kristina produserer i sin klasse, dette mÃ¥ vel utvides og endres med varierende oppgaver
		this.lesInnOppgaveObjekt();  // Lag objekt pÃ¥ bakgrunn av fila
		this.sorterOppgaver("alfabetisk");  // Sorter dei
		/*
		 * "Alfabetisk" bÃ¸r helst velgast via lÃ¦rar si innlegging, eller om lÃ¦rar vil, vÃ¦re eit alternativ i oppgavesamling-grensesnittet.
		 */
		this.avgrensUtvalget((int) Math.random()*10);
		/*
		 * Avgrensing bÃ¸r vÃ¦re lagt inn av lÃ¦rar, eller kunne velgast av spelar, akkurat som sortering.
		 */
		 } // end try
		 	catch ( FileNotFoundException fileNotFoundException )
		 		{
		 			JOptionPane.showMessageDialog(null,  "Feil ved Ã¥pning av fila.", null, JOptionPane.PLAIN_MESSAGE );
		 			return;
		} // end catch
		this.aktivoppgave=0;
		this.visAktivOppgave();

	}

	private void lesInnOppgaveObjekt() {
		int oppgaveTeller=0;
		while(this.fil.hasNext()){
		 // instansier eit oppgave-objekt, i oppg-matrisen. FÃ´r objektet med data frÃ¥ input-fila.
			String oppgaveOrd = this.fil.next();
			String oppgaveFasit = this.fil.next();
			int nivaa = Integer.parseInt(this.fil.next());
			/* Debug for å sjå kva som vert lest fra fila:
			System.out.println(oppgaveOrd+"."+oppgaveFasit+"."+nivaa+"."); */
			// Legg faktisk oppgaveobjekt_type_input i arrayen. 
			// This er lagt til, fordi oppgåva skal kunne trigge "neste oppgåve" her i oppgåvesamlinga.
			this.oppgaver[oppgaveTeller] = new OppgavetypeInput(oppgaveOrd,oppgaveFasit,nivaa,this);
			//oppgavene skal bare legges til om nivÃ¥et stemmer
			//if(spillerNivaa==oppgaver[oppgaveTeller].getNivaa)
			oppgaveTeller++;
		}
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
				//break;
			//case 	"alfabetisk":
				// Sorter elementa i array alfabetisk.
				//break;
			//default:
				// Sorter elementa slik dei vart skrive inn av lÃ¦raren, altsÃ¥ etter key i oppgave-arrayen.
		}

	private void avgrensUtvalget(int antall){
		// kutt arrayen etter "antall" element, altsÃ¥ skal vi ikkje nÃ¸dvendigvis vise alle oppgaver fra tekstfila
		if (antall>this.oppgaver.length)
		{
			for (int i=antall; i==this.oppgaver.length-1; i++)
			{
				oppgaver[i]=null;
			}
		}
	}

	public void visAktivOppgave(){
		/*
		 *  Viser oppgåva som skal vere aktiv. 
		 */
			this.vindu.setContentPane( oppgaver[this.aktivoppgave].visOppgave() );
	}
	public void nesteOppgave(){
		// Denne vert trigga av oppgave-objektet, når neste oppgåve skal visast. 
		this.aktivoppgave++;
		this.visAktivOppgave();
		
	}
}