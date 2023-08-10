# Parking lot

Youtube : https://www.youtube.com/watch?v=tVRyb4HaHgw

## Requirements : 
1. Size of parking lot (10k- 20k spots)
2. 4 Entrance and 4 exits
3. Customer collects the ticket while entring and spot is specified in the ticket.
4. Parking spot should be nearset to entrance.
5. Limit/Capacity, Don't assign tickets more than limit
6. Special spots, like handicap, motercycle, large car, compact car 
7. Per hour rate.
8. payment cash/credit card
9. Monitoring system: Count of entry and exit vehicle. video surveillance
10. The system should be extensible to other parking installations as well.

## Design Approach : 
1. Top-down design : first high level design, then components and then sub-components
2. Bottom-up design : We first design the smallest component. Then we use those components to design a bigger component. It is 
   align with Object oriented design.

# Components :
1. Parking lot system
2. Entry / Exit Terminal
    - printer
    - payment process
3. Parking spot
4. Ticket
5. Database
6. Monitoring System

The parking system will be using parking lot class but it will not be using Vehicle class. 

# Classes : 
**Parking spot**(property : id, reserved) : HandicapSpot, compactCarSpot, LargeCarSpot, bikeSpot \
**Parking Ticket** (id, spotId, spotType, Issue time) \
**Terminal** : EntryTerminal(getTicket()), ExitTerminal(accpetTicket(ticket)) \
**ParkingAssignment Stratergy** (getParkingSpot(Terminal), releaseParkingSpot(ParkingSpot)): ParkingspotNearEntranceStratergy \
(We will be using Stratergy design pattern).\
**EntryTerminal** : We can use min heap to find nearset parking spot. We can use 4 min heaps (1 for each entrance) \
We will have a Dict of key : terminals value : min heap.\
2 sets for parking spots : available and reserved \
**PaymentProcess** (process(Amount)): CreditCardPaymentProcess, CashPaymentProcess \
(We will be using Stratergy design pattern). \
**TrarifCaluclator** (calcualate(time, spotType)) \
**Logger** (logMessage()) \

**Parkinglot** (Singleton) : This will include all the other components. \
We can use **factory design** pattern to instantiate those object. We should pass the configuration object(like spot type, \
no. of spots, printer, payment process). \
We should instantiate entry,exit terminal and Parking assignment startergy. \
We should instantiate payment process and pass those to exit terminal object. \
We should instantiate printer and pass those to terminal object.

## Working : 
When the user calls the getParkingSpot(terminal). Based on the terminal id we will retrive min heap for the terminal. \
Pop the top element from the min heap, it will give us the spot which is nearest to the entrance. We will mark it as \
reserved. We will remove it from avilable set and add in reserved set. We will also remove it from all the other min \
heaps as well. \
Concurrency can be handled through locks. 
