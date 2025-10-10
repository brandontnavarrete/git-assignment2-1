import java.util.Scanner;

public class GameUI {
    private final GameEngine engine;
    private final Scanner scanner;

    public GameUI(GameEngine engine, Scanner scanner) {
        this.engine = engine;
        this.scanner = scanner;
    }

    public void start() {
        // Loop until the game reaches a terminal state
        while (!engine.isGameWon() && !engine.isGameOver() && !engine.hasUserQuit()) {
            System.out.print("Guess a number between "
                    + engine.getMin() + " and " + engine.getMax()
                    + " (or negative to exit): ");

            int guess = Utils.readInt(scanner);

            GuessResult result = engine.makeGuess(guess);
            System.out.println(result.getMessage());

            // Display attempts remaining (feature2)
            int remaining = engine.getMaxAttempts() - engine.getAttempts();
            System.out.println("Attempts left: " + remaining);
        }

        // Optional: terminal messages once we exit the loop
        if (engine.hasUserQuit()) {
            System.out.println("Goodbye!");
        } else if (engine.isGameWon()) {
            System.out.println("You win!");
        } else if (engine.isGameOver()) {
            System.out.println("Game over!");
        }
    }
}
