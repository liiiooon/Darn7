package project.darn7;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private MaterialTextView scoreView;
    private MaterialTextView incrementView;
    private MaterialTextView darnItView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initClickListeners();
        initViewModel();
    }

    private void initViews() {
        scoreView = requireViewById(R.id.scoreView);
        incrementView = requireViewById(R.id.incrementView);
        darnItView = requireViewById(R.id.darnItView);
    }

    private void initClickListeners() {
        incrementView.setOnClickListener(this::onViewClick);
        darnItView.setOnClickListener(this::onViewClick);
    }

    private void onViewClick(View view) {
        viewModel.updateScore(view.getId());
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.observeScore(this, this::onScoreChange);
    }

    private void onScoreChange(int score) {
        String formattedScore = String.valueOf(score);
        scoreView.setText(formattedScore);
    }
}