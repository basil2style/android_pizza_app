package alias.basil.basilalias1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.toptoche.multiselectwidget.MultiSelectFragment;
import com.toptoche.multiselectwidget.MultiSelectView;

import java.util.ArrayList;
import java.util.List;

import co.ceryle.radiorealbutton.library.RadioRealButton;
import co.ceryle.radiorealbutton.library.RadioRealButtonGroup;

public class DetailsActivity extends Activity {

    //final RadioRealButton button1 = (RadioRealButton) findViewById(R.id.button1);
    //final RadioRealButton button2 = (RadioRealButton) findViewById(R.id.button2);

   // RadioRealButtonGroup group = (RadioRealButtonGroup) findViewById(R.id.group);

    private String arrayValue[] = {"Chicken","Pepporoni","Mushrooms","Bacons","Pineapple","Tomatoes","Onions"};
    static  String mystring;
    static int pizza_size = 0;
    static int dough_size = 0;
    Button checkout_btn;
    ImageView imgv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        checkout_btn = (Button) findViewById(R.id.checkout);
        mystring = getIntent().getStringExtra("position");
        imgv = (ImageView) findViewById(R.id.imageView);

       switch (mystring) {
            case "Neapolitan Pizza":
                imgv.setImageResource(R.drawable.neapolian);
            case "Greek Pizza":
                imgv.setImageResource(R.drawable.greek);
            case "Silican Pizza":
                imgv.setImageResource(R.drawable.sicilian);
            case "Tomato Pie Pizza":
                imgv.setImageResource(R.drawable.tomato_pie);
            case "Basilio Pizza":
                imgv.setImageResource(R.drawable.basilio);
            default:
                imgv.setImageResource(R.drawable.greek);

        }

        final RadioRealButtonGroup rrbg1 = (RadioRealButtonGroup) findViewById(R.id.radioRealButtonGroup_1);
        final RadioRealButtonGroup rrbg2 = (RadioRealButtonGroup) findViewById(R.id.radioRealButtonGroup_2);



        rrbg1.setOnClickedButtonPosition(new RadioRealButtonGroup.OnClickedButtonPosition() {
            @Override
            public void onClickedButtonPosition(int position) {
                Toast.makeText(DetailsActivity.this, "Clicked position: " + position, Toast.LENGTH_SHORT).show();
                pizza_size = position;
            }
        });

         rrbg2.setOnClickedButtonPosition(new RadioRealButtonGroup.OnClickedButtonPosition() {
             @Override
             public void onClickedButtonPosition(int position) {
                 dough_size = position;
             }
         });


        final MultiSelectView multiSelectView = (MultiSelectView) findViewById(R.id.m);

        final List<String> stringList = new ArrayList<>();
        for (int i=0;i<arrayValue.length;i++){
            stringList.add(arrayValue[i]);
        }
        //stringList.add(getResources().getStringArray(android.R.array.imProtocols)[1]);

        //multiSelectView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,
          //      getResources().getStringArray(android.R.array.imProtocols)), stringList);

        multiSelectView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.toppings)), stringList);


        multiSelectView.setDelimiter(";");

        multiSelectView.setPositiveButton("OK", new MultiSelectFragment.OnPositiveButtonClicked() {
            @Override
            public void onPositiveButtonClicked(Dialog dialog) {
                multiSelectView.getSelectedItems();
            }
        });

        multiSelectView.setNegativeButton("CANCEL", new MultiSelectFragment.OnNegativeButtonClicked() {
            @Override
            public void onNegativeButtonClicked(Dialog dialog) {

            }
        });

        multiSelectView.setOnNoItemSelectedListener(new MultiSelectFragment.OnNoItemSelected() {
            @Override
            public void onNoItemSelected(Dialog dialog) {

            }
        });

        checkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DetailsActivity.this,OrderActivity.class);
                intent.putExtra("pizza_name",getIntent().getStringExtra("position"));
                intent.putExtra("Pizza_size",String.valueOf(pizza_size));
                intent.putExtra("Dough_size",String.valueOf(dough_size));
                intent.putStringArrayListExtra("Toppings", (ArrayList<String>) stringList);

                startActivity(intent);
            }
        });
    }

}
