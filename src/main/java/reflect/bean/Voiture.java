package reflect.bean;

public class Voiture {

	private String marque ;
	private String modele ;
	private int annee ;
	private double prix ;
	
	public Voiture(String marque, String modele, int annee, double prix) {
		super();
		this.marque = marque;
		this.modele = modele;
		this.annee = annee;
		this.prix = prix;
	}
	
	
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}
	public void setModele(String modele) {
		this.modele = modele;
	}
	
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
}
