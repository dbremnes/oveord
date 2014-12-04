
public class Innlegg {

	   private String norskOrd, engelskOrd;
	   private int nivaa;

	   public Innlegg(){}
	   
	    public Innlegg(String norskOrd, String engelskOrd, int nivaa) {
			 // TODO Auto-generated constructor stub
			 this.norskOrd = norskOrd;
			 this.engelskOrd = engelskOrd;
			 this.nivaa = nivaa;
	    }

	    public String getNorskOrd(){
	   		return norskOrd;
	    }
	    
	    public String getEngelskOrd(){
	   		return engelskOrd;
	    }
	    
	    public Integer getNivaa(){
	   		return nivaa;
	    }
	    

	    public  void setNorskOrd(String norskOrd){
	   	 	this.norskOrd = norskOrd;
	    }
	    
	    public  void setEngelskOrd(String engelskOrd){
	   	 	this.engelskOrd = engelskOrd;
	    }
	    
	    public  void setNivaa(int nivaa){
	   	 	this.nivaa = nivaa;
	    }
	    

}
