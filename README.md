Price Query Service
This Spring Boot application provides a RESTful API for querying price information based on various parameters. The application uses an in-memory H2 database, which is initialized with sample data.

Table of Contents
Introduction
Getting Started
API Endpoints
Request Examples
Testing

Introduction
In our e-commerce company's database, we have a PRICES table that reflects the final price (PVP) and the tariff applied to a product for a brand between specific dates. Here's an example of the relevant table structure:

PRICES Table
BRAND_ID	START_DATE	END_DATE	PRICE_LIST	PRODUCT_ID	PRIORITY	PRICE	CURR
1	2020-06-14-00.00.00	2020-12-31-23.59.59	1	35455	0	35.50	EUR
1	2020-06-14-15.00.00	2020-06-14-18.30.00	2	35455	1	25.45	EUR
1	2020-06-15-00.00.00	2020-06-15-11.00.00	3	35455	1	30.50	EUR
1	2020-06-15-16.00.00	2020-12-31-23.59.59	4	35455	1	38.95	EUR
BRAND_ID: Foreign key representing the brand (1 = ZARA).
START_DATE, END_DATE: Date range during which the price tariff is applicable.
PRICE_LIST: Identifier of the applicable price tariff.
PRODUCT_ID: Product identifier code.
PRIORITY: Priority for price application. In case of overlapping date ranges, the one with the higher priority is applied.
PRICE: Final selling price.
CURR: ISO currency code.
Getting Started
Follow these steps to run the Price Query Service locally:

Clone this repository to your local machine.

bash
Copy code
git clone https://github.com/yourusername/price-query-service.git
Navigate to the project directory.

bash
Copy code
cd price-query-service
Build and run the application using Maven.

bash
Copy code
mvn spring-boot:run
The application will start on port 8090 by default. You can change the port in the application.properties file if needed.

API Endpoints
The Price Query Service provides the following RESTful API endpoints:

GET /api/prices: Retrieve price information based on input parameters.
Request Examples
You can make GET requests to the /api/prices endpoint with the following query parameters:

application_date: The date for which you want to query the price. (e.g., 2020-06-14T00:00:01)
product_id: The product identifier code. (e.g., 35455)
brand_id: The brand identifier. (e.g., 1)
Here are some example requests:

Example 1: Get the price for product 35455, brand 1 (ZARA) on 2020-06-14T10:00:00.

http
GET http://localhost:8090/api/prices?application_date=2020-06-14T10:00:00&product_id=35455&brand_id=1
Example 2: Get the price for product 35455, brand 1 (ZARA) on 2020-06-14T16:00:00.

http
GET http://localhost:8090/api/prices?application_date=2020-06-14T16:00:00&product_id=35455&brand_id=1
Example 3: Get the price for product 35455, brand 1 (ZARA) on 2020-06-14T21:00:00.

http
GET http://localhost:8090/api/prices?application_date=2020-06-14T21:00:00&product_id=35455&brand_id=1
Example 4: Get the price for product 35455, brand 1 (ZARA) on 2020-06-15T10:00:00.

http
GET http://localhost:8090/api/prices?application_date=2020-06-15T10:00:00&product_id=35455&brand_id=1
Example 5: Get the price for product 35455, brand 1 (ZARA) on 2020-06-16T21:00:00.

http
GET http://localhost:8090/api/prices?application_date=2020-06-16T21:00:00&product_id=35455&brand_id=1
Testing
The application includes unit tests to ensure its functionality. You can run the tests using Maven:

bash
mvn test
