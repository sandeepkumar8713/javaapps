# Netflix 

Link : https://arxiv.org/ftp/arxiv/papers/2106/2106.00932.pdf (Class and DB design)

## DB Design

    Movie : movie_id, title, genre_id, production_id, release_id
    Movie_details : movie_id, rating, length, brief, Budget, Box office, pg_rating_id
    Photo : movie_id, photolink
    Genre : genre_id, name
    Language : language_id, name

    company : company_id, name, address, year_of_origin, country_id
    country : country_id, name, capital
    pg_rating : pg_rating_id, pg_rating

    Person : person_id, first_name, last_name, DOB, DOD, height, country_id
    cast : movie_id, role, person_id
    crew : movie_id, job, person_id

    Series : series_id, title, genre_id, production_id
    show : show_id, series_id, season, episode (Show id can be treated as movie id)

    Subtitile : movie_id, language, file_link
    Audio : movie_id, language, file_link
    Resolution : movie_id, resolution_id, resolution

    subscription : subscription_id, type
    subscription_to_movie : movie_id, subscription_id
