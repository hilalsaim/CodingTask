# Telnyx

This project is console application.
I used maven in the project. Because it is useful for JARs and other dependencies. 
We can easily build a project using maven.

## Getting Started

You can run the project directly from the Main class.

## Project structure

* We have controller and model file
* Models in the model file
* In the project we have 2 model classes named Device and Port
* Conrollor file contains classes for read and write operations
* In the DeviceReader class, we read the Vlans from the file
* We read the requests from the file in the RequestReader class
* We create our output file in the RequestWriter class.
* We use RedundantReserve or NonRedundantReserve classes according to the type of request.

## Running the tests

* There are test classes in the test file
* ProjectTest class has test of reading and writing operations
* The model file contains the DeviceBuilder class. Here we create new device for testing
* The controller file contains the RedundantReserveTest class. Here are tests for the redundantReserve class



## Input Files

* Vlans.csv
* Requests.csv
