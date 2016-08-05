package link.webarata3.dro.fragmenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class ResultActivity extends AppCompatActivity {
    public static final String PARAM_X = "paramX";
    public static final String PARAM_Y = "paramY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 戻るボタン
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        AppCompatTextView textView = (AppCompatTextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        int x = intent.getIntExtra(PARAM_X, 0);
        int y = intent.getIntExtra(PARAM_Y, 0);
        textView.setText(String.valueOf(x + y));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        boolean result = true;

        switch (id) {
            case android.R.id.home:
                finish();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }

        return result;
    }
}
