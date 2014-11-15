package alexandria.israelferrer.com.libraryofalexandria;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class ArtWorkListPresenter implements Presenter {

    @Override
    public void onCreate() {
        InputStream stream = getResources().openRawResource(R.raw.artwork);
        Type listType = new TypeToken<List<ArtWork>>() {
        }.getType();
        artWorkList = new Gson().fromJson(new InputStreamReader(stream), listType);
        final SharedPreferences preferences = getSharedPreferences(getPackageName()
                , Context.MODE_PRIVATE);
        for (ArtWork artWork : artWorkList) {
            artWork.setRating(preferences.getFloat(PACKAGE + artWork.getId(), 0F));
        }

        adapter = new ArtWorkAdapter();
        listView.setAdapter(adapter);
    }
}
