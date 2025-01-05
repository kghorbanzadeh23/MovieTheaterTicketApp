-- Drop the database if it exists and recreate it
DROP DATABASE IF EXISTS MOVIE_THEATRE_APP;
CREATE DATABASE MOVIE_THEATRE_APP;
USE MOVIE_THEATRE_APP;

-- Drop the REGISTERED_USER table if it exists, then create it
DROP TABLE IF EXISTS REGISTERED_USER;
CREATE TABLE REGISTERED_USER (
    ID_no              integer,
    User_Password      varchar(50) not null,
    First_Name         varchar(50) not null,
    Last_Name          varchar(50) not null,
    User_Email         varchar(50) not null,
    User_Bank_Info     integer,
    User_Day           integer,
    User_Month         integer,
    User_Year          integer,
    primary key (ID_no)
);

-- Insert data into REGISTERED_USER table
INSERT INTO REGISTERED_USER (ID_no, User_Password, First_Name, Last_Name, User_Email, User_Bank_Info, User_Day, User_Month, User_Year)
VALUES
    (0, 'pwd', 'Kamand', 'Ghorbanzadeh', 'kghorbanzadeh@gmail.com', 100, 3, 12, 2024),
    (1, 'pwd', 'Spiro', 'Douvis', 'sdouvis@gmail.com', 101, 2, 12, 2024),
    (2, 'pwd', 'Issy', 'Gaudet', 'igaudet@gmail.com', 102, 3, 12, 2024),
    (3, 'pwd', 'Dylan', 'Wenaas', 'dwenaas@gmail.com', 103, 4, 7, 2024);

-- Drop the SCREENING_ROOM table if it exists, then create it
DROP TABLE IF EXISTS SCREENING_ROOM;
CREATE TABLE SCREENING_ROOM (
    ID_no              integer,
    Row_Numbers        integer,
    Column_Numbers     integer,
    Theatre_Number     integer,
    primary key (ID_no)
);

-- Insert data into SCREENING_ROOM table
INSERT INTO SCREENING_ROOM (ID_no, Row_Numbers, Column_Numbers, Theatre_Number)
VALUES
    (300, 8, 7, 400),
    (301, 6, 6, 401),
    (302, 7, 8, 402), 
	(303, 7, 5, 403), 
	(304, 6, 6, 404), 
	(305, 5, 4, 405); 

-- Drop the THEATRE table if it exists, then create it
DROP TABLE IF EXISTS THEATRE;
CREATE TABLE THEATRE (
    ID_no               integer,
    Theatre_Name        varchar(150) not null,
    primary key (ID_no)
);

-- Insert data into THEATRE table
INSERT INTO THEATRE (ID_no, Theatre_Name)
VALUES
    (400, 'AcmePlex Crowfoot'),
    (401, 'AcmePlex University District'),
    (402, 'AcmePlex Cinemas Country Hills'),
    (403, 'AcmePlex Cinemas Market Mall'),
    (404, 'AcmePlex Odeon Sunridge'),
    (405, 'AcmePlex Odeon Westhills');

-- Add foreign key constraint on SCREENING_ROOM table (for Theatre_Number referencing THEATRE)
ALTER TABLE SCREENING_ROOM 
ADD CONSTRAINT fk_screening_room_theatre 
FOREIGN KEY (Theatre_Number) REFERENCES THEATRE(ID_no);

-- Drop the MOVIE table if it exists, then create it
DROP TABLE IF EXISTS MOVIE;
CREATE TABLE MOVIE (
    ID_no              integer,
    Movie_Name         varchar(50) not null,
    Genre              varchar(50) not null,
    Release_Year       integer,
    Director           varchar(50) not null,
    Duration           float,
    Rating             float,
    Movie_Code         varchar(50) not null,
    Movie_Price        float,
    Movie_Description  varchar(200) not null,
    primary key (ID_no)
);

