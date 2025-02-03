EXPENSES TRACKER.

1. User can login and create an account (Manadatory)
3. User can build their profile (picture, name, DOB, Bio, Address, email, Phone)
2. User can add expenses (expense name, amount, datetime(auto/manual), credit/debit, add reciepts(optional))




Database:


->	Store user data
		-> Pictures, firstname, lastname, email, phone, gender, DOB, about.
->	Store expense data
		-> Expense name, Expensetype(food, entertainment etc), picture, location, amount, credit/debit, datetime, reciepts
->	Store limits
		-> Storing limits for Food, Bills, Entertainment(one to one mapping)
		
FRONTEND:

1. Dashboard 	-> Present net amount
				-> Recent 10 transactions
				-> Spends on Food, Bills, Entertainment
				-> A graph showing the spends monthly
				-> User can set limit on expenses, if exceeded show it in using different colors.
				
2. Log an expense
				-> It is a form where user can log expenses
				-> expense name, amount, datetime(auto/manual), credit/debit, add reciepts(optional), location
				-> Automatically the icon changes based on the expense name
				-> After submitting the expense, if the expense exceeds the limits that was set,
					there has to be pop up telling not to spend much on that section (eg. food)
				-> User can delete or modify an expense
				
3. Show my expenses
				-> All of user expenses will be displayed
				-> Sorted based on name, amount, datetime
				-> Filtered based on Food, Bills, Entertainment, credit or debit
				-> When user clicked on the expense it shows all the info abount that transaction (pictures and all)
					-> expense name, amount, datetime, credit/debit, reciepts(optional)
				-> User can modify or delete that expense
					
4. Limits & Suggestions	
				-> Total monthly limit
				-> Limit on Food, Bills, Entertainment
				-> on the other half of the page we can see the suggestions on how much saving you can make with the leftover money.

5. About us
				-> Contains info about expense tracker
				
6. Profile		
				-> There will be an profile pic in the top right bottom and name of the user beside it.
				-> If they click on it, a drop-down will be there where they can log out, edit option.

Additional
				=> User can download his monthly expenses pdf
				=> User can download particular transaction.
				=> Mail will be triggered when a pdf is required.



			 
			 