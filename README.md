# Duke project template

This is a project template for a greenfield Java project. It's named Luigi. Given below are instructions on how to use it.

## Running the jar file

1. Head over [here](https://github.com/AnWe11/ip/releases/tag/Luigi-v2) and download the luigi.jar file.<br>
2. Once downloaded, create an empty folder and move the luigi.jar file into that folder.
3. Open the terminal in that folder (You can do so by right-clicking an empty area in the folder and click 'Open in terminal').
4. To run the jar file, type in the terminal "java -jar luigi.jar".
5. A popup window will appear, you can now use the Luigi Chatbot!

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/fx/Launcher.java` file, right-click it, and choose `Run Launcher.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, there will be a popup window of the Luigi Chatbot.

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

### How to use Luigi Chatbot

Luigi helps to keep track of your tasks so you don't have to!

To use its features, type the command you want to do followed by a description. For more information, refer to the bottom for some examples!

Ensure that any date follows the format "yy-mm-dd" (2025-01-20) and any time follows the 24-hour format (1800). The time is also optional, tasks can still be added even if the time is omitted. 

Its features include:

1. "list" - Lists all the current tasks and displays their task ID.
2. "todo <task description>" - Adds a todo task, which only contains the task description. (E.g. "todo meeting at 8pm with friends")
3. "deadline <task description> /by <date>" - Adds a deadline task, which contains the task description and date where it is due. (E.g. "deadline do homework /by 2025-02-21 1600")
4. "event <task description> /from <start date> /to <end date>" - Adds an event task, which contains the task description and the start date and end date. (E.g. "event trip to Thailand with family /from 2025-03-10 /to 2025-03-17")
5. "mark <task ID>" - Mark a task as completed. (E.g. "mark 2")
6. "unmark <task ID>" - Unmark a task as uncompleted. (E.g. "unmark 3")
7. "delete <task ID>" - Deletes a task from the list. (E.g. "delete 4")
8. "find /keyword <keyword> /month <month> /type <type of task>" - Filters the task list and returns tasks that matches all the descriptions. You need not include all 3 filters, one filter is enough, also the order of the filters do not matter.
   
   (E.g. find /keyword books) - Returns all tasks with "books" in their task description.

   (E.g. find /month December /keyword books) - Returns all deadline and event tasks which resides in December and have "books" in their task description.
   
   (E.g. /type deadline /month March) - Returns all deadline tasks that resides in March.
   
   (E.g. /keyword friends /month June /type event) - Returns all event tasks in the month of June that have "friends" in their task description.
   
The order of the /keyword /month /type does not matter, it is also unnecessary to use all the filter words. Any combination will work!

