package com.example.kavitha.tab_example;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Kavitha on 12/19/2017.
 */

public class Tab1 extends Fragment implements AdapterView.OnItemClickListener {
    String url;
    //MenuView mobj=new MenuView(getContext());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.tab1, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        ListView menuList=(ListView)getActivity().findViewById(R.id.menu_entry);
        menuList.setOnItemClickListener(this);
        url="C://Users//Kavitha//Desktop//kavi//xml data//menu_data.xml";
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String menuItem=adapterView.getItemAtPosition(i).toString();
        Log.i("OnClick", "onClick: "+url);
        HandleXML handleXML = new HandleXML(getContext());
        try
        {
            handleXML.fetchXMLData(menuItem);
        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Log.i("OnClick", "onClick: called HandleXML ");
    }
}
