# IMDB

Link : https://www.researchgate.net/figure/Complete-schema-of-the-IMDb-database-with-8-main-relations-movie-person-genre_fig1_348079657

## DB Design

    Movie : movie_id, title, genre_id, production_id, thumbnail_link
    movie_details : movie_id, rating, votes, length, brief, Budget, Box office
    Photo : movie_id, photolink
    Genre : genre_id, name
    Language : language_id, name
    pg_rating : pg_rating_id, pg_rating

    company : company_id, name, address, year_of_origin, country_id
    country : country_id, name, capital
    certificate : certificate_id, movie_id, country_id, pg_rating_id, link_to_certificate
    movie_country : movie_id, country_id, language_id, release_date, distribution_id

    Person : person_id, first_name, last_name, DOB, DOD, height, country_id
    cast : movie_id, role, person_id
    crew : movie_id, job, person_id

**13 Tables**
If we assign ids pg rating, in future we can **update** the value without changing other tables.
