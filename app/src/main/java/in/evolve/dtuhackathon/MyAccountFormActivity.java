package in.evolve.dtuhackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MyAccountFormActivity extends AppCompatActivity {

    private EditText adhaarNumberEditText;
    private EditText confirmPasswordEditText;
    private EditText passwordEditText;
    private SharedPrefUtil sharedPrefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_form);
        adhaarNumberEditText= (EditText) findViewById(R.id.my_account_form_adhaar_id);
        passwordEditText= (EditText) findViewById(R.id.my_account_form_password);
        confirmPasswordEditText= (EditText) findViewById(R.id.my_account_form_confirm_password);

        sharedPrefUtil=new SharedPrefUtil(MyAccountFormActivity.this);
        sharedPrefUtil.setAdhaarID(adhaarNumberEditText.getText().toString());

        /*if(passwordEditText.getText()!=confirmPasswordEditText.getText() && passwordEditText.getText()!=null &&
                                       confirmPasswordEditText.getText()!=null)
        {
            UtilFunction.toastL(MyAccountFormActivity.this,"Please check Your Password and Try Again");
        }*/
    }
}
