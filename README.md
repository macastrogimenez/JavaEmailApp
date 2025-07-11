# JavaEmailApp
This is an App which generates an email and password for a user based on input name, surname, company and department.

It is a fairly simple project, however, it implements some interesting programming concepts:

1. Interactive case handling: It uses a switch statement to assign department based on minimal user input.
2. Random (validated) String generation: It defines a PasswordGenerator class which is able to generate a random password (using SecureRandom) based on a String containing the desired length and characters (which will be allowed in the password). 
3. Data validation and string manipulation: It uses regular expressions to validate an alternative email.
4. Fast data storage and retrieval: Uses a Trie to store newly-generated emails and to check (in linear time) if this email is already taken. 
    Time complexity analysis: The time complexity for basic operations (insertion, search, deletion) in a is typically O(m), where 'm' is the length of the string being processed (in the case of our emails max length 44, so m is max 44). The space complexity is generally O(n*m), where 'n' is the number of strings stored and 'm' is the average string length (a value of approx 20).
5. "Collision handling": If a the new email is already taken, it uses an alternative way to generate a new email and if this would also fail it prompts the user to choose its own, which should also be validated (using the same method which makes use of regular expressions to check if the email would be valid on point 3).


Future improvements:
- Name and surname will also be inputted by the user, instead of added through an object
- The user will be asked if they want to keep creating new emails or if they are done
