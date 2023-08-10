import java.sql.Date;
import java.sql.Time;
import java.util.List;

enum SeatStatus {
    SEAT_BOOKED,
    SEAT_NOT_BOOKED;
}
  
enum MovieStatus {
    Movie_Available,
    Movie_NotAvailable;
}
  
enum MovieType {
    ENGLISH,
    HINDI;
}
  
enum SeatType {
    NORMAL,
    EXECUTIVE,
    PREMIUM,
    VIP;
}
  
enum PaymentStatus {
    PAID,
    UNPAID;
}
  
class User {
  
    int userId;
    String name;
    Date dateOfBirth;
    String mobNo;
    String emailId;
    String sex;
}
  
class Movie {
  
    int movieId;
    int theaterId;
    MovieType movieType;
    MovieStatus movieStatus;
}
  
class Theater {
  
    int theaterId;
    String theaterName;
    Address address;
  
    List<Movie> movies;
    float rating;
}
  
class Booking {
    int bookingId;
    int userId;
    int movieId;
    List<Movie> bookedSeats;
    int amount;
    PaymentStatus status_of_payment;
    Date booked_date;
    Time movie_timing;
}
  
class Address {
  
    String city;
    String pinCode;
    String state;
    String streetNo;
    String landmark;
}

// class SeatBook {
// 	Transaction transaction_obj;
// 	bool seats[total_seats];
// 	String place;
// 	String ticketType;

// 	bool check_availability();

// 	int position_of_seat()
// 	{
// 		return seat_pos_in_theater;
// 	}

// 	void multiple tickets();

// 	void final_booking()
// 	{
// 		place = positon_of_seat();
// 		if (single_ticket)
// 			continue;
// 		else
// 			mutliple_ticket_booking();

// 		Transaction_obj.pay(ticketType, seats_booked, place);
// 	}
// }

