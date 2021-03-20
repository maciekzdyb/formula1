package com.tena.formula1.controller;

import java.util.Map;

public interface SeasonService {

    /**
     * Returns performance map for specific racer and position
     * @param  username String
     * @param  position int
     * @return Map&lt;String, Integer&gt
     */
    Map<String, Integer> getRacerPerformance(String username, int position);

    /**
     * Returns performance map of all teams, for given position
     * @param position int
     * @return Map&lt;String, Integer&gt
     */
    Map<String, Integer> getTeamsPerformance(int position);
}
