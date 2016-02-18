package com.example.usu27.pr3_layouting_llistes_adaptadors;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    Spinner spiner;
    double total;
    ListView listview;
    ArrayList<Producto> ProductsSpinner = new ArrayList<>();
    ArrayList<Producto> ProductosList = new ArrayList<>();
    ArrayAdapter<Producto> listAdapter;
    String[] items ={"1:Cireres:Cireres vermelles:1.56:img000",
            "2:Plàtan:Plàtan boníssim:0.15:img001",
            "3:Pastís:Pastís d'aniversari:12.99:img002",
            "4:Barra de pa:De la nostra fleca:0.42:img003",
            "5:Pa dolç:Dolç i bo:0.65:img004",
            "6:Croissant:Fet al moment:0.85:img005",
            "7:Bistec de vedella 1/2Kg:Alta qualitat:14.50:img006",
            "8:Panet de llet:Ideal per als nens:0.15:img007",
            "9:Dotzena d'ous:Gallines de proximitat:1.89:img008",
            "10:Farina:Farina. Fina, fina:0.99:img009",
            "11:Raïm:Boníssim i sa:1.24:img010.jpg" ,
            "12:Pernil cuït:Suau, baix en sal:9.99:img011" ,
            "13:Piruleta:Sabor maduixa:0.10:img012" ,
            "14:Poma:Poma deliciosa:0.23:img013" ,
            "15:Pera:Pera ben bona:0.24:img014" ,
            "16:Pinya:Ideal amanides fresques:1.50:img015" ,
            "17:Maduixes:Vermelles i boníssimes:1.25:img016" ,
            "18:Sucre:Per endolcir-te la vida:0.99:img017" ,
            "19:Síndria:Estupenda i sana:3.65:img018" ,
            "20:Llet:Semidesnatada:0.75:img019" ,
            "21:Cafè:Mólt d'alta qualitat:0.76:img020" ,
            "22:Àpat preparat:Si no pots cuinar:5.85:img021" ,
            "23:Pizza:De 4 formatges:3.55:img022" ,
            "24:Salmó:De la canya al sarronet:4.56:img023" ,
            "25:Pésols:Ideals com a acompanyament:0.55:img024" ,
            "26:Bolets:Dónen gust a tots els dinars:0.42:img025" ,
            "27:Ceba:Dolça que no fa plorar:0.35:img026" ,
            "28:Formatge:Semisec, boníssim:2.30:img027" ,
            "29:Pastanaga:Ideal amanides:0.21:img028"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.Add);

           for (int i=0;i<items.length;i++){
            String[] item = items[i].split(":");
            Producto producto = new Producto();
            producto.cantidad =1;
            producto.name=item[1];
            producto.info=item[2];
            producto.price=Double.parseDouble(item[3]);
            producto.imagen =item[4];
            ProductsSpinner.add(producto);

        }

        spiner = (Spinner) findViewById(R.id.spinner);
        listview = (ListView) findViewById(R.id.listView);
        button.setOnClickListener(this);
        spiner.setAdapter(new ContactAdapterSpinner(this, ProductsSpinner));



        }

    @Override
    public void onClick(View v){

       Producto productu;
        productu = ProductsSpinner.get(spiner.getSelectedItemPosition());
        if(ProductosList.contains(productu)){
            productu.cantidad =productu.cantidad +1;

        }else{
            ProductosList.add(productu);
        }
        total = productu.price+total;


        TextView totalPrecio = (TextView) findViewById(R.id.total);

        double totalFin = total * 100;

        totalFin = Math.round(totalFin);

        totalFin = totalFin/100;

        totalPrecio.setText("Total: " + Double.toString(totalFin) + " €");

    listview.setAdapter(new ContactAdapterList(this, ProductosList));



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    private class Producto {
        public Integer cantidad;
        public String name;
        public String info;
        public double price;
        public String imagen;

    }

    private class ViewInfoSpinner {
        TextView nombreSpinner, precioSpinner;
        Producto prod;

        public ViewInfoSpinner(View view) {
            nombreSpinner = (TextView) view.findViewById(R.id.nombreSpinner);
            precioSpinner = (TextView) view.findViewById(R.id.precioSpinner);

        }

        public void setProd(Producto prod) {
            this.prod = prod;
            nombreSpinner.setText(prod.name);
            precioSpinner.setText(Double.toString(prod.price));

                  }
    }


    private class ViewInfoList {
        TextView nombre, precio;
        ImageView img;
        Producto contact;

        public ViewInfoList(View view) {
            nombre = (TextView) view.findViewById(R.id.textView1);
            precio = (TextView) view.findViewById(R.id.textView2);
            img = (ImageView) view.findViewById(R.id.imageView);

        }

        public void setContact1(Producto contact) {
            this.contact = contact;
            nombre.setText(contact.name);
            precio.setText(contact.cantidad +" x "+Double.toString(contact.price));
            img.setImageResource(getResources().getIdentifier(contact.imagen, "drawable", MainActivity.this.getPackageName()));

        }
    }



    private class ContactAdapterSpinner extends BaseAdapter {
        private Context context;
        private ArrayList<Producto> contacts;

        public ContactAdapterSpinner(Context context, ArrayList<Producto> contacts) {
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
                view = inflater.inflate(R.layout.item_list_spinner, parent, false);
                ViewInfoSpinner viewInfo = new ViewInfoSpinner(view);
                view.setTag(viewInfo);
            }
            ViewInfoSpinner viewInfo = (ViewInfoSpinner) view.getTag();
            Producto contact = contacts.get(position);
            viewInfo.setProd(contact);

            return view;
        }
    }

    private class ContactAdapterList extends BaseAdapter implements View.OnClickListener{
        private Context context;
        private ArrayList<Producto> contacts;
        Button button2;

        public ContactAdapterList(Context context, ArrayList<Producto> contacts) {


            this.context = context;
            this.contacts = contacts;
        }

        @Override
        public int getCount() { return contacts.size(); }
        @Override public Object getItem(int position) { return contacts.get(position); }
        @Override public long getItemId(int position) { return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_list, parent, false);
                ViewInfoList viewInfo = new ViewInfoList(view);
                view.setTag(viewInfo);
                button2 = (Button) view.findViewById(R.id.button2);
            }
            ViewInfoList viewInfo = (ViewInfoList) view.getTag();
            Producto contact = contacts.get(position);

            viewInfo.setContact1(contact);
            button2.setTag(contact);
            button2.setOnClickListener(this);
            return view;
        }

        @Override
        public void onClick(View v){
           RestarProducto((Producto) v.getTag());
        }


    }
    public void RestarProducto(Producto contacts){

        if(contacts.cantidad >1){
            contacts.cantidad =contacts.cantidad -1;

        }else{
            ProductosList.remove(contacts);
        }

        total = total-contacts.price;

        TextView total1 = (TextView) findViewById(R.id.total);

        double totalFin = total * 100;

        totalFin = Math.round(totalFin);

        totalFin = totalFin/100;


        total1.setText("Total: "+Double.toString(totalFin)+" €");
        listview.setAdapter(new ContactAdapterList(this, ProductosList));


    }



}