-- Insert data into MOVIE table
INSERT INTO MOVIE (ID_no, Movie_Name, Genre, Release_Year, Director, Duration, Rating, Movie_Code, Movie_Price, Movie_Description)
VALUES
    (200, 'The Adventures of Quantum Cat', 'Sci-Fi', 2023, 'Jane Doe', 120.0, 8.5, 'qcat', 12.99, 'Follow the thrilling journey of a cat that can teleport across galaxies.'),
    (201, 'Romance in the Rain', 'Romance', 2023, 'John Smith', 115.0, 7.2, 'rir', 9.99, 'Two strangers meet during a rainstorm and change each other"s lives forever.'),
    (202, 'Battle of the Titans', 'Action', 2023, 'Michael Brown', 140.0, 8.0, 'bot', 14.99, 'An epic showdown between rival warriors fighting for the fate of their world.'),
    (203, 'The Haunted Melody', 'Horror', 2023, 'Emily Clark', 100.0, 6.9, 'hm', 8.99, 'A cursed music box unleashes terror on those who dare to play its tune.'),
    (204, 'Laugh Out Loud', 'Comedy', 2023, 'Chris Wilson', 90.0, 7.4, 'lol', 7.99, 'A group of friends embarks on a hilarious road trip full of unexpected surprises.'),
    (205, 'Planet Ocean', 'Documentary', 2023, 'Sophia Taylor', 95.0, 8.3, 'po', 10.49, 'Explore the wonders of the deep blue sea and the creatures that call it home.'),
    (206, 'Chasing Shadows', 'Mystery', 2023, 'Robert Davis', 110.0, 7.8, 'cs', 11.99, 'A detective uncovers a series of cryptic clues to solve a decades-old disappearance.'),
    (207, 'The Digital Heist', 'Thriller', 2023, 'David Martinez', 130.0, 7.5, 'tdh', 13.49, 'A group of hackers plans the ultimate cyber robbery.'),
    (208, 'Eternal Echoes', 'Drama', 2023, 'Olivia Moore', 105.0, 7.7, 'ee', 9.49, 'A family struggles to overcome the loss of a loved one while finding hope.');

-- Drop if it exists
DROP TABLE IF EXISTS THEATRE_MOVIE;

-- Create junction table to associate movies with theaters
CREATE TABLE THEATRE_MOVIE (
    Theatre_ID   integer,
    Movie_ID     integer,
    PRIMARY KEY (Theatre_ID, Movie_ID),
    FOREIGN KEY (Theatre_ID) REFERENCES THEATRE(ID_no),
    FOREIGN KEY (Movie_ID) REFERENCES MOVIE(ID_no)
);

-- Insert data into THEATRE_MOVIE table (Association between theatres and movies)
INSERT INTO THEATRE_MOVIE (Theatre_ID, Movie_ID)
VALUES
    (400, 200), -- 'Cineplex Crowfoot' screens 'The Adventures of Quantum Cat'
    (400, 201), -- 'Cineplex Crowfoot' screens 'Romance in the Rain'
    (400, 202), -- 'Cineplex Crowfoot' screens 'Battle of the Titans'
    (401, 203), -- 'Cineplex University District' screens 'The Haunted Melody'
    (401, 204), -- 'Cineplex University District' screens 'Laugh Out Loud'
    (401, 205), -- 'Cineplex University District' screens 'Planet Ocean'
    (402, 206), -- 'Landmark Cinemas Country Hills' screens 'Chasing Shadows'
    (402, 207), -- 'Landmark Cinemas Country Hills' screens 'The Digital Heist'
    (403, 208), -- 'Landmark Cinemas Market Mall' screens 'Eternal Echoes'
    (404, 200), -- 'Cineplex Odeon Sunridge' screens 'The Adventures of Quantum Cat'
    (404, 202), -- 'Cineplex Odeon Sunridge' screens 'Battle of the Titans'
    (405, 201), -- 'Cineplex Odeon Westhills' screens 'Romance in the Rain'
    (405, 203), -- 'Cineplex Odeon Westhills' screens 'The Haunted Melody'
    (405, 206); -- 'Cineplex Odeon Westhills' screens 'Chasing Shadows'

-- Drop the BANK_INFO table if it exists, then create it
DROP TABLE IF EXISTS BANK_INFO;
CREATE TABLE BANK_INFO (
    ID_no          	integer,
    Card_Holder   	varchar(50) not null,
    Card_Number    	varchar(20) not null,
    Expiry_Date varchar(5) not null,
    CVV				integer,
    primary key (ID_no)
);

-- Insert data into BANK_INFO table
INSERT INTO BANK_INFO (ID_no, Card_Holder, Card_Number, Expiry_Date, CVV)
VALUES
	(100, 'Kamand Ghorbanzadeh', '1290283', "02/28", 123),
	(101, 'Spiro Douvis', '732792', "02/28", 244), 
    (102, 'Issy Douvis', '732792', "05/28", 321), 
    (103, 'Spiro Douvis', '732792', "03/28", 456); 
    alter TABLE REGISTERED_USER
    add foreign key (User_Bank_Info) references BANK_INFO(ID_no);
    
