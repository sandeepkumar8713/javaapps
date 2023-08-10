# Chess

Youtube : https://www.youtube.com/watch?v=yBsWza2039o

Geeks for Geeks : https://www.geeksforgeeks.org/design-a-chess-game/

Medium : https://medium.com/double-pointer/system-design-interview-a-two-player-online-chess-game-4a150ad8ea78

## Components :
1. Board
2. Piece
3. Box/Spot
4. Player
5. Game 

## Class : 
1. Pieces : Rook, Knight, Pawn, Bishops, Queen, King
2. Spot
3. Game 
4. Person
5. Player
7. Board
7. Move 
8. GameStatus

## Points : 
1. Main class is **Game**. It will have **Players, Board, Moves, GameStatus and CurrentPlayer**.
2. Board will have 64 **spots**. 
3. Each spot will have position and piece.
4. **Piece** can be black or white. It can be alive or killed.
5. **Move** will have start and stop spot, pieceMoved and currentPlayer
6. **makeMove(Move, Player)** method will be implmented by Game class.
7. Each type(child) of piece will have its own **canMove(Board, start, stop)** method implementation.
8. To publish the game after each move, the last entry in moves list can be placed in **Redis with key as Game id**.
9. We can have schedular running every 2-3 times in a sec to fetch the latest move from the Redis and send to user via \
   active **web socket** connection.

## DB Design

      Game : game_id, player_id_1, player_id_2, player_1_color, player_2_color, game_status, current_player_id, game_winner_player_id
      Piece : piece_id, game_id, spot_id, color, isAlive
      Spot : spot_id, game_id, x, y
      Player : player_id, first_name, last_name, active
      Move : game_id, player_id, piece_id, start_spot, end_spot, move_date_time

## APIs 

1. **Create** a game

        POST v1.0/game/
        HEADER : Authorization : Bearer <accessToken>
        Body : 
        {
            self_player : {
               username : "",
               colorChoosen : "Black"
            },
            other_player : {
               username : "",
               colorChoosen : "White"
            },
            startDateTime : "12-09-2019 12:00:00 PM IST"

        } 

        Response:
        {
            game_id : "kjsdakjnm",
        }

2. **Join** a game

         POST v1.0/game/{:id}/join
         Body : 
         {
            self_username: ""

         } 

         Response:
         HTTP/1.1 204 No Content

3. Make a **Move**

         PUT v1.0/game/{:id}/move
         Body : 
         {
            self_username: "",
            piece : Enum.Rook
            start_spot : {
               x : 1,
               y : 5
            }
            end_spot : {
               x : 1,
               y : 4
            }
         } 

         Response:
         {
               move_id : "kjsdakjnm",
         }

4. **Resign** from game

         POST v1.0/game/{:id}/resign
         Body : 
         {
            self_username: "",
         }

         Response:
         HTTP/1.1 204 No Content

5. **Show** game

         GET v1.0/game/{:id}/show

         Response:
         {
            game_id : "kjsdakjnm",
            game_status : enum.ACTIVE,
            player_1_details : {
               id : "playkjwn"
               color : "Black"
            }
            player_2_details : {
               id : "playkjwn"
               color : "Black"
            }
            current_player_id : "playkjwn"
            moves : [
               {
                  move_id : 1,
                  player_id : "player_id",
                  piece : Enum.Rook,
                  start_spot : {
                     x : 1,
                     y : 5
                  }
                  end_spot : {
                     x : 1,
                     y : 4
                  }
                  move_date_time : "12-09-2019 12:00:00 PM IST"
               }
            ]
            current_board : [       # pass a 2D List of 8*8 
               [
                  {
                     spot_id : 1,
                     piece : Enum.Rook,
                     color : "Black"
                     piece_id : 2
                  }
               ]
            ]
         }




