package th.aku.chawakorn.diaryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    protected Page page;
    private Intent intent;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        textView = (TextView) findViewById(R.id.textView);
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1&& resultCode == 1) {
           page = (Page) data.getExtras().getSerializable("pageReturn");
            //textView.setText(new StringBuilder().append("return success"));
            textView.setText(new StringBuilder().append(page.toString()));
        }
    }
}
