# Egypt Flight Reservation System 🚀

The **Egypt Flight Reservation System** is an innovative flight booking platform introduced by the **Egyptian Ministry of Tourism**. This Java-based system aims to simplify the flight booking process for citizens while promoting the tourism sector and supporting the country's digital transformation efforts. The platform integrates all airlines into one centralized system, offering a seamless and user-friendly experience for both citizens and airlines.

---

## 🌟 Key Benefits

### 1. For Citizens and Users
- **Improved User Experience**:  
  A unified platform to easily find flights that match their preferences.
  
- **Ease of Access**:  
  Book flights conveniently via phone or computer, eliminating the need for physical visits.

- **Transparency**:  
  Access detailed information about flight prices, schedules, and services for informed decision-making.

- **Wide Range of Options**:  
  Compare flights based on:  
  - Cost  
  - Timing  
  - Premium points earned  
  - Available seats  
  - Airline ratings  

- **Airline Ratings**:  
  Citizens can rate airlines and provide feedback, promoting accountability and service improvement.

- **Premium Points Feature**:  
  - New users receive **1,000 Premium Points** upon account creation (equivalent to a 10% discount on their first booking).  
  - Additional points are earned based on airline-specific rewards, encouraging loyalty.

---

### 2. For Airlines
- **Increased Customer Reach**:  
  Smaller airlines get access to a larger audience, enhancing competitiveness with bigger airlines.

- **Centralized Marketing**:  
  Use the platform to promote offers and services effectively.

- **Enhanced Competitiveness**:  
  Fair competition between airlines drives improvements in service quality.

---

### 3. For the Ministry of Tourism and National Economy
- **Tourism Promotion**:  
  Simplifies flight bookings for both citizens and foreign visitors, boosting tourism.

- **Revenue Generation**:  
  Nominal fees on reservations or airline partnerships contribute to the national treasury.

- **Data-Driven Insights**:  
  Provides valuable data on travel trends and user preferences, aiding in tourism infrastructure planning.

- **Facilitated Collaboration**:  
  Enhances coordination between airlines and the ministry to improve services.

---

### 4. Technical Advantages
- **Centralized Services**:  
  Consolidates all airlines into a single platform, increasing efficiency and reducing distractions for users.

- **Support for Digital Transformation**:  
  Aligns with Egypt's strategy to digitize government services and make them accessible online.

---

### 5. For Society
- **Job Creation**:  
  Creates technical and administrative job opportunities during the platform's development and operation.

- **Enhanced Public Trust**:  
  By offering a convenient government service, this application strengthens trust in government institutions.

---

## 🛠️ User Roles and Permissions

The system categorizes users into three distinct roles, each with specific permissions and functionalities:

### 1. **Main Admin (Ministry of Tourism Account)**  
The **Main Admin** is managed by the Ministry of Tourism and has the highest level of control over the application. Responsibilities include:

- **Admin Management**:  
  - Add new airline admins.  
  - Show and remove airline admins.  

- **Airport Management**:  
  - Add new airports.  
  - Show and remove airports.  

- **Airline Management**:  
  - Show and remove airlines.  

- **User Management**:  
  - Show and remove users.  

---

### 2. **Airline Admin**
The **Airline Admin** manages flights and airline operations. Permissions include:

- **Flight Management**:  
  - Add new flights.  
  - Remove flights.  
  - Update flight schedules.  
  - Update available seat numbers.  
  - View all flights.  

- **Ratings and Reviews**:  
  - View ratings and reviews for their airline.  

---

### 3. **User and Customer**
Users and customers have access to the platform’s core services for booking and managing their accounts. Functionalities include:

- **Flight Services**:  
  - View all available flights.  
  - Book flights.  

- **Profile Management**:  
  - View and edit profile information.  

- **Payment Management**:  
  - Add and manage payment methods.  

- **Booking History**:  
  - View booking history.  

- **Feedback and Reviews**:  
  - Rate airlines and submit reviews.  

---

## 🔧 Technical Details

- **Design Patterns**:  
  - Implements **MVC (Model-View-Controller)** for modular application design.  
  - Uses the **Singleton Design Pattern** to Database Class.
   
- **Professional High Level File Handling**

- **Efficient Database (Class) Handling**:  
  - A dedicated `Database` class is designed with an efficient and scalable structure to manage data effectively.  
  - **Data Structures Used**:  
    - **TreeMap**: Applied for admin operations and accessing user information in a sorted and efficient manner.  
    - **HashMap**: Used to quickly access specific flights or seat details.  
    - **HashSet**: Utilized to store and display all ratings and reviews for specific airlines.  

  - **Pair and Tuple Implementation**:  
    - Custom `Pair` and `Tuple` classes are created in Java to improve data organization and structure, supporting complex operations efficiently.

- **Complexity Efficiency**:  
  - **Searching Flights**:  
    - Time complexity: **O(n log(m))**, where `n` is the number of flights, and `m` is the number of Departure Dates.  
  - **Accessing Client Information**:  
    - Time complexity: **O(n log(m))**, enabling efficient retrieval of user data, booking history, and payment methods.  

- **Codebase**:  
  - **Number of Classes**: 43  
  - **Lines of Code**: ~4,300

## 🚀 How to Run the Project

1. **Clone the Repository**:  
   ```bash
   git clone https://github.com/AnsElwkel/Flight_Reservation_Project.git
   ```

2. **Navigate to the Project Directory**:  
   ```bash
   cd Flight_Reservation_Project
   ```
3. **Change Root Path Folder**:
    Go in `FileAdminstrator` in `FileHandling` package
    and Change Root Path variable to abs path of `Files` folder + '/'
 
4. **Compile the Code**:  
   Ensure you have Java installed, and compile the code:  
   ```bash
   javac -d bin src/*.java
   ```

5. **Run the Application**:  
   ```bash
   java -cp bin Main
   ```

---

## 📂 Project Structure

```
egypt-flight-reservation-system/
├── src/
│   ├── Files/
│   ├── main/
│       ├── java/
│           ├── com.egyptFlightReservation/
│               ├── Controller
│               ├── Model
│               ├── View
│           ├── TestDrivers/
│           ├── Tools/ 
└── README.md
```

---
