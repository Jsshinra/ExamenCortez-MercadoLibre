# Instructions to Fix Import Errors

The import errors you are seeing are because the project's dependencies (libraries) have not been downloaded and configured correctly. This is a common issue when setting up a new Java project.

Please follow these steps to resolve the issue.

---

## **NEXT STEP: Please Try This Now**

I have created a `gradle.properties` file to fix the "Unsupported class file major version" error you encountered.

**Please try to build the project again using one of the options below.** The new configuration should now work.

---

## Option 1: If you are using Visual Studio Code

This is the recommended approach if you have the project open in VS Code.

1.  **Open the Command Palette:** Press `Ctrl+Shift+P` (or `Cmd+Shift+P` on macOS).
2.  **Clean the Java workspace:** In the palette, type `Java: Clean Java Language Server Workspace` and press Enter. This will force the Java extension to re-analyze the project.
3.  **Update the project configuration:** After the cleaning is done, open the Command Palette again (`Ctrl+Shift+P`) and type `Java: Update Project Configuration` and press Enter. You may also see a prompt to do this automatically.

This should trigger Gradle to download all the necessary dependencies, and the errors in your files should disappear.

## Option 2: Using the Command Line

If the above steps don't work, or if you are not using VS Code, you can fix it using the command line.

1.  **Open a terminal:** Open your system's terminal (like PowerShell, Command Prompt, or Git Bash).
2.  **Navigate to the project directory:** Make sure you are in the root directory of the project (`c:\Users\nacho\OneDrive\Escritorio\Cortez-ExamenMercadoLibre`).
3.  **Run the build command:** Execute the following command exactly as written:

    ```sh
    .\gradlew.bat clean build
    ```

This command will clean any old files and then build the project, downloading and setting up all required dependencies in the process. After it completes successfully, the import errors in your editor should be gone.