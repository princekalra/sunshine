
#### Setting up your project to use the Places API
To set your project up to use the places API, you'll need to follow 4 steps.

- Find your app's certificate information
- Enable the Places API in the Developer Console
- Get an Android API key
- Add the API key to your Android manifest file.

--------------------------------------------------------------------------------------------------

### Finding your app's certificate information
For this tutorial, we'll be using a debug certificate. For information on release certificates, look [here](https://developers.google.com/places/android-api/signup).

First, you'll need to find your debug keystore file. This is a file located in your .android/ directory, along with your AVD files.

Once there, you'll need to open a terminal window and enter one of the following commands:

(Mac and Linux) keytool -list -v -keystore ~/.android/debug.keystore -alias androiddebugkey -storepass android -keypass android

(Windows) keytool -list -v -keystore "%USERPROFILE%\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android

You should get an output with a number of lines. What we need to get is the line starting with "**SHA1**", which should be 20 pairs of hexadecimal numbers separated by colons. Copy this key.

### Enabling the Places API in the Developer Console.
Since we've already created a project in the Developer console, we will need only to enable the Places API. To do so, follow [this link](https://console.developers.google.com/flows/enableapi?apiid=placesandroid&keyType=CLIENT_SIDE_ANDROID&pli=1) and follow the directions. You'll want to select the project you used for the GCM lesson, then enable the Places API.

### Get an API key
Once you've enabled the Places API, you should receive a message asking you to add credentials. Click on that drop-down menu and choose 'API key'. This will bring up a pop-up menu, and you will press the 'Android key' button. Choose a name for your key, and then enter your app's package name and SHA1 fingerprint. The fingerprint is the set of 20 pairs of hexadecimal numbers you got from the terminal earlier.

Click 'Create' and you should receive an API key, which should be a string of characters, like this:

`AIzaSyBdVl-cTICSwYKrZ95SuvNw7dbMuDt1KG0`

### Add the API key to your Android manifest file
Within your android manifest file, add a <meta-data> tag like the following, with your API key in place of YOUR_API_KEY:

```
<application>
  ...
  <meta-data
      android:name="com.google.android.geo.API_KEY"
      android:value="YOUR_API_KEY"/>
</application>
```
