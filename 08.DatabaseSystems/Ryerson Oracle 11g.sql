CREATE TABLE HR_Manager
(
    ID       VARCHAR2(50) PRIMARY KEY,
    Password VARCHAR2(255) NOT NULL
);

CREATE TABLE Employee
(
    ID            VARCHAR2(50) PRIMARY KEY,
    Name          VARCHAR2(255) NOT NULL,
    Banking_Info  VARCHAR2(255) NOT NULL,
    HR_Manager_ID VARCHAR2(50) NOT NULL,
    FOREIGN KEY (HR_Manager_ID) REFERENCES HR_Manager (ID)
);

CREATE TABLE Part_Time_Employee
(
    Employee_ID  VARCHAR2(50) PRIMARY KEY,
    Pay_Rate     NUMBER NOT NULL,
    Hours_Worked NUMBER NOT NULL,
    FOREIGN KEY (Employee_ID) REFERENCES Employee (ID)
);

CREATE TABLE Full_Time_Employee
(
    Employee_ID  VARCHAR2(50) PRIMARY KEY,
    Fixed_salary NUMBER NOT NULL,
    FOREIGN KEY (Employee_ID) REFERENCES Employee (ID)
);

CREATE TABLE Salary
(
    ID           NUMBER PRIMARY KEY,
    Gross_Pay    NUMBER NOT NULL,
    Vacation_Pay NUMBER NOT NULL,
    Sick_Pay     NUMBER NOT NULL
);

CREATE TABLE Final_Payment
(
    Transaction_ID NUMBER PRIMARY KEY,
    Net_Pay        NUMBER NOT NULL,
    Salary_ID      NUMBER NOT NULL,
    Employee_ID    VARCHAR2(50) NOT NULL,
    FOREIGN KEY (Salary_ID) REFERENCES Salary (ID),
    FOREIGN KEY (Employee_ID) REFERENCES Employee (ID)
);

CREATE TABLE Taxes
(
    ID                   NUMBER PRIMARY KEY,
    Employment_Insurance NUMBER NOT NULL,
    Canada_Pension_Plan  NUMBER NOT NULL,
    Provincial_Taxes     NUMBER NOT NULL,
    Federal_Taxes        NUMBER NOT NULL,
    Final_Payment_ID     NUMBER NOT NULL,
    FOREIGN KEY (Final_Payment_ID) REFERENCES Final_Payment (Transaction_ID)
);

CREATE TABLE Time_Off_Requests
(
    ID            NUMBER PRIMARY KEY,
    Start_Date    DATE NOT NULL,
    End_Date      DATE NOT NULL,
    Status        VARCHAR2(50) CHECK (Status IN ('Pending', 'Approved', 'Denied')) NOT NULL,
    HR_Manager_ID VARCHAR2(50) NOT NULL,
    FOREIGN KEY (HR_Manager_ID) REFERENCES HR_Manager (ID)
);

CREATE TABLE Benefits
(
    ID              NUMBER PRIMARY KEY,
    Benefit_Type    VARCHAR2(255) NOT NULL,
    Benefit_Details VARCHAR2(255) NOT NULL,
    Benefit_Cost    NUMBER NOT NULL
);

CREATE TABLE Overtime
(
    ID              NUMBER PRIMARY KEY,
    Hours           NUMBER NOT NULL,
    Rate_Multiplier NUMBER NOT NULL,
    Salary_ID       NUMBER NOT NULL,
    HR_Manager_ID   VARCHAR2(50) NOT NULL,
    FOREIGN KEY (Salary_ID) REFERENCES Salary (ID),
    FOREIGN KEY (HR_Manager_ID) REFERENCES HR_Manager (ID)
);

CREATE TABLE Bonuses
(
    ID            NUMBER PRIMARY KEY,
    BonusType     VARCHAR2(50) CHECK (BonusType IN ('Type1', 'Type2')) NOT NULL,
    BonusAmount   NUMBER NOT NULL,
    Salary_ID     NUMBER NOT NULL,
    HR_Manager_ID VARCHAR2(50) NOT NULL,
    FOREIGN KEY (Salary_ID) REFERENCES Salary (ID),
    FOREIGN KEY (HR_Manager_ID) REFERENCES HR_Manager (ID)
);

-- Many-to-many relationship tables
CREATE TABLE Employee_Time_Off_Requests
(
    Employee_ID         VARCHAR2(50) NOT NULL,
    Time_Off_Request_ID NUMBER NOT NULL,
    PRIMARY KEY (Employee_ID, Time_Off_Request_ID),
    FOREIGN KEY (Employee_ID) REFERENCES Employee (ID),
    FOREIGN KEY (Time_Off_Request_ID) REFERENCES Time_Off_Requests (ID)
);

CREATE TABLE Full_Time_Employee_Benefits
(
    Full_Time_Employee_ID VARCHAR2(50) NOT NULL,
    Benefit_ID            NUMBER NOT NULL,
    PRIMARY KEY (Full_Time_Employee_ID, Benefit_ID),
    FOREIGN KEY (Full_Time_Employee_ID) REFERENCES Full_Time_Employee (Employee_ID),
    FOREIGN KEY (Benefit_ID) REFERENCES Benefits (ID)
);

CREATE TABLE Part_Time_Employee_Overtime
(
    Part_Time_Employee_ID VARCHAR2(50) NOT NULL,
    Overtime_ID           NUMBER NOT NULL,
    PRIMARY KEY (Part_Time_Employee_ID, Overtime_ID),
    FOREIGN KEY (Part_Time_Employee_ID) REFERENCES Part_Time_Employee (Employee_ID),
    FOREIGN KEY (Overtime_ID) REFERENCES Overtime (ID)
);

-- Creating sequences for auto-incrementing primary keys
CREATE SEQUENCE hr_manager_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE employee_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE salary_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE final_payment_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE taxes_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE time_off_requests_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE benefits_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE overtime_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE bonuses_seq START WITH 1 INCREMENT BY 1;
