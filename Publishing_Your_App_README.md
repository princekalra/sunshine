Publishing Your App
====================

# The Basics
---------------
## A Note on Release .apk Files
Release .apk files are nearly the same as debug .apk files - they contain the same components and are built with the same build tools. The difference between the two files is that the release .apk is signed with your certificate and optimized with the zipalign tool. Everything else is just double-checking and finishing touches.

## Gather the Basics
### Cryptographic Key
- The Android system requires an installed app to be digitally signed with a certificate that is owned by the app's developer (that is, a certificate for which the developer holds the private key). The intent with these certificates is to establish authorship and trust between apps.
- Cryptographic keys MUST have a validity period that ends after 22 October 2033. However, they do not need to be signed by a certificate authority and may be self-signed.
- You will need a keystore file that contains a private signing key. A private key represents the entity to be identified with the app, such as a person or a company. Android Studio can help you generate both, as explained below.
- **Warning:** Keep your keystore (with private key) in a safe and secure place, and ensure that you have secure backups of them. If you publish an app to Google Play and then lose the key with which you signed your app, you will not be able to publish any updates to your app, since you must always sign all versions of your app with the same key.
- **Warning:** Keep your keystore (with private key) in a SAFE and SECURE place. This is worth repeating. Developers that lose their keystore (or access to their keystore) are [very unhappy developers](http://stackoverflow.com/search?q=lost+my+android+keystore). The Android platform enforces this security model.

### Application Icon
- You need an application icon that meets the recommended [icon guidelines](https://material.google.com/style/icons.html). Your app icon will be shown on a device's Home screen, in the Launcher window, under Manage Applications and My Downloads, and elsewhere. In addition, publishing services such as Google Play display your icon to users.
- Note that if you are publishing on Google Play, you need to create a high resolution version of your icon. See [Graphic Assets for Your Application](https://support.google.com/googleplay/android-developer/answer/1078870) for more information.

### EULA
Consider preparing an End User License Agreement (EULA) for your application. A EULA can help protect your person, organization, and intellectual property, and it is recommended that you provide one with your application.

# Configure and Build Your App
------------------------------------------

## Configure Your App
- Choose a good package name. You cannot change the package name after you distribute your application to users. See the [documentation](https://developer.android.com/guide/topics/manifest/manifest-element.html#package) for more information on the package attribute in AndroidManifest.xml.
- Turn off logging and debugging.

  1. Remove all `Log` statements.
  2. Remove the `android:debuggable` attribute from `AndroidManifest.xml` or set it to `false`.
  3. Remove any log files or static test files that were created in your project.
  4. Remove all Debug tracing calls that you added to your code, such as `startMethodTracing()` and `stopMethodTracing()` method calls. 

- Double check that your app conforms to the [Android directory structure](https://developer.android.com/studio/projects/index.html#ApplicationProjects). Stray or orphan files may cause your app to misbehave.
- Update your configs.

  1. Provide values for both `android:icon` and `android:label` in the application element of `AndroidManifest.xml`. These are required.
  2. Provide values for both `versionCode` and `versionName` in the app-level `build.gradle` file to version your app. See the [documentation](https://developer.android.com/studio/publish/versioning.html) for more information.

## Build Your App
Android Studio has integrated the Gradle Build system, and can automatically prepare your .apk for you. However, the build process assumes that you have a certificate and private key suitable for signing your application. If you do not have a suitable certificate and private key, Android Studio can help you generate one.

To sign and build your app, follow these steps:

1. On the menu bar, click **Build > Generate Signed APK**.
2. On the _Generate Signed APK Wizard_ window, click **Create new** to create a new keystore. (If you already have a keystore, go to step 4.)
3. On the _New Key Store_ window, provide the required information for your keystore location, the name of the private key, and a password for each.
 - Your key should be valid for at least 25 years, so you can sign app updates with the same key through the lifespan of your app.
 - You'll also want to provide all of your company information to establish authorship in the certificate.
4. Back on the _Generate Signed APK Wizard_ window, select a keystore, a private key, and enter the passwords for both. Then click **Next**.
5. On the next window, select a destination for the signed APK and click **Finish**.

The package in that location is now signed with your release key. You can see these steps, along with helpful images, in the [documentation](https://developer.android.com/studio/publish/app-signing.html). You can also find information on debug keys, how to set up automatically sign your app, and how to configure your keystore manually in your build.gradle.

The Android Studio wizard sets up your keystore and configures your build for you. However, clever students may notice that this places private information such as your key and password in your build.gradle file. You probably don’t want to do this. Instead, you should configure the build file to grab these values from environment variables. At the risk of repeating myself, you really should check out our super cool and very informative [documentation](https://developer.android.com/studio/publish/app-signing.html#release-mode) (Step 4).

## Last Steps!
- Test the release build on at least 1 target handset device and 1 target tablet device.
- Put it out there!
