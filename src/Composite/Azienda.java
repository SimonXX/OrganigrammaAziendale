package Composite;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class Azienda {


    @JsonIdentityReference
    private List<Employee> employees;

    private String name;

    private List<Unit> units;

    private Unit rootUnit;

    private List<Role> roles;

    private List<String> nomiUtilizzati = new ArrayList<>();

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void removeEmployee(Employee e) {
        if (employees.contains(e)) {
            employees.remove(e);
        }

        if (nomiUtilizzati.contains(e.getName())) {
            nomiUtilizzati.remove(e.getName());
        }
    }

    public void addRole(Role role) {
        if (!roles.contains(role))
            roles.add(role);

        if (!nomiUtilizzati.contains(role.getName())) {
            nomiUtilizzati.add(role.getName());
        }
    }

    public void removeRole(Role role) {
        if (roles.contains(role)) {
            roles.remove(role);

            for (Unit u : units) {
                if (u.getAllowedRoles().contains(role)) {
                    u.removeAllowedRole(role);
                }
            }

            for (Employee e : employees) {
                if (e.getRoles().contains(role)) {
                    e.removeRole(role);
                }
            }
        }

        if (nomiUtilizzati.contains(role.getName())) {
            nomiUtilizzati.remove(role.getName());
        }
    }

    @Override
    public String toString() {
        return "Azienda{" +
                "employees=" + employees +
                ", units=" + units +
                ", rootUnit=" + rootUnit +
                ", roles=" + roles +
                '}';
    }

    public Azienda() {
        // Costruttore di default vuoto
    }

    public Unit findUnitByName(String name) {
        for (Unit unit : units) {
            if (unit.getName().equals(name)) {
                return unit;
            }
        }

        return null; // Unità non trovata
    }

    public Azienda(Unit rootUnit) {
        this.rootUnit = rootUnit;
        employees = new ArrayList<>();
        units = new ArrayList<>();
        roles = new ArrayList<>();
        this.name = "azienda";
        nomiUtilizzati = new ArrayList<>();

        units.add(rootUnit);

        nomiUtilizzati.add(rootUnit.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getRootUnit() {
        return rootUnit;
    }

    public void addEmployee(Employee e) {
        if (!employees.contains(e)) {
            employees.add(e);
        }

        if (!nomiUtilizzati.contains(e.getName())) {
            nomiUtilizzati.add(e.getName());
        }
    }

    public void addUnit(Unit u) {
        if (!units.contains(u)) {
            units.add(u);
        }

        if (!nomiUtilizzati.contains(u.getName())) {
            nomiUtilizzati.add(u.getName());
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public Role findRoleByName(String roleName) {
        for (Role role : roles) {
            if (role.getName().equals(roleName)) {
                return role;
            }
        }
        return null; // Ruolo non trovato
    }

    public String[] getRoleNames() {
        List<Role> roles = this.getRoles();
        String[] roleNames = new String[roles.size()];

        for (int i = 0; i < roles.size(); i++) {
            roleNames[i] = roles.get(i).getName();
        }

        return roleNames;
    }

    public void removeUnit(Unit unit) {
        if (units.contains(unit)) {
            units.remove(unit);

            for (Employee e : employees) {
                if (e.getUnits().contains(unit)) {
                    e.removeUnit(unit);
                }
            }
        }

        if (nomiUtilizzati.contains(unit.getName())) {
            nomiUtilizzati.remove(unit.getName());
        }
    }

    public Employee findEmployeeByName(String name) {
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }

        return null; // Impiegato non trovato
    }

    public List<String> getNomiUtilizzati() {
        return nomiUtilizzati;
    }

    public List<String> getEmployeeNames() {
        List<Employee> employees = this.getEmployees();
        List<String> employeeNames = new ArrayList<>();

        for (Employee employee : employees) {
            employeeNames.add(employee.getName());
        }

        return employeeNames;
    }
}