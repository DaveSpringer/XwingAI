package com.devspringer.xwing.xwingai.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * The XwingList class captures a list for the X-Wing Miniatures Game that may be used either by the player or the AI.
 */
public class XwingList implements Serializable {
    private static final long serialVersionUID = 3579819475692231736L;

    private String name;
    private String faction;
    private List<PilotWithUpgrades> pilotsWithUpgrades;

    public XwingList(String name, String faction, List<PilotWithUpgrades> pilotsWithUpgrades) {
        this.name = name;
        this.faction = faction;
        this.pilotsWithUpgrades = pilotsWithUpgrades;
    }

    public XwingList() {
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
     * @return The faction as a String
     */
    public String getFaction() {
        return faction;
    }

    /**
     * @param faction The faction to set in String format
     */
    public void setFaction(String faction) {
        this.faction = faction;
    }

    /**
     * @return The pilotsWithUpgrades as a PilotWithUpgrades>
     */
    public List<PilotWithUpgrades> getPilotsWithUpgrades() {
        return pilotsWithUpgrades;
    }

    /**
     * @param pilotsWithUpgrades The pilotsWithUpgrades to set in PilotWithUpgrades> format
     */
    public void setPilotsWithUpgrades(List<PilotWithUpgrades> pilotsWithUpgrades) {
        this.pilotsWithUpgrades = pilotsWithUpgrades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        XwingList xwingList = (XwingList) o;

        return new EqualsBuilder()
                .append(name, xwingList.name)
                .append(faction, xwingList.faction)
                .append(pilotsWithUpgrades, xwingList.pilotsWithUpgrades)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(faction)
                .append(pilotsWithUpgrades)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "XwingList{" +
                "name='" + name + '\'' +
                ", faction='" + faction + '\'' +
                ", pilotsWithUpgrades=" + pilotsWithUpgrades +
                '}';
    }
}
