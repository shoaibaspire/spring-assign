# spring-assign
1. Hit the localhost:9898/txn/initializeData rest end point to load the data of parent and child into the H2 database
2. Hit the url: localhost:9898/txn?page=0&size=2&sort=id&dir=asc to get details on the paginated parent data. the payload would look as following:
   {
    "_embedded": {
        "parentList": [
            {
                "id": 1,
                "sender": "XYZ",
                "receiver": "XYZ",
                "totalAmount": 200,
                "totalPaidAmount": 100,
                "_links": {
                    "self": [
                        {
                            "href": "http://localhost:9898/txn/1"
                        },
                        {
                            "href": "http://localhost:9898/txn/childRec/1"
                        }
                    ]
                }
            },
            {
                "id": 2,
                "sender": "MNP",
                "receiver": "MNP",
                "totalAmount": 100,
                "totalPaidAmount": 100,
                "_links": {
                    "self": [
                        {
                            "href": "http://localhost:9898/txn/2"
                        },
                        {
                            "href": "http://localhost:9898/txn/childRec/2"
                        }
                    ]
                }
            }
        ]
    },
    "_links": {
        "first": {
            "href": "http://localhost:9898/txn/?dir=asc&page=0&size=2&sort=id,asc"
        },
        "self": {
            "href": "http://localhost:9898/txn/?dir=asc&page=0&size=2&sort=id,asc"
        },
        "next": {
            "href": "http://localhost:9898/txn/?dir=asc&page=1&size=2&sort=id,asc"
        },
        "last": {
            "href": "http://localhost:9898/txn/?dir=asc&page=6&size=2&sort=id,asc"
        }
    },
    "page": {
        "size": 2,
        "totalElements": 14,
        "totalPages": 7,
        "number": 0
    }
}

3. There are couple of things that can be observed from above response. The "first", "self", "next","last" tags are actually meant to imply that we have paginated and sorted data configured. Clicking on these links will open new GET requests which will server the page and size purposes.
4. Another thing to note is in the ParentList tag, for each of the parent mentioned above, we have shared 2 links, one is for parent self data and second is relevant to click on totalAmount requirement. Where in, we are passing out parent id and showcasing data corresponding in child records.
5. Sample for second requirement. Click on HATEOS link for first parent :http://localhost:9898/txn/childRec/1

[
    {
        "id": 8,
        "sender": "ABC",
        "receiver": "XYZ",
        "totalAmount": 200,
        "parentId": 1,
        "paidAmount": 10
    },
    {
        "id": 9,
        "sender": "ABC",
        "receiver": "XYZ",
        "totalAmount": 200,
        "parentId": 1,
        "paidAmount": 50
    },
    {
        "id": 10,
        "sender": "ABC",
        "receiver": "XYZ",
        "totalAmount": 200,
        "parentId": 1,
        "paidAmount": 40
    }
]

Note: The first step of initilzation can be done only once to load the database. Re running multiple times, may cause other error. Code can be enhances further to ensure we run this script only once, dependening on requirement later.
