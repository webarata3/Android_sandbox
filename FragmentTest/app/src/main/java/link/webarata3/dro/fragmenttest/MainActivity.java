package link.webarata3.dro.fragmenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
    implements MainFragment.OnMainFragmentListener {

    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View resultView = findViewById(R.id.fragmentResult);

        isTwoPane = resultView != null;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickButton(int x, int y) {
        if (isTwoPane) {
            // 2ペインの場合には、計算結果を更新する
            ResultFragment resultActivityFragment =
                (ResultFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragmentResult);
            resultActivityFragment.load(x, y);
        } else {
            Intent intent = ResultActivity.createIntent(this, x, y);
            startActivity(intent);
        }
    }
}
