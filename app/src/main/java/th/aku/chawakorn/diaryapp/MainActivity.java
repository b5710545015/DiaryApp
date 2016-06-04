package th.aku.chawakorn.diaryapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    protected Page page;
    private Intent intent;
    private ListView lv;
    private ArrayList<Page> pages;
    private ArrayAdapter<Page> arrayAdapter;
    private SharedPreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPrefs = getPreferences(MODE_PRIVATE);
        init();
    }

    private boolean loadData(){
        Gson gson = new Gson();
        pages = new ArrayList<Page>();
        int size = mPrefs.getInt("size",0);
        for(int i = 0;i<size;i++) {
            String json = mPrefs.getString("MyObject"+i, null);
            pages.add(gson.fromJson(json, Page.class));
            Log.i("HelloListView", "loadData() returned: " + (pages != null));
        }
        return !pages.isEmpty();
    }

    public void init(){
        lv = (ListView) findViewById(R.id.listView);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //page = new Page(00,00,00,"test1","pls work...");
                intent = new Intent(MainActivity.this, NewPageActivity.class);
                //intent.putExtra("page",page);
                startActivityForResult(intent,1);

            }
        });
        createListView();
    }

    public void createListView(){

        if(!loadData()){
            pages = new ArrayList<Page>();
            /*
            for(int i = 0;i<10;i++) {
                pages.add(new Page (i,i,i,"title"+i,"blah blah blah"));
            }*/
        }

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        arrayAdapter = new ArrayAdapter<Page>(
                this,
                android.R.layout.simple_list_item_1,
                pages );

        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> arg0,View arg1, int position, long arg3)
            {

                //Log.i("HelloListView", pages.get(position).toString());
                intent = new Intent(MainActivity.this, watchActivity.class);
                page = pages.get(position);
                intent.putExtra("page",page);
                startActivityForResult(intent,2);
            }
        });

    }

    public void sort(){
        Collections.sort(pages, new Comparator<Page>() {
            @Override public int compare(final Page o1, final Page o2) {
                return o1.compareTo(o2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1&& resultCode == 1) {
           page = (Page) data.getExtras().getSerializable("pageReturn");
            pages.add(page);
            sort();
            arrayAdapter.notifyDataSetChanged();

        }

        if(requestCode == 2 ) {
            //page = (Page) data.getExtras().getSerializable("pageDel");
            //Log.i("HelloListView", "Del:"+page.toString());
            pages.remove(page);
            sort();
            arrayAdapter.notifyDataSetChanged();

        }
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json;
        prefsEditor.putInt("size",pages.size());
        prefsEditor.commit();
        for(int i = 0;i<pages.size();i++) {
            json = gson.toJson(pages.get(i));
            prefsEditor.putString("MyObject"+i, json);
            prefsEditor.commit();
        }
    }
}
