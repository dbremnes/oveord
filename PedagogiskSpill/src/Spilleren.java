import javax.swing.JOptionPane;

public class Spilleren {
	private String navn,fornavn,etternavn;	// Spillers navn
	private int nivaa;
	private int sum;
	private int antallRiktige;

	public Spilleren(int nivaaet, String fornavn, String etternavn) {
		// TODO Auto-generated constructor stub
		this.navn = fornavn + " " + etternavn;	// Spillers navn
		this.fornavn = fornavn; 
		this.etternavn = etternavn;
		this.nivaa = nivaaet;
    }

   public int getTotalPoengsum(){
	   // Summer poengsummene (Fra arrayen)
	   return this.sum;
   }

   public void setPoengsum(){
	   this.sum+=10;
   }

   public void setNivaa(){
	   this.nivaa++;
   }
   
   public int getNivaa(){//må ha spillerens nivå, det hører til i spiller
	   return this.nivaa;
   }
   
   public int nivaaOpp(){
	   this.nivaa++;
	   return this.nivaa;
   }

   public String toString(){
	   String streng = this.navn+" - Nivå:"+this.nivaa;
	   return streng;
   }
   
   public String getNavn(){
	   return this.navn;
   }
	  
	public String getFornavn() {
		return fornavn;
	}
	
	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}
	
	public String getEtternavn() {
		return etternavn;
	}
	
	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	} 
}
