# Four Sqaure / Yelp

Link : https://gorkemgenccom.wordpress.com/2018/03/03/scalable-system-design-like-yelp/

## Functional Requirements
1. Users can register the system.
2. Users can login or log out the system.
3. Users can search a place such as a restaurant, theatre or etc..
4. Users can comment a place.
5. Users can like or dislike a place.
6. Users can review a place.
7. Users can add a place.
8. Users can add pictures to added places.
9. Users can update or delete a place if they added a place themselves.

## APIs

1. **Add** a place

        POST v1.0/place/
        HEADER : Authorization : Bearer <accessToken>
        Body : 
        {
            name_of_place : "xyz"
            description_of_place : "lorem ipsum", 
            category : "Resturant"
            latitude : 82.67,
            longitude: 57.90,
            address : {
                houseNo : 13,
                streetName : "lkwj",
                city : "ksjf"
                state : "Bihar"
                zipcode : 862701
            }
            photos : [
                'link1',
                'link2'
            ]
        } 

        Response:
        {
            place_id : "kjsdakjnm"
        }

2.  **Update** the place

        PUT v1.0/place/{:id}
        HEADER : Authorization : Bearer <accessToken>
        Body : 
        {
            name_of_place : "xyz"
            description_of_place : "lorem ipsum", 
            category : "Resturant"
            latitude : 82.67,
            longitude: 57.90,
            address : {
                houseNo : 13,
                streetName : "lkwj",
                city : "ksjf"
                state : "Bihar"
                zipcode : 862701
            }
            photos : [
                'link1',
                'link2'
            ]
        } 

        Response:
        HTTP/1.1 204 No Content

3. **Delete** the place

        DELETE v1.0/place/{:id}
        HEADER : Authorization : Bearer <accessToken>

        Response:
        HTTP/1.1 204 No Content

4. Add **review**

        POST v1.0/place/{:id}/review
        HEADER : Authorization : Bearer <accessToken>
        Body : 
        {
            user_id : "234uewik",
            like : True,
            rating : 4.5
            comment : "Lorem ipsum"
        }

        Response:
        {
            review_id : "gqhefj"
        }

5. **Search** a place based on current location for given category or name of place

        POST v1.0/search
        HEADER : Authorization : Bearer <accessToken>
        Body : 
        {
            category : "Resturant"
            latitude : 82.67,
            longitude: 57.90,
            radius : 12,
            name_of_place : "kj"
        }

        Response:
        {
            places : [
                {
                    place_id : "kjanf"
                    name_of_place : "xyz"
                    description_of_place : "lorem ipsum", 
                    rating : 3.0
                    photos : [
                        'link1',
                        'link2'
                    ],
                    Address : {}
                }
            ]
        }


## DB Design

    Place : place_id, user_id, name, latitude, longitude, Category, Description, AddedDate, rating, like_count, dislike_count
    Review : review_id, place_id, user_id, like, rating, commment, date
    Photo : photo_id, place_id, photo_link, user_id
    User : user_id, username, registerion_date, last_login_date, first_name, last_name, email, phone

