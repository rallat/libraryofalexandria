package alexandria.israelferrer.com.libraryofalexandria;

import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;


public class ArtWorkListActivityTests extends ActivityUnitTestCase<ArtWorkListActivity> {
    private ArtWorkListActivity activity;
    private Presenter presenter;

    public ArtWorkListActivityTests() {
        super(ArtWorkListActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        presenter = mock(Presenter.class);
        Intent launchIntent = new Intent(getInstrumentation()
                .getTargetContext(), ArtWorkListActivity.class);
        startActivity(launchIntent,null,null);
        activity=getActivity();
        activity.setPresenter(presenter);
    }

    public void testOnCreate() throws Exception {
        activity.onCreate(new Bundle());
        verify(presenter, only()).onCreate();
    }
}