DROP TABLE IF EXISTS SHOWS;
CREATE TABLE SHOWS(
	ID_no				integer,
    Movie_ID			integer,
    Screening_Room		integer,
    Showtime			varchar(10),
    Showdate            varchar(10),
    primary key (ID_no), 
    foreign key (Movie_ID) references MOVIE(ID_no), 
    foreign key (Screening_Room) references SCREENING_ROOM(ID_no)
);

-- Insert data into SHOWS table with showtime as a single column
INSERT INTO SHOWS (ID_no, Movie_ID, Screening_Room, Showtime, Showdate)
VALUES
    -- Cineplex Crowfoot shows
    (710, 200, 300, '13:30:00', '05/12/2024'), -- 'The Adventures of Quantum Cat' at Room 300
    (726, 200, 300, '18:15:00', '05/12/2024'), -- 'The Adventures of Quantum Cat' at Room 300
    (727, 200, 300, '21:00:00', '05/12/2024'), -- 'The Adventures of Quantum Cat' at Room 300

    (711, 201, 300, '16:00:00', '05/12/2024'), -- 'Romance in the Rain' at Room 300
    (728, 201, 300, '08:30:00', '05/12/2024'), -- 'Romance in the Rain' at Room 300

    (712, 202, 300, '19:45:00', '05/12/2024'), -- 'Battle of the Titans' at Room 300
    (729, 202, 300, '07:15:00', '05/12/2024'), -- 'Battle of the Titans' at Room 300
    (730, 202, 300, '12:30:00', '05/12/2024'), -- 'Battle of the Titans' at Room 300

    -- Cineplex University District shows
    (713, 203, 301, '14:15:00', '05/12/2024'), -- 'The Haunted Melody' at Room 301
    (731, 203, 301, '17:30:00', '05/12/2024'), -- 'The Haunted Melody' at Room 301

    (714, 204, 301, '17:00:00', '05/12/2024'), -- 'Laugh Out Loud' at Room 301
	(732, 204, 301, '8:15:00', '05/12/2024'), -- 'Laugh Out Loud' at Room 301
	(733, 204, 301, '10:30:00', '05/12/2024'), -- 'Laugh Out Loud' at Room 301

    (715, 205, 301, '20:30:00', '05/12/2024'), -- 'Planet Ocean' at Room 301
	(734, 205, 301, '10:00:00', '05/12/2024'), -- 'Planet Ocean' at Room 301

    -- Landmark Cinemas Country Hills shows
    (716, 206, 302, '11:30:00', '05/12/2024'), -- 'Chasing Shadows' at Room 302
    (717, 207, 302, '14:45:00', '05/12/2024'), -- 'The Digital Heist' at Room 302
    (718, 207, 302, '18:00:00', '05/12/2024'), -- 'The Digital Heist' at Room 302, second show

    -- Landmark Cinemas Market Mall shows
    (719, 208, 303, '12:00:00', '05/12/2024'), -- 'Eternal Echoes' at Room 303
    (720, 208, 303, '16:30:00', '05/12/2024'), -- 'Eternal Echoes' at Room 303, second show

    -- Cineplex Odeon Sunridge shows
    (721, 200, 304, '10:45:00', '05/12/2024'), -- 'The Adventures of Quantum Cat' at Room 304
    (735, 200, 304, '15:45:00', '05/12/2024'), -- 'The Adventures of Quantum Cat' at Room 304

    (722, 202, 304, '13:20:00', '05/12/2024'), -- 'Battle of the Titans' at Room 304

    -- Cineplex Odeon Westhills shows
    (723, 201, 305, '15:00:00', '05/12/2024'), -- 'Romance in the Rain' at Room 305
    (736, 201, 305, '12:30:00', '05/12/2024'), -- 'Romance in the Rain' at Room 305

    (724, 203, 305, '18:15:00', '05/12/2024'), -- 'The Haunted Melody' at Room 305
    (737, 203, 305, '20:30:00', '05/12/2024'), -- 'The Haunted Melody' at Room 305

    (725, 206, 305, '20:00:00', '05/12/2024'); -- 'Chasing Shadows' at Room 305


-- Drop the ANNOUNCEMENTS table if it exists, then create it
DROP TABLE IF EXISTS ANNOUNCEMENTS;
CREATE TABLE ANNOUNCEMENTS (
    ID_no              integer,
    Date_Private_Day   integer,
    Date_Private_Month integer,
    Date_Private_Year  integer,
    Date_Public_Day    integer,
    Date_Public_Month  integer,
    Date_Public_Year   integer,
    Movie_ID           integer,
    primary key (ID_no),
    foreign key (Movie_ID) references MOVIE(ID_no)
);

