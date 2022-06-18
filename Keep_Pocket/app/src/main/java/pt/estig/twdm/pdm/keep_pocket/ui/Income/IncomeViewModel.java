package pt.estig.twdm.pdm.keep_pocket.ui.Income;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IncomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public IncomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Income fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}