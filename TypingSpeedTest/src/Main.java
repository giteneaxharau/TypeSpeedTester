import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;



public class Main {
    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("TypingSpeedTest/src/wordlist.txt");
            BufferedReader bfr = new BufferedReader(fr); //read file
            ArrayList<String> words = new ArrayList<>();
            String.join(" ", words);
            String line; // avoid creating shuffle and read directly one some
            while ((line = bfr.readLine()) != null) words.add(line);
            bfr.close();
            // take random words

            // create gui
            JFrame mainFrame = new JFrame();
            JTextField targetText = new JTextField(textGenerator(words));
            JPanel separator = new JPanel();

            JTextArea inputText = new JTextArea();
            inputText.addMouseListener(new MouseListener() {

                boolean flag = false;
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if(e.getButton() == MouseEvent.BUTTON1) {
                        startGame(String.valueOf(words));
                    }

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });


            JPanel mainPanel = new JPanel();
            mainPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
            mainPanel.setLayout(new GridLayout(0,1));
            mainPanel.add(targetText);
            mainPanel.add(separator);
            mainPanel.add(inputText);
            mainPanel.setPreferredSize(new Dimension(840,400));

            mainFrame.add(mainPanel, BorderLayout.CENTER);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setTitle("Typing Speed Test");
            mainFrame.pack();
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String textGenerator(ArrayList<String> wordList){
        Collections.shuffle(wordList);
        List<String> target = wordList.subList(0, 10);
        return String.join(" ", target);
    }

    public static void startGame(String targetText){
        //wait for button to start counter
        Scanner sc = new Scanner(System.in);
        long startTime = System.currentTimeMillis();
        int counter = 0;
        for (String w:targetText.split(" ")){
        }


    }

}
