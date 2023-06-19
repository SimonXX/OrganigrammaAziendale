package Visitor;

import Composite.Employee;
import Composite.Unit;

public interface Visitor {
    void visit(Unit unit);
    void visit(Employee employee);
}

