package com.example.calvinkwan.buddy_cam;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CameraListAdapter extends BaseAdapter {
    private static ArrayList<IpCamera> searchArrayList;
    private LayoutInflater mInflater;

    public CameraListAdapter(Context context, ArrayList<IpCamera> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return searchArrayList.size();
    }

    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom_list_view, null);
            holder = new ViewHolder();
            holder.camera = (TextView) convertView.findViewById(R.id.cam_username);
            holder.ipAddress = (TextView) convertView.findViewById(R.id.ip_address);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.camera.setText(searchArrayList.get(position).getName());
        holder.ipAddress.setText(searchArrayList.get(position).getCityState());
        return convertView;
    }

    static class ViewHolder {
        TextView camera;
        TextView ipAddress;
    }
}
