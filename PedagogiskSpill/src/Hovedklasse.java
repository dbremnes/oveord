import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.*;


public class Hovedklasse extends JFrame
{
    public static void main (String[] args)
    {
    	//Programmet starter med å vente på input fra bruker om rolle
        String [] verdier = {"Elev", "Lærer"};
        String valgt;
        String fil="laerersord.txt";
        //Dette valget og if'en kan nå fjernes da menyen komme opp i hovedvinduet
        //Trenger da bare innholdet i if'en

        valgt=(String)JOptionPane.showInputDialog(null, "Velg rolle", "Elev eller lærer?", JOptionPane.INFORMATION_MESSAGE, null, verdier, verdier[0] );

        if (valgt.equals("Elev")){
        	Hovedvindu vindu = new Hovedvindu();
            vindu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            vindu.setSize(1000, 800);
            vindu.setVisible(true);
            Oppgavesamling oppgavesamlingen;
            oppgavesamlingen = new Oppgavesamling(fil,1, vindu);
        }
        else {
        	Laerersinnlegging laerer=new Laerersinnlegging();

        }

        	//Hvis rollevalg er lærer
        //<start med å lese inn fra input/fil>
        //<metode?>

        //Hvis ikke
        //<åpner hovedvindu med oppgavesamling>
        //OBS! Hvordan linker jeg "OK"-knapp sammen med åpning av hovedvindu?

        //Konstruktør. Lager og viser fram hovedvindu


    }
}
