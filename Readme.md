# Plants Vs Zombies
**Created by:
[Himanshu Raj](https://www.github.com/himanshuraj18) and [Pankil Kalra](https://www.github.com/pankilkalra)**

A clone of Plants vs Zombies made from scratch using JavaFX. Applied OOPs concept and suitable Design Patterns.
Made as a part of project in Advanced Programming course at IIIT-Delhi.

### Features-
1. 5 levels with increasing difficulty.
2. x types of different Plants to protect your arena.
3. Restart, Load and Save game feature.
4. Multiple User Support.

### Tools and Technologies Used-
1. JavaFX
2. FXML Scene Builder
3. Intellij IDE
4. Lucidchart for UML Diagram

### Design Patterns Used-
1. Model View Controller
   - Model : All classes and implementation of different functionality.
   - View  : FXML (for GUI) files
   - Controller : It uses model to make changes in view (or GUI).
2. Iterator to ensure that Plant, Zombie and Lawnmower lists are accessed in a synchronized way.
3. Facade for menu based implementation.
4. Singleton for the database and for progress bar feature, as we need only one reference through out the game.

### Screenshots of Game
![alt_text](https://github.com/himanshuraj18/PlantsVsZombies/blob/master/Screenshots/final-1.png)
![alt_text](https://github.com/himanshuraj18/PlantsVsZombies/blob/master/Screenshots/final-2.png)
![alt_text](https://github.com/himanshuraj18/PlantsVsZombies/blob/master/Screenshots/final-3.png)
![alt_text](https://github.com/himanshuraj18/PlantsVsZombies/blob/master/Screenshots/final-4.png)

**Note -** Delete all the .__ files(if any) present in src/sample directory before running this project.