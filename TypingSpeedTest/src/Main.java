import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //GUI INITIALIZING AND STYLING
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Typing Speed Test");
        JTextPane textPane = new JTextPane();
        JButton startGame = new JButton("Start Test");
        JLabel title = new JLabel();
        Highlighter highlighter;
        Font font = new Font("Bondon", Font.PLAIN, 20);
        Font fontTitle = new Font("Bondon", Font.PLAIN, 25);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setPreferredSize(new Dimension(1280, 720));
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        panel.add(title, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 400;
        gbc.ipadx = 1000;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(70,10 ,0,10);
        panel.add(textPane, gbc);
        gbc.ipady = 30;
        gbc.ipadx = 80; //reset to default
        gbc.weighty = 1.0;   //request any extra vertical space
        gbc.anchor = GridBagConstraints.PAGE_END; //bottom of space
        gbc.insets = new Insets(10,0,110,0);  //top padding
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.gridy = 2;
        panel.add(startGame, gbc);

        frame.add(panel);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startGame.setBorder(BorderFactory.createLineBorder(Color.GRAY,2,true));

        title.setText("Typing Speed Test");
        title.setFont(fontTitle);
        title.setForeground(Color.BLACK);


        var ref = new Object() { //GLOBAL OBJECT WITH ALL THE VARIABLES NEEDED
            ArrayList<String> words = null;
            List<String> targetWords = null;
            long startTime;
            String placeholder;
        };

        //FILE READING AND POPULATING
        try {
            FileReader fr = new FileReader("TypingSpeedTest/src/wordlist.txt");
            BufferedReader bfr = new BufferedReader(fr); //read file
            ref.words = new ArrayList<>();
            String line;
            while ((line = bfr.readLine()) != null) ref.words.add(line);
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //JButton FUNCTIONALITY
        startGame.addActionListener(actionEvent -> {
            //TAKES THE INPUT FROM USER TO SET THE AMOUNT OF WORDS ON THE JTextPane
            int wordCount = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter the number of words", JOptionPane.PLAIN_MESSAGE));
            ref.startTime = System.currentTimeMillis(); //STARTS TIME
            Collections.shuffle(ref.words); //SHUFFLES THE WORDS SO IT IS RANDOM
            ref.targetWords = ref.words.subList(0, wordCount); //CREATES THE TARGET TEXT AND SETS IT
            ref.placeholder = String.join(" ", ref.targetWords);
            textPane.setText(ref.placeholder);
            textPane.setFocusable(true);
            textPane.requestFocus();
            startGame.setEnabled(false);
        });

        textPane.setEditable(false);
        highlighter = textPane.getHighlighter();
        textPane.setHighlighter(highlighter);

        //CENTERING THE TEXT HORIZONTALLY FOR THE JTextPane

        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        textPane.setFont(font);
        textPane.setBorder(BorderFactory.createLineBorder(new Color(0, 180, 216), 4, true));
        textPane.setBackground(new Color(255, 255, 250));

        textPane.addKeyListener(new KeyAdapter() {
            int rightWords = 0;
            int spacePresses = 0;
            String input = "";
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                //LISTENER FOR SPACES
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    spacePresses += 1;
                    System.out.println(spacePresses);
                    String targetWord = ref.targetWords.get(spacePresses - 1);
                    String lastInput = input.split(" ")[input.split(" ").length -1];
                    if (targetWord.equals(lastInput)) rightWords++;
                    input += " ";
                }else {   //LISTENER FOR ANY CHARACTER
                    char ch = e.getKeyChar();
                    if (Character.isAlphabetic(ch)) input += ch;
                    //HIGHLIGHTER IMPLEMENTATION
                    try {
                        if (ref.placeholder.charAt(input.length()-1) != ch){
                            highlighter.addHighlight(input.length()-1, input.length(),
                                    new DefaultHighlighter.DefaultHighlightPainter(Color.RED));
                        } else {
                            highlighter.addHighlight(input.length()-1, input.length(),
                                    new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN));
                        }
                    } catch (BadLocationException ex){
                        ex.printStackTrace();
                    }
                }
                if (spacePresses == ref.targetWords.size()) {
                    double timeInSeconds = (System.currentTimeMillis() - ref.startTime)/1000.00;
                    String result = "You entered " + rightWords+"/" + ref.targetWords.size()+
                            " in "+ (timeInSeconds/ 60.00) + "minutes. " + "Means your speed is: " +
                            Math.round(rightWords/(timeInSeconds/60))+ " WPM(words per minute).";
                    JOptionPane.showMessageDialog(frame, result, "Results: ", JOptionPane.INFORMATION_MESSAGE);
                    endGame(textPane,startGame);
                    ref.placeholder = "";
                    input = "";
                    spacePresses = 0;
                    rightWords = 0;
                    System.out.println(ref.targetWords.size());
                }
            }
        });
    }

    public static void endGame(JTextPane pane, JButton button){
        button.setEnabled(true);
        pane.setText("Press start for a new game");
        pane.setEditable(false);
    }
}

