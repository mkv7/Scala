import scala.io.StdIn
import scala.util.Random

object WordGuessingGame:

  def main(args: Array[String]): Unit =
    println("Welcome to the Word Guessing Game!")
    println("You need to guess the hidden word letter by letter.")
    println("You have a limited number of attempts. Let's start!\n")

    val words = List("scala", "programming", "developer", "functional", "object", "variable", "syntax", "compile")
    val randomWord = words(Random.nextInt(words.length))
    val hiddenWord = randomWord.toCharArray
    var guessedWord = Array.fill(hiddenWord.length)('_')
    var attemptsLeft = 8
    var guessedLetters = Set.empty[Char]

    def displayGuessedWord(): Unit =
      println("\nWord: " + guessedWord.mkString(" "))
      println(s"Guessed Letters: ${guessedLetters.mkString(", ")}")
      println(s"Attempts Left: $attemptsLeft\n")

    def processGuess(letter: Char): Boolean =
      var correct = false
      for i <- hiddenWord.indices do
        if hiddenWord(i) == letter && guessedWord(i) != letter then
          guessedWord(i) = letter
          correct = true
      guessedLetters += letter
      correct

    // Main game loop
    while attemptsLeft > 0 && guessedWord.mkString != hiddenWord.mkString do
      displayGuessedWord()
      print("Enter your guess (single letter): ")
      val input = StdIn.readLine().toLowerCase

      if input == "exit" then
        println("Exiting the game. Goodbye!")
        return

      if input.length != 1 || !input.head.isLetter then
        println("Invalid input. Please enter a single letter.")
      else
        val letter = input.head
        if guessedLetters.contains(letter) then
          println(s"You already guessed '$letter'. Try a different letter.")
        else if processGuess(letter) then
          println(s"Good guess! '$letter' is in the word.")
        else
          println(s"Sorry, '$letter' is not in the word.")
          attemptsLeft -= 1

    // End of game
    if guessedWord.mkString == hiddenWord.mkString then
      println(s"Congratulations! You guessed the word: ${hiddenWord.mkString}")
    else
      println(s"Game Over! The word was: ${hiddenWord.mkString}")

    println("Thanks for playing!")

