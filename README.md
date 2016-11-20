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
  
### Attraction locator 
Users can input attractions they wish to visit, and the app will locate it on the in built map. Users can also choose to switch betweeen normal and satellite view. 
  
![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/Attraction%20Locator-1.png)
![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/Attraction%20Locator-2.png)
![overview](https://github.com/amish995/50-001-TravelApp/blob/master/screenshots/Attraction%20Locator-3.png)

### Itinerary planner
Planning the route to multiple locations with minimal travel time can be a pain. Our app will help users generate an optimal route that traverses all destinations they hope to visit within their transportation budget. 

Two methods are employed to do this -- brute force and fast approximation. 

The general structure of this generator can be broken down into the following: 
1. Taking user’s input for the list of attractions and the budget for transportation.  
2. Find the average time (taxi time, public transport time, walking time) required to travel between two points.   
3. Get the shortest route to traverse all attractions, using either brute force or fast approximation.  
4. Find the time optimal transportation method that is within budget.   
5. Return the itinerary and the cost required.   




