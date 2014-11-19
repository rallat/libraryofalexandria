package alexandria.israelferrer.com.libraryofalexandria;


import java.util.List;

public class ArtWorkListPresenter implements Presenter {
    private final Display display;
    private final Model model;

    public ArtWorkListPresenter(Display display, Model model) {
        this.display = display;
        this.model = model;
    }

    @Override
    public void onCreate() {
        model.getArtWorks(new Callback<List<ArtWork>>() {
            @Override
            public void success(List<ArtWork> result) {
                display.setAdapter(result);
            }

            @Override
            public void failure(Exception exception) {
                display.showError();
            }
        });
    }
}
