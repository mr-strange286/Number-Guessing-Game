package com.guessinggame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Main {
    JLabel label;
    JLabel attemptsLabel;
    JTextField textField;
    JButton button;
    JButton newGameButton;
    JComboBox<String> difficultyBox;
    int secretNumber;
    int attempts = 0;
    int maxAttempts = 10;
    int highScore = 0;
    int wins = 0;
    int losses = 0;

    JLabel scoreLabel;
    JLabel highScoreLabel;
    JLabel winsLabel;
    JLabel lossesLabel;
    public static void main(String[] args) {
        new Main();
    }
    public Main(){

        JFrame frame = new JFrame();

        frame.setTitle("Number Guessing Game");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label = new JLabel("Welcome to Number Guessing Game");
        attemptsLabel = new JLabel("Attempts Left: 10");
        scoreLabel = new JLabel("Score: 0");
        highScoreLabel = new JLabel("High Score: 0");
        winsLabel = new JLabel("Wins: 0");
        lossesLabel = new JLabel("Losses: 0");
        String[] levels = {"Easy", "Moderate", "Hard"};
        difficultyBox = new JComboBox<>(levels);
        secretNumber = ThreadLocalRandom.current().nextInt(1, 51);
        maxAttempts = 10;
        button = new JButton("Guess");
        newGameButton = new JButton("New Game");
        textField = new JTextField(10);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int guess;

                try
                {
                    guess = Integer.parseInt(textField.getText());
                }
                catch(NumberFormatException ex)
                {
                    label.setText("Please enter a valid number.");
                    return;
                }
                attempts++;
                attemptsLabel.setText(
                    "Attempts Left: " + (maxAttempts - attempts)
                );

                if (guess > secretNumber)
                {
                    label.setText("Too High");
                }
                else if (guess < secretNumber)
                {
                    label.setText("Too Low!");
                }
                else
                {
                    label.setText("Congratulations! You guessed correctly.");
                    button.setEnabled(false);
                    wins++;

                    int multiplier;

                    if(maxAttempts == 10)
                        multiplier = 100;
                    else if(maxAttempts == 8)
                        multiplier = 200;
                    else
                        multiplier = 500;

                    int score = (maxAttempts - attempts + 1) * multiplier;

                    scoreLabel.setText("Score: " + score);

                    if(score > highScore)
                    {
                        highScore = score;
                    }

                    highScoreLabel.setText("High Score: " + highScore);
                    winsLabel.setText("Wins: " + wins);
                }
                if (attempts == maxAttempts && guess != secretNumber)
                {
                    label.setText("Game Over! Number was " + secretNumber);
                    button.setEnabled(false);
                    losses++;
                    lossesLabel.setText("Losses: " + losses);
                }
            }   
        });
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String level = (String) difficultyBox.getSelectedItem();

                if(level.equals("Easy"))
                {
                    secretNumber = ThreadLocalRandom.current().nextInt(1,51);
                    maxAttempts = 10;
                }
                else if(level.equals("Moderate"))
                {
                    secretNumber = ThreadLocalRandom.current().nextInt(1,101);
                    maxAttempts = 8;
                }
                else
                {
                    secretNumber = ThreadLocalRandom.current().nextInt(1,501);
                    maxAttempts = 6;
                }

                attempts = 0;
                attemptsLabel.setText("Attempts Left: " + maxAttempts);
                textField.setText("");
                label.setText("Welcome to Number Guessing Game");

                button.setEnabled(true);
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,1));
        panel.add(difficultyBox);
        panel.add(label);
        panel.add(attemptsLabel);
        panel.add(scoreLabel);
        panel.add(highScoreLabel);
        panel.add(winsLabel);
        panel.add(lossesLabel);
        panel.add(textField);
        panel.add(button);
        panel.add(newGameButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}