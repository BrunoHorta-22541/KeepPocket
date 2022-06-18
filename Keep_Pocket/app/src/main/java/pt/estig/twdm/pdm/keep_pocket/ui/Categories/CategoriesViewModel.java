package pt.estig.twdm.pdm.keep_pocket.ui.Categories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CategoriesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CategoriesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Categories fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}