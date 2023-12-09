# Chess
### Nicolas Azzi and Nolan O'Rourke
For our final project, we decided to create a Chess game. It involves all the necessary pieces with their own pictures, a turn tracker on the top, a move history table on the east side of the frame, and a menu on the bottom.
## How To Play
We added an instruction link attached to a button on the bottom right to give the user instructions on how to play. We are using the standard chess rules, with more time we would have added another mode, like Green Chess (pawns only) or maybe all queens. 
The main premise of Chess is that each team starts with the same set of pieces, each kind with its own rules for how to move on the board. The game ends when the king of one player is in a state called "checkmate", when they are locked in place by their opponents pieces OR by a state called "stale mate" when no matter what moves occur, the game will never reach a check mate.


## Panels
# Top Panel
The top panel keeps track of whos turn it currently is within the game. It resets whenever someone calls for a reset or a new game.
If we had more time, we would have also implemented a clock, timing the moves and also keep an overall track of how long the game has been elapsed.

# Center Panel
The center panel is where the main gameboard happens. It includes an 8 x 8 chess board, with alternative colors, with the "lighter" one on the bottom right. The board also maintains intiailiation rules like which spot every piece, including both kings, start on.

# East Panel
The easy panel holds the move history table. This table keeps track of the previous moves made throuhgout the game. The format is as follows: "previous spot - new spot"
To add more features however, we have different character to represent different actions that occur when moving, they are as follows:
* "-" is a regular move
* "x" means took a players piece
* " = __" on the end of the line means an empessant (piece upgrade) took place, with the missing spot being the first character of whatever piece the pawn had turned into.
The move history table resets whenever the player resets, and clears whenever the player starts a new game.

# Bottom Panel
The bottom panel holds the menu. 

## Menu
The Menu is made up of three Jlabels that are meant to feel like buttons, and interact with the user as well. There are three options for the user to choose from. All buttons react when the user hovers over and clicks on them. 
# How To Play
The first option, this button uses a Desktop object browse using the URI link provided by us, to take the user to a site that teaches them how to play chess, on chess.com
# New Game
The second option, this button creates a new game, this erases the move history table and clears the board back to its original state.
# Reset 
The Third Option, similarly to new game, this clears the board, but lets the user see the previously used moves, denoting the reset location

## User Interface
# Chess Board
The chess board allows for drag and drop of pieces, highlighting the different options 
# Move History Table
The Move History table keeps track of all the current moves 
# Menu
The buttons in the menu interact with the user and offer the feeling and acknowledgement of being pushed
# Menu Bar
The menu bar at the top offers additional ways to commit the same changes one would with the menu at the bottom, just more options for whatever the user is used to. 