-- Insert data into ANNOUNCEMENTS table
INSERT INTO ANNOUNCEMENTS (ID_no, Date_Private_Day, Date_Private_Month, Date_Private_Year, Date_Public_Day, Date_Public_Month, Date_Public_Year, Movie_ID)
VALUES
    (800, 15, 11, 2024, 18, 11, 2024, 200), -- 'The Adventures of Quantum Cat' announcement
    (801, 20, 11, 2024, 25, 11, 2024, 201), -- 'Romance in the Rain' announcement
    (802, 1, 12, 2024, 25, 12, 2024, 202), -- 'Battle of the Titans' announcement
    (803, 10, 12, 2024, 15, 12, 2024, 203), -- 'The Haunted Melody' announcement
    (804, 20, 12, 2024, 25, 12, 2024, 204); -- 'Laugh Out Loud' announcement

-- Drop the TICKETS table if it exists, then create it
DROP TABLE IF EXISTS TICKETS;
CREATE TABLE TICKETS(
    ID_no				varchar(10),
    Show_ID				integer,
    User_ID				integer,
    Seat                varchar(5),
    primary key (ID_no),
    foreign key (Show_ID) references SHOWS(ID_no),
    foreign key (User_ID) references REGISTERED_USER(ID_no)
);

-- Insert data into TICKETS table
INSERT INTO TICKETS (ID_no, Show_ID, User_ID, Seat)
VALUES
    (900, 710, 0, 'A1'), -- Kamand Ghorbanzadeh buys a ticket for 'The Adventures of Quantum Cat' at Cineplex Crowfoot
    (901, 726, 0, 'B3'), -- Kamand Ghorbanzadeh buys a ticket for 'The Adventures of Quantum Cat' at Cineplex Crowfoot
    (902, 727, 0, 'C5'), -- Kamand Ghorbanzadeh buys a ticket for 'The Adventures of Quantum Cat' at Cineplex Crowfoot

    (903, 711, 1, 'A2'), -- Spiro Douvis buys a ticket for 'Romance in the Rain' at Cineplex Crowfoot
    (904, 728, 1, 'B4'), -- Spiro Douvis buys a ticket for 'Romance in the Rain' at Cineplex Crowfoot

    (905, 712, 2, 'A3'), -- Issy Gaudet buys a ticket for 'Battle of the Titans' at Cineplex Crowfoot
    (906, 729, 2, 'B5'), -- Issy Gaudet buys a ticket for 'Battle of the Titans' at Cineplex Crowfoot
    (907, 730, 2, 'C6'), -- Issy Gaudet buys a ticket for 'Battle of the Titans' at Cineplex Crowfoot

    (908, 713, 3, 'A4'), -- Dylan Wenaas buys a ticket for 'The Haunted Melody' at Cineplex University District
    (909, 731, 3, 'B2'), -- Dylan Wenaas buys a ticket for 'The Haunted Melody' at Cineplex University District

    (910, 714, 3, 'A5'), -- Dylan Wenaas buys a ticket for 'Laugh Out Loud' at Cineplex University District
    (911, 732, 3, 'B5'), -- Dylan Wenaas buys a ticket for 'Laugh Out Loud' at Cineplex University District
    (912, 733, 3, 'C5'), -- Dylan Wenaas buys a ticket for 'Laugh Out Loud' at Cineplex University District

    (913, 711, 1, 'B5'), -- Guest buys a ticket for 'Romance in the Rain' at Cineplex Crowfoot
    (914, 711, 1, 'B1'), -- Guest buys a ticket for 'Romance in the Rain' at Cineplex Crowfoot
    (915, 711, 1, 'B3'), -- Guest buys a ticket for 'Romance in the Rain' at Cineplex Crowfoot
    (916, 711, 1, 'B2'); -- Guest buys a ticket for 'Romance in the Rain' at Cineplex Crowfoot



-- Drop roles if they exist
DROP ROLE IF EXISTS db_admin@localhost, read_access@localhost, employee_access@localhost;

-- Create roles
CREATE ROLE db_admin@localhost, read_access@localhost, employee_access@localhost;

-- Grant permissions to roles
GRANT ALL PRIVILEGES ON MOVIE_THEATRE_APP.* TO db_admin@localhost;

-- Drop users if they exist
DROP USER IF EXISTS admin@localhost;

-- Create users with passwords
CREATE USER admin@localhost IDENTIFIED WITH mysql_native_password BY 'admin_pass';

-- Grant roles to users
GRANT db_admin@localhost TO admin@localhost;

-- Set default roles for each user
SET DEFAULT ROLE ALL TO admin@localhost;