
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import javax.swing.JOptionPane;



public class LaerersInnlegging {

	public LaerersInnlegging() {
		// kjøre hent inn ord
		ArrayList<Innlegg> midlertidigListe = hentInnOrd();
		// lagre til fil 
		
	}
	
	public static ArrayList<Innlegg> hentInnOrd(){
		String norskOrd, engelskOrd;
		ArrayList<Innlegg> Innleggsliste = new ArrayList<Innlegg>();
		
		int nivaa, flereOrd;
		while (true){
			norskOrd = JOptionPane.showInputDialog(null, "Skriv inn et norskt ord:", "Norsk", JOptionPane.PLAIN_MESSAGE);
			engelskOrd = JOptionPane.showInputDialog(null, "Skriv inn et tilsvarende engelsk ord:", "Engelsk", JOptionPane.PLAIN_MESSAGE);
			nivaa = Integer.parseInt(JOptionPane.showInputDialog(null, "Skriv inn nivå (eks: 1, 2 eller 3):", "Nivå", JOptionPane.PLAIN_MESSAGE));
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
			output = new Formatter (fileWriter);//Âpner fila kunder.txt for skriving, hvis den ikke finnes, opprettes den
		}
		catch ( SecurityException securityException )
		{
			JOptionPane.showMessageDialog(null, "", "Du har ikke skriverettigheter til denne fila", JOptionPane.PLAIN_MESSAGE );
			System.exit( 1 ); // avslutter programmet
			
		}
		catch ( FileNotFoundException fileNotFoundException )
		{
			JOptionPane.showMessageDialog(null, "", "Feil ved Âpning av fila", JOptionPane.PLAIN_MESSAGE );
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
