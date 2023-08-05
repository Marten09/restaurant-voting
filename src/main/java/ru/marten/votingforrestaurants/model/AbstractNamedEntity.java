package ru.marten.votingforrestaurants.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    protected String name;

    public AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    protected AbstractNamedEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AbstractNamedEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
