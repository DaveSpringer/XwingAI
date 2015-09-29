package com.devspringer.xwing.xwingai.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * The Pilot class describes a pilot of a ship in X-Wing.
 */
public class Pilot implements Serializable{
    private static final long serialVersionUID = -7658024486033715706L;

    private String id;
    private String name;
    private String faction;
    private String skill;
    private String ability;
    private Ship ship;


    public Pilot(String id, String name, String faction, String skill, String ability, Ship ship) {
        this.id = id;
        this.name = name;
        this.faction = faction;
        this.skill = skill;
        this.ability = ability;
        this.ship = ship;
    }

    public Pilot() {
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
     * @return The skill as a String
     */
    public String getSkill() {
        return skill;
    }

    /**
     * @param skill The skill to set in String format
     */
    public void setSkill(String skill) {
        this.skill = skill;
    }

    /**
     * @return The ability as a String
     */
    public String getAbility() {
        return ability;
    }

    /**
     * @param ability The ability to set in String format
     */
    public void setAbility(String ability) {
        this.ability = ability;
    }

    /**
     * @return The ship as a Ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * @param ship The ship to set in Ship format
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Pilot pilot = (Pilot) o;

        return new EqualsBuilder()
                .append(id, pilot.id)
                .append(name, pilot.name)
                .append(faction, pilot.faction)
                .append(skill, pilot.skill)
                .append(ability, pilot.ability)
                .append(ship, pilot.ship)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(faction)
                .append(skill)
                .append(ability)
                .append(ship)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", faction='" + faction + '\'' +
                ", skill='" + skill + '\'' +
                ", ability='" + ability + '\'' +
                ", ship=" + ship +
                '}';
    }
}
