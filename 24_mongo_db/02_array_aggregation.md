## Mongo DB

Link : https://www.mongodb.com/docs/v3.4/reference/operator/aggregation-array/

**Array Aggregation Operators**

1. **$map** : Applies a subexpression to each element of an array and returns the array of resulting values 
in order. Accepts named parameters.
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/map/

``` 
    // Exmaple 1
    { _id: 1, quizzes: [ 5, 6, 7 ] }
    { _id: 2, quizzes: [ ] }
    { _id: 3, quizzes: [ 3, 8, 9 ] }

    db.grades.aggregate(
    [
        { $project:
            { adjustedGrades:
                {
                $map:
                    {
                    input: "$quizzes",
                    as: "grade",
                    in: { $add: [ "$$grade", 2 ] }
                    }
                }
            }
        }
    ]
    )

    { "_id" : 1, "adjustedGrades" : [ 7, 8, 9 ] }
    { "_id" : 2, "adjustedGrades" : [ ] }
    { "_id" : 3, "adjustedGrades" : [ 5, 10, 11 ] }

    // Exmaple 2
    db.deliveries.aggregate(
    [
        { $project:
            {  city: "$city",
                integerValues:
                { $map:
                    {
                        input: "$distances",
                        as: "integerValue",
                        in: { $trunc: "$$integerValue" }
                    }
                }
            }
        }
    ]
    )
```

2. **$objectToArray** : Converts a document to an array of documents representing key-value pairs.
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/objectToArray/

```
    // Example 1
    { $objectToArray: { item: "foo", qty: 25 } }

    [
    {
        "k" : "item",
        "v" : "foo"
    },
    {
        "k" : "qty",
        "v" : 25
    }
    ]

    // Example 2
    { $objectToArray: {
        item: "foo",
        qty: 25,
        size: { len: 25, w: 10, uom: "cm" }
    } }

    [
        {
            "k" : "item",
            "v" : "foo"
        },
        {
            "k" : "qty",
            "v" : 25
        },
        {
            "k" : "size",
            "v" : {
                "len" : 25,
                "w" : 10,
                "uom" : "cm"
            }
        }
    ]

```

3. **$range** : Outputs an array containing a sequence of integers according to user-defined inputs.
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/range/

```
    { $range: [ 0, 10, 2 ] } 	[ 0, 2, 4, 6, 8 ]
    { $range: [ 10, 0, -2 ] } 	[ 10, 8, 6, 4, 2 ]
    { $range: [ 0, 10, -2 ] } 	[ ]
    { $range: [ 0, 5 ] } 	[ 0, 1, 2, 3, 4 ]

```

4. **$reduce** : Applies an expression to each element in an array and combines them into a single value.
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/reduce/

```
    // Example 1
    {
    $reduce: {
        input: ["a", "b", "c"],
        initialValue: "",
        in: { $concat : ["$$value", "$$this"] }
    }
    }
    "abc"

    // Example 2
    {
    $reduce: {
      input: [ 1, 2, 3, 4 ],
      initialValue: { sum: 5, product: 2 },
      in: {
         sum: { $add : ["$$value.sum", "$$this"] },
         product: { $multiply: [ "$$value.product", "$$this" ] }
        }
        }
    }
    { "sum" : 15, "product" : 48 }

    // Example 3
    {
    $reduce: {
        input: [ [ 3, 4 ], [ 5, 6 ] ],
        initialValue: [ 1, 2 ],
        in: { $concatArrays : ["$$value", "$$this"] }
    }
    }
    [ 1, 2, 3, 4, 5, 6 ]
```

5. **$reverseArray** : Returns an array with the elements in reverse order.
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/reverseArray/

```
    { $reverseArray: { $literal: [ 1, 2, 3 ] } } 	[ 3, 2, 1 ]
    { $reverseArray: { $slice:  [ [ "foo", "bar", "baz", "qux" ], 1, 2 ] } } } 	[ "baz", "bar" ]
    { $reverseArray: null } 	null
    { $reverseArray: { $literal: [ ] } } 	[ ]
```

6. **$size** : Returns the number of elements in the array. Accepts a single expression as argument.
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/size/

```
    { "_id" : 1, "item" : "ABC1", "description" : "product 1", colors: [ "blue", "black", "red" ] }
    { "_id" : 2, "item" : "ABC2", "description" : "product 2", colors: [ "purple" ] }
    { "_id" : 3, "item" : "XYZ1", "description" : "product 3", colors: [ ] }
    { "_id" : 4, "item" : "ZZZ1", "description" : "product 4 - missing colors" }
    { "_id" : 5, "item" : "ZZZ2", "description" : "product 5 - colors is string", colors: "blue,red" }

    db.inventory.aggregate([
    {
      $project: {
         item: 1,
         numberOfColors: { $cond: { if: { $isArray: "$colors" }, then: { $size: "$colors" }, else: "NA"} }
      }
     }
    ])

    { "_id" : 1, "item" : "ABC1", "numberOfColors" : 3 }
    { "_id" : 2, "item" : "ABC2", "numberOfColors" : 1 }
    { "_id" : 3, "item" : "XYZ1", "numberOfColors" : 0 }
    { "_id" : 4, "item" : "ZZZ1", "numberOfColors" : "NA" }
    { "_id" : 5, "item" : "ZZZ2", "numberOfColors" : "NA" }

```

7. **$slice** :	Returns a subset of an array.
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/slice/

```
    { $slice: [ [ 1, 2, 3 ], 1, 1 ] } 	[ 2 ]
    { $slice: [ [ 1, 2, 3 ], -2 ] } 	[ 2, 3 ]
    { $slice: [ [ 1, 2, 3 ], 15, 2 ] } 	[  ]
    { $slice: [ [ 1, 2, 3 ], -15, 2 ] } 	[ 1, 2 ]
```

8. **$zip** : Transposes an array of input arrays so that the first element of the output array would be an array 
containing, the first element of the first input array, the first element of the second input array, etc.
https://www.mongodb.com/docs/v3.4/reference/operator/aggregation/zip/


```
    { $zip: { inputs: [ [ "a" ], [ "b" ], [ "c" ] ] } 	[ [ "a", "b", "c" ] ]
    
    { $zip: { inputs: [ [ "a" ], [ "b", "c" ] ] } } 	[ [ "a", "b" ] ]

    { $zip: {
            inputs: [ [ 1 ], [ 2, 3 ] ],
            useLongestLength: true
        }
    }
    [ [ 1, 2 ], [ null, 3 ] ]

    {
        $zip: {
            inputs: [ [ 1 ], [ 2, 3 ], [ 4 ] ],
            useLongestLength: true,
            defaults: [ "a", "b", "c" ]
        }
    }
    [ [ 1, 2, 4 ], [ "a", 3, "c" ] ]
```
