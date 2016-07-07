package ru.kotovalex.CareHire.tools;

/**
 * Created by vasilenko.aleksandr on 05.07.2016.
 */
public class Result {
    private String display_name;
    private int parent_place_id;
    private Place place;
    private String individual_id;
    private String geo_type;
    private String localised_geo_type;
    private boolean is_bookable;

    private Result(){

    }

    public String getDisplay_name() {
        return display_name;
    }

    public int getParent_place_id() {
        return parent_place_id;
    }

    public String getIndividual_id() {
        return individual_id;
    }

    public String getGeo_type() {
        return geo_type;
    }

    public String getLocalised_geo_type() {
        return localised_geo_type;
    }

    public boolean is_bookable() {
        return is_bookable;
    }

    public void setPlace(Place place) {
        this.place = place;
    }


}
