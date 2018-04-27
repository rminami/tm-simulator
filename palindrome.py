states 12
s
s0
s1
s2
f0
f1
f2
c0
c1
c2
b
a +
alphabet 3 0 1 2
# First, it reads the first symbol, and then moves left.
s 0 s0 _ R
s 1 s1 _ R
s 2 s2 _ R
# If the tape is already blank here, it means that the original
# string was of length 1. If this is the case, we can accept.
s0 _ a _ S
s1 _ a _ S
s2 _ a _ S
# If not, it moves on and goes into a 'forward' state, where
# it keeps on moving right until it reaches the edge.
s0 0 f0 0 R
s0 1 f0 1 R
s0 2 f0 2 R
s1 0 f1 0 R
s1 1 f1 1 R
s1 2 f1 2 R
s2 0 f2 0 R
s1 1 f1 1 R
s1 2 f1 2 R
# Then, it moves right until it reaches a blank entry on the tape
f0 0 f0 0 R
f0 1 f0 1 R
f0 2 f0 2 R
f1 0 f1 0 R
f1 1 f1 1 R
f1 2 f1 2 R
f2 0 f2 0 R
f2 1 f2 1 R
f2 2 f2 2 R
# When it does find a blank character, it goes into a 'checking'
# state and moves back left.
f0 _ c0 _ L
f1 _ c1 _ L
f2 _ c2 _ L
# Then, it checks to see if the character matches
c0 0 b _ L
c1 1 b _ L
c2 2 b _ L
# If not, the Turing machine should keep on going left.
b 0 b 0 L
b 1 b 1 L
b 2 b 2 L
# When it does find a non-empty character, it goes back into the 
# initial state and moves right
b _ s _ R
# If this next character is also a blank character, then we have
# already gone though the entire input!
s _ a _ S