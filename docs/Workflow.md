# GitHub Workflow Guide for Teammates

## Table of Contents
1. [Cloning the Repository](#cloning-the-repository)
2. [Creating an Issue](#creating-an-issue)
3. [Creating a Branch](#creating-a-branch)
4. [Switching to the New Branch](#switching-to-the-new-branch)
5. [Making Changes and Pushing Code](#making-changes-and-pushing-code)
6. [Creating a Pull Request](#creating-a-pull-request)

---

## Cloning the Repository

If you haven't already cloned the repository:

1. **Code Button**: On the main repo page, you'll see a green "Code" button. Click it.
2. **HTTPS**: Make sure you're on the HTTPS tab and copy the link.
3. **Terminal**: Open your terminal, navigate to where you want the repo, and run:
    ```bash
    git clone https://github.com/Software-Engineering-1-Group-13/Bookstore-API.git
    ```

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
