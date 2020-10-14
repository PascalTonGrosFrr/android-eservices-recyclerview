package android.eservices.recyclerview;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {
    List<GameViewModel> gameList;
    GameActionInterface gameActionInterface;

    public GameAdapter(GameActionInterface gameActionInterface) {
        this.gameActionInterface = gameActionInterface;
    }


    @NonNull
    @Override
    // Create new views (invoked by the layout manager)
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false);
        return new GameViewHolder(view, gameActionInterface);
    }

    @Override
    // Replace the contents of a view (invoked by the layout manager)
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bind(gameList.get(position));

    }

    @Override
    // Return the size of your dataset (invoked by the layout manager)
    public int getItemCount() {
        return gameList.size();
    }

    public void bindViewModelList(List<GameViewModel> gameList) {
        this.gameList = gameList;
        notifyDataSetChanged();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView title;
        private TextView description;
        private ImageView gameImage;
        private ImageButton imageGameButton;
        private ImageButton imageInfoButton;
        private GameActionInterface gameActionInterface;


        public GameViewHolder(View gameView, GameActionInterface gameActionInterface) {
            super(gameView);
            view = gameView;
            title = gameView.findViewById(R.id.title_textview);
            description = gameView.findViewById(R.id.description_textview);
            gameImage = gameView.findViewById(R.id.icon_imageview);
            imageGameButton = gameView.findViewById(R.id.game_button);
            imageInfoButton = gameView.findViewById(R.id.info_button);
            this.gameActionInterface = gameActionInterface;
        }

        public void bind(final GameViewModel gameViewModel) {
            title.setText(gameViewModel.getTitle());
            description.setText(gameViewModel.getDescription());
            Glide.with(view).load(gameViewModel.getImageUrl()).into(gameImage);

            imageGameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gameActionInterface.onGameClicked(gameViewModel.getTitle());
                }
            });

            imageInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gameActionInterface.onGameInfoClicked(gameViewModel.getTitle());
                }
            });
        }

    }
}
