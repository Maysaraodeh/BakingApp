package com.masotech.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.masotech.bakingapp.R;
import com.masotech.bakingapp.model.Step;

import java.util.List;

/**
 * Created by maysaraodeh on 21/09/2017.
 */

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeStepViewHolder> {

    private Context mContext;
    private List<Step> mSteps;
    final private ListItemClickListener lOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(List<Step> stepsOut,int clickedItemIndex);
    }

    public RecipeStepAdapter(ListItemClickListener listener){
        lOnClickListener = listener;
    }



    public void setRecipeData(Context context, List<Step> stepIn){
        mContext = context;
        mSteps = stepIn;

    }

    @Override
    public RecipeStepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        int layoutIdForIngredients = R.layout.recipe_step_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean attachImmediately = false;

        View view = inflater.inflate(layoutIdForIngredients , parent , attachImmediately);
        RecipeStepViewHolder recipeStepViewHolder = new RecipeStepViewHolder(view);

        return  recipeStepViewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeStepViewHolder holder, int position) {
        holder.recipeStepTitle.setText(mSteps.get(position).getShortDescription());
//        Picasso.with(mContext)
//                .load(mSteps.get(position).getVideoURL())
//                .into(holder.recipeStepImage);

    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    class RecipeStepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView recipeStepTitle;
        //ImageView recipeStepImage;

        public RecipeStepViewHolder(View itemView) {
            super(itemView);
            recipeStepTitle = (TextView) itemView.findViewById(R.id.recipe_step_title);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            lOnClickListener.onListItemClick(mSteps , clickedPosition);

        }
    }
}
