package Visitor;


import Composite.Employee;
import Composite.Unit;
import Composite.UnitComponent;

public class PrintVisitor implements Visitor{

    StringBuilder sb = new StringBuilder();

    public StringBuilder getSb() {
        return sb;
    }

    @Override
    public void visit(Unit unit) {
        sb.append("Unità: " + unit.getName() +"\n");
        sb.append("Ruoli Ammessi" + unit.getAllowedRoles()+"\n");

        for (Employee employee : unit.getEmployees()) {
            sb.append("Impiegati: " + employee.getName() + ", Ruoli Ricoperti: " + employee.getRoles()+"\n");
        }

        sb.append("-----------------------"+"\n");

        for (UnitComponent component : unit.getSubUnits()) {
            component.accept(this);
        }
    }


    @Override
    public void visit(Employee employee) {
        // Non viene utilizzato
    }
}