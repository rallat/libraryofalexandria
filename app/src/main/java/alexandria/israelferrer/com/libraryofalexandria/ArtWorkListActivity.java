package alexandria.israelferrer.com.libraryofalexandria;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class ArtWorkListActivity extends Activity implements Display {
    private static final String KEY_FAVS = "com.israelferrer.alexandria.FAVS";
    List<ArtWork> artWorkList;
    ArtWorkAdapter adapter;
    ListView listView;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        artWorkList = new ArrayList<ArtWork>();
        if (presenter == null) {
            presenter = new ArtWorkListPresenter(this,
                    new ArtWorkModel(new ArtWorkServiceImpl(getResources().openRawResource(R.raw
                            .artwork)), getSharedPreferences(getPackageName()
                            , Context.MODE_PRIVATE)));
        }
        presenter.onCreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to  the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.filter) {
            adapter.orderMode();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setAdapter(List<ArtWork> artWorks) {
        artWorkList = artWorks;
        adapter = new ArtWorkAdapter();
        listView.setAdapter(adapter);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private class ArtWorkAdapter extends BaseAdapter {

        private final List<ArtWork> orderedList;
        private boolean isOrder;

        public ArtWorkAdapter() {
            super();
            orderedList = new LinkedList<ArtWork>();
        }

        @Override
        public int getCount() {
            return artWorkList.size();
        }

        @Override
        public Object getItem(int position) {
            return artWorkList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return Long.valueOf(artWorkList.get(position).getId());
        }

        public void orderMode() {
            isOrder = !isOrder;
            if (isOrder) {
                orderedList.clear();
                orderedList.addAll(artWorkList);
                Collections.sort(orderedList);
                notifyDataSetChanged();
            } else {
                notifyDataSetChanged();
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ArtWork artWork;
            if (isOrder) {
                artWork = orderedList.get(position);
            } else {
                artWork = artWorkList.get(position);
            }
            View row;

            switch (artWork.getType()) {
                case ArtWork.QUOTE:
                    row = getLayoutInflater().inflate(R.layout.text_row, null);
                    TextView quote = (TextView) row.findViewById(R.id.quote);
                    TextView author = (TextView) row.findViewById(R.id.author);

                    quote.setText("\"" + artWork.getText() + "\"");
                    author.setText(artWork.getAuthor());
                    break;
                case ArtWork.PAINTING:
                    final SharedPreferences preferences = getSharedPreferences(getPackageName()
                            , Context.MODE_PRIVATE);
                    final HashSet<String> favs = (HashSet<String>) preferences
                            .getStringSet(KEY_FAVS,
                                    new HashSet<String>());
                    row = getLayoutInflater().inflate(R.layout.painting_row, null);
                    ImageView image = (ImageView) row.findViewById(R.id.painting);
                    TextView painter = (TextView) row.findViewById(R.id.author);
                    painter.setText(artWork.getTitle() + " by " + artWork.getAuthor());
                    Picasso.with(ArtWorkListActivity.this).load(artWork.getContentUrl()).fit()
                            .into(image);
                    RatingBar rating = (RatingBar) row.findViewById(R.id.rate);
                    rating.setRating(artWork.getRating());
                    rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float rating,
                                                    boolean fromUser) {
                            preferences.edit().putFloat(ArtWorkModel.PACKAGE + artWork.getId(),
                                    rating).apply();
                            artWork.setRating(rating);
                        }
                    });
                    CheckBox fav = (CheckBox) row.findViewById(R.id.fav);
                    fav.setChecked(favs.contains(artWork.getId()));
                    fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            final HashSet<String> favs = new HashSet<String>((HashSet<String>)
                                    preferences
                                            .getStringSet(KEY_FAVS,
                                                    new HashSet<String>()));
                            if (isChecked) {
                                favs.add(artWork.getId());
                            } else {
                                favs.remove(artWork.getId());
                            }
                            preferences.edit().putStringSet(KEY_FAVS,
                                    favs).apply();
                        }
                    });
                    break;
                case ArtWork.MOVIE:
                case ArtWork.OPERA:
                    row = new ViewStub(ArtWorkListActivity.this);
                    break;

                default:
                    row = getLayoutInflater().inflate(R.layout.text_row, null);
            }
            return row;
        }

    }
}
