package pt.estig.twdm.pdm.keep_pocket.ui.Limits;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import pt.estig.twdm.pdm.keep_pocket.databinding.FragmentLimitsBinding;
import pt.estig.twdm.pdm.keep_pocket.ui.Categories.CategoriesViewModel;


public class LimitsFragment extends Fragment {

    private FragmentLimitsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LimitsViewModel limitsViewModel =
                new ViewModelProvider(this).get(LimitsViewModel.class);

        binding = FragmentLimitsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textLimits;
        limitsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}