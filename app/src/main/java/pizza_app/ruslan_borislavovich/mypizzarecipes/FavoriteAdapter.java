package pizza_app.ruslan_borislavovich.mypizzarecipes;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>   {
    private ArrayList<InfoCardItem> cardItems;
    private Context context;
    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView description;


        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageView = itemView.findViewById(R.id.imagePizza);
            this.title = itemView.findViewById(R.id.pizzaName);
            this.description = itemView.findViewById(R.id.pizzaText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position  = getAdapterPosition();
                    InfoCardItem cardItem = cardItems.get(position);
                    Intent intent = new Intent(context, RecipeActivity.class);

                    intent.putExtra("title" , cardItem.getTitle());
                    intent.putExtra("description" , cardItem.getDescription());
                    intent.putExtra("recipe" , cardItem.getRecipe());
                    intent.putExtra("imageResourse" , cardItem.getImageView());
                    intent.putExtra("adapterPosition" , position);
                    intent.putExtra("context","favorite");
                    context.startActivity(intent);

                }
            });
        }
    }

    public FavoriteAdapter(ArrayList<InfoCardItem> cardItems, Context context){
        this.cardItems = cardItems;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        InfoCardItem cardItem = cardItems.get(position);
        holder.imageView.setImageResource(cardItem.getImageView());
        holder.title.setText(cardItem.getTitle());
        holder.description.setText(cardItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }
}
