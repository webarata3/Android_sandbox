package link.webarata3.dro.fragmenttest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class ResultActivity extends AppCompatActivity {
    public static final String PARAM_X = "paramX";
    public static final String PARAM_Y = "paramY";

    /**
     * このActivity呼び出し用のIntentを作成する
     *
     * @param context コンテキスト
     * @param paramX  足される数
     * @param paramY  足す数
     * @return 作成したIntent
     */
    public static Intent createIntent(Context context, int paramX, int paramY) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(PARAM_X, paramX);
        intent.putExtra(PARAM_Y, paramY);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 戻るボタン
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        Intent intent = getIntent();
        int x = intent.getIntExtra(PARAM_X, 0);
        int y = intent.getIntExtra(PARAM_Y, 0);
        ResultActivityFragment resultActivityFragment =
            (ResultActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentResult);
        resultActivityFragment.load(x, y);
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
