package in.evolve.dtuhackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyAccountFormActivity extends AppCompatActivity {

    private EditText adhaarNumberEditText;
    private EditText confirmPasswordEditText;
    private EditText passwordEditText;
    private Button button;
    private SharedPrefUtil sharedPrefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_form);
        adhaarNumberEditText= (EditText) findViewById(R.id.my_account_form_adhaar_id);
        passwordEditText= (EditText) findViewById(R.id.my_account_form_password);
        confirmPasswordEditText= (EditText) findViewById(R.id.my_account_form_confirm_password);
        button = (Button) findViewById(R.id.my_account_form_done_button) ;

        sharedPrefUtil=new SharedPrefUtil(MyAccountFormActivity.this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefUtil.setAdhaarID(adhaarNumberEditText.getText().toString());
                MyAccountFormActivity.this.finish();
            }
        });


        /*if(passwordEditText.getText()!=confirmPasswordEditText.getText() && passwordEditText.getText()!=null &&
                                       confirmPasswordEditText.getText()!=null)
        {
            UtilFunction.toastL(MyAccountFormActivity.this,"Please check Your Password and Try Again");
        }*/
    }
}
