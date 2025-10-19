# Number Guessing – Branch Overview

## Branch Roles
- **main**: Stable production baseline.
- **dev**: Integration branch for features.

## Feature Branches (from inspection)
- **feature1**
  - Commits:
    - `Add ability to quit game with negative number input`
    - `Add play-again loop functionality`
    - `Improve user feedback messages for guesses`
    - `Add version comment documenting quit feature`
  - **Summary:** Adds quit-on-negative, play-again loop, and clearer feedback/messages.

- **feature2**
  - Commits:
    - `Add maxAttempts constant and game over state`
    - `Implement max attempts logic and game over condition`
  - **Summary:** Introduces a **max attempts** limit and game-over behavior. (Also picks up dev’s “encouraging message”.)

- **feature3**
  - Commits:
    - `started hint` → `got it done` → `had to fix` → `done`
  - **Summary:** Implements a **hint system** with iterative fixes.

- **hotfix**
  - **Summary:** Contains a single fix intended for **main** (to be cherry-picked in Step 7).
  - (If you want the exact message: `git log hotfix --oneline -n 1`)
