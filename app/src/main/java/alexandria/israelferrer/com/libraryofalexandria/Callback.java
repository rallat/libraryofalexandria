package alexandria.israelferrer.com.libraryofalexandria;

public interface Callback<T> {
    void success(T result);

    void failure(Exception exception);
}
