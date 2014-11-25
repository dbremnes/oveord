//denne kommer fra OOP-eksamen 2012

//=========================================================================
//skriv til studfil
//Her oppretter vi en txt-fil for hver student. Filen får navnet etter studentnummeret. 
//=========================================================================

String fag = fagnavn.getText();
try
{
BufferedWriter data = new BufferedWriter(new FileWriter("fag/"+fag+".txt"));//lagrer til en .txt med faget som navn
data.write("\r\n" + fagnavn.getText() + "\r\n" + kategori.getText() + "\r\n" + studiepoeng.getText()
+ "\r\n"+ obligatorisk.getText() + "\r\n" + spraak.getText() + "\r\n" + fjernundervisning.getText() +"\r\n" + selvstudium.getText()+"\r\n");//sender alle feltene til Fag.txt.
data.close();//Lukker fila når programmet er ferdig med den.


//=========================================================================
//videre fra kursvalg.java-fila
//=========================================================================


    public void hentFag(String fag1, String fag2, String fag3)
    //Denne metoden er antagelig unodvendig stor,
    //men det var paa dette viset jeg fikk lest inn fra fil og brukt linjene til aa utfore testen

        {

              try//forsoker aa hente filen som inneholder informasjon om faget
              {
                  FileInputStream fsFag1= new FileInputStream("fag/"+fag1+".txt"); //Finner riktig fagfil.
                  BufferedReader brFag1 = new BufferedReader(new InputStreamReader(fsFag1));


                  //Henter kategori
                  for(int i = 0; i < 2; ++i)//begynner paa andre linje

                      brFag1.readLine();//Leser en gitt linje

                  fag1Kategori = brFag1.readLine();//for-lokka loper gjennom fagfila (xx.txt) og henter ut den linja hvor kategorien staar og putter denne i variablen.
                  fag1Kategori = fag1Kategori.trim();//metoden trim() tar bort hvite felt for og etter strengen, saa den blir lettere aa behandle

                  //Henter studiepoeng
                  for(int i = 0; i < 0; ++i)//her gaar den til neste linje. Dersom den skal hoppe over en skriver man at i skal vare mindre enn 1.
                                            //Dersom to linje skal hoppes over skriver man 0 < 2 osv

                      brFag1.readLine();//Leser en gitt linje

                  fag1stPoeng = brFag1.readLine();
                  fag1stPoeng = fag1stPoeng.trim();

                  //Henter spraak
                  for(int i = 0; i < 0; ++i)

                      brFag1.readLine();//Leser en gitt linje

                  fag1Spraak = brFag1.readLine();
                  fag1Spraak = fag1Spraak.trim();

                  //Henter fjernundervisning
                  for(int i = 0; i < 0; ++i)

                      brFag1.readLine();//Leser en gitt linje

                  fag1Nett = brFag1.readLine();
                  fag1Nett = fag1Nett.trim();

                    //Henter selvstudium
                    for(int i = 0; i < 0; ++i)

                        brFag1.readLine();//Leser en gitt linje

                    fag1Selv = brFag1.readLine();
                    fag1Selv = fag1Selv.trim();


              }
              catch (IOException l)//kaster en Exception hvis brukeren har valgt et fag som ikke finnes.
              {
                  JOptionPane.showMessageDialog(null, "Det finnes ingen fag med " + fag1);

              }

              try //her begynner man aa lese inn fra valg nummer 2
              {
                  FileInputStream fsFag2 = new FileInputStream("fag/"+fag2+".txt"); //Finner riktig fagfil
                  BufferedReader brFag2 = new BufferedReader(new InputStreamReader(fsFag2));

