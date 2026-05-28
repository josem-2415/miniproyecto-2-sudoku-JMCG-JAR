# Sudoku 6x6 - JavaFX

## Description

Sudoku 6x6 is a desktop application developed in Java using JavaFX and the MVC architectural pattern.  
The project implements a functional Sudoku game with a modern graphical interface, timer system, hints, undo functionality, input validation, pause mode, and dynamic highlighting of rows, columns, blocks, and equal numbers.

The game uses a **6x6 Sudoku board** with **2x3 blocks** instead of the traditional 9x9 format.

---

# Features

- Dynamic generation of Sudoku boards
- Validation of valid and invalid moves
- Interactive graphical interface using JavaFX
- 6x6 board with 2x3 blocks
- Keyboard and keypad input support
- Pause system
- Game timer
- Undo movements
- Erase cell content
- Hint system
- Win detection
- Highlighting system:
  - Selected cell
  - Related row and column
  - Related 2x3 block
  - Equal numbers
- Visual differentiation between:
  - Fixed numbers
  - Hint numbers
  - User-entered numbers

---

# Technologies Used

- Java 17 / JavaFX
- FXML
- CSS3
- MVC Architecture
- IntelliJ IDEA

---

# Project Structure

```text
src/
│
├── controller/
│   ├── GameController.java
│   ├── MenuController.java
│   └── PauseController.java
│
├── model/
│   ├── Cell.java
│   ├── GameModel.java
│   └── Move.java
│
├── view/
│   ├── GameStage.java
│   ├── MenuStage.java
│   ├── PauseStage.java
│   └── IView.java
│
├── FXML/
│   ├── game-view.fxml
│   ├── menu-view.fxml
│   └── pause-view.fxml
│
├── stilesCSS/
│   ├── style.css
│   └── style2.css
│
└── Main.java
```

---

# Installation

## Requirements

- JDK 17 or higher
- JavaFX SDK
- IntelliJ IDEA (recommended)

---

# Clone the repository

```bash
git clone https://github.com/your-user/sudoku-javafx.git
```

---

# Open the project

Open the project in IntelliJ IDEA.

---

# Configure JavaFX

Add the JavaFX SDK to the project libraries and VM options.

Example VM options:

```bash
--module-path "PATH_TO_FX/lib" --add-modules javafx.controls,javafx.fxml
```

---

# Run the project

Execute the `Main.java` file.

---

# Gameplay

## Objective

Complete the 6x6 Sudoku board ensuring that:

- Each row contains numbers from 1 to 6
- Each column contains numbers from 1 to 6
- Each 2x3 block contains numbers from 1 to 6
- No number repeats within its row, column, or block

---

# Controls

| Action | Description |
|---|---|
| Mouse Click | Select a cell |
| Keyboard 1-6 | Insert numbers |
| Delete / Backspace | Clear selected cell |
| Undo | Revert previous move |
| Hint | Reveal one correct value |
| Pause | Pause the game |
| New Game | Generate a new Sudoku |

---

# Visual System

| Cell Type | Description |
|---|---|
| Fixed numbers | Bold dark blue |
| User numbers | Normal blue |
| Hint numbers | Green |
| Selected cell | Gold highlight |
| Related row/column/block | Light blue |
| Same numbers | Medium blue |

---

# Architecture

The project follows the MVC pattern:

- **Model** → Game logic and validation
- **View** → JavaFX stages and FXML files
- **Controller** → User interaction and communication between model and view

---

# Authors

Developed by:

- Jose Manuel Cardona Gil

---

# License

This project is for academic and educational purposes.
