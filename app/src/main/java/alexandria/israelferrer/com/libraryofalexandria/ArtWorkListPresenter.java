package alexandria.israelferrer.com.libraryofalexandria;


public class ArtWorkListPresenter implements Presenter {
    private final Display display;
    private final Model model;

    public ArtWorkListPresenter(Display display, Model model) {
        this.display = display;
        this.model = model;
    }

    @Override
    public void onCreate() {
        display.setAdapter(model.getArtWorks());
    }
}
