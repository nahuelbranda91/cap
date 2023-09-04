# Query Price Service

Este proyecto se enfoca en el procesamiento de datos de precios de productos en una base de datos de comercio electrónico. Proporciona un servicio REST para consultar los precios aplicables a un producto de una cadena en función de una fecha dada.

## Tabla de Contenidos

- [Descripción](#descripción)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Contribución](#contribución)


## Descripción

La base de datos contiene una tabla llamada PRICES que almacena información sobre precios y tarifas aplicables a productos de una cadena durante un período de tiempo. Aquí hay un ejemplo de la estructura de la tabla y sus campos relevantes:

PRICES

BRAND_ID
START_DATE
END_DATE
PRICE_LIST
PRODUCT_ID
PRIORITY
PRICE CURR
1 2020-06-14-00.00.00 2020-12-31-23.59.59 1 35455 0 35.50 EUR


Los campos significativos incluyen:

- `BRAND_ID`: clave externa que representa la cadena (1 = ZARA).
- `START_DATE` y `END_DATE`: rango de fechas en el que aplica la tarifa de precios.
- `PRICE_LIST`: identificador de la tarifa de precios aplicable.
- `PRODUCT_ID`: identificador del producto.
- `PRIORITY`: desambiguador de aplicación de precios. Si dos tarifas coinciden en un rango de fechas, se aplica la de mayor prioridad (mayor valor numérico).
- `PRICE`: precio final de venta.
- `CURR`: código ISO de la moneda.

El objetivo del proyecto es construir un servicio Spring Boot que permita consultar estos precios y tarifas mediante una API REST.

## Requisitos

- Java JDK 17 o superior
- Maven
- Base de datos H2 (se inicializará con datos de ejemplo)

## Instalación

1. Clona este repositorio en tu máquina local.
2. Asegúrate de que tienes Java y Maven instalados.
3. Ejecuta `mvn clean install` en la raíz del proyecto para compilar y empaquetar la aplicación.
4. Ejecuta `mvn spring-boot:run` para iniciar la aplicación Spring Boot.

## Uso

La aplicación expone un punto de conexión REST que permite consultar precios. Puedes hacer peticiones a la siguiente URL:

```http
GET http://localhost:8080/api/prices?applicationDate=YYYY-MM-DDTHH:mm:ss&productId=ID_DEL_PRODUCTO&brandId=ID_DE_LA_CADENA
```

```
Example 1: Get the price for product 35455, brand 1 (ZARA) on 2020-06-14T10:00:00.
GET http://localhost:8080/api/prices?application_date=2020-06-14T10:00:00&product_id=35455&brand_id=1

Example 2: Get the price for product 35455, brand 1 (ZARA) on 2020-06-14T16:00:00.
GET http://localhost:8080/api/prices?application_date=2020-06-14T16:00:00&product_id=35455&brand_id=1

Example 3: Get the price for product 35455, brand 1 (ZARA) on 2020-06-14T21:00:00.
GET http://localhost:8080/api/prices?application_date=2020-06-14T21:00:00&product_id=35455&brand_id=1

Example 4: Get the price for product 35455, brand 1 (ZARA) on 2020-06-15T10:00:00.
GET http://localhost:8080/api/prices?application_date=2020-06-15T10:00:00&product_id=35455&brand_id=1

Example 5: Get the price for product 35455, brand 1 (ZARA) on 2020-06-16T21:00:00.
GET http://localhost:8080/api/prices?application_date=2020-06-16T21:00:00&product_id=35455&brand_id=1
```

- `applicationDate`: la fecha de aplicación en formato ISO 8601 (por ejemplo, `2020-06-14T10:00:00`).
- `productId`: el ID del producto que deseas consultar.
- `brandId`: el ID de la cadena a la que pertenece el producto.

La respuesta será un JSON que contiene información sobre el precio aplicable.

En caso de error la Api expone un json con los campos status_code y detail

Example Errors 
```
Example Error 1
http://localhost:8085/api/prices?application_date=2020-06-14T10:00:00&product_id=35455&brand_id=a
Header Status Code: 400
{
"status": "BAD_REQUEST",
"details": "brand_id should be of type long"
}

Example Error 2
http://localhost:8085/api/prices?application_date=2020-06-14T10:00:00&product_id=35455&brand_id=5
Header Status Code: 404
{
"status": "NOT_FOUND",
"details": "Price not found"
}

Example Error 3
Header Status Code: 400
http://localhost:8085/api/prices?application_date=2020-6-14T10:00:00&product_id=35455&brand_id=1
{
"status": "BAD_REQUEST",
"details": "application_date should be of type java.time.LocalDateTime"
}```




