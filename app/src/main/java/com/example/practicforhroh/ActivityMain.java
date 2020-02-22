package com.example.practicforhroh;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class ActivityMain extends Activity {
   private TextView dayTextView;
   private TextView degreesTextView;
   private TextView timeTextView;
   private TextView locationTextView;
   private Button   buttonClick;

   public Elements title;
   public ArrayList<String> titleList=new ArrayList<String>();
   private ArrayAdapter<String> adapter;
   private ListView lv;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //buttonClick=(Button) findViewById(R.id.btn_click_me);
        //dayTextView=(TextView)findViewById(R.id.txt_day);

        lv=(ListView) findViewById(R.id.listView1);
        new NewThread().execute();
        adapter=new  ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, titleList );
    }

      public class NewThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInbackground(String... arg){
            Document doc;
            try{
                doc= Jsoup.connect("http://freehabr.ru/").get();
                title=doc.select(".title");
                titleList.clear();
                for(Element titles: title){
                    titleList.add(titles.text());
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result){
            lv.setAdapter(adapter);
        }
    }


    /*
    public void ClickMe(View v){
    }

     */

}
