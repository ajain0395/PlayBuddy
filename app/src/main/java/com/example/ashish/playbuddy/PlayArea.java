package com.example.ashish.playbuddy;

public class PlayArea {
    String sportId;
    String email;
    String venueId;
    String playAreaId;
    String slotId;
    static String selectedSportId = null;
    static String selectedVenueId = null;

    public String getSportId() {
        return sportId;
    }

    public void setSportId(String sportId) {
        this.sportId = sportId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getPlayAreaId() {
        return playAreaId;
    }

    public void setPlayAreaId(String playAreaId) {
        this.playAreaId = playAreaId;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }
}
