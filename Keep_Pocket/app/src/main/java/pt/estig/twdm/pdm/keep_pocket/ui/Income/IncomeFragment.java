package pt.estig.twdm.pdm.keep_pocket.ui.Income;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import pt.estig.twdm.pdm.keep_pocket.databinding.FragmentIncomeBinding;


public class IncomeFragment extends Fragment {

    private FragmentIncomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        IncomeViewModel incomeViewModel =
                new ViewModelProvider(this).get(IncomeViewModel.class);

        binding = FragmentIncomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textIncome;
        incomeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}