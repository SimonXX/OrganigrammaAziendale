package Composite;

import Visitor.Visitor;

import java.util.List;

public class AbstractUnitComponent implements UnitComponent{
    @Override
    public String getName() {
        return null;
    }


    public void addEmployee(Employee employee) {

    }


    public void removeEmployee(Employee employee) {

    }


    public List<Employee> getEmployees() {
        return null;
    }


    public void addSubUnit(Unit subUnit) {

    }


    public void removeSubUnit(Unit subUnit) {

    }


    public List<Unit> getSubUnits() {
        return null;
    }


    public void addAllowedRole(Role role) {

    }


    public void removeAllowedRole(Role role) {

    }


    public List<Role> getAllowedRoles() {
        return null;
    }


    public boolean hasSubUnits() {
        return false;
    }


    public boolean hasEmployees() {
        return false;
    }


    public void accept(Visitor v) {

    }
}
