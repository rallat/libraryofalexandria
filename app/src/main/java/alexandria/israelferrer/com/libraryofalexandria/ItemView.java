package alexandria.israelferrer.com.libraryofalexandria;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by icamacho on 11/9/14.
 */
public class ItemView extends View {
    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setArtWork(ArtWork artWork){
        switch (artWork.getType()){
            case ArtWork.QUOTE:
                break;
            case ArtWork.PAINTING:
                break;
            case ArtWork.MOVIE:
                break;
            case ArtWork.OPERA:
                break;
        }
        if(artWork.isEditorSelected()){

        }
    }
}
