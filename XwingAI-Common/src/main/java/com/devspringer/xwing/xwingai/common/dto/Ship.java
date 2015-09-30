package com.devspringer.xwing.xwingai.common.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * The Ship class describes a ship in X-Wing.
 */
public class Ship implements Serializable {
    private static final long serialVersionUID = -7874168132710843560L;

    private String id;
    private String name;
    private String attack;
    private String agility;
    private String hull;
    private String shields;
    private String[] actions;
    private List<List<Integer>> maneuvers;

    public Ship(String id, String name, String attack, String agility, String hull, String shields, String[] actions, List<List<Integer>> maneuvers) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.agility = agility;
        this.hull = hull;
        this.shields = shields;
        this.actions = actions;
        this.maneuvers = maneuvers;
    }

    public Ship() {
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
     * @return The attack as a String
     */
    public String getAttack() {
        return attack;
    }

    /**
     * @param attack The attack to set in String format
     */
    public void setAttack(String attack) {
        this.attack = attack;
    }

    /**
     * @return The agility as a String
     */
    public String getAgility() {
        return agility;
    }

    /**
     * @param agility The agility to set in String format
     */
    public void setAgility(String agility) {
        this.agility = agility;
    }

    /**
     * @return The hull as a String
     */
    public String getHull() {
        return hull;
    }

    /**
     * @param hull The hull to set in String format
     */
    public void setHull(String hull) {
        this.hull = hull;
    }

    /**
     * @return The shields as a String
     */
    public String getShields() {
        return shields;
    }

    /**
     * @param shields The shields to set in String format
     */
    public void setShields(String shields) {
        this.shields = shields;
    }

    /**
     * @return The actions as a String
     */
    public String[] getActions() {
        return actions;
    }

    /**
     * @param actions The actions to set in String format
     */
    public void setActions(String[] actions) {
        this.actions = actions;
    }

    /**
     * @return The maneuvers as a Integer[]
     */
    public List<List<Integer>> getManeuvers() {
        return maneuvers;
    }

    /**
     * @param maneuvers The maneuvers to set in Integer[] format
     */
    public void setManeuvers(List<List<Integer>> maneuvers) {
        this.maneuvers = maneuvers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        return new EqualsBuilder()
                .append(id, ship.id)
                .append(name, ship.name)
                .append(attack, ship.attack)
                .append(agility, ship.agility)
                .append(hull, ship.hull)
                .append(shields, ship.shields)
                .append(actions, ship.actions)
                .append(maneuvers, ship.maneuvers)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(attack)
                .append(agility)
                .append(hull)
                .append(shields)
                .append(actions)
                .append(maneuvers)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", attack='" + attack + '\'' +
                ", agility='" + agility + '\'' +
                ", hull='" + hull + '\'' +
                ", shields='" + shields + '\'' +
                ", actions=" + Arrays.toString(actions) +
                ", maneuvers=" + maneuvers +
                '}';
    }
}
