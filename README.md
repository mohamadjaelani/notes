## Uninstall Open JDK
  - To uninstall OpenJDK (if installed). First check which OpenJDK packages are installed.
    ```sudo dpkg --list | grep -i jdk```
  - To remove openjdk:
    ```sudo apt-get purge openjdk*```
  - Uninstall OpenJDK related packages.
    ```sudo apt-get purge icedtea-* openjdk-*```
  - Check that all OpenJDK packages have been removed.
    ```sudo dpkg --list | grep -i jdk```
