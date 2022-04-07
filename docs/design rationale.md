## REQ1
The tree stages were implemented as 3 subclasses that inherit from an abstract class
**Tree**. All tree stages are expected to grow to a new stage after a set amount of 
turns. The number of turns required to grow should be stored as an instance variable for a Tree.
Once the requisite amount of time to grow has passed, a new object of the next growth stage may be returned.
Once 5 turns has passed to grow for the Mature stage, the growth timer may be reset back to 5 to
for it's to regrow a new sprout. 

### Pros
* Safeguarding implementation of grow().
* Able to identify all growth stages as a Tree class.
### Cons
* Additional code required to implement abstract class.
* Increased code size.

## REQ2
JumpActorAction extends MoveActorAction:
As we want the player to have an action to move to another location in the gamemap, the MoveActorAction has the necessary methods to enable player to move to another location when called.

JumpManager is associated with <<interface>> Jumpable:
We want jumpmanager to store the instances of jumpable grounds, so it can be assessed by JumpActorAction and decide whether player can jump to new location or not.

<<abstract>> tree and wall implements the <<interface>> Jumpable:
We want only specific grounds to be jumpable but not others, such as dirt. So we will use an interface, that will be implemented by trees and the wall.

## REQ3
Goomba and Kooper both extends <<abstract>> enemy, and Player extends <<abstract>> friendly
As we know enemies have different methods compared to friendlies, e.g enemies are able to wander and follow the player but player can’t. Indeed, both enemy and friendly extends actor.

Player holds Wrench
If player is holding Wrench in the inventory, then HitAction can be called to hit Koopa. On the other hand, Goomba is able to kick Player if it is close enough to Player.
