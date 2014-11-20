import javax.swing.*; 					  //<importerer n?dvendige pakker>
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Hovedvindu extends JFrame implements ActionListener  //deklarerer datafelter for knapper og tekstomr?de
{
    public static final int MAXANTALL = 10;
    private JButton kurs, student, oversikt, fag, avslutt;
    private JTextField utskriftsområde;



    public Hovedvindu()  		//konstrukt?r uten parametre
    {
//        super("Øveord");//super gj?r at denne subklassens konstrukt?r arver egenskapene til java-klassen Object.

        kurs = new JButton("Kurs"+ "\n"); 		//oppretter knappene Kurs, Student, Oversikt og Avslutt
        kurs.addActionListener(this);
        student = new JButton("Student\t\t");
        student.addActionListener(this);
        oversikt = new JButton("Oversikt\t\t");
        oversikt.addActionListener(this);
        fag = new JButton("Fag\t\t");
        fag.addActionListener(this);
        avslutt = new JButton("Avslutt\t\t");
        avslutt.addActionListener(this);
        utskriftsområde = new JTextField (12);
        utskriftsområde.setSize( 300, 300 ); 	//kaller p? metoden setSize og setter st?rrelsen p? vinduet.
        utskriftsområde.addActionListener(this);

        Container c = getContentPane(); 		//oppretter vinduets komponent-"container"
        c.setBackground(Color.WHITE);
        c.setLayout(new FlowLayout());  		//setter komponentene i "containeren" i riktig rekkef?lge
        c.add(kurs);
        c.add(student);
        c.add(oversikt);
        c.add(fag);
        c.add(avslutt);
    }


    public void startStudent() 		  //her hentes Studentklassen.
    {
        Hovedvindu vindu = new Hovedvindu();
		vindu.setSize( 700, 630 );
		vindu.setVisible( true );
    }


    public void actionPerformed(ActionEvent e)  //Ved trykk kalles metodene opp.
    {
    if (e.getSource() == kurs)
        startKurs();
    else if (e.getSource() == student)
        startStudent();
    else if (e.getSource() == oversikt)
        startOversiktsplan();
    else if (e.getSource() == fag)
        startFag();
    }


	private void startFag() {
		// TODO Auto-generated method stub

	}


	private void startOversiktsplan() {
		// TODO Auto-generated method stub

	}


	private void startKurs() {
		// TODO Auto-generated method stub

	}
}
