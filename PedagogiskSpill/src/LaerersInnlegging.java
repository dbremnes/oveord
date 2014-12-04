
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.*;
import javax.swing.JOptionPane;



public class LaerersInnlegging {

	public LaerersInnlegging() {
		// kjøre hent inn ord
		//lag elevliste
		ArrayList<Innlegg> midlertidigListe = hentInnOrd();
		lagElevliste();
		
		// lagre til fil 
		
	}
	
	public static void lagElevliste()
	{
		ArrayList<Elev> Elevliste = new ArrayList<Elev>();
		
		String Fornavn, Etternavn;
		int nivaaElev, flereOrd;
		
	
		
		while (true){
			int elevTellerer++; 
			Fornavn = JOptionPane.showInputDialog(null, "Skriv inn fornavnet til elev "+elevTellerer, "Fornavn", JOptionPane.PLAIN_MESSAGE);
			Etternavn = JOptionPane.showInputDialog(null, "Skriv inn etternavn til elev "+elevTellerer, "Etternavn", JOptionPane.PLAIN_MESSAGE);
			nivaaElev = Integer.parseInt(JOptionPane.showInputDialog(null, "Hvilket nivå er eleven på?", "Nivå", JOptionPane.PLAIN_MESSAGE));
			flereOrd = Integer.parseInt(JOptionPane.showInputDialog(null, "Er du ferdig trykk 1:", "Flere ord", JOptionPane.PLAIN_MESSAGE));
			
			//Lager nytt objekt
			Elev eleven = new Elev();
			
			// legger inn variablene
			eleven.setFornavn(Fornavn);
			eleven.setEtternavn(Etternavn);
			eleven.setnivaaElev(nivaaElev);
			// legge objektet i arraylist
			Elevliste.add(eleven);
			
			if (flereOrd != 0){
				break;
			}
		}	
		elevTilFil(Elevliste);
		
		
		}
		
	

	public static void elevTilFil(ArrayList<Elev> minListe) 
	{
		Formatter output = null; 
		try
		{
		    //exception handling left as an exercise for the reader		
			FileWriter fileWriter = new FileWriter("elever.txt", true); 
			output = new Formatter (fileWriter);//âˆšÃ‡pner fila kunder.txt for skriving, hvis den ikke finnes, opprettes den
		}
		catch ( SecurityException securityException )
		{
			JOptionPane.showMessageDialog(null, "", "Du har ikke skriverettigheter til denne fila", JOptionPane.PLAIN_MESSAGE );
			System.exit( 1 ); // avslutter programmet
			
		}
		catch ( FileNotFoundException fileNotFoundException )
		{
			JOptionPane.showMessageDialog(null, "", "Feil ved âˆšÃ‡pning av fila", JOptionPane.PLAIN_MESSAGE );
			System.exit( 1 ); // avslutter programmet
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// skrive ordene til fil
	
		for (Elev denneEleven : minListe){
			
			System.out.println(denneEleven.getFornavn());
			System.out.println(denneEleven.getEtternavn());
			System.out.println(denneEleven.getnivaaElev());
			output.format("%s %s %d\n", denneEleven.getFornavn(), denneEleven.getEtternavn(), denneEleven.getnivaaElev());

			
			
		}
		if ( output != null)
			output.close();
		
	}


	
	   
	public static ArrayList<Innlegg> hentInnOrd(){
		String norskOrd, engelskOrd;
		ArrayList<Innlegg> Innleggsliste = new ArrayList<Innlegg>();
		
		int nivaa, flereOrd;
		while (true){
			norskOrd = JOptionPane.showInputDialog(null, "Skriv inn et norskt ord:", "Norsk", JOptionPane.PLAIN_MESSAGE);
			engelskOrd = JOptionPane.showInputDialog(null, "Skriv inn et tilsvarende engelsk ord:", "Engelsk", JOptionPane.PLAIN_MESSAGE);
			nivaa = Integer.parseInt(JOptionPane.showInputDialog(null, "Skriv inn nivâˆšâ€¢ (eks: 1, 2 eller 3):", "Nivâˆšâ€¢", JOptionPane.PLAIN_MESSAGE));
			flereOrd = Integer.parseInt(JOptionPane.showInputDialog(null, "Vil du se flere ord, trykk 0:", "Flere ord", JOptionPane.PLAIN_MESSAGE));
			// lager nytt objekt
			Innlegg midlertidigInnlegg = new Innlegg();
			// legger inn variablene
			midlertidigInnlegg.setNorskOrd(norskOrd);
			midlertidigInnlegg.setEngelskOrd(engelskOrd);
			midlertidigInnlegg.setNivaa(nivaa);
			// legge objektet i arraylist
			Innleggsliste.add(midlertidigInnlegg);
			
			if (flereOrd != 0){
				break;
			}
		}
		ordTilFil(Innleggsliste);
		return Innleggsliste;
	}
	

	public static void  ordTilFil(ArrayList<Innlegg> minListe) 
	{
		Formatter output = null; 
		try
		{
		    //exception handling left as an exercise for the reader		
			FileWriter fileWriter = new FileWriter("laerersord.txt", true); 
			output = new Formatter (fileWriter);//âˆšÃ‡pner fila kunder.txt for skriving, hvis den ikke finnes, opprettes den
		}
		catch ( SecurityException securityException )
		{
			JOptionPane.showMessageDialog(null, "", "Du har ikke skriverettigheter til denne fila", JOptionPane.PLAIN_MESSAGE );
			System.exit( 1 ); // avslutter programmet
			
		}
		catch ( FileNotFoundException fileNotFoundException )
		{
			JOptionPane.showMessageDialog(null, "", "Feil ved âˆšÃ‡pning av fila", JOptionPane.PLAIN_MESSAGE );
			System.exit( 1 ); // avslutter programmet
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// skrive ordene til fil
	
		for (Innlegg detteInnlegget : minListe){
			
			System.out.println(detteInnlegget.getNorskOrd());
			System.out.println(detteInnlegget.getEngelskOrd());
			System.out.println(detteInnlegget.getNivaa());
			output.format("%s %s %d\n", detteInnlegget.getNorskOrd(), detteInnlegget.getEngelskOrd(), detteInnlegget.getNivaa());

			
			
		}
		if ( output != null)
			output.close();
		
	}

}
