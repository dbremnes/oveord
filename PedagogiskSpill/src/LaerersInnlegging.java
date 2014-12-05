
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.JTextArea;
import java.util.Scanner;
import java.io.IOException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.io.BufferedWriter;

import javax.swing.JOptionPane;

public class LaerersInnlegging {
	private Hovedklasse hoved;
	private Scanner fil;

	public LaerersInnlegging(Hovedklasse vindu) {
		// kjøre hent inn ord
		//lag elevliste
		this.hoved=vindu;
	        String [] verdier = {"elev", "flere ord", "se resultater"};
	        String valgt;
	        valgt=(String)JOptionPane.showInputDialog(null, "Legg til", "Hva vil du gjøre", JOptionPane.INFORMATION_MESSAGE, null, verdier, verdier[0] );
	
	        if (valgt.equals("elev")){
	        	lagElevliste();
	        	// Her bør vi legge til en sjekk på om det finnes ord som lærer har lagt til. Det blir i versjon 2.  
			JOptionPane.showMessageDialog(null,  "Takk, \n"+"Har du husket å legge inn ord?" + " Da kan du la elevene gjennomføre prøven","Takk!", JOptionPane.PLAIN_MESSAGE );
	        }else if (valgt.equals("flere ord")){
	        	ArrayList<Innlegg> midlertidigListe = hentInnOrd();
	        	// Her bør vi legge til en sjekk på om det finnes elever.  Det blir i versjon 2. 
	  		JOptionPane.showMessageDialog(null,  "Takk, \n"+"Har du husket å legge til elever?" + " Da kan du la elevene gjennomføre prøven","Takk!", JOptionPane.PLAIN_MESSAGE );
	        }
	        else{
			this.hoved.velgSpiller();
			this.seResultater();
		}
	}
	//Oppretter metode hvor det skal hentes inn Fornavn, Etternavn og Nivå på eleven
	public static void lagElevliste()
	{	//Lager en Arraylist til tilleggsklassen "Elev"
		ArrayList<Elev> Elevliste = new ArrayList<Elev>();
		//Oppretter String og Int variabler
		String Fornavn, Etternavn;
		int nivaaElev, flereOrd;


		int flereord=0;
		while (true){
			//Er dette riktig?
			int elevTellerer=0;
			try{
			elevTellerer++;
			//Gir variablene verdier med inputdialoger fra klassen JOptionPane
			Fornavn = JOptionPane.showInputDialog(null, "Skriv inn fornavnet til elev "+elevTellerer, "Fornavn", JOptionPane.PLAIN_MESSAGE);
			Etternavn = JOptionPane.showInputDialog(null, "Skriv inn etternavn til elev "+elevTellerer, "Etternavn", JOptionPane.PLAIN_MESSAGE);
			//Med nivaaElev og flereOrd må jeg gjøre de om til int fordi det skal brukes tall i inputdialogboksen
			nivaaElev = Integer.parseInt(JOptionPane.showInputDialog(null, "Hvilket nivå er eleven på?"+ " 1,2,3 ", "Nivå", JOptionPane.PLAIN_MESSAGE));
			//flereOrd = Integer.parseInt(JOptionPane.showInputDialog(null, "Er du ferdig trykk 1:", "Flere ord", JOptionPane.PLAIN_MESSAGE));

			//Lager nytt objekt
			Elev eleven = new Elev();

			// legger inn variablene
			eleven.setFornavn(Fornavn);
			eleven.setEtternavn(Etternavn);
			eleven.setnivaaElev(nivaaElev);
			// legger objektet i arraylist
			Elevliste.add(eleven);


			}catch(IllegalArgumentException e){
						JOptionPane.showMessageDialog(null, "Du har gjort feil i registreringen husk at nivået er et tall ", "Feil registrering", JOptionPane.PLAIN_MESSAGE);
			}
			flereOrd=JOptionPane.showConfirmDialog(null, "ønsker du å registrere flere elever?",null, JOptionPane.YES_NO_OPTION);
			//Hvis ikke lÃ¦reren vil legge inn flere ord, går vi ut fra while-løkken
			if (flereOrd != 0){
				break;
			}
			}
		//Kall av metoden elevTilFil
		elevTilFil(Elevliste);


		}


