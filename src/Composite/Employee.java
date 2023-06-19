package Composite;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private List<Unit> units;
    private List<Role> roles;
    private int id;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
        units = new ArrayList<>();
        roles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void addUnit(Unit unit) {
        if (!units.contains(unit))
            units.add(unit);
    }

    public void removeUnit(Unit unit) {
        if (units.contains(unit)) {
            units.remove(unit);
        }
    }

    public void removeRole(Role role) {
        if (roles.contains(role)) {
            roles.remove(role);
        }
    }

    public void deleteByEmployee() {
        for (Unit u : units) {
            u.removeEmployee(this);
        }
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", units=" + units +
                ", roles=" + roles +
                '}';
    }
}