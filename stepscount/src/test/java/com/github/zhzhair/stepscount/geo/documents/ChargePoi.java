package com.github.zhzhair.stepscount.geo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by 49535
 * on 2018/4/17.
 */
@Document(collection = "charge_poi")
public class ChargePoi implements Serializable {

    @Id
    private String _id;

    @Indexed
    private String poi_id;
    private String poi_name;
    @GeoSpatialIndexed
    private Double[] location;
    private String media_url;
    private Double price;

    public ChargePoi() {
        super();
    }

    @PersistenceConstructor
    public ChargePoi(String _id, String poi_id, String poi_name, Double[] location, String media_url, Double price) {
        super();
        this._id = _id;
        this.poi_id = poi_id;
        this.poi_name = poi_name;
        this.location = location;
        this.media_url = media_url;
        this.price = price;
    }

    public ChargePoi(String _id, String poi_id, String poi_name, Double x, Double y, String media_url, Double price) {
        super();
        this._id = _id;
        this.poi_id = poi_id;
        this.poi_name = poi_name;
        this.location = new Double[]{x, y};
        this.media_url = media_url;
        this.price = price;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPoi_id() {
        return poi_id;
    }

    public void setPoi_id(String poi_id) {
        this.poi_id = poi_id;
    }

    public String getPoi_name() {
        return poi_name;
    }

    public void setPoi_name(String poi_name) {
        this.poi_name = poi_name;
    }

    public Double[] getLocation() {
        return location;
    }

    public void setLocation(Double[] location) {
        this.location = location;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ChargePoi [_id=" + _id + ", poi_id=" + poi_id + ", poi_name=" + poi_name + ", location="
                + Arrays.toString(location) + ", media_url=" + media_url + ", price=" + price + "]";
    }
}