	//Lager en metode som skal sende elevens navn og nivå til fil
	public static void elevTilFil(ArrayList<Elev> minListe)
	{

		Formatter output = null;
		try
		{
		    //For å skrive til fil må jeg bruke klassen FileWriter
			FileWriter fileWriter = new FileWriter("elever.txt", true);
			output = new Formatter (fileWriter);//åpner fila elever.txt for skriving, hvis den ikke finnes, opprettes den
		}
		//Oppretter feilmeldinger slik at hvis programmet ikke finner filen, kommer det en feilmelding som sier det, istedenfor errorer
		catch ( SecurityException securityException )
		{
			JOptionPane.showMessageDialog(null, "", "Du har ikke skriverettigheter til denne fila", JOptionPane.PLAIN_MESSAGE );
			System.exit( 1 ); // avslutter programmet

		}
		catch ( FileNotFoundException fileNotFoundException )
		{
			JOptionPane.showMessageDialog(null, "", "Feil ved åpning av fila", JOptionPane.PLAIN_MESSAGE );
			System.exit( 1 ); // avslutter programmet
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// skrive ordene til fil via system out print

		for (Elev denneEleven : minListe){

			System.out.println(denneEleven.getFornavn());
			System.out.println(denneEleven.getEtternavn());
			System.out.println(denneEleven.getnivaaElev());
			output.format("%s %s %d\n", denneEleven.getFornavn(), denneEleven.getEtternavn(), denneEleven.getnivaaElev());



		}
		//Lukker filen
		if ( output != null)
			output.close();

	}



	   //Lager en metode for lÃ¦reren sine innlegg av ord
	public static ArrayList<Innlegg> hentInnOrd(){

		//Oppretter variabler av type String som senere blir tildelt verdi
		String norskOrd, engelskOrd;

		//Lager en Arraylist som sender til tilleggsklassen "Innlegg"
		ArrayList<Innlegg> Innleggsliste = new ArrayList<Innlegg>();

		//Oppretter variabler av type int som senere blir tildelt verdi
		int nivaa, flereOrd;

		//Oppretter en while-løkke som kjører så lenge den er "true"
		while (true){
			try{
			norskOrd = JOptionPane.showInputDialog(null, "Skriv inn et norskt ord:", "Norsk", JOptionPane.PLAIN_MESSAGE);
			engelskOrd = JOptionPane.showInputDialog(null, "Skriv inn et tilsvarende engelsk ord:", "Engelsk", JOptionPane.PLAIN_MESSAGE);
			nivaa = Integer.parseInt(JOptionPane.showInputDialog(null, "Skriv inn nivå (eks: 1, 2 eller 3):", "Nivå", JOptionPane.PLAIN_MESSAGE));
			flereOrd = JOptionPane.showConfirmDialog(null, "ønsker du å registrere flere ord?",null, JOptionPane.YES_NO_OPTION);
			// lager nytt objekt
			Innlegg midlertidigInnlegg = new Innlegg();
			// legger inn variablene
			midlertidigInnlegg.setNorskOrd(norskOrd);
			midlertidigInnlegg.setEngelskOrd(engelskOrd);
			midlertidigInnlegg.setNivaa(nivaa);
			// legge objektet i arraylist
			Innleggsliste.add(midlertidigInnlegg);

			//Her går vi ut av while-løkken hvis flereOrd er noe annet enn 0
			if (flereOrd != 0){
				break;
			}
			}catch(IllegalArgumentException e){
									JOptionPane.showMessageDialog(null, "Du har gjort feil i registreringen husk at nivået er et tall ", "Feil registrering", JOptionPane.PLAIN_MESSAGE);
			}
		}
		//Kall av metoden ordTilFil
		ordTilFil(Innleggsliste);
		return Innleggsliste;
	}

	//Oppretter metoden ordTilFil
	public static void  ordTilFil(ArrayList<Innlegg> minListe)
	{
		Formatter output = null;
		try
		{
		    //For å skrive til fil må jeg bruke klassen FileWriter
			FileWriter fileWriter = new FileWriter("laerersord.txt", true);
			output = new Formatter (fileWriter);//åpner fila laerersord.txt for skriving, hvis den ikke finnes, opprettes den
		}
		//Oppretter feilmeldinger slik at hvis programmet ikke finner filen, kommer det en feilmelding som sier det, istedenfor errorer
		catch ( SecurityException securityException )
		{
			JOptionPane.showMessageDialog(null, "", "Du har ikke skriverettigheter til denne fila", JOptionPane.PLAIN_MESSAGE );
			System.exit( 1 ); // avslutter programmet

		}
		catch ( FileNotFoundException fileNotFoundException )
		{
			JOptionPane.showMessageDialog(null, "", "Feil ved åpning av fila", JOptionPane.PLAIN_MESSAGE );
			System.exit( 1 ); // avslutter programmet
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		for (Innlegg detteInnlegget : minListe){
			System.out.println(detteInnlegget.getNorskOrd());
			System.out.println(detteInnlegget.getEngelskOrd());
			System.out.println(detteInnlegget.getNivaa());
			output.format("%s %s %d\n", detteInnlegget.getNorskOrd(), detteInnlegget.getEngelskOrd(), detteInnlegget.getNivaa());
		}
		//Lukker filen
		if ( output != null){
			output.close();
		}
	}
	private void seResultater(){
	//tekstområde
	JTextArea elevensResultater = new JTextArea();
	String overskrifter;
	overskrifter=this.hoved.spiller.getFornavn()+" "+this.hoved.spiller.getEtternavn()+"resultater";
	overskrifter+="\n";
	//gir overskriftene i teksområdet
  	elevensResultater.setText(overskrifter);
  	//String variabel for innlegging av resultatene i tekstområdet
  	String resultatLinje="";
  	//String tilfilnavnet
  	String filnavn;

	//prøver lesning av fil
	filnavn="Resultat_"+this.hoved.spiller.getFornavn()+"_"+this.hoved.spiller.getEtternavn()+".txt";
			try {
				//oppretter fil scanneren, basert på innsendt filnavn
		        this.fil=new Scanner(new File(filnavn));
				 // Hentar inn "laerersord.txt" (el.l.), som Kristina produserer i sin klasse, dette må vel utvides og endres med varierende oppgaver
				 //Oppretter en array med 100 plasser. Ideelt sett skulle antall plasser basert seg på antall oppgaver som eksisterer i file
			 } // end try
			 //Fanger opp eventuelle feil ved lesning av filen
		 	catch ( FileNotFoundException fileNotFoundException ){
		 			JOptionPane.showMessageDialog(null,  "Feil ved åpning av fila.", null, JOptionPane.PLAIN_MESSAGE );//Gir tilbakemeldingom feilen
		 			return;
			} // end catch
			//sikrer at aktiv Oppgave starter på 0


			while(this.fil.hasNext()){
			 // Henter inn ei linje fra elevenes resultatfil
			 //Leser inn resultatene fra et forsøk
				resultatLinje = this.fil.next();
				//JOptionPane.showMessageDialog(null,resultatLinje, null, JOptionPane.PLAIN_MESSAGE );
				//Leser inn fasiten
				resultatLinje+="\r\n";
				elevensResultater.append(resultatLinje);
				resultatLinje="";

			}

			//viser tekstområdet på skjermen
			JOptionPane.showMessageDialog( null, elevensResultater,"Resultater", JOptionPane.INFORMATION_MESSAGE );
	}
}
