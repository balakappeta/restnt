package com.example.kavitha.tab_example;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kavitha on 12/20/2017.
 */

public class MenuView extends LinearLayout
{
    private Button add;
    private Button del;
    TextView tvName = (TextView)findViewById(R.id.tv_name);
    TextView tvCost = (TextView)findViewById(R.id.tv_cost);
    public MenuView(Context context)
    {
        super(context);
        initialiseViews(context);
    }

    public MenuView(Context context, AttributeSet attrs)
    {
        super(context,attrs);
        initialiseViews(context);
    }

    public MenuView(Context context,AttributeSet attrs,int defStyle)
    {
        super(context,attrs,defStyle);
        initialiseViews(context);
    }

    private void initialiseViews(Context context)
    {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu_view,this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        add=(Button)this.findViewById(R.id.add_button);
        add.setBackgroundResource(android.R.drawable.ic_input_add);
        //add.setBackgroundColor(android.R.color.holo_red_dark);
        del=(Button)this.findViewById(R.id.del_button);
        del.setBackgroundResource(android.R.drawable.ic_input_delete);

        final HandleXML handleXMLObj = new HandleXML(this.getContext());

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"added",Toast.LENGTH_LONG).show();
                handleXMLObj.cartObj.name = (String) tvName.getText();
                handleXMLObj.cartObj.cost= Double.parseDouble((String)tvCost.getText());
                handleXMLObj.cartItems.add(handleXMLObj.cartObj);
            }
        });

        del.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"dropped",Toast.LENGTH_LONG).show();
            }
        });

    }
}
