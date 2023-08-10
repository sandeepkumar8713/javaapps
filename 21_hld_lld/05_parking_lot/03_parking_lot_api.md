# Parking Lot

## APIs

1. Show **available** spots

        GET v1.0/parkingLot/{:id}/available
        HEADER : Authorization : Bearer <accessToken>

        Response : 
        {
            handicap : 4,
            car : 30,
            truck : 25,
            bike : 50
        } 

2. **Print** ticket

        POST v1.0/parkingLot/{:id}/terminal/{:id}/ticket
        Body : 
        {
            spotType : Enum.Car,
        }

        Response : 
        {
            ticketId : "kjsnd",
            spotId : "kjsnf",
            spotType : Enum.Car,
            entryDateAndTime : "23-09-2021 20:53:56 IST"
        } 

3. **Accept ticket** and calculate fee

        GET v1.0/parkingLot/{:id}/terminal/{:id}/ticket/{:id}/fee

        Response : 
        {
            ticketId : "kjsnd",
            entryDateAndTime : "23-09-2021 20:53:56 IST",
            exitDateAndTime : "23-09-2021 21:45:36 IST",
            fee : 45.00
        } 

4. Make **payment**

        POST v1.0/parkingLot/{:id}/terminal/{:id}/ticket/{:id}/payment
        Body : 
        {   
            billAmount : 45.00,
            PaymentOption : "CreditCard"
        }
        
        Response : 
        HTTP/1.1 302 Found
        Location: https://www.example.org/index.asp

## DBs

    parking_lot: parking_lot_id, address
    spot : spot_id, spot_type, floor_number
    terminal : terminal_id, parking_lot_id, terminal_type, floor_number, status, isOpen
    parking_rate: parking_lot_id, spot_type, minimumRate, perHrRate
    printer: printer_id, terminal_id, status
    POS : pos_id, terminal_id, status
    parking_lot_to_spot : parking_lot_id, spot_id, available
    spot_distance : terminal_id, spot_id, distance

    ticket : ticket_id, entry_terminal_id, exit_terminal_id, spot_id, entryTime, exitTime, fee, payment_id
    payment : payment_id, amount, payment_type, date, status

