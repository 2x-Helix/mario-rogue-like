# Requirement 11

**Title**:
_More Items_

**Description**:
_New class of item - Equipment is added to the game. Equipments can do all sort of things, but requires cool down durations before using it again. For example, BFG instantly kills all enemies around the user and Fruit heals the users for 50HP._

**Explanation why it adheres to SOLID principles** (WHY):

- Single Responsibility Principle: Each equipment has its own implementation of the inherited **onUse()** function from the **Equipment** parent class, and all the effects of an equipment are implemented in that function. **onUse()** is called everytime the **UseAction** of the equipment is executed. This avoids implementing the effects of all equipments inside the class of **UseAction**, and let each equipment takes the responsibility of providing its own effects.
- Open-Close Principle: The abstract class **Equipment** itself is inherited from **Item**, and the implementation of **Equipment** does not modify the base code of **Item**. All the actual equipments, like **BFG**, are only responsible for overriding the **onUse()** method from **Equipment** and provides their own behaviour, again, without modifying the **Equipment** base class.
- Dependency Inversion Principle: All the equipments are inherited from **Equipment**, which is inherited from **Item**. This allows actor's inventory (List of **Item**) to store **Equipment** directly, without the need of making a new list just for storing **Equipment**.

| Requirements                                                                                                            | Features (HOW) / Your Approach / Answer                                                                                                                              |
|-------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Must use at least two (2) classes from the engine package                                                               | _**Item** and **Action** from the engine package are used. **Equipment** is inherited from **Item**, and it provides **UseAction**, which is inherited from Action._ |
| Must use/re-use at least one(1) existing feature (either from assignment 2 and/or fixed requirements from assignment 3) | _**Status** and **Lava** are used. **Cryotov** applies a FROZEN status to the ground, and **Molotov** converts ground into **Lava**._                                |
| Must use existing or create new abstractions (e.g., abstract or interface, apart from the engine code)                  | _**Equipment**, the new abstract class is created, which is inherited from the existing abstract class **Item.**_                                                    |
| Must use existing or create new capabilities                                                                            | _FROZEN is the new GroundCapability that is added for **Cryotov**, which convert **Lava** into **Floor** prevents the ground from turning into **Lava** again_       |

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
|-------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Must use at least two (2) classes from the engine package                                                               | _We use two classes from the engine package: Actor and Ground. DisguisedChest extends Enemy which extends Actor, Chest extends Ground_                                                                                                                                                                |
| Must use/re-use at least one(1) existing feature (either from assignment 2 and/or fixed requirements from assignment 3) | _We use the existing features, coin and wallet, where Chest allows the OpenChestAction, which takes credits away from actor opening the Chest. Also, DisguisedChest contains behaviours, such as AttackBehaviour. Both classes also have the ability to hold different **Item**s in their inventory._ |
| Must use existing or create new abstractions (e.g., abstract or interface, apart from the engine code)                  | _We use the abstraction **Enemy**, which the actor **DisguisedChest** extends from. Moreover, An abstract class, **Chest** was created to allow for different types of Chests to be implemented into game._                                                                                           |
| Must use existing or create new capabilities                                                                            | _We use the existing capability **Status.ENEMY** to identify **DisguisedChest** as an Enemy. We also added ItemPool to randomly select an item from a group of items. This was intended for use with the chest but this can be later extended to enemy loot pools._                                   |
