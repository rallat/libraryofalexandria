package alexandria.israelferrer.com.libraryofalexandria;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;


public class ArtWorkModel implements Model {
    public static final String PACKAGE = "com.israelferrer.alexandria";
    private final ArtWorkService service;
    private final SharedPreferences persistence;

    public ArtWorkModel(ArtWorkService service,SharedPreferences persistence) {
        this.service = service;
        this.persistence=persistence;
    }


    @Override
    public void getArtWorks(final Callback<List<ArtWork>> callback) {
        service.getArtWorks(new Callback<List<ArtWork>>() {
            @Override
            public void success(List<ArtWork> result) {
                restoreRating(result);
                callback.success(result);
            }

            @Override
            public void failure(Exception exception) {
                callback.failure(exception);
            }
        });
    }

    private void restoreRating(List<ArtWork> result) {
        for (ArtWork artWork : result) {
            artWork.setRating(persistence.getFloat(PACKAGE + artWork.getId(), 0F));
        }
    }
}
