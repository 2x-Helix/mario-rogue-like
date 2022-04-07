# FIT2099 Assignment 1: Design Rationale

## Lab 6 Team 3

<br>

## REQ1: Tress

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

JumpActorAction extends MoveActorAction:
As we want the player to have an action to move to another location in the gamemap, the MoveActorAction has the necessary methods to enable player to move to another location when called.

JumpManager is associated with <<interface>> Jumpable:
We want jumpmanager to store the instances of jumpable grounds, so it can be assessed by JumpActorAction and decide whether player can jump to new location or not.

<<abstract>> tree and wall implements the <<interface>> Jumpable:
We want only specific grounds to be jumpable but not others, such as dirt. So we will use an interface, that will be implemented by trees and the wall.
This follows the SOLID principle *"the Dependency Inversion Principle"* allowing for other objects (i.e. **JumpableManager**) to depend on the abstraction **Jumpable** 
rather than the **Ground** classes that are jumpable.

<br>

## REQ3: Enemies

**Goomba** and **Koopa** both extends the abstract class **Enemy**, and **Player** extends the abstract class **Friendly**.
As we know enemies have different methods compared to friendlies, e.g enemies are able to wander and follow the player but player can’t. Indeed, both enemy and friendly extends actor.

Player holds Wrench
If player is holding Wrench in the inventory, then HitAction can be called to hit Koopa. On the other hand, Goomba is able to kick Player if it is close enough to Player.

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

* Not as simple as each item having an Integer attribute representing its cost/player having an Integer attribute representing how much the player have

<br>

## REQ6: Monologue

foobar

### Pros

* foo
* bar

### Cons

* foo
* bar

<br>

## REQ7: Reset Game

A **Resettable** interface and a **ResetManager** class are created. All classes that can be reset, like **Player** and **Enemy**, implments this interface. And **ResetManager** manages the reset process.

### Pros

* Easier to debug this features as all the reset method are implemented in this class

### Cons

* Increased code size and complexity
  