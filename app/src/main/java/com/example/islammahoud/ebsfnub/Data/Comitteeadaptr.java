package com.example.islammahoud.ebsfnub.Data;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.islammahoud.ebsfnub.Firebaseconnemction.Savingdata;
import com.example.islammahoud.ebsfnub.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by islam mahoud on 10/23/2017.
 */

public class Comitteeadaptr extends ArrayAdapter<Comitti> {
    Context context;
    ArrayList<Comitti> com=new ArrayList<>();
    public Comitteeadaptr( Context context, int resource, List<Comitti> com,int i ) {
        super(context, resource,com );

     com = Savingdata.getInstance().getcombynum(i);
        this.context = context;
        if (com == null) {
            com = new ArrayList<>();
            Comitti s = new Comitti();
            s.setName("Waiting for network connection");
            s.setDesc("Sorry");
            com.add(s);

        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.comittee_list, parent, false);
        }

        TextView sname = (TextView) convertView.findViewById(R.id.com_name);
        TextView sdesc = (TextView) convertView.findViewById(R.id.com_desc);
        Comitti com = getItem(position);
        sname.setText(com.getName());
        sdesc.setText(com.getDesc());
        return convertView;
    }

}
