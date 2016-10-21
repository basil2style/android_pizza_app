package alias.basil.basilalias1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.k.recyclerview.KRecyclerView;

public class MenuActivity extends AppCompatActivity {

    KRecyclerView kRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        kRecyclerView = (KRecyclerView) findViewById(R.id.krv);
        kRecyclerView.setAdapter(new SimpleAdapter(kRecyclerView));
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }



    class SimpleViewHolder extends RecyclerView.ViewHolder {
        float lastScale = 0;
        TextView textView;
        ImageView imageView;

        SimpleViewHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_center);
            imageView = (ImageView) itemView.findViewById(R.id.iv_background);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //kRecyclerView.setFocusPosition(getAdapterPosition());
                    Intent myIntent = new Intent(MenuActivity.this, DetailsActivity.class);
                    int position = getAdapterPosition();

                    //kRecyclerView.getChildLayoutPosition(itemView);
                    //Log.v(("",String.valueOf(position));
                    Log.d("Test", String.valueOf(position));
                    myIntent.putExtra("position",textView.getText());
                    startActivity(myIntent);
                }
            });
        }

        void onScaled(float f) {
            if (Math.abs(f - lastScale) > 0.05f) {
                lastScale = f;
                textView.setScaleX(f + 1);
                textView.setScaleY(f + 1);
            }
        }
    }


    class SimpleAdapter extends KRecyclerView.EndlessAdapter<SimpleViewHolder> {
        LayoutInflater layoutInflater;
        Picasso picasso;

        SimpleAdapter(KRecyclerView kRecyclerView) {
            super(kRecyclerView);
            layoutInflater = LayoutInflater.from(MenuActivity.this);
            picasso = Picasso.with(MenuActivity.this);
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SimpleViewHolder(layoutInflater.inflate(R.layout.rv_item, parent, false));
        }

        @Override
        public int getItemViewType_(int position) {
            return 0;
        }

        @Override
        public int getItemCount_() {
            return 5;
        }

        @Override
        public void onBindViewHolder_(SimpleViewHolder holder, int position) {
            switch (position) {
                case 0:
                    holder.textView.setText("Neapolitan Pizza");
                    holder.imageView.setImageResource(R.drawable.neapolian);
                    break;
                case 1:
                    holder.textView.setText("Greek Pizza");
                    holder.imageView.setImageResource(R.drawable.greek);
                    break;
                case 2:
                    holder.textView.setText("Silican Pizza");
                    holder.imageView.setImageResource(R.drawable.sicilian);
                    break;
                case 3:
                    holder.textView.setText("Tomato Pie Pizza");
                    holder.imageView.setImageResource(R.drawable.tomato_pie);
                    break;
                case 4:
                    holder.textView.setText("Basilio Pizza");
                    holder.imageView.setImageResource(R.drawable.basilio);
                    break;
                case 5:
                    holder.textView.setText("Salami Pizza");
                    holder.imageView.setImageResource(R.drawable.broc_basil);
                    break;
            }
        }

        @Override
        public void onScaled(SimpleViewHolder holder, int position, float f) {
            holder.onScaled(f);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_pizza:
                // refresh
                String url = "https://www.dominos.ca/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            case R.id.action_help:
                // help action
                return true;
            case R.id.action_name:
                // check for updates action
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
