# TypeSpeedTester <img alt="GitHub top language" src="https://img.shields.io/github/languages/top/giteneaxharau/TypeSpeedTester?style=for-the-badge">

This typing speed tester is meant to measure the amount of right words per minute you write(WPM). App is made using Java & Swing/AWT, making possible to have a GUI.

# Development ![Lines of code](https://img.shields.io/tokei/lines/github.com/giteneaxharau/TypeSpeedTester?style=for-the-badge)

<details><summary><b>Development</b></summary>
       
1. Downloaded 1000 most used words in the english lexicon
2. Made a `FileReader` and `BufferedReader`. 
       Put them in an ArrayList and a While loop to add the new words in said ArrayList.
       Enclosed it in a Try & catch.
3. Made the GUI using `JComponents`.
4. Coded the functionality of the `JTextPane`.
       Using `.addKeyListener()`.
       We measure the index of the spaces.
       We validate the words.
       Else we listen to each character and add a red highlight if it is incorrect 
           and a green one for the opposite.
       If `spacePresses` are equal to the sublist `targetWords` we pop up a `JOptionPane` with the results 
           and reset everything so you can play again. Using Function `endGame`
5. Added the functionality of the `JButton`.
       It pops up a JOption pane promting for the amount of words.
       It starts the time, shuffles the ArrayList using `Collections.shuffle()` 
       Creates a sublist based on user input.
       Sets the 'textPane' uneditable and forces focus on it.
       Disables the button so you cant restart the game.
       Disables Backspace using Function `disableKeys` as not to mess with indexing of characters.
6. Styled GUI.
7. Fixed Bugs.
</details>

# How to use 

<details><summary><b>Get the app</b></summary>
       ```sh
       git clone https://github.com/giteneaxharau/TypeSpeedTester.git
       
       cd TypeSpeedTester
       ```
       Open the folder in your prefered IDE (Mine is JetBrains IntelliJ :])
</details>

