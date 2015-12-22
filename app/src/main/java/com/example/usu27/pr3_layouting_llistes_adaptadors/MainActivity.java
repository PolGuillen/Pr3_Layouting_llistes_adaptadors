package com.example.usu27.pr3_layouting_llistes_adaptadors;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import android.view.View.OnClickListener;


import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements OnClickListener {

    String[] items ={"1:Cireres:Cireres vermelles:1.56:img000.jpg",
            "2:Plàtan:Plàtan boníssim:0.15:img001.jpg",
            "3:Pastís:Pastís d'aniversari:12.99:img002.jpg",
            "4:Barra de pa:De la nostra fleca:0.42:img003.jpg",
            "5:Pa dolç:Dolç i bo:0.65:img004.jpg",
            "6:Croissant:Fet al moment:0.85:img005.jpg",
            "7:Bistec de vedella 1/2Kg:Alta qualitat:14.50:img006.jpg",
            "8:Panet de llet:Ideal per als nens:0.15:img007.jpg",
            "9:Dotzena d'ous:Gallines de proximitat:1.89:img008.jpg",
            "10:Farina:Farina. Fina, fina:0.99:img009.jpg",
            "11:Raïm:Boníssim i sa:1.24:img010.jpg" ,
            "12:Pernil cuït:Suau, baix en sal:9.99:img011.jpg" ,
            "13:Piruleta:Sabor maduixa:0.10:img012.jpg" ,
            "14:Poma:Poma deliciosa:0.23:img013.jpg" ,
            "15:Pera:Pera ben bona:0.24:img014.jpg" ,
            "16:Pinya:Ideal amanides fresques:1.50:img015.jpg" ,
            "17:Maduixes:Vermelles i boníssimes:1.25:img016.jpg" ,
            "18:Sucre:Per endolcir-te la vida:0.99:img017.jpg" ,
            "19:Síndria:Estupenda i sana:3.65:img018.jpg" ,
            "20:Llet:Semidesnatada:0.75:img019.jpg" ,
            "21:Cafè:Mólt d'alta qualitat:0.76:img020.jpg" ,
            "22:Àpat preparat:Si no pots cuinar:5.85:img021.jpg" ,
            "23:Pizza:De 4 formatges:3.55:img022.jpg" ,
            "24:Salmó:De la canya al sarronet:4.56:img023.jpg" ,
            "25:Pésols:Ideals com a acompanyament:0.55:img024.jpg" ,
            "26:Bolets:Dónen gust a tots els dinars:0.42:img025.jpg" ,
            "27:Ceba:Dolça que no fa plorar:0.35:img026.jpg" ,
            "28:Formatge:Semisec, boníssim:2.30:img027.jpg" ,
            "29:Pastanaga:Ideal amanides:0.21:img028.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Producto> stock = new ArrayList<>();
        for (int i=0;i<items.length;i++){
            String[] item = items[i].split(":");
            Producto productos = new Producto();
            productos.num = item[0];
            productos.name = item[1];
            productos.des = item[2];
            productos.precio = Double.parseDouble(item[3]);
            productos.img = Uri.parse(item[4]);
            stock.add(productos);
        }
        Spinner spiner = (Spinner) findViewById(R.id.spinner_prod);
        spiner.setAdapter(new ContactAdapter(this, stock));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    private class Producto {
        public String num;
        public String name;
        public String des;
        public double precio;
        public Uri img;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private class ViewInfo {
        TextView textView1, textView2;
        Producto contact;

        public ViewInfo(View view) {
            textView1 = (TextView) view.findViewById(R.id.textView1);
            textView2 = (TextView) view.findViewById(R.id.textView2);
        }

        public void setContact(Producto contact) {
            this.contact = contact;
            textView1.setText(contact.name);
            textView2.setText(Double.toString(contact.precio));
        }
    }
private class ContactAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Producto> contacts;

    public ContactAdapter(Context context, ArrayList<Producto> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override public int getCount() { return contacts.size(); }
    @Override public Object getItem(int position) { return contacts.get(position); }
    @Override public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list, parent, false);
            ViewInfo viewInfo = new ViewInfo(view);
            view.setTag(viewInfo);
        }
        ViewInfo viewInfo = (ViewInfo) view.getTag();
        Producto contact = contacts.get(position);
        viewInfo.setContact(contact);
        //view.setOnClickListener(MainActivity.this);
        return view;
    }
}
}
