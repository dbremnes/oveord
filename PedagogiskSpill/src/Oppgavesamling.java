//Oppgavesamling
/* Oppgavesamling er et objekt med følgende funksjoner:
Den oppretter en array med objekter basert på oppgavetypeInput og innlesing fra fil
Den kontrollerer aktiv spiller med informasjon og hvilken oppgave som skal vises
Den kontrollerer hvilke oppgaver som skal vises til en hver tid og hvilke som skal være tilgjengelig. Tilgjenligheten baserer seg på oppgavenes vanskelighetsgrad og elevens nivå
Den kontrollerer om det er flere tilgjengelige oppgaver i arrayen og viser eventuelt disse for elevene
Dersom alle oppgaver er forsøkt løst kontrollerer den om elevene skal øke i nivå basert på prestasjonen og oppdaterer i tilfelle elevobjektet
Den lagrer informasjon om elevens prestasjoner innenfor et oppgave nivå, slik at læreren i etterkant kan se resultatene */

/*Importerer nødvendige klasser for å kunne utføre
lesing fra fil, med feil behandlig
skriving til fil, med feilbehandling
tilfeldig behandling*/

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



import javax.swing.JOptionPane;

public class Oppgavesamling {
	/*Defenisjon av objektet med variabler tilgjengelig i hele objektet*/
	private Scanner fil;
	private OppgavetypeInput[] oppgaver;
	private Panel grensesnitt;
	private int aktivOppgaveNR;
	private Hovedklasse vindu;
	private int antallOppgaver;
	private int antallRiktigeSvar;
	private Spilleren spiller;
	private String riktigBesvart="";
	private String feilBesvart="";



	public Oppgavesamling(String filNavn, Hovedklasse vindu) {
		/*Konstruktør hvor filnavn og aktivt vindu sendes inn
		Basert på innsendt informasjon settes det opp en peker til det aktive vinduet og den aktive spilleren (peker betyr at den viser til)
			this.vindu=vindu
			this.spiller=vindu.spiller
		Videre brukes informasjon om spilleren til å kontrollere hvilke oppgaver som skal aktiveres av de læreren har skrevet inn. Utvalget gjøres i samsvar
			Prøver å sette opp scanneren (filleseren) med riktig filnavn ut fra tilgjengelig informasjon
			Oppretter en array med 100 plasser av typen oppgavinputType
			kaller opp metoden for innlesing av fil og opprettelse av det enkelte oppgave objektet
			Kaller opp metoden for at oppgavene skal komme i tilfeldig rekkefølge med informasjon om metode

			fanger opp informasjonen om filen ikke eksisterer og oppretter en ny fil via metoden lagfil*/


		this.vindu = vindu; // Sett peikaren frå konstruktÃ¸r-parameteret til klassevariabel.
		this.spiller=vindu.spiller; // lag peikar til spiller-objektet fra hovudvinduet

		//prøver lesning av fil
		try {
			//oppretter fil scanneren, basert på innsendt filnavn
	        this.fil=new Scanner(new File(filNavn));
			 // Hentar inn "laerersord.txt" (el.l.), som Kristina produserer i sin klasse, dette må vel utvides og endres med varierende oppgaver
			 //Oppretter en array med 100 plasser. Ideelt sett skulle antall plasser basert seg på antall oppgaver som eksisterer i filen
	        this.oppgaver = new OppgavetypeInput[100];
	        this.lesInnOppgaveObjekt();  //Metode for å lage objekter basert på oppgavene i filen
			this.sorterOppgaver("alfabetisk");  // Sorter objektene i tilfeldig rekkefølge


		 } // end try
		 //Fanger opp eventuelle feil ved lesning av filen
	 	catch ( FileNotFoundException fileNotFoundException ){
				lagFil(filNavn,""); //kaller funksjonen for å lage fil, slik at programmet ikke stopper
	 			JOptionPane.showMessageDialog(null,  "Feil ved åpning av fila.", null, JOptionPane.PLAIN_MESSAGE );//Gir tilbakemeldingom feilen
	 			return;
		} // end catch
		//sikrer at aktiv Oppgave starter på 0
		this.aktivOppgaveNR=0;
		//kaller metoden visAktivOppgave
		this.visAktivOppgave();

	}

	private void lesInnOppgaveObjekt() {
		/*Metode les inn oppgaveObjekt ()
		løkke som går så lenge flere elementer kan leses fra fil (while løkke)
		henter inn norskord, engelskord og nivå fra filen
		Hvis elevens nivå er likt oppgave nivå
		Oppretter objekt via oppgavetype arrayen og kontruktøren der basert på innlest info
		teller opp slik at oppgavene legges nedover arrayen (øker teller med en)
		Tar vare på antall innleste oppgaver, slik at denne kan styre gjennomlesning av arrayen senere*/
		//lokal variabel
		int oppgaveTeller=0;
		//Løkke som går så lenge det er flere oppgaver i filen
		while(this.fil.hasNext()){
		 // instansier eit oppgave-objekt, i oppg-matrisen. Får objektet med data frå input-fila.
		 //leser inn oppgaveordet
			String oppgaveOrd = this.fil.next();
			//Leser inn fasiten
			String oppgaveFasit = this.fil.next();
			//leser inn nivået på oppgaven
			int nivaa = Integer.parseInt(this.fil.next());
			//oppgavene skal bare legges til om nivået stemmer
			if(this.spiller.getNivaa()==nivaa){
				// This er lagt til, fordi oppgåva skal kunne trigge "neste oppgåve" her i oppgåvesamlinga.
				//Oppretter objektet basert på den innhentede informasjonen fra filen.Legger oppgavene i arrayen slik at de blir objekter
				this.oppgaver[oppgaveTeller] = new OppgavetypeInput(oppgaveOrd,oppgaveFasit,nivaa,this);
				//øker telelr emd en slik at neste oppgave legges etter i arrayen
				oppgaveTeller++;
			}
		}
		//variabler for kontroll av antall oppgaver
		this.antallOppgaver= oppgaveTeller;


	}

