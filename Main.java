package javaapplication3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {

    String[] questions = {
        "1. What is 5 + 3?",
        "2. What is 12 - 4?",
        "3. What is 6 * 7?",
        "4. What is 20 / 4?",
        "5. What is 10 % 3?",
        "6. What is 15 + 6?",
        "7. What is 8 * 5?",
        "8. What is 100 - 45?",
        "9. What is 9 + 9?",
        "10. What is 81 / 9?"
    };

    String[] correctAnswers = {"8", "8", "42", "5", "1", "21", "40", "55", "18", "9"};
    JTextField[] answerFields = new JTextField[10];
    JButton submitButton;
    JTextArea resultArea;

    public Main() {
        setTitle("Math Quiz Application");
        setSize(700, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // Header
        JLabel titleLabel = new JLabel("Math Quiz - Answer All Questions", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(40, 40, 100));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Quiz Panel
        JPanel quizPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        quizPanel.setBackground(Color.WHITE);

        Font questionFont = new Font("Segoe UI", Font.PLAIN, 16);
        for (int i = 0; i < questions.length; i++) {
            JLabel qLabel = new JLabel(questions[i]);
            qLabel.setFont(questionFont);
            answerFields[i] = new JTextField();
            quizPanel.add(qLabel);
            quizPanel.add(answerFields[i]);
        }

        // Submit Button
        submitButton = new JButton("Submit Quiz");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(new Color(0, 123, 255));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(submitButton);

        // Combine quiz + button
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(quizPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Result Area - move to BOTTOM
        resultArea = new JTextArea(10, 60);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Result Summary"));
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // Add main panel
        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int score = 0;
        StringBuilder sb = new StringBuilder();

        sb.append("Answer Review:\n\n");

        for (int i = 0; i < questions.length; i++) {
            String userAnswer = answerFields[i].getText().trim();
            if (userAnswer.equals(correctAnswers[i])) {
                score++;
                sb.append("Q").append(i + 1).append(": Correct (Your answer: ").append(userAnswer).append(")\n");
            } else {
                sb.append("Q").append(i + 1).append(": Wrong (Your answer: ").append(userAnswer)
                        .append(", Correct: ").append(correctAnswers[i]).append(")\n");
            }
        }

        sb.append("\nTotal Score: ").append(score).append(" / 10\n");

        if (score == 10) {
            sb.append("Excellent! You got all correct.");
        } else if (score >= 7) {
            sb.append("Good job! You're doing well.");
        } else if (score >= 4) {
            sb.append("Not bad, but you need more practice.");
        } else {
            sb.append("Please try again. Practice makes perfect!");
        }

        resultArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
