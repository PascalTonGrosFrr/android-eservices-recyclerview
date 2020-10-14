package android.eservices.recyclerview;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GameActionInterface {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        setupRecyclerView();

        this.res = getResources();
    }

    private void setupRecyclerView() {
        // Setup recycler view
        recyclerView = findViewById(R.id.my_recyclerview);
        recyclerView.setHasFixedSize(true);

        // specify layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        GameAdapter gameAdapter = new GameAdapter(this);
        recyclerView.setAdapter(gameAdapter);

        //Use data generator to get data to display.
        List<GameViewModel> gameList = DataGenerator.generateData();
        gameAdapter.bindViewModelList(gameList);
    }

    public void displaySnackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onGameInfoClicked(String gameTitle) {
        List<GameViewModel> gameViewModels = DataGenerator.generateData();
        for(GameViewModel g : gameViewModels){
            if(gameTitle.equals(g.getTitle())){
                displaySnackBar(String.format(this.res.getString(R.string.game_info_clicked), g.getTitle()));
            }
        }
    }

    @Override
    public void onGameClicked(String gameTitle) {
        List<GameViewModel> gameViewModels = DataGenerator.generateData();
        for(GameViewModel g : gameViewModels){
            if(gameTitle.equals(g.getTitle())){
                displaySnackBar(String.format(this.res.getString(R.string.game_clicked), g.getTitle()));
            }
        }
    }


    //TODO create callback methods for item click
    //Use ressource strings to get the text to display

}
