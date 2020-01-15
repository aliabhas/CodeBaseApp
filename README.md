# CodeBaseApp
This project follows the MVVM Architecture with Databinding, Dagger2, RXJava, Room and Navigation component
Project based on 3 Screens: 
When application launches we'll have a list of images and details regarding account and category which those images fall in, on launch of application first category we display is "fruits"
There is a search button on AppBar where user can modify the search and see the result accordingly.
On click of the image, there is a confirmation message that asked wether you want to continue to the next screen that is Detail screen or you want to stay on the same screen.
If user moves to the Detail screen here he will see the Larger image of the category and likes, comments , favourites and all the tags related to that Image .   
Now, if the user has already searched the category will display him data from the Cache and for that purpose i have used Room inMemorycache feature. Data will be there in Cache till Application lives. Means will not get the data from API but from Cache.
