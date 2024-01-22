### Persistence in Android

# Exercise 3: Solution

This is our solution for exercise 2, your homework assignment. You can use it as a reference and compare it to your own solution.

## What code was originally provided?

The provided code contained a simple Todo list app that worked as provided but did not yet persist the data. Also the search bar was not implemented.

## What was the assignment?

- Set up the required extensions and dependencies for _Room_ (see the slides for reference)
- Prepare the _Todo Items_ for persistence by creating an _Entity_ and a _Dao_. Make sure to insert your code/files in places that make sense.
- Implement persistence for all 4 CRUD operations (Create, Read, Update, Delete).
  - Persist newly created _Todo Items_
  - Load and display the _Todo Items_ from the database
  - Persist changes to _Todo Items_
  - Delete _Todo Items_ from the database when they are deleted in the app
- Make sure that all changes to the database are reflected in the UI (i.e. update the todo list)
- To get all points: Implement the search functionality by filtering the _Todo Items_ in the database. The specifics are up to you.

## Grading

| Task                                | Points |
| ----------------------------------- | ------ |
| Todo Item Entity                    | 0.5    |
| Todo Item Dao                       | 0.5    |
| Persistence for adding Todo Items   | 1.0    |
| Loading Todo Items from database    | 1.0    |
| Persistence for updating Todo Items | 1.0    |
| Persistence for deleting Todo Items | 1.0    |
| Implement search in database        | 1.0    |
| _Total_                             | 6.0    |

The points for the Todo Item Entity and Dao are awarded even if the persistence is otherwise not implemented or does not work (provided that the code is correct).
