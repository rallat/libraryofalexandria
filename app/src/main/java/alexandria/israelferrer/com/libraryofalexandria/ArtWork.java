package alexandria.israelferrer.com.libraryofalexandria;


import java.util.List;

public class ArtWork implements Comparable<ArtWork> {
    public static final int QUOTE = 1;
    public static final int PAINTING = 2;
    public static final int OPERA = 3;
    public static final int MOVIE = 4;

    private final int type;
    private final String id;

    private String text;
    private String author;
    private String title;
    private String style;
    private String contentUrl;
    private String lyrics;
    private List<String> actors;
    private List<String> singers;
    private boolean isEditorSelected;
    private float rating;

    public ArtWork(int type, String id) {
        this.type = type;
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String description) {
        this.style = description;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getSingers() {
        return singers;
    }

    public void setSingers(List<String> singers) {
        this.singers = singers;
    }

    public boolean isEditorSelected() {
        return isEditorSelected;
    }

    public void setEditorSelected(boolean isEditorSelected) {
        this.isEditorSelected = isEditorSelected;
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(ArtWork another) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (this == another || this.rating == another.rating) return EQUAL;
        if (this.rating > another.rating) return BEFORE;
        if (this.rating < another.rating) return AFTER;
        return EQUAL;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return this.rating;
    }
}
