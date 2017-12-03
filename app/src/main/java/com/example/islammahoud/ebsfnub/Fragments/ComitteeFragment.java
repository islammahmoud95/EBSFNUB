package com.example.islammahoud.ebsfnub.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.islammahoud.ebsfnub.Activties.MainActivity;
import com.example.islammahoud.ebsfnub.Data.Comitteeadaptr;
import com.example.islammahoud.ebsfnub.Data.Comitti;
import com.example.islammahoud.ebsfnub.Data.Newsadapter;
import com.example.islammahoud.ebsfnub.Firebaseconnemction.L;
import com.example.islammahoud.ebsfnub.Firebaseconnemction.Savingdata;
import com.example.islammahoud.ebsfnub.R;

import java.util.ArrayList;
import java.util.List;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * A simple {@link Fragment} subclass.

 */
public class ComitteeFragment extends Fragment {
    private static final String DAY_NUMBER = "DAY_NUMBER";
    private int dayNumber = 1;

    public static ComitteeFragment newInstance(int dayNumber) {
        ComitteeFragment fragment = new ComitteeFragment();
        Bundle args = new Bundle();
        args.putInt(ComitteeFragment.DAY_NUMBER, dayNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            dayNumber = getArguments().getInt(DAY_NUMBER);
        }
    }
    private Comitteeadaptr mMessageAdapter;
    List<Comitti> com=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_comittee, container, false);
        ListView listView= view.findViewById(R.id.com_list);
        // news=newfir.getnewsdata();
        Context context=view.getContext();
        Savingdata getdata=new Savingdata(context);

        com=getdata.getcombynum(dayNumber);
        mMessageAdapter = new Comitteeadaptr(context, R.layout.comittee_list,com,dayNumber);
        listView.setAdapter(mMessageAdapter);
        return view;
    }
}