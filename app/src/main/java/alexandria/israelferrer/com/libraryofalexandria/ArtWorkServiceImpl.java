package alexandria.israelferrer.com.libraryofalexandria;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArtWorkServiceImpl implements ArtWorkService {

    private final InputStream source;
    private List<ArtWork> artWorkList;

    public ArtWorkServiceImpl(InputStream source) {
        this.source = source;
        this.artWorkList = new ArrayList<ArtWork>();
    }

    @Override
    public void getArtWorks(Callback<List<ArtWork>> result) {
        Type listType = new TypeToken<List<ArtWork>>() {
        }.getType();
        artWorkList = new Gson().fromJson(new InputStreamReader(source), listType);
        result.success(artWorkList);
    }
}
