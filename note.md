# Day 3 - 09/01/2017

### The concepts for this day are:

- Passing Functions as Values (that leads to the following)
- High Order Functions
- Anonymous Functions

- Partial Functions
- Currying
- Function Composition

## http://stackoverflow.com/questions/18887264/what-is-the-difference-between-def-and-val-to-define-a-function
## val evaluates when defined, def - when called:
val pp = (x: String) => x == "a"
def p(x: String): Boolean = { x == "a"}