# Dotify
## Jay Houppermans

The third version of the homework project for my Android Development class




A music player app for my Android Development class, though it doesn't yet have the ability to play music.
It now has an implemented list of songs, as well as the ability to shuffle on that list and select a song.


I did not attempt any extra credit.

![image of the recyclerview working](https://github.com/jhoupps/Dotify/blob/hw2/hw2_working_recyclerview.PNG)
![image of the mainview working](https://github.com/jhoupps/Dotify/blob/hw2/hw2_working_mainview.PNG)


## Usage Notes and known bugs:
It does not display the title of the app, due to a conflict with the BottomAppBar that I used for implementing the music player
It also does not display the artist in the main activity, due to a carry-over error from hw1
If the song selected has a very long title or a very long artist, it pushes the shuffle button off the screen

In order to move to the Main Activity, the user must click on the title of the song in the music player container
Just clicking on the container itself is insufficient
You must click on the song title or author text


