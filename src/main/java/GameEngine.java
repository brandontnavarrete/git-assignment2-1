import java.util.concurrent.ThreadLocalRandom;

public class GameEngine {
    private static final int MAX_ATTEMPTS = 10;

    private final int min;
    private final int max;
    private int target;
    private int attempts;
    private boolean gameWon;
    private boolean userQuit;

    private boolean gameOver;

    private boolean hintsEnabled;

    private static final int MAX_ATTEMPTS = 10;
    private boolean gameOver;   

    public GameEngine(int min, int max) {
        this.min = min;
        this.max = max;
        this.attempts = 0;
        this.gameWon = false;

        this.userQuit = false;

        this.gameOver = false;

        this.hintsEnabled = true;

        reset();
    }

    public GuessResult makeGuess(int guess) {
        // 
        if (guess < 0) {
            userQuit = true;
            return new GuessResult(false, "Exiting game...", attempts);
        }

        attempts++;

        if (guess == target) {
            gameWon = true;
            return new GuessResult(true, "Correct! You guessed it in " + attempts + " attempts.", attempts);
        }

        // After a wrong guess, check attempts cap
        if (attempts >= MAX_ATTEMPTS) {
            gameOver = true;
            return new GuessResult(false,
                "Game Over! You've used all " + MAX_ATTEMPTS + " attempts. The number was " + target + ".",
                attempts);
        }

        // Still have attempts left; return directional feedback + remaining attempts
        int remaining = MAX_ATTEMPTS - attempts;

        GuessResult result;
        if (guess < target) {
            result = new GuessResult(false, "Too low! Try a higher number.", attempts);
        } else {
            result = new GuessResult(false, "Too high! Try a lower number.", attempts);
        }
        result.setRemainingAttempts(remaining);

        // Base feedback message
        String message = (guess < target)
                ? "Too low! Try a higher number."
                : "Too high! Try a lower number.";

        // --- HINT logic (begins on 3rd attempt) ---
        String hint = "";
        if (hintsEnabled && attempts >= 3) {
            int diff = Math.abs(target - guess);
            if (diff <= 5) {
                hint = "HINT: You're very close!";
            } else if (diff <= 20) {
                hint = "HINT: Getting warmer!";
            }
            if (!hint.isEmpty()) {
                message += " " + hint; // include hint text in the user-facing message
            }
        }

        // Build result with remaining attempts and hint field populated
        GuessResult result = new GuessResult(false, message, attempts);
        result.setRemainingAttempts(remaining); // keep remaining-attempts behavior
        result.setHint(hint);                   // so testHintFieldAccessor() sees a non-empty hint

        return result;
    }

    public void reset() {
        target = ThreadLocalRandom.current().nextInt(min, max + 1);
        attempts = 0;
        gameWon = false;
        userQuit = false;
        gameOver = false;
    }


    public boolean isGameWon() {
        return gameWon;
    }

    public boolean hasUserQuit() {
        return userQuit;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public boolean isHintsEnabled() {
        return hintsEnabled;
    }

    public void setHintsEnabled(boolean enabled) {
        this.hintsEnabled = enabled;
    }

    private String getHint(int guess) {
        if (!hintsEnabled) return "";
        if (attempts < 3)  return "";   // no hint before the 3rd attempt

        int diff = Math.abs(target - guess);
        if (diff <= 5) {
            return "HINT: You're very close!";
        } else if (diff <= 20) {
            return "HINT: Getting warmer!";
        }
        return "";
    }


    // For testing purposes only
    protected void setTarget(int target) {
        this.target = target;
    }

    protected int getTarget() {
        return target;
    }

    public boolean isGameOver() { 
        return gameOver;
    }
    
    public int getMaxAttempts() { 
        return MAX_ATTEMPTS;
    }

}
