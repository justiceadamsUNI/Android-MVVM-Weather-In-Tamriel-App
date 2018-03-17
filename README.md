# Android-MVVM-Weather-In-Tamriel-App
(Still a WIP)

## Weather in Tamriel
Weather in Tamriel is a demo app that serves two purposes. First, It allows me to demonstrate some of what I've learned in my 3+ years of professionall android development. Second, this repo serves as an example MVVM architected android app anyone can read through to better understand both android development in Kotlin and the MVVM pattern. It was origiannly derived from a simple weather application that I built while reading Antonio Levia's Kotlin for Android Developers. I took that idea and decided to add more features while also injecting an overall design architecture while redisigning the way everything works together. You'll notice the app is themed like that of Tamriel from the Elder Scrolls series... that part was just for fun! Praise Talos.

## Final Product
Here's the gif of the final product. I encourage you to read more about the apps development, but if you just want to see the final form, I totally get it.

<p align="center">
  <img src=https://i.imgur.com/m2IgX5f.gif/>
</p>

To make sense of what your seeing, consult the below tables for how dates in Tamriel work, curtesy of The Unnoficial Elder Scrolls Pages.

| Month on Tamriel  | Month on Earth |
| ----------------- |:--------------:|
|Morning Star	      |January         |
|Sun's Dawn	        |February	       |
|First Seed	        |March	         |
|Rain's Hand	      |April	         |
|Second Seed	      |May	           |
|Midyear            |June            |
|Sun's Height      	|July	           |
|Last Seed	        |August	         |
|Hearthfire         |September	     |
|Frostfall          |October	       |
|Sun's Dusk         |November	       |
|Evening Star      	|December        |

| Day of week on Tamriel  | Day of week on Earth |
| ----------------------- |:--------------------:|
|Sundas	                  |Sunday                |
|Morndas	                |Monday                |
|Tirdas	                  |Tuesday               |
|Middas	                  |Wednesday             |
|Turdas	                  |Thursday              |
|Fredas	                  |Friday                |
|Loredas	                |Saturday              |


## How To Run The App
The app is not going to be deployed on the google play store for various reasons including liscensing (I don't work for bethesda yet) and API request limits (I don't own the API's being utilized). What this means is that to run the app you'll need to manually build the APK yourself. Simply download android studio if you don't already have it installed, clone the repo, and build the project APK to whichever device/emulator you wish! For more on how to run and deploy a project with android studio I encourage you to read the following [page](https://developer.android.com/studio/run/index.html) of the android developer documentation.

## An Agile Approach
This project took on a very agile-like workflow. I consistently produced iterations which featured fully functioning producs as I worked towards my final deliverable. This iterable approach allowed for faster developed prototypes and the ability to make changes to my development process with ease. Unfortunatley, as the only contributor, I wasn't able to utilize any sort of feature-branch-workflow nor did I write any unit test (which is part of the greatnesss of the MVVM architecture, more on that later...) I've included gifs of every "iteration" I developed. Each of these stages can be found by scraping through the commit history of this repo. You can think of the below images as iterations 0-5 in the order they are displayed.

<p align="center">
  <img src=https://i.imgur.com/84gThTs.png/>
  <img src=https://i.imgur.com/uzvuB0Z.gif>
  <img src=https://i.imgur.com/wFJO9qi.gif>
  <img src=https://i.imgur.com/6SzuvV5.gif>
  <img src=https://i.imgur.com/Ed0742u.gif>
  <img src=https://i.imgur.com/m2IgX5f.gif>
</p>

## Why MVVM/Architecture Components
I once read a quote in another developers blog post that stuck with me. It basically stated "You know an app has been thoughtfully designed when something breaks, and as a developer, you know exactly where to go to fix it." That is the *essential* idea behind carefully implementing an application architecture.
