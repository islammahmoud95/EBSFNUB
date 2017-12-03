package com.example.islammahoud.ebsfnub.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.islammahoud.ebsfnub.Data.News;
import com.example.islammahoud.ebsfnub.Data.Newsadapter;
import com.example.islammahoud.ebsfnub.Firebaseconnemction.Savingdata;
import com.example.islammahoud.ebsfnub.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

private Newsadapter mMessageAdapter;
    List<News> news=new ArrayList<>();
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_news, container, false);
        listView= view.findViewById(R.id.news_list);
        swipeRefreshLayout= view.findViewById(R.id.swiperefresh);

        // news=newfir.getnewsdata();
        Context context=view.getContext();
        Savingdata getdata=new Savingdata(context);

       news=getdata.getnewsdata();
         mMessageAdapter = new Newsadapter(context, R.layout.memberitem,news);
        listView.setAdapter(mMessageAdapter);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        Context context = getContext();
                        mMessageAdapter.clear();
                        listView.setAdapter(mMessageAdapter);
                        Savingdata getdata = new Savingdata(context);

                        news = getdata.getnewsdata();
                        mMessageAdapter = new Newsadapter(context, R.layout.memberitem, news);
                        listView.setAdapter(mMessageAdapter);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
        return view;
    }


}
