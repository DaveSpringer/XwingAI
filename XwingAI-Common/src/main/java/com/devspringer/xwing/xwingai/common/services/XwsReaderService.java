package com.devspringer.xwing.xwingai.common.services;

import com.devspringer.xwing.xwingai.common.dto.Pilot;
import com.devspringer.xwing.xwingai.common.dto.Ship;
import com.devspringer.xwing.xwingai.common.dto.XwingList;

import java.util.List;

/**
 * The XwsReaderService interface defines the basic implementation that an XWS Reading service would need to implement.
 */
public interface XwsReaderService {

    /**
     * The readInXwsList method describes the expected inputs for reading XWS in JSON format.  The String parameter is expected to contain JSON.
     *
     * @param jsonXws
     * @return
     */
    public XwingList readInXwsList(String jsonXws);

    /**
     * Get all ships available for the specified version.
     * @return
     */
    public List<Ship> getAllShips(String version);

    /**
     * Get all pilots available for the specified version.
     * @param version
     * @return
     */
    public List<Pilot> getAllPilots(String version);
}
