package com.example.islammahoud.ebsfnub.Data;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.islammahoud.ebsfnub.Firebaseconnemction.Savingdata;
import com.example.islammahoud.ebsfnub.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by islam mahoud on 10/26/2017.
 */

public class Memberadapter extends ArrayAdapter<Member> {
    private Context context;
    public Memberadapter(@NonNull Context context, @LayoutRes int resource, List<Member> members) {
        super(context, resource,members);
        members = Savingdata.getInstance().getmemberdata();
        this.context = context;
        if (members == null) {
            members = new ArrayList<>();
            Member s = new Member();
            s.setName("Waiting for network connection");
            s.setJob("Sorry");
            s.setImage("ddd");
            members.add(s);

        }
    }
    private int mLastPosition;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.memberitem, parent, false);
        }

        CircleImageView photoImageView = (CircleImageView) convertView.findViewById(R.id.mamberimage);
        TextView sname = (TextView) convertView.findViewById(R.id.name);
        TextView sdesc = (TextView) convertView.findViewById(R.id.position);

        Member news = getItem(position);
        sname.setText(news.getName());
        sdesc.setText(news.getJob());
        //  Glide.with(photoImageView.getContext())
        //  .load(news.getImage())
        //   .into(photoImageView);
        Picasso.with(context)
                .load(news.getImage())
                .error(R.drawable.ic_tag_faces_black_48dp)
                .placeholder(R.drawable.ic_tag_faces_black_48dp)
                .into(photoImageView);

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(convertView, "translationY",  300 , 0);
        ObjectAnimator animatorRotation = ObjectAnimator.ofFloat(convertView, "rotationX",   -90, 0f);
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(convertView, "scaleX", 0.5f, 1f);
        animatorSet.playTogether(animatorTranslateY, animatorRotation, animatorScaleX);
        animatorSet.setInterpolator(new DecelerateInterpolator(1.1f));
        animatorSet.setDuration(1000);
        animatorSet.start();

        // Keep track of the last position we loaded
        mLastPosition = position;


        return convertView;
    }
}
