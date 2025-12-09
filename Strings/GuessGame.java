public class GuessGame {
    private int picked;

    public GuessGame(int picked) {
        this.picked = picked;
    }

    public int guess(int num) {
        if (num == picked)
            return 0;
        else if (num > picked)
            return -1;
        else
            return 1;
    }

   
}
