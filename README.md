# EntrataWebsiteTest

# Overview
This project contains automated test cases written using Selenium WebDriver and TestNG for testing the functionality of the Entrata website.

# Project Structure
.
├── src
│   └── test
│       └── java
│           └── com
│               └── automation
│                   └── EntrataTests.java
└── pom.xml

# EntrataTests.java
The test file EntrataTests.java contains multiple test cases:

- testHomePageTitle(): Verifies the title of the Entrata homepage.
- testMultifamilyChallengesCards(): Verifies the number and titles of cards in the "Overcome the biggest challenges in multifamily" section.
- testNavigateToBlogPage(): Navigates to the Blog page from the Resources menu and verifies successful navigation.
- testSignInResidentLogin(): Navigates from the "Sign In" page to "Resident Login" and then to the "Resident Portal", verifying each transition.

# Tools Used
Selenium WebDriver: Browser automation.
TestNG: Test framework to structure and run the tests.
Maven: Dependency management and project build tool.
WebDriverManager: Automatically manages browser driver binaries.

# Setup Instructions
# 1.Clone the repository:
git clone <repository-url>

# 2.Navigate to the project directory:
cd <project-directory>

# 3.Install dependencies:
The pom.xml file includes the dependencies for Selenium, TestNG, and WebDriverManager. Run the following command to install them:
mvn clean install

# 4.TestNG Configuration:
TestNG is already configured as part of the project setup. No additional configuration is required.

# Running the Tests

# Option 1: Running Tests via Maven
You can run the test cases using the mvn command:
mvn test

This will execute all the tests in the EntrataTests.java file. Maven will automatically download any required dependencies and run the TestNG tests.

# Option 2: Running Tests via an Eclipse
1.Open the project in your preferred IDE (Eclipse or IntelliJ).
2.Build the project to ensure all dependencies are resolved.
3.Run the tests:

Right-click on the EntrataTests.java file and choose Run As > TestNG Test (in Eclipse)
