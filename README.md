# Number Guessing – Branch Overview

## Branch Roles
- **main**: Stable production baseline.
- **dev**: Integration branch for features.

## Feature Branches (my inspection)
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

## My Learning Summary - Strategies and History

### What each strategy does

- **Merge**: Combines histories and creates a merge commit. Preserves the branching history.  

  *Use when*: integrating a feature with shared history, or when you want to keep the graph accurate.


- **Rebase**: Replays commits onto a new base (no merge commit). Should makes history linear or 'cleaner'.  

  *Use when*: cleaning up your own branch before merging; **avoid** rebasing shared branches.


- **Squash**: Combines multiple commits into one. Keeps final code, hides noisey commits.  

  *Use when*: your feature branch has lots of WIP commits; squash before merging to keep `dev` tidy.


- **Cherry-pick**: Copies a single commit onto another branch.  

  *Use when*: applying a hotfix to `main` without pulling in unrelated `dev` work.

### What I observed in this repo
- **feature1 (merge)**: Merged `dev` → `feature1`, resolved conflicts, then merged back into `dev`.  

  History shows a merge commit; branch structure is visible in the graph.

- **feature2 (rebase)**: Rebasing onto `dev` produced a **linear** (or clean) sequence of commits on top of `dev`.  

  No extra merge commit; conflicts were resolved during `rebase --continue`.

- **feature3 (squash + rebase)**: 4 messy commits squashed into **one**, then rebased onto `dev`, then merged.  

  Result: `dev` got ONE single clean commit for the feature.

- **hotfix (cherry-pick)**: Applied `hotfix` to `main` via `git cherry-pick`, then merged `main` → `dev` so `dev` includes the fix too.

### When I’d use each in real projects
- **Daily feature work**: Branch off `dev`, commit often. Before opening a PR, **rebase or squash** to clean history.

- **Team integration**: Prefer **merge** when multiple people contribute to the same branch and you want to preserve context.

- **Emergency fixes**: **Cherry-pick** to `main` for production; then **merge main into dev** to keep branches aligned.

- **General rule**: Don’t rebase shared branches (`dev`, `main`). Rebase only your personal feature branches.


