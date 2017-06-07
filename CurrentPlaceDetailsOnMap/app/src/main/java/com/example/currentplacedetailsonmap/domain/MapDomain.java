package com.example.currentplacedetailsonmap.domain;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ars on 6/5/17.
 */

public class MapDomain {
    private LatLng latLng;
    private String title;

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
