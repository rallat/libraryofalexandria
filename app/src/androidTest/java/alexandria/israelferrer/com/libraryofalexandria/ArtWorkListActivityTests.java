package alexandria.israelferrer.com.libraryofalexandria;

import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;


public class ArtWorkListActivityTests extends ActivityUnitTestCase<ArtWorkListActivity> {
    static final String ANY_ID = "15224484";
    static final List<ArtWork> ARTWORKS = new ArrayList<ArtWork>() {
        {
            add(new ArtWork(ArtWork.QUOTE, ANY_ID));
        }
    };
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
        startActivity(launchIntent, null, null);
        activity = getActivity();
        activity.setPresenter(presenter);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testOnCreate() throws Exception {
        activity.onCreate(new Bundle());
        verify(presenter, only()).onCreate();
    }

    public void testSetAdapter() throws Exception {
        activity.setAdapter(ARTWORKS);
        Adapter adapter = activity.listView.getAdapter();
        assertEquals(activity.artWorkList, ARTWORKS);
        assertEquals(adapter.getCount(), ARTWORKS.size());
        assertEquals(adapter.getItem(0), ARTWORKS.get(0));
    }

}
