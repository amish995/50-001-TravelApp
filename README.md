# 50-001-TravelApp
## Our group
- Amish Bhandari
- Elaine Cheong
- Jonathan Bei
- Keong Jo Hsi
- Navnidh Bhalla
- Ruth Wong

## Our application
This application is made with the intention to improve the travelling experience through the implementation of an Itinerary Planner, Tourist Attraction Locator and Tip Calculator. The UI is built to be simple and easy to understand, for quick use during a busy day. The Itinerary Planner optimises your journey based on time and cost, giving you the best route to your desired locations. 
  
## Functionalities
### Overview
Our app makes use of Android's visual structure, allowing users to navigate through our functions through a drawer, accessible from the top left corner. 
  
![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/App%20Drawer.png)
  
  ***
### Attraction locator 
Users can input attractions they wish to visit, and the app will locate it on the in built map. Users can also choose to switch betweeen normal and satellite view. 
  
![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/Attraction%20Locator-1.png)
![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/Attraction%20Locator-2.png)
![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/Attraction%20Locator-3.png)
  
  ***
### Itinerary planner
Planning the route to multiple locations with minimal travel time can be a pain. Our app will help users generate an optimal route that traverses all destinations they hope to visit within their transportation budget.   

Users can select from a drop down menu the attractions they wish to visit and add it to the list. The transportation budget can be keyed in using a number pad. The user can then choose to generate their optimal path using either the brute force method or the fast approximation method. 

![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/Itinerary%20Planner-1.png)
![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/Itinerary%20Planner-2.png)
![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/Itinerary%20Planner-3.png)
![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/Itinerary%20Planner-4.png)
![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/Itinerary%20Planner-5.png)


  
Two methods are employed to do this -- brute force and fast approximation.   

The general structure of this generator can be broken down into the following:   
1. Taking user’s input for the list of attractions and the budget for transportation.  
2. Find the average time (taxi time, public transport time, walking time) required to travel between two points.   
3. Get the shortest route to traverse all attractions, using either brute force or fast approximation.  
4. Find the time optimal transportation method that is within budget.   
5. Return the itinerary and the cost required.   

**Brute Force**   
The brute force approach generates all possible permutations of the provided list of attractions. It generates an time average of the 3 transport methods and picks the best route based on the time average.  

**Fast Approximation**    
The fast approximation approach uses the nearest neighbor algorithm to generate an approximated shortest path. This algorithm takes an arbitrary starting node, then finds the shortest path to the next unvisited node, N. Node N is then marked as visited, and it will find the shortest path to the next unvisited node again. 
After generating this as an arrayList, we iterate through this list, swapping the nodes if the swap reduces travelling time.   
  
**Selection of Transport Modes**    
The mode of transport from node to node will be first calculated by taking the cost average of the three transport modes. Subsequently, we check the cost by setting the transport mode to taxi for all edges. While the total cost exceeds budget, find the next time-cost average shortest edge and check if the walking time between locations is less than 15 minutes. If yes, change the mode to walking. If not, change the transportation method to public transport and check the total cost exceeds budget. If this still exceeds the total budget, then repeat this while loop till we get a total cost within budget.  
   
   ***
### Tip Calculator
Finally, we have implemented a simple tip calculator for the users to easily calculate how much they should tip. The user can input the amount they spent, and a tip level (low, medium or high). The app will output the amount the user should tip.  

![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/Tip1.png) ![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/Tip2.png)
![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/Tip3.png)
  
  ***
###Future Improvements 
The app could be updated to include more of the attractions within Singapore, possibly with a dynamically updated list of attractions that updates in real time. 
