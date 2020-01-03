# Project-OOP
Project OOP - HK 1
## Road Map
 ![alt text](https://github.com/dominhhoang4299/Diagrams/blob/master/Roadmap.png)

## Milestone 1 (master branch) - Completed On: Monday, October 29th, 2019
Implement a text-based version of the popular Plants vs Zombies (PvZ) game.
# Implementations
# PLANT - 
## Plants have two subclasses: PassivePlant and ShooterPlant

# Sunflower; subclass of PassivePlant

    *Generates 50 sun every four turns
    *Cost = 50 sun
    *Hit Threshold = 5 damage.
    *Peashooter; subclass of ShooterPlant

# Shoots peas at incoming Zombies
    *Cost = 100 sun
    *Pea Damage = 2 damage per turn
    *Hit Threshold = 10.
    *ZOMBIES - Ryan Tordesillas
    *Zombies have one subclass so far; BasicZombie

# BasicZombie:

    *Speed = Basic Zombie Speed (0.5 tiles per turn/1 tile every two turns)
    *Zombie Damage = Basic Zombie Damage (1 damage per turn)
    *Hit Threshold = Basic Zombie Hit Threshold (10).
 
##### LAWN:
  - Keeps track of it's respective plants, zombies and it's lawnmower
  - Has a plants arrayList, zombie arrayList, lawnMower boolean
#### UML/Sequence Diagrams - MINH HOANG
##### UML 
![alt text](https://github.com/dominhhoang4299/Project-OOP/blob/master/Diagram.png) 

##### Sequence Diagram - Plant Class
![alt text](https://github.com/dominhhoang4299/Diagrams/blob/master/PlantClass.PNG) 

##### Sequence Diagram - Zombie Class
![alt text](https://github.com/dominhhoang4299/Diagrams/blob/master/ZombieClass.PNG) 

## Milestone 2 (master branch) - Completed On: Friday, November 16th, 2019

### Implementations 

#### GUI - 
* GUI Classes Implemented:
  * GameGUI -> Creates a new GUI with 9 by 5 grid (Lawn). It is able to populate board with zombies automatically every turn and the                    user can place either a sunflower or peashooter plant. It has 4 main functions place plant, remove plant, update state of                the board with each turn, and clearing the board when finished with the game.
  
  * ShovelSelectController -> Shovel Selector has a mouse listener and only has one main function to remove existing plant type from the                                board.
  
  * SkipTurnListener -> Button that is able to skip or move the game one turn forward.
 
  * SunflowerSelectController -> Using mouse listener the user is able to select a sunflower from the menu and place it to the main                                       board (lawn).
  
  * TileController -> Has Mouse listener, and anytime the user places a plant it will update the board with the updated plant position.

#### JUnit Tests
  ##### Plants 
     * Test Classes Include: 
  - PlantTest CLASS -> testPlant(), testPlantCost(), testPlantHit(), testSetPlantHit(), testXPos(), testYPos()
  - PeashooterTest CLASS -> testPeashooterPosition(), testPeashooterHealth(), testPeashooterBuyThreshold()
  - SunflowerTest CLASS -> testSunfloerPosition()

  ##### Zombies
    * Test Classes Include: 
  - BasicZombieTest CLASS -> testZombieSpeed(), testZombieAttack(), testZombieType(), testZombieMovement(), testZombieIsNotDead(), testZombieIsDead()
  #### Model View COntroller- Diagram
 ![alt text](https://github.com/dominhhoang4299/Project-OOP/blob/master/MVC-sequence.png)
  
## Milestone 3 (master branch) - Completed On: Sunday, November 25th, 2019

    Implementations

#### PLANTS - 
Added new Plants : FreezePeaShooter 
type to the Plants vs. Zombies GUI:

* Snow Pea Shooter : subclass of Shooter Plant Type
  - Shoots snow/frozen peas that damage and slow the zombies.
  - Cost: 175
  - Hit Threshold: 10
  - Attack: 3
  
  
#### GUI 
* GUI Classes Implemented 
  * ImagePanel -> Used for drawing the lawn tiles on the grid panel. 
  
  * Wall-nut, Snow Pea, Potato Mine, Select Controller -> Contains a mouse listener for when the user wants to plant       either of the above plants during when the game is running.
  
#### JUnit Test Cases
* Refined old test cases
