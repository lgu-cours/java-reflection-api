package reflect.lib.td2;

import java.util.Date;

// visibility : "package"
class Employe extends Personne implements IDescribe
{
    private int Matricule = 200 ;
    private Date DateEmbauche = new Date() ;
    private double Salaire = 1000 ;
    

    /**
     * @param matricule
     * @param salaire
     */
    public Employe(int matricule, double salaire)
    {
        super();
        Matricule = matricule;
        Salaire = salaire;
    }

    public Employe(String nom, String pre)
    {
        super();
        super.setNom(nom);
        super.setPrenom(pre);
    }
    
    public String getPrenom()
    {
        return super.getPrenom();
    }
    public void setPrenom(String nom)
    {
        super.setPrenom(nom);
    }

    /**
     * @return Returns the dateEmbauche.
     */
    public Date getDateEmbauche()
    {
        return DateEmbauche;
    }
    /**
     * @param dateEmbauche The dateEmbauche to set.
     */
    public void setDateEmbauche(Date dateEmbauche)
    {
        DateEmbauche = dateEmbauche;
    }
    /**
     * @return Returns the matricule.
     */
    public int getMatricule()
    {
        return Matricule;
    }
    /**
     * @param matricule The matricule to set.
     */
    public void setMatricule(int matricule)
    {
        Matricule = matricule;
    }
    /**
     * @return Returns the salaire.
     */
    public double getSalaire()
    {
        return Salaire;
    }
    /**
     * @param salaire The salaire to set.
     */
    public void setSalaire(double salaire)
    {
        Salaire = salaire;
    }
    
    public String desc()
    {
        return "Je suis un employe, mon matricule est " + Matricule;
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Employe " + getNom() + " " + getPrenom() ;
	}

}
