# Webtech-TicTackToe

Mitglieder: Daniel Edlich, Tobias Armbruster

Kurzbeschreibung:

In unserem Projekt werden wir das Spiel TickTackToe als Single-Page-Application darstellen.
Die DB wird entweder als Highscore-Liste oder als Speichermöglichkeit des Spiels genutzt.

# Using the [Website](https://webtech-tictactoe-frontend.herokuapp.com/)
In order to use the Website you first need to login. If you don't have an account already you need to create one. Every account needs an unique Username.

## Home
The home page displays the Information of the currently logged in user and allows to create and join a Game. Both buttons redirect the user to the game page.

## Highscore
On the highscore page the highscores of every user with a highscore greater then zero are displayed.

## Game
If the user created a game he is greeted by the game board. By default the mode is a single player game, indicated by the title. In this case the opponents move are done by an AI. 
The game continues until one player has won or the board is filled. 
After that the player can start a new game.

If the user decided to join a game he has to enter the ID of an game. 
Now the first player (the player who created the game) can do his first move as soon as he sees that the game has changed into a multiplayer game - indicated by the header. 
The player can do their move until one player has won or the board is filled.
After the game is finished both players have to go back to the home page in order to start a new game.

**Note: The multiplayer is rather slow in reaction due to long reload delays to save resources locally and on the server.** 
