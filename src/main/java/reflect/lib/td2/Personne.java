package reflect.lib.td2;

import java.util.Date;

public abstract class Personne
{
    private String nom ;
    private String prenom ;
    private int age ;
    private Date dateNais ;
    
    /**
     * @return Returns the age.
     */
    public int getAge()
    {
        return age;
    }
    /**
     * @param age The age to set.
     */
    public void setAge(int age)
    {
        this.age = age;
    }
    /**
     * @return Returns the dateNais.
     */
    public Date getDateNais()
    {
        return dateNais;
    }
    /**
     * @param dateNais The dateNais to set.
     */
    public void setDateNais(Date dateNais)
    {
        this.dateNais = dateNais;
    }

    public void setDateNais(String s)
    {
        Date d = new Date();
        //d.setMonth(m-1);
        this.dateNais = d;
    }
    /**
     * @return Returns the nom.
     */
    public String getNom()
    {
        return nom;
    }
    /**
     * @param nom The nom to set.
     */
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    /**
     * @return Returns the prenom.
     */
    protected String getPrenom()
    {
        return prenom;
    }
    /**
     * @param prenom The prenom to set.
     */
    protected void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String desc()
    {
        return "Je suis une personne.";
    }
    
}
