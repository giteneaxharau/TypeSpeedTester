# TypeSpeedTester <img alt="GitHub top language" src="https://img.shields.io/github/languages/top/giteneaxharau/TypeSpeedTester?style=for-the-badge">

This typing speed tester is meant to measure the amount of right words per minute you write(WPM). App is made using Java & Swing/AWT, making possible to have a GUI.

# Development ![Lines of code](https://img.shields.io/tokei/lines/github.com/giteneaxharau/TypeSpeedTester?style=for-the-badge)

1. Downloaded 1000 most used words in the english lexicon
2. Made a `FileReader` and `BufferedReader`. 
       Put them in an `ArrayList` and a `While loop` to add the new words in said ArrayList.
       Enclosed it in a `Try & catch`.
3. Made the GUI using `JComponents`.
4. Coded the functionality of the `JTextPane`.
       Using `.addKeyListener()`.
       We measure the index of the spaces.
       We validate the words.
       Else we listen to each character and add a red highlight if it is incorrect 
           and a green one for the opposite.
       If `spacePresses` are equal to the sublist `targetWords` we pop up a `JOptionPane` with the results 
           and reset everything so you can play again.
5. Added the functionality of the `JButton`.
    

