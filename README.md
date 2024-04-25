# ToolRentalAssessment

This project is a Tool Rental System developed using Spring Boot for assessment. This allows users to rent tools from an H2 database. Users can check out tools by providing necessary inputs such as checkout date, tool code, number of days for rental, and discount percentage. Charges will be calculated based on various factors such as No Charge Days (weekdays, weekends, and holidays) and Discounts as well.

## Features

- **Tool Checkout**: 
		Users can check out tools by providing checkout date, tool code, number of days for rental, and discount percentage.
		Calculate rental charges considering weekdays, weekends, and holidays.
		Apply discounts based on user-specified percentage.
		Generate a rental agreement with detailed information about the rental transaction.
- **H2 Database Integration**: The system is integrated with an H2 in-memory database to store tool information.
- **Spring Boot**: Built using Spring Boot framework for easy configuration and development.
- **RESTful API**: Provides a RESTful API for tool rental operations.

## Installation

1. Clone the repository:

    ```bash
    git clone <repository_url>
    ```
2. Navigate to the project directory:

   ```bash
    cd ToolRental
    ```
3. Build the project:
    ```bash
    /mvnw clean install
    ```
4. Run the application:
    ```bash
    java -jar target/ToolRental.jar
    ```

## Usage

Once the project is set up, you can use the following POST API to checkout the tool and generate the rental agreement for the tool

## API Endpoints

**Checkout a tool**
######  POST api/v1/tool/checkoutTool 

##### Request Body
```json
{
    "toolCode": "JAKR",
    "rentalDays": 4,
    "discountPercentage": 50,
    "checkoutDate": "2020-07-02"
}
```

##### Response Body
```json
{
    "toolCode": "JAKR",
    "toolType": "Ridgid",
    "toolBrand": "DeWalt",
    "rentalDays": 4,
    "discountPercentage": 50,
    "checkoutDate": "2020-07-02",
    "dueDate": "2020-07-06",
    "dailyRentalCharge": 2.99,
    "chargeDays": 1,
    "preDiscountCharge": 2.99,
    "discountAmount": 1.495,
    "finalCharge": 1.495
}
```
## Testing
Unit tests are provided to ensure the functionality of the ToolRentalService. You can run the tests using Maven
```bash
   mvn test
```







