package alias.basil.basilalias1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.toptoche.multiselectwidget.MultiSelectView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class OrderActivity extends AppCompatActivity {


    Button review_btn;
    TextView pizza_type_tv,pizza_size_tv,dough_type_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        final MultiSelectView multiSelectView = (MultiSelectView) findViewById(R.id.m1);

        ArrayList<String> stringList = getIntent().getStringArrayListExtra("Toppings");

        multiSelectView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.toppings)), stringList);

        review_btn = (Button) findViewById(R.id.review_confirm_btn);

        pizza_size_tv = (TextView) findViewById(R.id.pizza_size);
        pizza_type_tv = (TextView) findViewById(R.id.pizza_type);
        dough_type_tv = (TextView) findViewById(R.id.dough_size);

       String pizza_type = getIntent().getStringExtra("pizza_name");
        switch (pizza_type) {
            case "Neapolitan Pizza":
                pizza_type= "Neapolitan Pizza";break;
            case "Silican Pizza":
                pizza_type = "Silican Pizza";break;
            case "Tomato Pie Pizza":
                pizza_type = "Tomato Pie";break;
            case "Greek Pizza":
                pizza_type = "Greek Pizza";break;
            case "Basilio Pizza":
                pizza_type= "Basilio Pizza";break;
            default:
                pizza_type="Greek Pizza";break;

        }
        pizza_type_tv.setText(pizza_type);

        String pizza_size = getIntent().getStringExtra("Pizza_size");

        String dough_type = getIntent().getStringExtra("Dough_size");
        int pizza_size_int = 0,dough_size_int =1;
        try {
            pizza_size_int = Integer.parseInt(pizza_size);
            dough_size_int = Integer.parseInt(dough_type);
        } catch(NumberFormatException nfe) {

        }
        switch (pizza_size_int) {
            case 0:
                pizza_size = "Small";break;
            case 1:
                pizza_size = "Medium";break;
            case 2:
                pizza_size = "Large";break;
            case 3:
                pizza_size = "Extra Large";break;

        }
        switch (dough_size_int) {
            case 0:
                dough_type = "Thin";break;
            case 1:
                dough_type = "Thick";break;


        }
        pizza_size_tv.setText(pizza_size);

        dough_type_tv.setText(dough_type);

        review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OrderActivity.this,OrderConfirmActivity.class);
                startActivity(i);
            }
        });
    }
}
