package th.aku.chawakorn.diaryapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    protected Page page;
    private Intent intent;
    private TextView textView;
    private ListView lv;
    private ArrayList<Page> pages;
    private ArrayAdapter<Page> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        textView = (TextView) findViewById(R.id.textView);
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

        pages = new ArrayList<Page>();
        for(int i = 0;i<10;i++) {
            pages.add(new Page (i,i,i,"title"+i,"blah blah blah"));
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

                Log.i("HelloListView", pages.get(position).toString());
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1&& resultCode == 1) {
           page = (Page) data.getExtras().getSerializable("pageReturn");
            //textView.setText(new StringBuilder().append("return success"));
            textView.setText(new StringBuilder().append(page.toString()));
            pages.add(page);
            Collections.sort(pages, new Comparator<Page>() {
                @Override public int compare(final Page o1, final Page o2) {
                    return o1.compareTo(o2);
                }
            });
            arrayAdapter.notifyDataSetChanged();

        }
    }
}
