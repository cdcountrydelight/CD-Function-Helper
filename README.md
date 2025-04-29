# CD Function Helper

**CD Function Helper** is a lightweight Android library that provides a collection of **handy
utility functions** for common everyday tasks.

Whether it's working with **Dates**, **Strings**, **Numbers**, **Assets**, **Permissions**, or *
*Booleans**,  
CDFunctionHelper saves developers time by offering clean, reusable helper functions.

![Platform](https://img.shields.io/badge/Platform-Android-green.svg)
![API Level](https://img.shields.io/badge/API-21+-blue.svg)
![Language](https://img.shields.io/badge/Language-Kotlin-orange.svg)
![Moshi](https://img.shields.io/badge/Moshi-1.15.0-brightgreen.svg)
[![](https://jitpack.io/v/cdcountrydelight/CD-Function-Helper.svg)](https://jitpack.io/#cdcountrydelight/CD-Function-Helper)


---

## ✨ Features

- 📦 **AssetsHelper ** [🔗](https://github.com/cdcountrydelight/CD-Function-Helper/blob/master/CDFunctionHelper/src/main/java/com/countrydelight/cdfunctionhelper/AssetsHelper.kt)
  - Read and parse asset files easily into Strings or classes (using Moshi).
- 🛡️ **PermissionHelper** [🔗](https://github.com/cdcountrydelight/CD-Function-Helper/blob/master/CDFunctionHelper/src/main/java/com/countrydelight/cdfunctionhelper/PermissionHelper.kt)
  - Check permission states and show rationales quickly.
- 🗓️ **DateTimeHelper** [🔗](https://github.com/cdcountrydelight/CD-Function-Helper/blob/master/CDFunctionHelper/src/main/java/com/countrydelight/cdfunctionhelper/DateTimeHelper.kt)
  - Easy date formatting, previous/next day or month calculations, etc.
- 🔢 **NumberHelper** [🔗](https://github.com/cdcountrydelight/CD-Function-Helper/blob/master/CDFunctionHelper/src/main/java/com/countrydelight/cdfunctionhelper/NumberHelper.kt)
  - Simple number formatting and conversion utilities.
- 🔤 **StringHelper** [🔗](https://github.com/cdcountrydelight/CD-Function-Helper/blob/master/CDFunctionHelper/src/main/java/com/countrydelight/cdfunctionhelper/StringHelper.kt)
  - String validation, formatting, and more.
- ✅ **BooleanHelper** [🔗](https://github.com/cdcountrydelight/CD-Function-Helper/blob/master/CDFunctionHelper/src/main/java/com/countrydelight/cdfunctionhelper/BooleanHelper.kt)
  - Handy extensions for Boolean operations.

---

## 📦 Installation

### Gradle (JitPack)

1. Add it in your `settings.gradle` file at the end of repositories:

    ```gradle
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            google()
            mavenCentral()
            maven(url = "https://jitpack.io")
        }
    }
    ```

2. Add the dependency to your `build.gradle` file (module-level):
    ```gradle
    dependencies {
        implementation ("com.github.cdcountrydelight:CD-Function-Helper:<latest-version>")
    }
   ```
---

✅ **Note**:  
You **do not** need to add the `com.squareup.moshi:moshi` and `com.squareup.moshi:moshi-kotlin` dependency manually to your project.  
It is already included and exposed by the library, so you can easily access the `Moshi` without any extra setup.

Feel free to contribute, suggest improvements, and open PRs! 🚀

# 👏 Happy Coding!

