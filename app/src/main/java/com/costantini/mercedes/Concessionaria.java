package com.costantini.mercedes;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Administrator on 12/04/2015.
 */
public class Concessionaria implements ClusterItem {
    private final LatLng mPosition;

    public Concessionaria(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }}