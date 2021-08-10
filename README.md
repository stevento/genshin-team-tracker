# Genshin Impact Team Tracker

## What is Genshin Impact Team Tracker?

<p>The Genshin Impact Team Tracker will allow the user to save teams of four characters for the
Spiral Abyss game mode. Users will be shown the possible elemental reactions and elemental resonance present within the 
teams. 

**Inspiration**: a desire to save my own teams for future reference.</p> 

## Who will use this?

<p>Genshin Impact players who challenge the Spiral Abyss will use this application to save their previous team 
compositions for future reference (since the Spiral Abyss is constantly changing).</p>

## User Stories

As a user, I want to ...
- create and add multiple teams to a list of teams
- add up to four characters a team (a character can be used on different teams)
- know what elements are on each team  
- know what elemental resonance(s) is/are on each teams
- be able to save my list of teams to file
- be able to load my list of teams from file

## Phase 4: Task 2
I have implemented the robust class option:
> Test and design a class in your model package that is robust.  You must have at least one method that throws a 
> checked exception.  You must have one test for the case where the exception is expected and another where the 
> exception is not expected.

The robust class is the **Character** class in the **model** package. The method that has a robust design and throws a 
checked exception is **Character.find(String name)**. The tests for this class are in **test.model.CharacterTest** and 
include a case where the exception is expected and another where the exception is not expected.

