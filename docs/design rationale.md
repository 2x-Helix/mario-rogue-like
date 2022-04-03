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


## REQ 2
Added JumpAction to allow jumping on walls and trees. The MoveActorAction does not allow for conditional movement
of actors. Since we cannot modify engine files, this requires to be a separate action to allow for the
success chance of jumping.

### Pros
* Allows for optional chance of not succeeding the jump.

### Cons
* Jump hotkey is not bound to the standard movement hotkeys.