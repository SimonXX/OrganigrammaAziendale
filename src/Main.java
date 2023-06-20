import Composite.Azienda;
import Composite.Unit;
import ObserverGUI.OrganigrammaModel;
import ObserverGUI.view.OrganigrammaView;
import Visitor.PrintVisitor;
import Visitor.StatsVisitor;
import Visitor.Visitor;
public class Main {
    public static void main(String[] args) {
        // Creazione dell'organigramma
        Unit rootUnit = new Unit("Azienda");//nodo radice

        Azienda azienda = new Azienda(rootUnit);


        // Creazione del modello e della vista dell'organigramma
        OrganigrammaModel model = new OrganigrammaModel(rootUnit, azienda);
        OrganigrammaView view = new OrganigrammaView(model);


    }

}
