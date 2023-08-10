# Flipkart 

## Class Components : 
1. 4 types of stakeholders : **Customer, Supplier, DeliveryMan, Admin**. These can extend from user class.
2. Customer class has account class.
3. Account class have shopping cart. 
4. Shopping cart have multiple Line Items class.
5. Each Line Item object will have object product class. 
6. Order can be placed by Account class for the line items. 
7. Payment needs to be done for the order.
8. We have **enum** for specify the state of the account and state of the order.

## Notes : 
1. Once order has arrived at the Hub, **deliveryMan** should be assigned to it and be notified.
2. Product should have supplier id and count of stocks.
3. Order can be **cancelled/returned/not delivered**.
4. Account can have multiple orders/payments. 
