-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 24, 2024 at 05:29 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `blood_bank_managment_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `ask`
--

CREATE TABLE `ask` (
  `id` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Bloodtype` varchar(255) NOT NULL,
  `Bloodamount` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `Contact` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ask`
--

INSERT INTO `ask` (`id`, `Name`, `Bloodtype`, `Bloodamount`, `location`, `Contact`) VALUES
(1, 'Ali', 'A+', '1 bottle', 'Karachi', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `donate`
--

CREATE TABLE `donate` (
  `donate_id` int(11) NOT NULL,
  `date_of_donation` varchar(255) NOT NULL,
  `blood_bank_name` varchar(255) NOT NULL,
  `bloodgroup` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `flag` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `donate`
--

INSERT INTO `donate` (`donate_id`, `date_of_donation`, `blood_bank_name`, `bloodgroup`, `contact`, `flag`) VALUES
(1, '2024-01-09', 'KHI Blood Center', 'A+', '032', 1),
(2, '2024-01-23', 'RWP Blood Services', 'AB+', '03241924403', 1);

-- --------------------------------------------------------

--
-- Table structure for table `donor`
--

CREATE TABLE `donor` (
  `donor_id` int(11) NOT NULL,
  `donor_name` varchar(255) NOT NULL,
  `blood_group` varchar(255) NOT NULL,
  `donor_contact_no` varchar(255) NOT NULL,
  `donor_address` varchar(255) NOT NULL,
  `disease` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ask`
--
ALTER TABLE `ask`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `donate`
--
ALTER TABLE `donate`
  ADD PRIMARY KEY (`donate_id`);

--
-- Indexes for table `donor`
--
ALTER TABLE `donor`
  ADD PRIMARY KEY (`donor_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ask`
--
ALTER TABLE `ask`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `donate`
--
ALTER TABLE `donate`
  MODIFY `donate_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `donor`
--
ALTER TABLE `donor`
  MODIFY `donor_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
