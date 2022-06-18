package pt.estig.twdm.pdm.keep_pocket.ui.Limits;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LimitsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LimitsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Limits fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}