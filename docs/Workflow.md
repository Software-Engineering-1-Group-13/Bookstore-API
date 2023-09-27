# GitHub Workflow Guide for Teammates

## Table of Contents
1. [Folder Management](#folder-management-guidelines)
2. [Cloning the Repository](#cloning-the-repository)
3. [Creating an Issue](#creating-an-issue)
4. [Creating a Branch](#creating-a-branch)
5. [Switching to the New Branch](#switching-to-the-new-branch)
6. [Making Changes and Pushing Code](#making-changes-and-pushing-code)
7. [Creating a Pull Request](#creating-a-pull-request)

---

## Folder Management Guidelines

Proper folder management is essential for streamlining the development workflow. Follow these steps for each issue you work on:

### Step 1: Create Root Project Folder

Firstly, create a root folder for your project. The folder name should match the name of the GitHub repository. To do this via terminal, run:
```bash
mkdir Bookstore-API-Project
```

### Step 2: Navigate to Root Folder

Get inside your root folder using the `cd` command:
```bash
cd Bookstore-API-Project
```

### Step 3: Create Issue-Specific Folder

Inside the root folder, create a new folder with the same name as your issue prefix. For instance, if you are working on issue `BSAPI-10`, create a folder named `BSAPI-10`:
```bash
mkdir BSAPI-10
```

Then get inside of that folder:
```bash
cd BSAPI-10
```

### Step 4: Clone the Repository

For every new issue you work on, clone the repository again. Given that this is a small-scale project, cloning multiple times should not be a problem. To clone, run:
```bash
git clone https://github.com/Software-Engineering-1-Group-13/Bookstore-API.git
```

### Step 5: Navigate to the Repository Folder

Navigate to the cloned repository:
```bash
cd Bookstore-API
```

### Step 6: Open the Project in IntelliJ IDEA

Finally, open the repository folder through IntelliJ IDEA. This will automatically download any dependencies and set up the project for you.

To open the project, either:
- Use the IntelliJ IDEA interface and navigate to `File -> Open`, then choose the `Bookstore-API` folder.
- Or drag and drop the `Bookstore-API` folder into IntelliJ IDEA.

By following these steps, you'll maintain a clean and organized workspace for each issue you tackle.



## Cloning the Repository

If you haven't already cloned the repository:

1. **Code Button**: On the main repo page, you'll see a green "Code" button. Click it.
2. **HTTPS**: Make sure you're on the HTTPS tab and copy the link.
3. **Terminal**: Open your terminal, navigate to where you want the repo, and run:
    ```bash
    git clone https://github.com/Software-Engineering-1-Group-13/Bookstore-API.git
    ```

---

## How to Pull Branch Updates

To receive the changes that were made to all the branches since the last time you pulled/cloned the repo use:

   ```bash
   git pull
   ```

**IMPORTANT**: If you are using this git command while working on a same branch with colleague, you might get merge conflicts if your colleague has pushed any code.  

---

## Creating an Issue

1. **Go to GitHub Repo**: Navigate to the repository (Repo) on GitHub.
2. **Issue Tab**: Click on the "Issues" tab next to "Code".
3. **New Issue**: Click on the "New Issue" button.
4. **Template & Details**: Choose a template (`API Feature Template` is available), or write your own issue details.
    - **Title**: Change the `#` (which means number) to a number, and the give small description of feature you are going to implement.
    - **Description**: Replace all the explanation text with your feature explanation text in all sections (Keep the format consistent)
    - **Assignees**: On the right sidebar, If you are creating this issue for yourself click `assign yourself`, if not click `Assignees` and list of collaborators will pop up, then assign them to the issue.
    - **Labels**: Under `Assignees`, you’ll see a "Labels" section. Click and select appropriate labels for your issue (Time interval label, and label for what kind of issue this is, it would normally be `feature` label).
    - **Projects**: Find the project that his issue belongs to, it will be named normally `Bookstore-API: Sprint-# (from date - to date)`
    - **Milestone**: Underneath `Projects`, you’ll see "Milestone". Click and assign a milestone if it aligns with one (`API Implementation` is the current milestone we use).
5. **Submit**: Click on "Submit new issue".

---

## Creating a Branch

After you've created an issue, it's good practice to create a new branch for that issue.

1. **While in issue**: on bottom left on the right sidebar, there is section called development, click "create branch".
2. **Branch creation**: There are 3 sections you need to decide on:
    1. **Branch Name**: Delete the `number` in front and the `-`, and leave the rest same.
    2. **Repository Destination**: If you are creating out of master, leave it as is, if not: 
        1. Click **Change branch source**
        2. Choose the branch you want to branch out of.
    3. **What's next?**: out of the 3 options choose **Checkout locally**
3. **Create branch**: Click the `Create branch button.
    1. After clicking, a pop-up window will appear that will help you `fetch` and `checkout` to the next branch.

---

## Switching to the New Branch

1. **Terminal**: Navigate to your cloned repo directory.
2. **Fetch and Checkout**: Run the following commands to fetch the latest branches and switch to your new one.
    ```bash
    git fetch
    git checkout [your-branch-name]
    ```

---

## Making Changes and Pushing Code

1. **Code**: Make your code changes.
2. **Terminal**: Navigate to your repo directory.
3. **Commit**: Stage and commit your changes:
    ```bash
    git add .
    git commit -m "Your commit message"
    ```
4. **Push**: Push the changes to the remote repo:
    ```bash
    git push origin [your-branch-name]
    ```

---

## Creating a Pull Request

1. **Go to GitHub Repo**: Navigate back to the repo on GitHub.
2. **Pull Requests Tab**: Click the "Pull Requests" tab next to "Code".
3. **New Pull Request**: Click on "New Pull Request".
4. **Branches**: In the "compare" dropdown, choose the branch you were working on.
5. **Review & Create**: Review your changes and then click "Create Pull Request".
