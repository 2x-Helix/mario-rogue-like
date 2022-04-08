# FIT2099 Assignment 1: Design Rationale

## Lab 6 Team 3

<br>

## REQ1: Trees

The tree stages were implemented as 3 subclasses that inherit from the abstract class **Tree**. 
Since all tree stages are expected to grow to a new stage after a set amount of turns, 
The number of turns required to grow should be stored as an instance variable as part of **Tree**.
Once the requisite amount of time to grow has passed, a new object of the next tree stage may be returned.
Once 5 turns has passed to grow for the Mature stage, the growth timer may be reset back to 5 to
for it's to regrow a new sprout. 

### Pros

* Safeguarding implementation of grow() and growTurns attribute.
* Able to identify all growth stages as a Tree class.

### Cons

* Additional code required to implement abstract class.
* Increased code size.


As sprouts require fertile land to grow, we have chosen to give **Ground** objects that are fertile
the capability **FERTILE** in the enumeration **GroundTraits**. 
This is as future **Ground** tiles may also be fertile tiles, requiring a unified identifier for all such tiles.
This allows us to follow the *"Don't repeat yourself"* design principle through use of public constants.
**Ground** already features a **CapabilitySet** which we can use to store the enumeration value.

<br>

## REQ2: Jump

**JumpActorAction** extends **MoveActorAction**:
We want **Player** to have an action to move to another location in the **GameMap**, the **MoveActorAction** has the methods to enable **Player** to have this ability.

**JumpManager** is associated with **(interface) Jumpable**:
We want **JumpManager** to store the instances of **Jumpable** grounds, so it can be assessed by JumpActorAction and decide whether player can jump to new location or not.

**(abstract) Tree** and **Wall** implements **(interface) Jumpable**:
We want only **Tree** and **Wall** grounds to be jumpable but not others, such as **Dirt**. Hence, we use interface.

### Pros
This follows the SOLID principle *"the Dependency Inversion Principle"* allowing for other objects (i.e. **JumpableManager**) to depend on the abstraction **Jumpable** 
rather than the **Ground** classes that are jumpable.

### Cons
* Use of abstraction - slower time complexity and more use of resources (less efficient).

<br>

## REQ3: Enemies

**Goomba** and **Koopa** both extends the abstract class **Enemy**, and **Player** extends the abstract class **Friendly**.
As we know enemies have different methods compared to friendlies, e.g enemies are able to wander and follow the player but player can’t. Indeed, both enemy and friendly extends actor.

**AttackBehaviour**, **WanderBehaviour** and **FollowBehaviour** all implement **(interface) Behaviour**, so can act these behaviours without
the player's input.

**Player** holds **Wrench**
If **Player** is holding **Wrench** in the inventory, then **HitAction** can be called to hit **Koopa**. On the other hand, **Goomba** is able to use **KickAction** on **Player** if it is close enough to **Player** via the **GameMap** and **Location**.

### Pros
* Easier to understand and follow, e.g. an **Action** is used on the object being attacked
* Separating Actors into either enemies or friendlies (abstract classes) allows simple, clear divide between two Actors with different methods.

### Cons
* More space and time complexity is required for abstraction

<br>

## REQ4: Magical Items

A new abstract class **MagicalItem**, which is inherited from the base class **Item**,  has been added. **PowerStar** and **SuperMushroom** are implemented as inheriting **MagicalItem**. This is done to differentiate 2 types of **Item**: item that can be equipped as a weapon - **WeaponItem**; item that is not a weapon - **MagicalItem**. **MagicalItem** is consumable, which provides status to the **Actor**.

### Pros

* More defined purpose of items
* Avoid the need of implementing 2 interfaces
* Future-proof for adding new features, like an inventory system

### Cons

* Increased code sized
* Potentially harder to debug

<br>

## REQ5: Trading

Two new classes, **Toad** and **Coin**, are added in this section. **Toad** serves as the item shop in this game, and **Coin** is the currency for buying items from **Toad**. **Coin** is inherited from **Item** as, like all items, coins can be picked up from the ground. However, it is not inherited from either **WeaponItem** or **MagicalItem**, as it can not be equiped as a weapon or consume to gain status. **Coin** should have 1 Integer attribute to represent its value and 1 static String attribute to represent its visual to be displayed. **Player** should also have one **Coin** class attribute.

### Pros

* One class to represent how many coins a player have/how much an item costs and coins on the ground

### Cons

* Not as simple as each item having an Integer attribute representing its cost/player having an Integer attribute representing how much the player have.

<br>

## REQ6: Monologue

**GameMap** and **Location** keep track of where **Toad** and **Player** are. If close enough, **SpeakAction** is available as an option: grabs monologue from **Toad**, checks if **Player** contains **Wrench**, **Status** of a PowerStar or else the **CapabilitySet** of the Actor e.g cannot be Goomba and decides what to say. Uses **Display** to print monologue string.

**Player** holds **(abstract) WeaponItem**, and is extended by **Wrench**.
Use of **(abstract) WeaponItem** from engine, ease of implementation of new weapons in the future. **(enum) Status** able to contain the different statuses for **Player**

### Pros

* Choice to utilise enumeration will avoid the excessive use of literals, and hence improve maintainability and extensibility of the code in the long-term.

### Cons

* **Toad** isn't directly responsible for calling **SpeakAction**, but rather relies on **GameMap** and **Location**, e.g, **Player** must close enough in coordinates to **Toad** for **SpeakAction** to be available in console (could be a possible way for Toad to directly call **SpeakAction**).

<br>

## REQ7: Reset Game

A **Resettable** interface and a **ResetManager** class are created. All classes that can be reset, like **Player** and **Enemy**, implments this interface. And **ResetManager** manages the reset process.

### Pros

* Easier to debug this features as all the reset method are implemented in this class

### Cons

* Increased code size and complexity
  