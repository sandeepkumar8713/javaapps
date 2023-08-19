## Mongo DB

Link : https://www.mongodb.com/docs/v3.4/reference/operator/aggregation-array/

Playground : https://mongoplayground.net/

**Array Aggregation Operators**

1. **$arrayElemAt** : Returns the element at the specified array index. 
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/arrayElemAt/

``` 
    // Example 1
    { $arrayElemAt: [ [ 1, 2, 3 ], 0 ] } 	1
    { $arrayElemAt: [ [ 1, 2, 3 ], -2 ] } 	2
    { $arrayElemAt: [ [ 1, 2, 3 ], 15 ] } 	 


    // Example 2
    [
        { "_id" : 1, "name" : "dave123", favorites: [ "chocolate", "cake", "butter", "apples" ] },
        { "_id" : 2, "name" : "li", favorites: [ "apples", "pudding", "pie" ] },
        { "_id" : 3, "name" : "ahn", favorites: [ "pears", "pecans", "chocolate", "cherries" ] },
        { "_id" : 4, "name" : "ty", favorites: [ "ice cream" ] }
    ]

    db.users.aggregate([
        {
            $project:
            {
                name: 1,
                first: { $arrayElemAt: [ "$favorites", 0 ] },
                last: { $arrayElemAt: [ "$favorites", -1 ] }
            }
        }
    ])

    // Output
    [
        { "_id" : 1, "name" : "dave123", "first" : "chocolate", "last" : "apples" },
        { "_id" : 2, "name" : "li", "first" : "apples", "last" : "pie" },
        { "_id" : 3, "name" : "ahn", "first" : "pears", "last" : "cherries" },
        { "_id" : 4, "name" : "ty", "first" : "ice cream", "last" : "ice cream" },
    ]

```

2. **$arrayToObject** : Converts an array of key value pairs to a document.
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/arrayToObject/

```
    // Input 
    { $arrayToObject:  { $literal: [
        { "k": "item", "v": "abc123"},
        { "k": "qty", "v": 25 }
    ] } }

        
    // Output 
    { "item" : "abc123", "qty" : 25 }
```


3. **$concatArrays** :	Concatenates arrays to return the concatenated array.
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/concatArrays/

``` 
    // Example 1
    { $concatArrays: [ [ "hello", " "], [ "world" ] ] } 	[ "hello", " ", "world" ]
    { $concatArrays: [ [ "hello", " "], [ [ "world" ], "again"] ] } 	[ "hello", " ", [ "world" ], "again" ]

    // Example 2
    { "_id" : 1, instock: [ "chocolate" ], ordered: [ "butter", "apples" ] }
    { "_id" : 2, instock: [ "apples", "pudding", "pie" ] }
    { "_id" : 3, instock: [ "pears", "pecans"], ordered: [ "cherries" ] }
    { "_id" : 4, instock: [ "ice cream" ], ordered: [ ] }

    db.warehouses.aggregate([
    { $project: { items: { $concatArrays: [ "$instock", "$ordered" ] } } }
    ])

    { "_id" : 1, "items" : [ "chocolate", "butter", "apples" ] }
    { "_id" : 2, "items" : null }
    { "_id" : 3, "items" : [ "pears", "pecans", "cherries" ] }
    { "_id" : 4, "items" : [ "ice cream" ] }
```

4. **$filter** : Selects a subset of the array to return an array with only the elements that match the filter condition.
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/filter/

``` 
    // Example 1 
    {
        $filter: {
            input: [ 1, "a", 2, null, 3.1, NumberLong(4), "5" ],
            as: "num",
            cond: { $and: [
                { $gte: [ "$$num", NumberLong("-9223372036854775807") ] },
                { $lte: [ "$$num", NumberLong("9223372036854775807") ] }
            ] }
        }
    }

    [ 1, 2, 3.1, NumberLong(4) ]



    // Example 2 
    {
    _id: 0,
    items: [
        { item_id: 43, quantity: 2, price: 10 },
        { item_id: 2, quantity: 1, price: 240 }
    ]
    }
    {
    _id: 1,
    items: [
        { item_id: 23, quantity: 3, price: 110 },
        { item_id: 103, quantity: 4, price: 5 },
        { item_id: 38, quantity: 1, price: 300 }
    ]
    }
    {
        _id: 2,
        items: [
        { item_id: 4, quantity: 1, price: 23 }
        ]
    }

    db.sales.aggregate([
    {
        $project: {
            items: {
                $filter: {
                input: "$items",
                as: "item",
                cond: { $gte: [ "$$item.price", 100 ] }
                }
            }
        }
    }
    ])

    {
        "_id" : 0,
        "items" : [
            { "item_id" : 2, "quantity" : 1, "price" : 240 }
        ]
    }
    {
        "_id" : 1,
        "items" : [
            { "item_id" : 23, "quantity" : 3, "price" : 110 },
            { "item_id" : 38, "quantity" : 1, "price" : 300 }
        ]
    }
    { "_id" : 2, "items" : [ ] 
    }
```
5. **$in** : Returns a boolean indicating whether a specified value is in an array.
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/in/

