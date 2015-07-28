package com.example.calvinkwan.buddy_cam;

/**
 * Created by calvinkwan on 7/27/15.
 */
public class IpCamera {
    private String cameraName = "";
    private String ipAddress = "";

    public void setName(String camName) {
        this.cameraName = camName;
    }

    public String getName() {
        return cameraName;
    }

    public void setCityState(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCityState() {
        return ipAddress;
    }
}
