package com.example.islammahoud.ebsfnub.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.islammahoud.ebsfnub.Firebaseconnemction.L;
import com.example.islammahoud.ebsfnub.Firebaseconnemction.Savingdata;
import com.example.islammahoud.ebsfnub.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by islam mahoud on 10/16/2017.
 */

public class Newsadapter2 extends RecyclerView.Adapter<Newsadapter2.ViewHolder> implements View.OnClickListener{
        ArrayList<News> news;
        Context context ;
    private int mPreviousPosition = 0;

        public Newsadapter2(Context context) {
        news = Savingdata.getInstance().getnewsdata();
        this.context = context;
        if (news == null) {
            news = new ArrayList<>();
            News s = new News();
            s.setDesc("Waiting for network connection");
            s.setTitile("Sorry");
            s.setImage("ddd");
            news.add(s);

        }

//members.addAll(members);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memberitem, parent, false);
        view.setOnClickListener(this);

        return new ViewHolder(view);
    }
    int first=0;
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(news.get(position).getTitle());
        holder.desc.setText((news.get(position).getDesc()));
        Picasso.with(context).load(news.get(position).getImage())
                .into(holder.sp_image);
        L.m(position+" "+news.get(position).getTitle());


        mPreviousPosition = position;
        first++;
    }


    @Override
    public int getItemCount() {
        return news.size();
    }




    @Override
    public void onClick(View v) {
        int position = v.getId();
        L.m("position " + position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        ImageView sp_image;
        View view;

        public ViewHolder(View view) {
            super(view);
            sp_image = (ImageView) view.findViewById(R.id.mamberimage);
            title = (TextView) view.findViewById(R.id.name);
            desc = (TextView) view.findViewById(R.id.position);
            this.view = view;
        }


    }
}
