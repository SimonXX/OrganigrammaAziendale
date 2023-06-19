package Composite;


import java.util.List;
import Visitor.Visitor;

public interface UnitComponent {
    String getName();
    void addEmployee(Employee employee);
    void removeEmployee(Employee employee);
    List<Employee> getEmployees();
    void addSubUnit(Unit subUnit);
    void removeSubUnit(Unit subUnit);
    List<Unit> getSubUnits();
    void addAllowedRole(Role role);
    void removeAllowedRole(Role role);
    List<Role> getAllowedRoles();
    boolean hasEmployee(Employee employee);
    void printStructure();
    boolean hasSubUnits();
    boolean hasEmployees();

    void accept(Visitor v);

}