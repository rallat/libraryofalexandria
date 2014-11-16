package alexandria.israelferrer.com.libraryofalexandria;

import java.util.List;

public interface ArtWorkService {
    void getArtWorks(Callback<List<ArtWork>> result);
}
