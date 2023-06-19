package Composite;

public class Role {
    private String name;
    private int id;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
        // Costruttore vuoto richiesto da Jackson per la deserializzazione
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Role otherRole = (Role) obj;
        return name.equals(otherRole.name);
    }
}