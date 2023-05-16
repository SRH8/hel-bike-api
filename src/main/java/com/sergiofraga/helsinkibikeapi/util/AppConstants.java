package com.sergiofraga.helsinkibikeapi.util;

/**
 * This class stores constants used by the application
 */
public class AppConstants {

    private  AppConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public  static final String DEFAULT_PAGE_SIZE = "15";
    public static final String DEFAULT_STATIONS_SORT_BY = "nimi";
    public static final String DEFAULT_SORT_DIRECTION = "asc";
    public static final String DEFAULT_JOURNEYS_SORT_BY = "departureDate";
}