	private void sorterOppgaver(String type)
	{
		/*Metode sortering (hvordan sortere)
		Sorterer kun tilfeldig innenfor arrayen
		Bruker en tilfeldig funksjon for å endre plasseringen
		Bytter om plasseringen via en midlertidig variabel, gjøres via en løkke med to tellere
		*/

		//Lokale variabler brukes kun til sorteringen
		 int k;
		 int j;

				//
				for(int i=0;i<this.antallOppgaver; i++)
				{
					// Loop og lag ei tilfeldig liste
					//tilfeldig tall basert på lengden av oppgavene
					k = (int) Math.random()*this.antallOppgaver;
					//
					oppgaver[this.antallOppgaver]=this.oppgaver[i];
					oppgaver[i]=oppgaver[k];
					oppgaver[k]=oppgaver[antallOppgaver];
				}
				//nullstiller den aktive oppgaven
				this.aktivOppgaveNR=0;
		}

	public void visAktivOppgave(){
		/*Metode visAktivOppgave()
		Sender den aktiveOppgaven til oppgaveType objektet for visning
		setter fokus i vnduet
		*/
		//legger ut den første oppgaven fra den sorterte listen
		this.vindu.setContentPane( oppgaver[this.aktivOppgaveNR].visOppgave() );
		this.vindu.validate();
	}

	public void nesteOppgave(){
		/*Metode nesteOppgave()
		Denne metoden holder styring på om dette finnes flere oppgaver å vise
		Viser neste oppgave om mulig
		Når alle er vist sjekkes det hvor mange rett eleven har
		Samler hvilke feil eleven har gjort
		Lagrer informasjonen ved å kalle opp lagreFil metoden
		Bygger opp en string for filnavn basert på elevens navn og nivå

			Samlingen av informasjonen skjer i en for løkke like lans som antallOppgaver
			Sjekker om oppgaven er riktig besvart, og øker i tilfelle antallRikitge med en og legger oppgaven inn i stringen riktigBesvart
			Er oppgaven ikke riktig besvart bygges stringen feilSvart opp med oppgaven og elevens svar

			Legger det hele sammen til en string og lager filnavnStringen
			Sender informasjonen til Lagfil med filnavn og stringen med innholdet
		*/


		//sjekker om alle oppgaver på nivået er løst
		if(this.aktivOppgaveNR==this.antallOppgaver-1){
			//nullstiller antall riktige oppgaver
			this.antallRiktigeSvar=0;
			//løkke som går så lenge der er flere oppgaver i arrayen oppgaver
			for(int i=0; i<this.antallOppgaver; i++){
				//sjekker om oppgavene er riktig besvart
				if (this.oppgaver[i].getRiktigBesvart()){
					//øker antallRiktige med 1
					antallRiktigeSvar++;
					//Bygger opp stringen med riktig besvarte oppgaver
					riktigBesvart+=this.oppgaver[i].getOppgaveOrd()+" ";
					//feil besvarte oppgaver behandles her
				}else{
					//bygger opp stringen knyttet til feilbesvarte oppgaver
					feilBesvart+=this.oppgaver[i].getOppgaveOrd()+" "+this.oppgaver[i].getElevensSvar()+" ";

				}
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
					//noe EKSTRA kult skal skje, vedkomande har full pott.
				}//end if
			}

			/*
			 * Skriv resultatet til fil, på mønsteret Resultat_Nivaa_Elevens_Navn.txt
			 */
			String filnavnet= "Resultat_"+"_"+this.spiller.getFornavn()+"_"+this.spiller.getEtternavn()+".txt";
				// Lag klar string til fila, altså innhaldet.
				//Bygger opp stringen
			String filInnholdet = "Antall riktige svar: "+antallRiktigeSvar+"+\r\n";
				filInnholdet += "Riktig besvart oppgaver er: "+riktigBesvart+"+\r\n";
				filInnholdet += "Feil besvarte oppgaver er:"+feilBesvart+"+\r\n";
			
				lagFil(filnavnet,filInnholdet);



				// Kva skal skje når spelet er over.
				JOptionPane.showMessageDialog(null, "Du klarte: "+this.antallRiktigeSvar, "Takk! Og velkommen tilbake", JOptionPane.PLAIN_MESSAGE );

			vindu.dispose();
	}else{
		//henter frem oppgavene igjen
			 //this.lesInnOppgaveObjekt();
			 //this.sorterOppgaver("alfabetisk");
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
				output = new Formatter (fileWriter);//Åpner fila for skriving, hvis den ikke finnes, opprettes den
			}
			//feil behandling
			catch ( SecurityException securityException )
			{
				//Meldinger evd feil
				JOptionPane.showMessageDialog(null, "", "Du har ikke skriverettigheter til denne fila", JOptionPane.PLAIN_MESSAGE );
				System.exit( 1 ); // avslutter programmet

			}
			//feil behandling
			catch ( FileNotFoundException fileNotFoundException )
			{
				JOptionPane.showMessageDialog(null, "", "Feil ved Ãƒâ€špning av fila", JOptionPane.PLAIN_MESSAGE );
				System.exit( 1 ); // avslutter programmet
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			// opprett filen
			output.format("%s", innhold);

			//stnger filen etter skriving
			if ( output != null)
				output.close();

	}
	//Diverse get og set funksjoiner knyttet til klassens variabler

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

		return this.antallOppgaver;
	}
}
