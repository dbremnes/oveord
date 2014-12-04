
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.swing.JOptionPane;

public class LaerersInnlegging {

	public LaerersInnlegging() {
		// kj�re hent inn ord
		//lag elevliste
        String [] verdier = {"elev", "flere ord"};
        String valgt;
        valgt=(String)JOptionPane.showInputDialog(null, "Legge til: ", "Hva vil du gjøre?", JOptionPane.INFORMATION_MESSAGE, null, verdier, verdier[0] );
        
        if (valgt.equals("elev")){
        	lagElevliste();
        }else{
        	ArrayList<Innlegg> midlertidigListe = hentInnOrd();
        }
		JOptionPane.showMessageDialog(null,  "Takk, du kan n� be elevene gjennomf�re pr�ven","Takk!", JOptionPane.PLAIN_MESSAGE );        
	}
	//Oppretter metode hvor det skal hentes inn Fornavn, Etternavn og Niv� p� eleven
	public static void lagElevliste()
	{	//Lager en Arraylist til tilleggsklassen "Elev"
		ArrayList<Elev> Elevliste = new ArrayList<Elev>();
		//Oppretter String og Int variabler 
		String Fornavn, Etternavn;
		int nivaaElev, flereOrd;
		
	
		
		while (true){
			int elevTellerer=0;
			elevTellerer++; 
			//Gir variablene verdier med inputdialoger fra klassen JOptionPane
			Fornavn = JOptionPane.showInputDialog(null, "Elevens fornavn "+elevTellerer, "Fornavn", JOptionPane.PLAIN_MESSAGE);
			Etternavn = JOptionPane.showInputDialog(null, "Elevens etternavn "+elevTellerer, "Etternavn", JOptionPane.PLAIN_MESSAGE);
			//Med nivaaElev og flereOrd m� jeg gj�re de om til int fordi det skal brukes tall i inputdialogboksen
			nivaaElev = Integer.parseInt(JOptionPane.showInputDialog(null, "Hvilket nivå er eleven på?", "Nivå", JOptionPane.PLAIN_MESSAGE));
			flereOrd = Integer.parseInt(JOptionPane.showInputDialog(null, "Er du ferdig trykk 1:", "Flere ord", JOptionPane.PLAIN_MESSAGE));
			
			//Lager nytt objekt
			Elev eleven = new Elev();
			
			// legger inn variablene
			eleven.setFornavn(Fornavn);
			eleven.setEtternavn(Etternavn);
			eleven.setnivaaElev(nivaaElev);
			// legger objektet i arraylist
			Elevliste.add(eleven);
			//Hvis ikke læreren vil legge inn flere ord, g�r vi ut fra while-l�kken
			if (flereOrd != 0){
				break;
			}
		}
		//Kall av metoden elevTilFil
		elevTilFil(Elevliste);
		
		
		}
		
	
	//Lager en metode som skal sende elevens navn og niv� til fil
	public static void elevTilFil(ArrayList<Elev> minListe) 
	{	
		
		Formatter output = null; 
		try
		{
		    //For � skrive til fil m� jeg bruke klassen FileWriter		
			FileWriter fileWriter = new FileWriter("elever.txt", true); 
			output = new Formatter (fileWriter);//�pner fila elever.txt for skriving, hvis den ikke finnes, opprettes den
		}
		//Oppretter feilmeldinger slik at hvis programmet ikke finner filen, kommer det en feilmelding som sier det, istedenfor errorer
		catch ( SecurityException securityException )
		{
			JOptionPane.showMessageDialog(null, "", "Du har ikke skriverettigheter til denne fila", JOptionPane.PLAIN_MESSAGE );
			System.exit( 1 ); // avslutter programmet
			
		}
		catch ( FileNotFoundException fileNotFoundException )
		{
			JOptionPane.showMessageDialog(null, "", "Feil ved �pning av fila", JOptionPane.PLAIN_MESSAGE );
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


	
	   //Lager en metode for læreren sine innlegg av ord
	public static ArrayList<Innlegg> hentInnOrd(){
		
		//Oppretter variabler av type String som senere blir tildelt verdi
		String norskOrd, engelskOrd;
		
		//Lager en Arraylist som sender til tilleggsklassen "Innlegg"
		ArrayList<Innlegg> Innleggsliste = new ArrayList<Innlegg>();
		
		//Oppretter variabler av type int som senere blir tildelt verdi
		int nivaa, flereOrd;
		
		//Oppretter en while-l�kke som kj�rer s� lenge den er "true"
		while (true){
			norskOrd = JOptionPane.showInputDialog(null, "Skriv inn et norskt ord:", "Norsk", JOptionPane.PLAIN_MESSAGE);
			engelskOrd = JOptionPane.showInputDialog(null, "Skriv inn et tilsvarende engelsk ord:", "Engelsk", JOptionPane.PLAIN_MESSAGE);
			nivaa = Integer.parseInt(JOptionPane.showInputDialog(null, "Skriv inn niv� (eks: 1, 2 eller 3):", "Niv�", JOptionPane.PLAIN_MESSAGE));
			flereOrd = Integer.parseInt(JOptionPane.showInputDialog(null, "Vil du se flere ord, trykk 0:", "Flere ord", JOptionPane.PLAIN_MESSAGE));
			// lager nytt objekt
			Innlegg midlertidigInnlegg = new Innlegg();
			// legger inn variablene
			midlertidigInnlegg.setNorskOrd(norskOrd);
			midlertidigInnlegg.setEngelskOrd(engelskOrd);
			midlertidigInnlegg.setNivaa(nivaa);
			// legge objektet i arraylist
			Innleggsliste.add(midlertidigInnlegg);
			
			//Her g�r vi ut av while-l�kken hvis flereOrd er noe annet enn 0
			if (flereOrd != 0){
				break;
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
		    //For � skrive til fil m� jeg bruke klassen FileWriter	
			FileWriter fileWriter = new FileWriter("laerersord.txt", true); 
			output = new Formatter (fileWriter);//�pner fila laerersord.txt for skriving, hvis den ikke finnes, opprettes den
		}
		//Oppretter feilmeldinger slik at hvis programmet ikke finner filen, kommer det en feilmelding som sier det, istedenfor errorer
		catch ( SecurityException securityException )
		{
			JOptionPane.showMessageDialog(null, "", "Du har ikke skriverettigheter til denne fila", JOptionPane.PLAIN_MESSAGE );
			System.exit( 1 ); // avslutter programmet
			
		}
		catch ( FileNotFoundException fileNotFoundException )
		{
			JOptionPane.showMessageDialog(null, "", "Feil ved �pning av fila", JOptionPane.PLAIN_MESSAGE );
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
}
