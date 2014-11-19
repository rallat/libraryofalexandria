package alexandria.israelferrer.com.libraryofalexandria;

import junit.framework.TestCase;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static alexandria.israelferrer.com.libraryofalexandria.ArtWorkListActivityTests.ARTWORKS;
import static org.mockito.ArgumentCaptor.*;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

public class ArtWorkPresenterTests extends TestCase {
    private Display display;
    private Model model;
    private Presenter presenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        display = Mockito.mock(Display.class);
        model = Mockito.mock(Model.class);
        presenter = new ArtWorkListPresenter(display, model);
    }

    public void testOnCreate_success() throws Exception {
        presenter.onCreate();
        Callback callback = getCallback();
        callback.success(ARTWORKS);
        verify(display, only()).setAdapter(ARTWORKS);
    }

    public void testOnCreate_failure() throws Exception {
        presenter.onCreate();
        Callback callback = getCallback();
        callback.failure(new Exception());
        verify(display, only()).showError();
    }

    private Callback<List<ArtWork>> getCallback() {
        ArgumentCaptor<Callback> callbackCatcher = forClass(Callback.class);
        verify(model, only()).getArtWorks(callbackCatcher.capture());
        return callbackCatcher.getValue();
    }
}
