package ObserverGUI;


import Composite.Singleton.Azienda;
import Composite.Unit;

public class OrganigrammaModel {
    //la classe OrganigrammaModel rappresenta il modello dell'organigramma e si occupa della gestione dei dati e della logica dell'organigramma.

    private Unit organigramma;
    private Azienda azienda;

    public OrganigrammaModel(Unit rootUnit, Azienda azienda) {
        this.organigramma = rootUnit;
        this.azienda = azienda;

    }
    public Unit getOrganigramma(){
        return organigramma;
    }

    public void setOrganigramma(Unit organigramma){
        this.organigramma = organigramma;
    }

    public Azienda getAzienda(){
        return azienda;
    }

}
