states 11
s
f0
f1
f2
c0
c1
c2
ba
bb
b
a +
alphabet 3 0 1 2
# First, it reads the first symbol, goes into a corresponding 
# 'forward' state, sets the symbol to blank, and moves right
s 0 f0 _ R
s 1 f1 _ R
s 2 f2 _ R
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
c0 0 ba _ L
c1 1 ba _ L
c2 2 ba _ L
# Then, it goes back left once
ba 0 bb 0 L
ba 1 bb 1 L
ba 2 bb 2 L
# And another time. If this one is a blank, that means that there
# is only one non-blank character left so the entire string was
# a palindrome of odd length.
bb _ a _ S
# If not, the Turing machine should keep on going left.
bb 0 b 0 L
bb 1 b 1 L
bb 2 b 2 L
b 0 b 0 L
b 1 b 1 L
b 2 b 2 L
# When it does find a non-empty character, it goes back into the 
# initial state and moves right
ba _ s _ R
b _ s _ R
# If this next character is also a blank character, then we have
# already gone though the entire input!
s _ a _ S