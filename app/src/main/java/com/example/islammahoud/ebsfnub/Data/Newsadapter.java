package com.example.islammahoud.ebsfnub.Data;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.islammahoud.ebsfnub.Firebaseconnemction.Firebaseconnection;
import com.example.islammahoud.ebsfnub.Firebaseconnemction.Savingdata;
import com.example.islammahoud.ebsfnub.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by islam mahoud on 10/10/2017.
 */

public class Newsadapter extends ArrayAdapter<News> {
    Context context;

    public Newsadapter(Context context, int resource, List<News> news) {
        super(context, resource, news);

       // news = Savingdata.getInstance().getnewsdata();
        this.context = context;
        if (news == null) {
            news = new ArrayList<>();
            News s = new News();
            s.setTitile("Waiting for network connection");
            s.setDesc("Sorry");
            s.setImage("ddd");
            news.add(s);

        }
    }

    private int mLastPosition;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.newslist, parent, false);
        }

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.mamberimage);
        TextView sname = (TextView) convertView.findViewById(R.id.name);
        TextView sdesc = (TextView) convertView.findViewById(R.id.position);

        News news = getItem(position);
        sname.setText(news.getTitle());
        sdesc.setText(news.getDesc());
        //  Glide.with(photoImageView.getContext())
        //  .load(news.getImage())
        //   .into(photoImageView);
        Picasso.with(context)
                .load(news.getImage())
                .error(R.drawable.ic_sentiment_very_dissatisfied_black_48dp)
                .placeholder(R.drawable.ic_sentiment_very_dissatisfied_black_48dp)
                .into(photoImageView);

        float initialTranslation = (mLastPosition <= position ? 500f : -500f);


        convertView.setTranslationY(initialTranslation);
        convertView.animate()
                .setInterpolator(new DecelerateInterpolator(1.0f))
                .translationY(0f)
                .scaleX(.5f)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setDuration(1000)
                .setListener(null);

        // Keep track of the last position we loaded
        mLastPosition = position;
        return convertView;
    }


}
