package pt.estig.twdm.pdm.keep_pocket.ui.Expenses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import pt.estig.twdm.pdm.keep_pocket.databinding.FragmentCategoriesBinding;
import pt.estig.twdm.pdm.keep_pocket.databinding.FragmentExpensesBinding;
import pt.estig.twdm.pdm.keep_pocket.ui.Categories.CategoriesViewModel;


public class ExpensesFragment extends Fragment {

    private FragmentExpensesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ExpensesViewModel expensesViewModel =
                new ViewModelProvider(this).get(ExpensesViewModel.class);

        binding = FragmentExpensesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textExpenses;
        expensesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
