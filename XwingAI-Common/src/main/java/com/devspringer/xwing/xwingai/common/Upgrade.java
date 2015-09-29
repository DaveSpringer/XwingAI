package com.devspringer.xwing.xwingai.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * The Upgrade class describes an upgrade.
 */
public class Upgrade implements Serializable {
    private static final long serialVersionUID = -4201167439835811008L;

    private String id;
    private String name;
    private String type;
    private String description;

    public Upgrade(String id, String name, String type, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public Upgrade() {
    }

    /**
     * @return The id as a String
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id to set in String format
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set in String format
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The type as a String
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type to set in String format
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The description as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set in String format
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Upgrade upgrade = (Upgrade) o;

        return new EqualsBuilder()
                .append(id, upgrade.id)
                .append(name, upgrade.name)
                .append(type, upgrade.type)
                .append(description, upgrade.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(type)
                .append(description)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Upgrade{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
