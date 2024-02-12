# Test Automation Assignment

### The main Frameworks included in the project:
1. Selenium Webdriver
2. TestNG
3. Allure Report
   
### Design implementation:
1. Page Object Model (POM) design pattern
2. Data Driven framework
3. Have a supporting Utilities package in src/main/java file path, named "Utils" that includes utility classes and many wrapper methods which services as the core engine for the project
### How to execute the test cases locally:
## Setup
Import or clone the maven project on any java IDE (Eclipse or IntelliJ for example)
A properties file "configurations.properties" can be found it src/main/resources file path including all the configurations needed in the execution (make sure that the "execution.type" 
Can find the test cases in the src/test/java folder inside the phptravels.gui.tests package "JsonPlaceholderTests"
Execution
Can execute each class sperately
Or an find the test suite xml file in the src/test/resources folder inside the TestSuites folder; To start executing the test classes, you can right click on the testng.xml* folder and then click "Run As" > "TestNG Suite"
Reporting
After executing, you can easily generate the Allure Report by opening a commandline terminal on the project root path and type mvn allure:serve (needs to be able to execute mvn commands); 
