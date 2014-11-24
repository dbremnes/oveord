//Oppgavesamling
/* Denne klassa skal instansiere fleire objekt av typen “oppgave”, altså eit sett med oppgåver (eller ei samling om du vil). Desse kan så testast og vil etterkvart innehalde sine poengsummar. Denne klassa vil dermed kunne innehalde aggregert poengsum og kommunisere med brukar-objektet om å oppgradere level, tilføre nye poeng (prosentar kanskje?)  */

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
	private Oppgavetype_input oppgaver[];
	private Panel grensesnitt;
	int spillerNummer;
	int spillerNivaa=1;
	int aktivoppgave;
	private static int oppgaveTeller=0;

	//private int oppgaver_sorteringsliste[]; // Innheld sorteringsrekkefølga, produsert av metode sorter() Ser ikke at denne trengs


	public Oppgavesamling(String filen, int spillerNR, Hovedvindu vindu) {
		/*
		 * Konstruktøren
		 */

		 //spillerNivaa=spiller.getNivaa(spillerNR);
		//super(); //hva er dette?
		// Muligens redundant, kan sikker hente ut filnavnet frå this.fil, men tek med inntil vidare:


		try
		{
		Scanner fil;
        fil=new Scanner(new File(filen));

		 // Hentar inn "innlesteOppgaver.txt" (el.l.), som Kristina produserer i sin klasse, dette må vel utvides og endres med varierende oppgaver

		lesInnOppgaveObjekt(oppgaveTeller);  // Lag objekt på bakgrunn av fila
		sorterOppgaver("alfabetisk");  // Sorter dei
		/*
		 * "Alfabetisk" bør helst velgast via lærar si innlegging, eller om lærar vil, være eit alternativ i oppgavesamling-grensesnittet.
		 */
		avgrensUtvalget((int) Math.random()*10);
		/*
		 * Avgrensing bør være lagt inn av lærar, eller kunne velgast av spelar, akkurat som sortering.
		 */
		 } // end try
		 	catch ( FileNotFoundException fileNotFoundException )
		 		{
		 			JOptionPane.showMessageDialog(null,  "Feil ved åpning av fila.", null, JOptionPane.PLAIN_MESSAGE );
		 			return;
		} // end catch

	}

	private void lesInnOppgaveObjekt(int teller) {
		while(fil.hasNext()){
		//JOptionPane.showMessageDialog(null, "har vi noe info?"+fil.next());
		 // instansier eit oppgave-objekt, i oppg-matrisen. Fôr objektet med data frå input-fila.
		 String norskOrd=fil.next();
		 String engelskOrd=fil.next();
		 int nivaaet=Integer.parseInt(fil.next());
			oppgaver[teller] = new Oppgavetype_input(norskOrd,engelskOrd,nivaaet);
			//oppgavene skal bare legges til om nivået stemmer
			//if(spillerNivaa==oppgaver[oppgaveTeller].getNivaa)
				teller++;
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
				// Sorter elementa slik dei vart skrive inn av læraren, altså etter key i oppgave-arrayen.
		}

	private void avgrensUtvalget(int antall){
		// kutt arrayen etter "antall" element, altså skal vi ikkje nødvendigvis vise alle oppgaver fra tekstfila
		if (antall>this.oppgaver.length)
		{
			for (int i=antall; i==this.oppgaver.length-1; i++)
			{
				oppgaver[i]=null;
			}
		}
	}

	public Panel visOppgave(){
		/*
		 *
		 */
			int oppgaveNummer=0;

			Panel aktivtOppgavePanel = oppgaver[oppgaveNummer].visOppgave();
			grensesnitt.add(aktivtOppgavePanel);
			besvartbutton.addlistner();
			if (besvart.change)
				{
					oppgaver[oppgaveNummer].setbesvart=true;
					if(svar==oppgaver[oppgaveNummer].getSvar)
						spiller.setpoeng=oppgave[oppgaveNummer].getPoeng;
					return aktivtOppgavePanel;
				}

				// vente, dette er muligens ikkje så smart.
				// Bør nok bytte dette ut med eventlistener
			}

// Fjernar panelet, så neste oppgåve kan visast
			//grensesnitt.remove(aktivtOppgavePanel);
		//grensesnitt.add( visOppgaveSporsmaalet() ); // Vi legg til spørsmåldelen.
		//grensesnitt.add( visOppgaveSvarInputPanel() ); // Og svardelen...
		//grensesnitt.add( visSvarPaaOppgavePanel() ); // Og send-svar-delen.
		//return grensesnitt;
