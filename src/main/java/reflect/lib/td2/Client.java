package reflect.lib.td2;


public class Client extends Personne implements IDescribe
{
    private int NoClient = 0 ;
    
    public Client(int noClient) {
		super();
		NoClient = noClient;
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
     * @return Returns the noClient.
     */
    public int getNoClient()
    {
        return NoClient;
    }
    /**
     * @param noClient The noClient to set.
     */
    public void setNoClient(int noClient)
    {
        NoClient = noClient;
    }
    
    public String desc()
    {
        return "Je suis un client, mon no est " + NoClient ;
    }
    
	@Override
	public String toString() {
		return "Client " + NoClient ;
	}

}
