package pt.estig.twdm.pdm.keep_pocket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private final CategoryAdapterEventListener eventListener;
    private List<Category> categoryList;

    public CategoryAdapter(CategoryAdapterEventListener eventListener){
        this.eventListener = eventListener;
        this.categoryList = new ArrayList<>();
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row, parent, false);
        return new CategoryViewHolder(layout, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        Category category =this.categoryList.get(position);

        holder.setCategoryName(category.getCategoryName());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventListener.onCategoryClicked(category.getIdCategory());
            }

        });
        holder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                eventListener.onCategoryLongClicked(category.getIdCategory());
                return true;
            }
        });
    }

    public int getItemCount(){
        return this.categoryList.size();
    }

    public void updateCategoryList(List<Category> allCategory){
        this.categoryList = allCategory;
        notifyDataSetChanged();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        private View rootView;
        private TextView categoryName;
        private Context context;

        public CategoryViewHolder(@NonNull View rootView, Context context){
            super(rootView);
            this.context = context;
            this.rootView = rootView;
            this.categoryName = itemView.findViewById(R.id.categoryName);

        }
        public void setCategoryName(String categoryName){
            this.categoryName.setText(categoryName);
        }

    }
    public interface CategoryAdapterEventListener{
        void onCategoryClicked(long categoryId);
        void onCategoryLongClicked(long chatsId);
    }
}
