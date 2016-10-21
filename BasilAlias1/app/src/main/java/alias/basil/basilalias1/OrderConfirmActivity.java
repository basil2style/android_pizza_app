package alias.basil.basilalias1;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.devmarvel.creditcardentry.library.CreditCardForm;

import java.util.Random;

public class OrderConfirmActivity extends AppCompatActivity {

    final Context context = this;
    Button btn_checkout;
    TextView text_confirm,text_price;
    EditText edit_name,edit_address,edit_phone;
    CreditCardForm cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        cv = (CreditCardForm) findViewById(R.id.credit_card_form);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_address = (EditText) findViewById(R.id.edit_address);
        edit_phone = (EditText) findViewById(R.id.edit_phone);

        btn_checkout = (Button) findViewById(R.id.order_checkout);
        text_confirm = (TextView) findViewById(R.id.text_confirm);
        text_price = (TextView) findViewById(R.id.price);
        text_price.setText("$10");
        text_confirm.setText(getSaltString());
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if (edit_name.length() == 0 || edit_phone.length() ==0 || edit_address.length() ==0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Warning");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Please enter details")
                        .setCancelable(false)
                        .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }

        else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set title
                alertDialogBuilder.setTitle("Order Success");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Click yes to exit!")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                OrderConfirmActivity.this.finish();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }

            }
        });
    }
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
