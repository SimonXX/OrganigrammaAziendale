package Visitor;


import Composite.Employee;
import Composite.Unit;
import Composite.UnitComponent;

public class StatsVisitor implements Visitor {
    private int totalUnits;
    private int totalEmployees;
    private int totalRoles;

    @Override
    public void visit(Unit unit) {
        totalUnits++;
        totalRoles += unit.getAllowedRoles().size();
        totalEmployees += unit.getEmployees().size();

        for (UnitComponent component : unit.getSubUnits()) {
            component.accept(this);
        }
    }

    public int getTotalUnits() {
        return totalUnits;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public int getTotalRoles() {
        return totalRoles;
    }

    @Override
    public void visit(Employee employee) {
        // Non viene utilizzato in questo esempio
    }

    public void printStatistics() {
        System.out.println("Totale Unità presenti nell'Azienda: " + totalUnits);
        System.out.println("Totale Impiegati assunti nell'Azienda: " + totalEmployees);
        System.out.println("Totale Ruoli definiti nell'Azienda: " + totalRoles);
    }
}