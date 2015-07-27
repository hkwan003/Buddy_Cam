package com.example.calvinkwan.buddy_cam;

/**
 * Created by calvinkwan on 7/27/15.
 */
public class List_User_Cameras
{
    private String camera_name = "";
    private String ip_address = "";

    public void setName(String cam_name)
    {
        this.camera_name = cam_name;
    }

    public String getName()
    {
        return camera_name;
    }

    public void setCityState(String ip_address)
    {
        this.ip_address = ip_address;
    }

    public String getCityState()
    {
        return ip_address;
    }
}
