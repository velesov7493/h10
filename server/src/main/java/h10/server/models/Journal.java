package h10.server.models;

import java.util.Objects;

public class Journal {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Journal journal = (Journal) o;
        return id == journal.id
               && Objects.equals(name, journal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
