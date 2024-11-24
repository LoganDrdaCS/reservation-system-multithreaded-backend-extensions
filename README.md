# Full Stack Hotel Room Reservation System

## **Overview**
This is a full-stack web application designed for hotel room reservation management. The system provides functionality for reserving rooms, viewing availability, and other core reservation features. It also incorporates new features for time zone-based meeting scheduling and localized welcome messages, which were custom-built to enhance the application's utility and user experience.

The application uses:
- **Spring Boot** for the back-end
- **Angular** for the front-end
- **H2 Database** for development and testing

## **Features**

### **Prebuilt Functionality**
1. **Room Reservation System**:
   - Create, view, and update room reservations.
   - Built-in database support for managing room and reservation data.

2. **RESTful API**:
   - REST endpoints for room and reservation management.
   - Extensible architecture for integrating additional features.

3. **Angular Front-End**:
   - A modern UI for users to interact with the reservation system.
   - Dynamic routing and responsive design.

### **Custom Features**
The following features were designed and implemented to extend the base functionality:

#### 1. **Meeting Invitation with Time Zone Conversion**
- **Files**:
  - `DateTimeController.java`
  - `DateTimeService.java`
- **Description**:
  - Dynamically generates a meeting invitation displaying the current time in **Eastern**, **Mountain**, and **UTC** time zones.
  - Uses Java's `ZonedDateTime` API to convert and format times appropriately.
  - Provides a REST endpoint (`/api/datetime/results`) to fetch the time-converted message.

#### 2. **Localized Welcome Messages**
- **Files**:
  - `WelcomeMessageController.java`
  - `WelcomeMessageService.java`
  - `welcome_message_en_US.properties`
  - `welcome_message_fr_CA.properties`
- **Description**:
  - Loads and serves welcome messages in English (US) and French (Canada) from `.properties` files.
  - Uses a multithreaded service to read and process localization files asynchronously.
  - Exposes a REST endpoint (`/api/welcome/messages`) to fetch localized messages.

#### 3. **Integration with Angular Front-End**
- **Files**:
  - `app.component.ts`
  - `app.component.html`
- **Description**:
  - Added functionality to fetch and display the meeting invitation and welcome messages in the Angular UI.
  - Provided seamless integration with the existing front-end.

## **Project Structure**

### **Key Directories**
- **`src/main/java`**:
  - Contains all Java-based back-end code.
  - Divided into subpackages for configuration, controllers, converters, entities, models, repositories, services, and REST resources.
- **`src/main/resources`**:
  - Configuration files and static resources for the application.
  - Includes localization files for welcome messages.
- **`src/main/UI`**:
  - Angular front-end source code.
  - Includes components, routing, styles, and assets.

### **File Tree (Relevant Sections)**
Below are the key files related to the custom features:

```text
src/main/java/edu/wgu/d387_sample_code/controller
    ├── DateTimeController.java
    ├── WelcomeMessageController.java
src/main/java/edu/wgu/d387_sample_code/service
    ├── DateTimeService.java
    ├── WelcomeMessageService.java
src/main/resources
    ├── welcome_message_en_US.properties
    ├── welcome_message_fr_CA.properties
    ├── welcome_message.properties (empty, fallback file)
src/main/UI/src/app
    ├── app.component.ts
    ├── app.component.html
```

## **How to Run the Application**

### **Prerequisites**
- Java 17+
- Node.js and npm
- Maven

### **Steps**
1. **Back-End Setup**:
   - Navigate to the root directory of the project.
   - Run the Spring Boot application with:
     ```
     mvn spring-boot:run
     ```
   - The back-end server runs on `http://localhost:8080`.

2. **Front-End Setup**:
   - Navigate to `src/main/UI`.
   - Install dependencies with:
     ```
     npm install
     ```
   - Start the Angular development server:
     ```
     ng serve
     ```
   - The front-end runs on `http://localhost:4200`.

3. **Access the Application**:
   - Open `http://localhost:4200` in a browser.
   - Explore the reservation system and custom features.

---

## **API Endpoints**

### **Custom Endpoints**
1. **Meeting Invitation with Time Zone Conversion**:
   - **GET** `/api/datetime/results`
   - Returns a formatted meeting invitation with times in different time zones.

2. **Localized Welcome Messages**:
   - **GET** `/api/welcome/messages`
   - Returns an array of welcome messages (English and French).

---

## **Future Improvements**
- **Encoding Fix**: Resolve the character encoding issue in `welcome_message_fr_CA.properties`.
- **Thread-Safety**: Refactor `WelcomeMessageService` to ensure thread-safety and avoid premature thread termination.
- **Scalability**: Extend `DateTimeService` to support additional time zones.

---

## **Credits**
This project is based on prebuilt functionality for room reservations but was extended with custom features by Logan Drda. These extensions demonstrate skills in back-end development, front-end integration, localization, and time zone handling.

## Contact

Logan Drda