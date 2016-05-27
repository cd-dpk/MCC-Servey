CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `category` (`category_id`, `category_name`) VALUES
(1, 'Men'),
(2, 'Women'),
(3, 'Home'),
(4, 'Abroad'),
(5, 'Village'),
(6, 'Others'),
(7, 'SPL');