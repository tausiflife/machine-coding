# Machine-coding
This is repo for preparation of machine coding round. 

1. Design LLD of notification system. When an item appears on an ecommerce website which has not available at that 
   moment, and the user has the ability to click on notify me. Then when the inventory is available then the users 
   are notified. 
    1. This looks like a perfect place to use the observer pattern.

2. code a TextPad with following functionality:

display() – to display the entire content
display(n, m) – to display from line n to m
insert(n, text) – to insert text at line n
delete(n) – delete line n
delete(n, m) – delete from line n to m
copy(n, m) – copy contents from line n to m to clipboard
paste(n) – paste contents from clipboard to line n
undo() – undo last command
redo() – redo last command
expected the textpad to be in memory(not as file) and also expected to handle error gracefully and the program to be menu driven.
