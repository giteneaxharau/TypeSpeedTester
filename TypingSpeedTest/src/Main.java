import javax.swing.*;
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
        //Gui initializing
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Typing Speed Test");
        JTextPane textPane = new JTextPane();
        JButton startGame = new JButton("Start Game");

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(2, 1, 20, 30));
        panel.setPreferredSize(new Dimension(840, 400));
        panel.add(textPane);
        panel.add(startGame);
        frame.add(panel);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        var ref = new Object() { // global object with all variables used by all components
            ArrayList<String> words = null;
            List<String> targetWords = null;
            long startTime;
        };

        try { //file reading and words populating
            FileReader fr = new FileReader("TypingSpeedTest/src/wordlist.txt");
            BufferedReader bfr = new BufferedReader(fr); //read file
            ref.words = new ArrayList<>();
            String line;
            while ((line = bfr.readLine()) != null) ref.words.add(line);
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //button functionality
        startGame.addActionListener(actionEvent -> {
            //take input about number of words by user
            int wordCount = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter the number of words", JOptionPane.PLAIN_MESSAGE));
            ref.startTime = System.currentTimeMillis(); //start stopwatch
            Collections.shuffle(ref.words); // shufle the words
            ref.targetWords = ref.words.subList(0, wordCount); // create target text and set it to the pane
            String placeholder = String.join(" ", ref.targetWords);
            textPane.setText(placeholder);
            textPane.setFocusable(true);
            textPane.requestFocus();
        });

        //text pane validator and keylisteners
        textPane.setEditable(false);
        textPane.addKeyListener(new KeyAdapter() {
            int rightWords = 0; //when 10 show joption and end program or reset
            int enterPresses = 0;
            String input = "";
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println();
                super.keyTyped(e);
                //space listener
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    enterPresses += 1;
                    //textPane.setCaretPosition(textPane.getCaretPosition()+1);
                    String targetWord = ref.targetWords.get(enterPresses - 1);
                    String lastInput = input.split(" ")[input.split(" ").length -1];
                    if (targetWord.equals(lastInput)) rightWords++;
                    input += " ";
                    if (enterPresses == ref.targetWords.size()) {
                        String result = "You entered " + rightWords+"/" + ref.targetWords.size()+
                                " in "+(ref.startTime - System.currentTimeMillis()) / 60 + "minutes";
                        JOptionPane.showMessageDialog(frame, result, "Results: ", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0); //just close the window and reset textpane text
                    }
                }else {   // any character listener
                    char ch = e.getKeyChar();
                    if (Character.isAlphabetic(ch)) input += ch;

                } // add highlet using caretPosition -1
            }
        });
    }
}

