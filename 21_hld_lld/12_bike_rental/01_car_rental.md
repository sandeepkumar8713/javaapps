# Car Rental

https://www.youtube.com/watch?v=AXa6jCfziRY

## Functional Requirement 
1. User should be able to create an account in our application.
2. Enter to location from where user wants to book the car.
3. List down all the cars based on given location
4. User should be able to select the car from that list.
5. User should be able to fill up the pick up and drop location.
6. Payment gateway
7. Send confirmation mail to user

## Non functional Requirement
1. Scalablity
2. Maintianbilty 
3. Modular enough 

Actors : Customers and operator

Entity : User, Car, Reservation, payment, Notification server

```
    Class Car{
        String name;
        String brand;
        int bookingPrice;
        float kmDriven;
        String RegistrationNumber;
        Cartype Type (SUV, Sedan, Hatchback);
        CarStatus Status; (Booked, Available, Non-operational, Running)
        Location loc;

        reserveVechical();
        updateCarDetails();
    }

    class Location {
        String name;
        float lat;
        float long;
        int zipcode;
    }

    class User {
        String name;
        String email;
        String LicenceNumber;
        String phone;
    }

    class CarReservationSystem{
        List<User> user;
        Hashmap<Location, Car> allCars;

        getCarByLocation(Location);
        GetCarByType(Cartype);
        bookingCar(Reserve);
        confirmationMail(Reserve);
    }

    class Reserve{
        User user;
        Car car;
        Location Pickup;
        Location Drop;
        float rentalPrice;
        Date bookingDate;
    }

    // inhereted from person class
    class Operator{
        NotifiedAboutReservation();
    }
```