``` 
    // Example 1
    { $in: [ 2, [ 1, 2, 3 ] ] } 	true
    { $in: [ "abc", [ "xyz", "abc" ] ] } 	true
    { $in: [ "xy", [ "xyz", "abc" ] ] } 	false
    { $in: [ [ "a" ], [ "a" ] ] } 	false

    // Example 2
    { "_id" : 1, "location" : "24th Street",
    "in_stock" : [ "apples", "oranges", "bananas" ] }
    { "_id" : 2, "location" : "36th Street",
    "in_stock" : [ "bananas", "pears", "grapes" ] }
    { "_id" : 3, "location" : "82nd Street",
    "in_stock" : [ "cantaloupes", "watermelons", "apples" ] }

    db.fruit.aggregate([
    {
        $project: {
        "store location" : "$location",
        "has bananas" : {
            $in: [ "bananas", "$in_stock" ]
        }
        }
    }
    ])

    { "_id" : 1, "store location" : "24th Street", "has bananas" : true }
    { "_id" : 2, "store location" : "36th Street", "has bananas" : true }
    { "_id" : 3, "store location" : "82nd Street", "has bananas" : false }


```

6. **$indexOfArray** : Searches an array for an occurence of a specified value and returns the array index of the first occurence. If the substring is not found, returns -1.
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/indexOfArray/

```
    { $indexOfArray: [ [ "a", "abc" ], "a" ] } 	0
    { $indexOfArray: [ [ "a", "abc", "de", ["de"] ], ["de"] ] } 	3
    { $indexOfArray: [ [ 1, 2 ], 5 ] } 	-1
    { $indexOfArray: [ [ 1, 2, 3 ], [1, 2] ] } 	-1
    { $indexOfArray: [ [ 10, 9, 9, 8, 9 ], 9, 3 ] } 	4
    { $indexOfArray: [ [ "a", "abc", "b" ], "b", 0, 1 ] } 	-1
    { $indexOfArray: [ [ "a", "abc", "b" ], "b", 1, 0 ] } 	-1
    { $indexOfArray: [ [ "a", "abc", "b" ], "b", 20 ] } 	-1
    { $indexOfArray: [ [ null, null, null ], null ] } 	0
    { $indexOfArray: [ null, "foo" ] } 	null
    { $indexOfArray: [ "foo", "foo" ] } 	Error
```

7. **$isArray** : Determines if the operand is an array. Returns a boolean.
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/isArray/

``` 
    // Example 1
    { $isArray: [ "hello" ] } 	false
    { $isArray: [ [ "hello", "world" ] ] } 	true

    // Example 2
    { "_id" : 1, instock: [ "chocolate" ], ordered: [ "butter", "apples" ] }
    { "_id" : 2, instock: [ "apples", "pudding", "pie" ] }
    { "_id" : 3, instock: [ "pears", "pecans"], ordered: [ "cherries" ] }
    { "_id" : 4, instock: [ "ice cream" ], ordered: [ ] }

    db.warehouses.aggregate([
    { $project:
      { items:
          { $cond:
            {
              if: { $and: [ { $isArray: "$instock" }, { $isArray: "$ordered" } ] },
              then: { $concatArrays: [ "$instock", "$ordered" ] },
              else: "One or more fields is not an array."
            }
          }
      }
    }
    ])

    { "_id" : 1, "items" : [ "chocolate", "butter", "apples" ] }
    { "_id" : 2, "items" : "One or more fields is not an array." }
    { "_id" : 3, "items" : [ "pears", "pecans", "cherries" ] }
    { "_id" : 4, "items" : [ "ice cream" ] }
```

