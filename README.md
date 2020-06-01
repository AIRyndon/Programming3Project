# Detective Game

```
That is an RPG game where your role is a detective.
You are able to move, get NPC talks, unlock keys, and save hints to figure out who is the killer.
```

## How the game works?

### Step 1: Introduce story

```
1. Ask player to enter some basic information (name, gender, age).
2. Print story introduction.
3. Assign player to the scheme (victim house).
4. To finish the game, player need to get 5 hints or press 'q' to quit
```

### Step 2: Allow user to play the game

```
Initially, the game has

- Moving area, allows player to move around the house.
- Five NPCs, declared as 'A', 'B', 'D', 'M', 'W' in the moving area.
- Victim body declared as 'V'
- 2 locks, declared as '#' in Ground and Working room.
- 4 key passwords to unlock those 2 locks.
- To get a key password, player need to correctly answer a question.

To-do list to finish the game:
-> Talk with 5 NPC
-> Talk with NPC 'A' again to get his secrect talk -> A hint appears and able to get (Hint get: 1)
-> Talk with NPC 'W' again to get her secrect talk -> A hint appears but inside the Locked Area
-> Answer questions and get 2 key passwords -> Unlock the Locked Area and get the hint (Hint get: 2)
-> After getting the hint, talk with NPC 'B' again to get his secrect talk
-> A hint appears and able to get (Hint get: 3)
-> Talk with NPC 'M' again to get her secrect talk -> A hint appears inside the Dog House
-> Talk with NPC 'D' again to get her secrect talk -> A hint appears and able to get (Hint get: 4)
-> Answer questions and get 2 key passwords -> Unlock the Dog House and get the hint (Hint get: 5)
```

### Step 3: End game (5 hints has picked up)

```
-> Ask player to decide which NPC is the killer (select 1 - 5).
-> Print "YOU WIN THE GAME" or "YOU LOSE THE GAME" depending on player choice.
-> Just reveal "Daughter is the killer" when player selects the correct answer.
-> Ask if player wants to play again or quit.
```

## Versioning

- CUI

## Authors

- **Group 4**
