package com.example.kavitha.tab_example;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.kavitha.tab_example.R.layout.tab1;

/**
 * Created by Kavitha on 12/20/2017.
 */

public class HandleXML
{
    Tab1 temp=new Tab1();
    private Context context;
    ArrayList<Cart> cartItems = new ArrayList<Cart>();
    Cart cartObj=null;

    HandleXML(Context context) {
        this.context=context;
    }

    void fetchXMLData(final String menuItem) throws XmlPullParserException, IOException {
        Log.i("HandleXML", "fetchXMLData: ");

        XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
        InputStream inputStream = this.context.getAssets().open("menu_data.xml");

        xmlPullParser.setInput(inputStream, null);
        xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);

        parseData(xmlPullParser, menuItem);
        inputStream.close();
    }

    void parseData(XmlPullParser xmlPullParser,String menuItem) throws IOException {
        int event;
        MenuView menuViewVegObj=null,menuViewNonvegObj=null;

        try {
            event = xmlPullParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT)
            {
                String type = "";
                String name=xmlPullParser.getName();
                Log.i("Parse Data", "EventName: " + name);

                switch (event)
                {
                    case XmlPullParser.START_TAG:
                        name=xmlPullParser.getName();
                        if(name.equalsIgnoreCase(menuItem))
                        {
                            menuViewVegObj=new MenuView(this.context);
                            menuViewNonvegObj=new MenuView(this.context);
                        }
                        else if(menuViewVegObj!=null||menuViewNonvegObj!=null)
                        {
                            if(name.equals("item"))
                            {
                                cartObj=new Cart();
                                type = xmlPullParser.getAttributeValue(null,"type");
                            }
                            if(type=="veg")
                            {
                                if(name.equals("name"))
                                {
                                    //cartObj.name=xmlPullParser.nextText();
                                    menuViewVegObj.tvName.setText(xmlPullParser.nextText());
                                }
                                if(name.equals("price"))
                                {
                                    //cartObj.cost=Double.parseDouble(xmlPullParser.nextText());
                                    menuViewVegObj.tvCost.setText(xmlPullParser.nextText());
                                }
                            }

                            else
                            {
                                if(name.equals("name"))
                                {
                                    //cartObj.name=xmlPullParser.nextText();
                                    menuViewNonvegObj.tvName.setText(xmlPullParser.nextText());
                                }
                                if(name.equals("price"))
                                {
                                    //cartObj.cost=Double.parseDouble(xmlPullParser.nextText());
                                    menuViewNonvegObj.tvCost.setText(xmlPullParser.nextText());
                                }
                            }

                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if(name.equals("item"))
                        {
                            cartItems.add(cartObj);
                        }
                        break;
                    default:break;
                }
                event=xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        //subList.toArray(array);
       // ArrayAdapter<String> subListAdaptor = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,array);
        //mainObj.rl_lv.setAdapter(subListAdaptor);
    }

}
