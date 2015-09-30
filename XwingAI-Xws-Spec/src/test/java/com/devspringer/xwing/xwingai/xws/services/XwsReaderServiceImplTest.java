package com.devspringer.xwing.xwingai.xws.services;

import com.devspringer.xwing.xwingai.common.dto.Pilot;
import com.devspringer.xwing.xwingai.common.dto.Ship;
import com.devspringer.xwing.xwingai.common.services.XwsReaderService;
import junit.framework.TestCase;

import java.util.List;

public class XwsReaderServiceImplTest extends TestCase {

    public void testReadInXwsList() throws Exception {

    }

    public void testGetAllShips() throws Exception {
        // Setup.
        XwsReaderService service = new XwsReaderServiceImpl();

        // Execute
        List<Ship> result = service.getAllShips("0.3.0");

        // Verify.
        assertNotNull(result);
        assertFalse(result.isEmpty());
        Ship shipZero = result.get(0);
        assertNotNull(shipZero);
        assertEquals(shipZero.getName(), "X-Wing");
        assertEquals(shipZero.getId(), "X-Wing");
        assertEquals(shipZero.getAttack(), "3");
        assertEquals(shipZero.getAgility(), "2");
        assertEquals(shipZero.getHull(), "3");
        assertEquals(shipZero.getShields(), "2");
        assertNotNull(shipZero.getManeuvers());
    }

    public void testGetAllPilots() throws Exception {

        // Setup.
        XwsReaderService service = new XwsReaderServiceImpl();

        // Execute.
        List<Pilot> pilots = service.getAllPilots("0.3.0");

        // Verify.
        assertNotNull(pilots);
        assertFalse(pilots.isEmpty());
        Pilot pilotZero = pilots.get(0);
        assertNotNull(pilotZero);
        assertEquals(pilotZero.getId(), "prototypepilot");
        assertEquals(pilotZero.getName(), "Prototype Pilot");
        assertEquals(pilotZero.getFaction(), "rebel");
        assertEquals(pilotZero.getSkill(), "1");
        assertEquals(pilotZero.getAbility(), null);
        assertEquals(pilotZero.getShipName(), "A-Wing");
    }
}