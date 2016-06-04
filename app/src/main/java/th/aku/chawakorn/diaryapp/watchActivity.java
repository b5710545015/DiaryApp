package th.aku.chawakorn.diaryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class watchActivity extends AppCompatActivity {

    private TextView date,title,detail;
    private Page page;
    private Button del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch);
        init();
    }

    public void init(){
        date = (TextView)findViewById(R.id.dateText);
        title = (TextView) findViewById(R.id.titleText);
        detail = (TextView) findViewById(R.id.detailText);
        page = (Page) getIntent().getSerializableExtra("page");
        setUp();
        setUpButton();
    }

    private void setUpButton(){
        del = (Button)findViewById(R.id.deleteButton);
        del.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("pageDel", page);
                setResult(1, resultIntent);
                finish();

            }
        });
    }

    private void setUp() {
        date.setText(date.getText().toString() + page.getDate());
        title.setText(title.getText().toString() + "\n" + page.getTitle());
        detail.setText(detail.getText().toString() + "\n" + page.getDetail());
    }

}
