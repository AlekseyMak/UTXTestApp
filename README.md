1. Create an Android application in Android Studio that prompts the user to:
- Select a user type (Spinner - with support for User types of: ‘Admin’ and ‘Regular User’)
- Enter a Username (Edittext)
- Enter a Password (Edittext)
2. Check if the user has entered the correct credentials and display (with a Toast or a Snackbar) an
appropriate message for success or failure.
3. Perform the credentials validation in a background task, as it may take up to 10 seconds.
4. After three consecutive failed attempts, the account should be blocked and an appropriate
message should be displayed.
5. Information that the user is blocked should persist between application launches (you can use any
method you prefer)
6. As mention above, add support for two user types, ‘Admin’ and ‘Regular User’. However, take into
consideration when planning your architecture that more user types might be added in the future.

Notes:
 Credentials should be hardcoded inside the app
 Any libraries may be used
 Online resources may be used, providing the functionality they offer is understood
 When grading the exercise, more weight will be given to the implementation than the UI