package project.darn7;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private static final int DEFAULT_SCORE = 0;

    private final MutableLiveData<Integer> score = new MutableLiveData<>(DEFAULT_SCORE);

    public void observeScore(LifecycleOwner owner, Observer<Integer> observer) {
        score.observe(owner, observer);
    }

    public void updateScore(int viewId) {

        Runnable onIsDarnIt = this::resetScore;
        Runnable onIsNotDarnIt = this::incrementScore;

        if (viewId == R.id.darnItView) {
            onIsDarnIt = this::incrementScore;
            onIsNotDarnIt = this::resetScore;
        }

        int score = getScore() + 1;

        String formattedScore = String.valueOf(score);

        boolean modulo3 = score % 3 == 0;
        boolean modulo7 = score % 7 == 0;
        boolean contains3 = formattedScore.contains("3");
        boolean contains7 = formattedScore.contains("7");

        boolean isDarnIt = modulo3 || modulo7 || contains3 || contains7;

        if (isDarnIt) {
            onIsDarnIt.run();
        } else {
            onIsNotDarnIt.run();
        }
    }

    private void resetScore() {
        score.setValue(DEFAULT_SCORE);
    }

    private void incrementScore() {
        int newScore = getScore() + 1;
        score.setValue(newScore);
    }

    private int getScore() {
        return score.getValue() != null ? score.getValue() : DEFAULT_SCORE;
    }
}