package com.example.calvinkwan.buddy_cam;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class List_cameras_adapter extends BaseAdapter
{
    private static ArrayList<List_User_Cameras> searchArrayList;

    private LayoutInflater mInflater;

    public List_cameras_adapter(Context context, ArrayList<List_User_Cameras> results)
    {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount()
    {
        return searchArrayList.size();
    }

    public Object getItem(int position)
    {
        return searchArrayList.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.custom_list_view, null);
            holder = new ViewHolder();
            holder.camera = (TextView) convertView.findViewById(R.id.cam_username);
            holder.ip_address = (TextView) convertView.findViewById(R.id.ip_address);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.camera.setText(searchArrayList.get(position).getName());
        holder.ip_address.setText(searchArrayList.get(position).getCityState());
        return convertView;
    }

    static class ViewHolder
    {
        TextView camera;
        TextView ip_address;
    }
}
