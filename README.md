
# GifDialog
 
[![](https://jitpack.io/v/aliwaris0572/GifDialog.svg)](https://jitpack.io/#aliwaris0572/GifDialog)

## Custom loading dialog library in Kotlin
This library aims to help in creating beautiful loading dialogs with animated gifs.
Thanks a lot to [Fresco](https://github.com/facebook/fresco), without which it would not be possible for me to create this. :)

|![Image](https://github.com/aliwaris0572/GifDialog/blob/master/art/screen.gif)| 


## How to use?

 1. #### Initialize - 
 `val dialog = GifDialog.with(this)`
 
 2. #### Configure - 
  ` dialog!!`
 `.isCancelable(false)`
`    .setText("Loading...")`
  `  .setTextSize(18)`
`    .setTextBackgroundColor(ContextCompat.getColor(this, android.R.color.white))`
`    .setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))`
`    .setResourceId(R.drawable.giphy5)`

 3. #### Show dialog (**with TAG) - 
 `dialog?.showDialog("TAG_NAME")`
 
 4. #### Dismiss dialog (**with TAG) - 
 `dialog?.dismissDialog("TAG_NAME")`
 
Easy enough!!! :)
For more customization, refer sample application code.

## Gradle
Add it in your root build.gradle at the end of repositories

    allprojects {
		    repositories {
			    ...
			    maven { url 'https://jitpack.io' }
		    }
	    }
  
---------------------------------------------------------------

Then, add this in you app level build.gradle

    dependencies {
	            implementation 'com.github.aliwaris0572:GifDialog:{latest_version}'
	    }
