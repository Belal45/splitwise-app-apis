
Step-1
  create a database named as "clearexpenses".

Step-2

1-First Api for creating the group 
        uri -> 
        http://localhost:9090/API/createGroup
        request->
        {
            "group_name":"flat-504"
        }
        
2- add the users in any perticular group

          uri -> http://localhost:9090/API/addUsersByGroup
          
          request -> {
              "group_name": "flat-504",
              "users": [
                  {
                      "user_name": "belal",
                      "mobile_number": "8989898765",
                      "address": "hyderabad"
                  },
                  {
                      "user_name": "ahmad",
                      "mobile_number": "6578765453",
                      "address": "noida"
                  },
                  {
                      "user_name": "azmi",
                      "mobile_number": "9876780987",
                      "address": "delhi"
                  }
              ]
          }
          
          
 3-add the expenses by any user in group
              uri->http://localhost:9090/API/addExpensesByUser
              
              request->
              {
                "user_id":7,
                "itemsList":[
                    {
                        "item_name":"rice",
                        "item_price":20
                    },
                     {
                        "item_name":"milk",
                        "item_price":20
                    },
                     {
                        "item_name":"potato",
                        "item_price":50
                    },
                     {
                        "item_name":"tomato",
                        "item_price":40
                    }

                ]

            }
            
4- get details of expenses how much a user need to pay and how much need to receive by their group member
      uri-> http://localhost:9090/API/calculateExpeses/3
      
      Note--here 3 is the group id
          
