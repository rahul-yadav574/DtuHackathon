package in.evolve.dtuhackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public class MyAccountActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private  SharedPrefUtil sharedPrefUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Account");
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.my_account_menu,menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id)
        {
            case android.R.id.home:
                sharedPrefUtil.clearData();
                MyAccountActivity.this.finish();
        }
    }
}
