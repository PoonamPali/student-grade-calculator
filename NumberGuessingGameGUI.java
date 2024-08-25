import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame implements ActionListener {
    private JTextField guessField;
    private JButton guessButton, playAgainButton;
    private JLabel messageLabel, attemptsLabel, scoreLabel;
    private int numberToGuess, attempts, maxAttempts = 5, score = 0;
    private Random random;

    public NumberGuessingGameGUI() {
        random = new Random();
        numberToGuess = random.nextInt(100) + 1;

        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        messageLabel = new JLabel("Guess a number between 1 and 100:");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(messageLabel);

        guessField = new JTextField();
        add(guessField);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        add(guessButton);

        attemptsLabel = new JLabel("Attempts remaining: " + maxAttempts);
        attemptsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(attemptsLabel);

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(scoreLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(this);
        playAgainButton.setEnabled(false);
        add(playAgainButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            String guessText = guessField.getText();
            try {
                int userGuess = Integer.parseInt(guessText);
                attempts++;
                if (userGuess == numberToGuess) {
                    messageLabel.setText("Correct! You've guessed the number!");
                    score += 10;
                    endGame();
                } else if (userGuess < numberToGuess) {
                    messageLabel.setText("Too low! Try again.");
                } else {
                    messageLabel.setText("Too high! Try again.");
                }
                attemptsLabel.setText("Attempts remaining: " + (maxAttempts - attempts));

                if (attempts >= maxAttempts) {
                    messageLabel.setText("Out of attempts! The correct number was " + numberToGuess);
                    endGame();
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number.");
            }
        } else if (e.getSource() == playAgainButton) {
            resetGame();
        }
    }

    private void endGame() {
        guessButton.setEnabled(false);
        playAgainButton.setEnabled(true);
        scoreLabel.setText("Score: " + score);
    }

    private void resetGame() {
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;
        guessField.setText("");
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);
        messageLabel.setText("Guess a number between 1 and 100:");
        attemptsLabel.setText("Attempts remaining: " + maxAttempts);
    }

    public static void main(String[] args) {
        new NumberGuessingGameGUI();
    }
}
