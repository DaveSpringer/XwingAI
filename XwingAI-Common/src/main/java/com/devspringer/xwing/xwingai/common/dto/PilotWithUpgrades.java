package com.devspringer.xwing.xwingai.common.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * The PilotWithUpgrades class describes a pilot and the upgrades currently assigned to him.
 */
public class PilotWithUpgrades implements Serializable{
    private static final long serialVersionUID = -8952057947353688175L;

    private Pilot pilot;
    private List<Upgrade> upgrades;

    public PilotWithUpgrades(Pilot pilot, List<Upgrade> upgrades) {
        this.pilot = pilot;
        this.upgrades = upgrades;
    }

    public PilotWithUpgrades() {
    }

    /**
     * @return The pilot as a Pilot
     */
    public Pilot getPilot() {
        return pilot;
    }

    /**
     * @param pilot The pilot to set in Pilot format
     */
    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    /**
     * @return The upgrades as a List of Upgrades
     */
    public List<Upgrade> getUpgrades() {
        return upgrades;
    }

    /**
     * @param upgrades The upgrades to set in List format
     */
    public void setUpgrades(List<Upgrade> upgrades) {
        this.upgrades = upgrades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PilotWithUpgrades that = (PilotWithUpgrades) o;

        return new EqualsBuilder()
                .append(pilot, that.pilot)
                .append(upgrades, that.upgrades)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(pilot)
                .append(upgrades)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "PilotWithUpgrades{" +
                "pilot=" + pilot +
                ", upgrades=" + upgrades +
                '}';
    }
}
