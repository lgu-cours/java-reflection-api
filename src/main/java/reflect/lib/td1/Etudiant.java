package reflect.lib.td1 ;

import java.util.Date;

public class Etudiant
{
    //----------------------------------------------------------------------
    // ATTRIBUTES 
    //----------------------------------------------------------------------
    private String     nom          = "";     
    private String     prenom       = "";     
    private Date       datenais     = new Date();     
    private int        departement  = 0;     
    private int        matricule    = 0;  // KEY ELEMENT     
  
    //----------------------------------------------------------------------
    // GETTERS & SETTERS
    //----------------------------------------------------------------------

    //--- FIELD : Nom ( varchar )
    public void setNom( String value )
    {
        this.nom = value;
    }
    public String getNom()
    {
        return this.nom;
    }

    //--- FIELD : Prenom ( varchar )
    public void setPrenom( String value )
    {
        this.prenom = value;
    }
    public String getPrenom()
    {
        return this.prenom;
    }

    //--- FIELD : DateNais ( date )
    public void setDatenais( Date value )
    {
        this.datenais = value;
    }
    public Date getDatenais()
    {
        return this.datenais;
    }


    public void setDepartement( int value )
    {
        this.departement = value;
    }
    public int getDepartement()
    {
        return this.departement;
    }


    //--- KEY : Matricule ( int )
    public void setMatricule( int value )
    {
        this.matricule = value;
    }
    public int getMatricule()
    {
        return this.matricule;
    }
  
    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString()
    {
        return nom + "|" + prenom + "|" + datenais + "|" + departement + "|" + matricule;
    }
}

