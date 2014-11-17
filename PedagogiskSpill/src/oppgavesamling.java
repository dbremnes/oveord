//Oppgavesamling
/* Denne klassa skal instansiere fleire objekt av typen “oppgave”, altså eit sett med oppgåver (eller ei samling om du vil). Desse kan så testast og vil etterkvart innehalde sine poengsummar. Denne klassa vil dermed kunne innehalde aggregert poengsum og kommunisere med brukar-objektet om å oppgradere level, tilføre nye poeng (prosentar kanskje?)  */

import java.awt.Panel;
import java.awt.Window;
import java.util.Scanner;
import java.math.*;

import javax.swing.JOptionPane;

public class oppgavesamling {

	private Scanner fil;
	private oppgavetype_input oppgaver[];
	private Panel grensesnitt;
	int spillerNummer;
	int spillerNivaa=1;
	int aktivoppgave;

	//private int oppgaver_sorteringsliste[]; // Innheld sorteringsrekkefølga, produsert av metode sorter() Ser ikke at denne trengs


	public oppgavesamling(Scanner fil, String spillerNR) {
		/*
		 * Konstruktøren
		 */
		
		 //spillerNivaa=spiller.getNivaa(spillerNR);
		super(); //hva er dette?
		// Muligens redundant, kan sikker hente ut filnavnet frå this.fil, men tek med inntil vidare:
		this.fil = fil; // Hentar inn "innlesteOppgaver.txt" (el.l.), som Kristina produserer i sin klasse, dette må vel utvides og endres med varierende oppgaver
		this.lesInnOppgaveObjekt();  // Lag objekt på bakgrunn av fila
		this.sorterOppgaver("alfabetisk");  // Sorter dei
		/*
		 * "Alfabetisk" bør helst velgast via lærar si innlegging, eller om lærar vil, være eit alternativ i oppgavesamling-grensesnittet.
		 */
		this.avgrensUtvalget((int) Math.random()*10);
		/*
		 * Avgrensing bør være lagt inn av lærar, eller kunne velgast av spelar, akkurat som sortering.
		 */

	}

	private void lesInnOppgaveObjekt() {
		int oppgaveTeller=0;
		while(this.fil.hasNext()){
		 // instansier eit oppgave-objekt, i oppg-matrisen. Fôr objektet med data frå input-fila.
			oppgaver[oppgaveTeller] = new oppgavetype_input(this.fil.next(), this.fil.next(), Integer.parseInt(this.fil.next()) );
			//oppgavene skal bare legges til om nivået stemmer
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
			//besvartbutton.addlistner();
			//if (besvart.change)
				//{
					//oppgaver[oppgaveNummer].setbesvart=true;
					//if(svar==oppgaver[oppgaveNummer].getSvar)
					//	spiller.setpoeng=oppgave[oppgaveNummer].getPoeng;
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
