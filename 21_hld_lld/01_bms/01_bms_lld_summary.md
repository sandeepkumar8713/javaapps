# **Book My Show**

## Class components :

1. 3 types of stakeholders : Customer, Theater Partners, Admin. These can extend from user class.
2. User class can have Phone number and Address class.
3. Account class will have user class. 
4. Customer Account class will have Cart. 
5. Cart will have movie ticket. 
6. Ticket class will have movie class. Movie class will have timing and theatre class.
7. Payment will be done for the ticket present in the cart.
8. After payment, receipt will be genetated.

## Notes : 
1. Main classes will have its own ID.
2. userid and password will present in account class.
3. There should be **Seat** class as well, with seat number, seat type and seat price. 1 screen class will have all seats class.
4. **Screen** class can be associated with Theatre class.
4. Ticket details can be idetified using : **TicketId, movieId, theatreId, showId**.
5. Account can be blocked.
6. Each user should have an **Account**. Customer should have UserAccount. Admin should have AdminAccount.
