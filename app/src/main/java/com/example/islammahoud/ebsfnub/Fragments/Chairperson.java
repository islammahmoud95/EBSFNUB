package com.example.islammahoud.ebsfnub.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.islammahoud.ebsfnub.Data.Member;
import com.example.islammahoud.ebsfnub.Data.Memberadapter;
import com.example.islammahoud.ebsfnub.Data.News;
import com.example.islammahoud.ebsfnub.Data.Newsadapter;
import com.example.islammahoud.ebsfnub.Firebaseconnemction.Savingdata;
import com.example.islammahoud.ebsfnub.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Chairperson.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Chairperson#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Chairperson extends Fragment {

    private Memberadapter mMessageAdapter;
    List<Member> members=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chairperson, container, false);
        ListView listView= view.findViewById(R.id.member_list);

        // news=newfir.getnewsdata();
        Context context=view.getContext();
        Savingdata getdata=new Savingdata(context);

        members=getdata.getmemberdata();
        mMessageAdapter = new Memberadapter(context, R.layout.memberitem,members);
        listView.setAdapter(mMessageAdapter);
        return view;
    }


}
