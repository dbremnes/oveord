import java.util.ArrayList;


public class Elev {
	String Fornavn, Etternavn;
	int nivaaElev;

	public String getFornavn(){
		return Fornavn;
	}
	
	public String getEtternavn(){
		return Etternavn;
	}
	
	public Integer getnivaaElev(){
		return nivaaElev;
	}
	

	public void setFornavn(String Fornavn){
		this.Fornavn = Fornavn;
	}
	
	public  void setEtternavn(String Etternavn){
		this.Etternavn = Etternavn;
	}
	
	public  void setnivaaElev(int nivaaElev){
		this.nivaaElev = nivaaElev;
	}

	public static void add(ArrayList<Elev> elevensinput) {
		// TODO Auto-generated method stub	
	}
}
