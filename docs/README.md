# Dark Knight User Guide

Dark Knight is a personal task manager chatbot with a JavaFX GUI. It helps you keep track of todos, deadlines, and events, and supports archiving tasks you no longer need in your active list.

![Dark Knight screenshot](img.png)

## Quick Start

1. Ensure you have **JDK 17** installed.
2. Download `darkknight.jar` from the [releases](../../releases) page.
3. Run it with:
   ```
   java -jar darkknight.jar
   ```
4. Type a command and press **Enter** or click **Send**.

---

## Commands

### `list` — Show all tasks

Shows all tasks currently in your list.

Example: `list`

```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] assignment (by: Mar 01 2026)
3.[E][X] team meeting (from: Feb 20 2026 to: Feb 20 2026)
```

---

### `todo` — Add a todo task

Adds a simple task with no date.

Format: `todo <description>`

Example: `todo read book`

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

---

### `deadline` — Add a deadline task

Adds a task with a due date.

Format: `deadline <description> /by <yyyy-MM-dd>`

Example: `deadline submit assignment /by 2026-03-01`

```
Got it. I've added this task:
  [D][ ] submit assignment (by: Mar 01 2026)
Now you have 2 tasks in the list.
```

---

### `event` — Add an event task

Adds a task with a start and end date.

Format: `event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>`

Example: `event team meeting /from 2026-02-20 /to 2026-02-20`

```
Got it. I've added this task:
  [E][ ] team meeting (from: Feb 20 2026 to: Feb 20 2026)
Now you have 3 tasks in the list.
```

---

### `mark` — Mark a task as done

Marks the task at the given index as completed.

Format: `mark <index>`

Example: `mark 1`

```
Nice! I've marked this task as done:
[T][X] read book
```

---

### `unmark` — Mark a task as not done

Unmarks the task at the given index.

Format: `unmark <index>`

Example: `unmark 1`

```
OK, I've marked this task as not done yet:
[T][ ] read book
```

---

### `delete` — Delete a task

Removes the task at the given index from the list.

Format: `delete <index>`

Example: `delete 2`

```
Noted. I've removed this task:
  [D][ ] submit assignment (by: Mar 01 2026)
Now you have 2 tasks in the list.
```

---

### `find` — Search tasks by keyword

Lists all tasks whose description contains the keyword.

Format: `find <keyword>`

Example: `find meeting`

```
Here are the matching tasks in your list:
1.[E][ ] team meeting (from: Feb 20 2026 to: Feb 20 2026)
```

---

### `archive` — Archive a task

Moves a task out of your active list into the archive.

Format: `archive <index>`

Example: `archive 1`

```
OK, I have archived this task for you:
[T][X] read book
```

---

### `unarchive` — Restore an archived task

Moves a task from the archive back into your active list.

Format: `unarchive <index>`

Example: `unarchive 1`

```
OK, I have moved this task out of archive for you:
[T][X] read book
```

---

### `archivelist` — Show archived tasks

Shows all tasks currently in the archive.

Example: `archivelist`

```
Here are the tasks in your archive:
1.[T][X] read book
```

---

### `help` — Show all commands

Displays a summary of all available commands.

Example: `help`

```
Here are the commands you can use:
  list                                 - show all tasks
  todo <desc>                          - add a todo task
  deadline <desc> /by <date>           - add a deadline task
  event <desc> /from <date> /to <date> - add an event task
  mark <index>                         - mark a task as done
  unmark <index>                       - mark a task as not done
  delete <index>                       - delete a task
  find <keyword>                       - search tasks by keyword
  archive <index>                      - move a task to archive
  unarchive <index>                    - move a task out of archive
  archivelist                          - show all archived tasks
  bye                                  - exit the app
  help                                 - show this help message
```

---

### `bye` — Exit the app

Closes the application.

Example: `bye`

```
Bye. Hope to see you again soon!
```

---

## Data Storage

Tasks are saved automatically to `data/dark_knight.txt` and archived tasks to `data/archive.txt` in the same directory as the JAR file. No manual saving is needed.
