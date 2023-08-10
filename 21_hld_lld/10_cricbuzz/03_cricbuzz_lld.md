# Cricbuzz

## DB Design

    Game : game_id, tournament_id, team_1_id, team_2_id, venue_id, start_date_time, end_date_time, game_status, 
           game_type, winner_team_id, umpire_id_1, umpire_id_2,
    Toss : game_id, toss_won_team_id, decision
    Venue : venue_id, address, capacity
    umpires : umpire_id, first_name, last_name
    playing_11 : team_id, game_id, player_id, batting_position
    team_captain : team_id, game_id, player_id
    Player : player_id, first_name, last_name, DOB
    Player_stats : player_id, game_type, runs, balls, wickets
    
    Ball : ball_id, game_id, from_id, to_id, ball_type, run, wicket, commentary_id, date_and_time, over_number
    Innings : inning_id, game_id, team_id, runs, wicket, overs
    individual_batter : game_id, player_id, runs, balls, fours, sixes, status
    individual_bowler : game_id, player_id, overs, runs, wickets
    Wicket : game_id, team_id, player_out_id, bowled_by_id, caught_by_id, stumpted_by_id, runout_by_id
    Commentary : commentary_id, commentary, commentator_id


## API Design
1. **Add** game (by admin)

        POST v1.0/tournament/{id}/game
        HEADER : Authorization : Bearer <accessToken>
        Body : 
        {
            team_1_id, team_2_id, venue_id, start_date_time, game_status, 
            game_type, umpire_id_1, umpire_id_2
        } 

        Response:
        {
            game_id : "kjsdakjnm",
        }

2. Add **toss** won (by admin)

        POST v1.0/tournament/{id}/game/{id}/toss
        Body : 
        {
            toss_won_team_id : "skdn",
            decision : enum.BAT,
            referee : referee_id
        } 

        Response:
        HTTP/1.1 204 No Content

3. Add **Team** (by admin)

        POST v1.0/tournament/{id}/game/{id}/team
        Body : 
        {
            team_id : 
            playing_11_ids : []
        } 

        Response:
        HTTP/1.1 204 No Content

4. Add **Ball** (by admin)

        POST v1.0/tournament/{id}/game/{id}/innings/{id}/ball
        Body : 
        {
            from_id : 
            to_id : 
            ball_type : 
            run : 
            wicket : 
            commentary : 
            ball_type : 
            over_number : 
        } 

        Response:
        {
            ball_id : "kjsdakjnm",
        }


5. Show live **score**

        GET v1.0/tournament/{id}/game/{id}/score

        Response:
        {   
            team_batting_id : 
            current_inning_id : 
            player_1 : {
                id : 
                runs : 
                balls : 
            },
            player_2 : {
                id : 
                runs : 
                balls : 
            }
            batting_score : {
                1 : {
                    team_id : 
                    runs : 
                    wickets : 
                    overs :
                },
                2 : {
                    team_id : 
                    runs : 
                    wickets : 
                    overs :
                }
            }
            bowler : {
                id: 
                balls:
                runs:
                wicket:
            }
        }

6. Get live **commentary**

        GET v1.0/tournament/{id}/game/{id}/commentary

        Response:
        {  
            inning_id : [
                {
                    time : 
                    over_number : 
                    ball_id : 
                    from_id : 
                    to_id : 
                    run : 
                    wicket : 
                    ball_type : 
                    commentary : 
                },
            ]
        }
