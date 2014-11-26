import javax.swing.*; 					  //<importerer n?dvendige pakker>
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Hovedvindu extends JFrame implements ActionListener  //deklarerer datafelter for knapper og tekstomr?de
{
    public static final int MAXANTALL = 10;
    private JButton laerer, elev, besvart, avslutt;
    private JTextField oppgave;
    private JTextField svar;



    public Hovedvindu()  		//konstrukt?r uten parametre
    {
        super("Øveord");//super gj?r at denne subklassens konstrukt?r arver egenskapene til java-klassen Object.


        laerer = new JButton("lærer\t\t");
        laerer.addActionListener(this);
        elev = new JButton("elev\t\t");
        elev.addActionListener(this);

        avslutt = new JButton("Avslutt\t\t");
        avslutt.addActionListener(this);



        Container c = getContentPane(); 		//oppretter vinduets komponent-"container"
        c.setBackground(Color.WHITE);
        c.setLayout(new FlowLayout());  		//setter komponentene i "containeren" i riktig rekkef?lge
        c.add(laerer);
        c.add(elev);
        c.add(besvart);
        c.add(avslutt);
        c.add(oppgave);
        c.add(svar);
    }


    public void startelev() 		  //her hentes Studentklassen.
    {
        Hovedvindu vindu = new Hovedvindu();
		vindu.setSize( 700, 630 );
		vindu.setVisible( true );
    }


    public void actionPerformed(ActionEvent e)  //Ved trykk kalles metodene opp.
    {
    if (e.getSource() == elev)
        startelev();
    else if (e.getSource() == laerer)
        startLaerer();
    else if (e.getSource() == besvart)
        startbesvart();
    else if (e.getSource() == avslutt)
        startavslutt();
    }


	private void startLaerer() {
		// TODO Auto-generated method stub

		Hovedvindu vindu = new Hovedvindu();
		vindu.setSize( 700, 630 );
		vindu.setVisible( true );

	}


	private void startbesvart() {
		// TODO Auto-generated method stub

	}


	private void startavslutt() {
		// TODO Auto-generated method stub

	}
}
