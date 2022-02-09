# Basic Web Server

A web server written in JAVA for performing some Matrix operations.

## Installation

Use Maven to run the web server.

```bash
mvn clean package -Dmaven.test.skip
java -jar target/leagueChallenge-1.0-SNAPSHOT.jar
```

## Test

```java
mvn test
```

## Usage

Given an uploaded csv file
```
1,2,3
4,5,6
7,8,9
```

1. Echo
    - Return the matrix as a string in matrix format.
```
curl -F 'file=@/path/matrix.csv' "localhost:8080/echo"
```

    ```
    // Expected output
    1,2,3
    4,5,6
    7,8,9
    ```

2. Invert
    - Return the matrix as a string in matrix format where the columns and rows are inverted
```
curl -F 'file=@/path/matrix.csv' "localhost:8080/invert"
```
    ```
    // Expected output
    1,4,7
    2,5,8
    3,6,9
    ``` 
3. Flatten
    - Return the matrix as a 1 line string, with values separated by commas.
```
curl -F 'file=@/path/matrix.csv' "localhost:8080/flatten"
```
    ```
    // Expected output
    1,2,3,4,5,6,7,8,9
    ``` 
4. Sum
    - Return the sum of the integers in the matrix
```
curl -F 'file=@/path/matrix.csv' "localhost:8080/sum"
```
    ```
    // Expected output
    45
    ``` 
5. Multiply
    - Return the product of the integers in the matrix
```
curl -F 'file=@/path/matrix.csv' "localhost:8080/multiply"
```
    ```
    // Expected output
    362880
    ``` 

The input file to these functions is a matrix, of any dimension where the number of rows are equal to the number of columns (square). Each value is an integer, and there is no header row. matrix.csv is example valid input. 


