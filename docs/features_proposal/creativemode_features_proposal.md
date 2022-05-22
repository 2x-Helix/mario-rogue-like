# Requirement 12

**Title**:
_Chests and Disguised Chests_

**Description**:
_This requirement implements a Chest, 'c' and Disguised Chest, 'c' into the game. Players can buy from a chest and a random item is dropped onto the Player. However, a Disguised Chest, which looks like a Chest, will attack Friendlies close to it, and reveal its true appearance, 'e'. Upon death, this Enemy will drop a random item onto its location._

**Explanation why it adheres to SOLID principles** (WHY):

- Adheres to the **Single Responsibility Principle** as though each **Chest/DisguisedChest** can have their own methods for creating their own ArrayList of items, another class, **ItemPool**, is responsible for creating the ArrayList of items, and also responsible for picking a random item from this ArrayList of items. 
- Each **Chest/DisguisedChest** is only responsible for importing and instantiating their own **ItemPool**, (hence ItemPool is not static, following the **Open-Close Principle**) and is only responsible for carrying a random item. E.g, as **Disguised Chest** extends from Enemy, it also carries the behaviours of an **Enemy**, hence, adhering to the **Open-Close Principle**, where **Enemy** is able to be extended to many new actors in the future.
- **DisguisedChest** is implemented such that it extends **Enemy**, carrying over the behaviours of **Enemy** further following the **Dependency Inversion Principle**.

| Requirements                                                                                                            | Features (HOW) / Your Approach / Answer                                                                                                                                                                                                                                                               |
| ----------------------------------------------------------------------------------------------------------------------- |-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Must use at least two (2) classes from the engine package                                                               | _We use two classes from the engine package: Actor and Ground. DisguisedChest extends Enemy which extends Actor, Chest extends Ground_                                                                                                                                                                |
| Must use/re-use at least one(1) existing feature (either from assignment 2 and/or fixed requirements from assignment 3) | _We use the existing features, coin and wallet, where Chest allows the OpenChestAction, which takes credits away from actor opening the Chest. Also, DisguisedChest contains behaviours, such as AttackBehaviour. Both classes also have the ability to hold different **Item**s in their inventory._ |
| Must use existing or create new abstractions (e.g., abstract or interface, apart from the engine code)                  | _We use the abstraction **Enemy**, which the actor **DisguisedChest** extends from. Moreover, An abstract class, **Chest** was created to allow for different types of Chests to be implemented into game._                                                                                           |
| Must use existing or create new capabilities                                                                            | _We use the existing capability **Status.ENEMY** to identify **DisguisedChest** as an Enemy._                                                                                                                                                                                                         |

---

# Requirement 11

**Title**:
_More Items_

**Description**:
_write down the summary of the feature here..._

**Explanation why it adheres to SOLID principles** (WHY):

-

| Requirements                                                                                                            | Features (HOW) / Your Approach / Answer                                                                                                                               |
| ----------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Must use at least two (2) classes from the engine package                                                               | _EXAMPLE (please delete this later): We use three classes from the engine package: Actor, Item, and Ground. ClassA extends Actor, ClassB and ClassC extend Item, ..._ |
| Must use/re-use at least one(1) existing feature (either from assignment 2 and/or fixed requirements from assignment 3) |                                                                                                                                                                       |
| Must use existing or create new abstractions (e.g., abstract or interface, apart from the engine code)                  |                                                                                                                                                                       |
| Must use existing or create new capabilities                                                                            |                                                                                                                                                                       |
