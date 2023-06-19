package Composite;

import java.util.*;

public class Unit implements UnitComponent {
    private String name;
    private List<Employee> employees;
    private List<Unit> subUnits;
    private List<Role> allowedRoles;
    private Unit parentUnit;
    private Map<String, List<String>> employeeRole;
    private int id;

    public Unit() {
    }

    public Unit(String name) {
        this.name = name;
        employees = new ArrayList<>();
        subUnits = new ArrayList<>();
        allowedRoles = new ArrayList<>();
        employeeRole = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getParentUnit() {
        return parentUnit;
    }

    public void setParentUnit(Unit parentUnit) {
        this.parentUnit = parentUnit;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
            employee.addUnit(this);
        }
    }

    public void removeEmployee(Employee employee) {
        if (employees.contains(employee)) {
            employees.remove(employee);
            employee.removeUnit(this);
        }
    }

    public List<Unit> getSubUnits() {
        return subUnits;
    }

    public void addSubUnit(Unit subUnit) {
        if (!subUnits.contains(subUnit)) {
            subUnits.add(subUnit);
            subUnit.setParentUnit(this);
        }
    }

    public void removeSubUnit(Unit subUnit) {
        if (subUnits.contains(subUnit)) {
            subUnits.remove(subUnit);
        }
    }

    public void deleteByParent() {
        if (parentUnit != null) {
            parentUnit.removeSubUnit(this);
        }
    }

    public List<Role> getAllowedRoles() {
        return allowedRoles;
    }

    public void addAllowedRole(Role role) {
        if (!allowedRoles.contains(role)) {
            allowedRoles.add(role);
        }
    }

    public void removeAllowedRole(Role role) {
        if (allowedRoles.contains(role)) {
            allowedRoles.remove(role);

            ListIterator<Employee> iterator = employees.listIterator();

            while (iterator.hasNext()) {
                Employee e = iterator.next();
                List<String> roles = employeeRole.get(e.getName());
                if (roles.contains(role.getName())) {
                    roles.remove(role.getName());
                }

                if (roles.isEmpty()) {
                    iterator.remove();
                }
            }
        }
    }

    public Map<String, List<String>> getEmployeeRole() {
        return employeeRole;
    }

    public void addEmployeeRole(String nameE, String nameR) {
        if (!employeeRole.containsKey(nameE)) {
            employeeRole.put(nameE, new ArrayList<>());
        }

        List<String> roles = employeeRole.get(nameE);

        if (!roles.contains(nameR)) {
            roles.add(nameR);
        }

        employeeRole.put(nameE, roles);
    }

    public boolean hasEmployee(Employee employee) {
        return employees.contains(employee);
    }

    public void printStructure() {
        printStructure("");
    }

    private void printStructure(String prefix) {
        System.out.println(prefix + name);

        for (Unit subUnit : subUnits) {
            subUnit.printStructure(prefix + "--");
        }

        for (Employee employee : employees) {
            System.out.println(prefix + "--" + employee.getName());
        }
    }

    public String[] getAllowedRoleNames() {
        String[] roleNames = new String[allowedRoles.size()];
        for (int i = 0; i < allowedRoles.size(); i++) {
            roleNames[i] = allowedRoles.get(i).getName();
        }
        return roleNames;
    }

    public boolean hasSubUnits() {
        return !subUnits.isEmpty();
    }

    public boolean hasEmployees() {
        return !employees.isEmpty();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                ", subUnits=" + subUnits +
                ", allowedRoles=" + allowedRoles +
                ", parentUnit=" + parentUnit +
                '}';
    }
}