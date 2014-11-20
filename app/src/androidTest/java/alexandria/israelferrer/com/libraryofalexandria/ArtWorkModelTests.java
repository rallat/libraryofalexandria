package alexandria.israelferrer.com.libraryofalexandria;

import android.content.SharedPreferences;

import junit.framework.TestCase;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static alexandria.israelferrer.com.libraryofalexandria.ArtWorkListActivityTests.ARTWORKS;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ArtWorkModelTests extends TestCase {
    private ArtWorkModel model;
    private SharedPreferences preferences;
    private ArtWorkService service;
    private Callback callback;
    private Exception exception;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        preferences = mock(SharedPreferences.class);
        service = mock(ArtWorkService.class);
        callback = mock(Callback.class);
        model = new ArtWorkModel(service, preferences);
        exception = new Exception();
    }

    public void testGetArtWorks_success() throws Exception {
        serviceSuccessAnswer();
        model.getArtWorks(callback);
        verify(service, only()).getArtWorks(any(Callback.class));
        verify(callback, only()).success(ARTWORKS);
        verify(preferences, times(ARTWORKS.size())).getFloat(any(String.class), eq(0F));
    }

    private void serviceSuccessAnswer() {
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                ((Callback) args[0]).success(ARTWORKS);
                return null;
            }
        }).when(service).getArtWorks(any(Callback.class));
    }

    public void testGetArtWorks_failure() throws Exception {
        serviceFailureAnswer();
        model.getArtWorks(callback);
        verify(service, only()).getArtWorks(any(Callback.class));
        verify(callback, only()).failure(exception);
    }

    private void serviceFailureAnswer() {
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                ((Callback) args[0]).failure(exception);
                return null;
            }
        }).when(service).getArtWorks(any(Callback.class));
    }
}
