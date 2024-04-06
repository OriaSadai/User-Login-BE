DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS favorite_list;
DROP TABLE IF EXISTS order_list;
DROP TABLE IF EXISTS order_item_list;

CREATE TABLE item (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    name varchar(20) NOT NULL DEFAULT '',
    description text(900),
    small_url_picture varchar(300),
    big_url_picture varchar(300),
    in_stock int(11) NOT NULL DEFAULT 0,
    price DECIMAL(100,2) NOT NULL DEFAULT '',
    PRIMARY KEY (id)
);

CREATE TABLE user (
     id int(11) unsigned NOT NULL AUTO_INCREMENT,
     first_name varchar(300),
     last_name varchar(300) DEFAULT 'unknown customer',
     email varchar(300),
     phone_number varchar(300),
     address varchar(300),
     username varchar(20) NOT NULL DEFAULT '',
     password varchar(20) NOT NULL DEFAULT '',
     active tinyint(1) NOT NULL DEFAULT '1',
     roles varchar(200) NOT NULL DEFAULT '',
     permissions varchar(200) NOT NULL DEFAULT '',
     PRIMARY KEY (id)
);

CREATE TABLE favorite_list (
    item_id int(11) unsigned NOT NULL,
    user_id int(11) unsigned NOT NULL,
    PRIMARY KEY (item_id, user_id),
    FOREIGN KEY (item_id) REFERENCES item (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE order_list (
    id int(11) unsigned NOT NULL AUTO_INCREMENT,
    user_id int(11) NOT NULL,
    order_date DATE NOT NULL DEFAULT CURRENT_DATE,
    shipping_address varchar(300),
    total_price DECIMAL(100,2) NOT NULL DEFAULT 0.0,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE order_item_list (
    order_id int(11) unsigned NOT NULL,
    item_id int(11) unsigned NOT NULL,
    quantity int(11),
    PRIMARY KEY (order_id, item_id),
    FOREIGN KEY (order_id) REFERENCES order_list (id),
    FOREIGN KEY (item_id) REFERENCES item (id)
);

INSERT INTO item (name, description, small_url_picture, big_url_picture, in_stock, price) VALUES
('teddy bear','A teddy bear is a stuffed toy in the form of a bear. Developed apparently simultaneously by toymakers Morris Michtom in the U.S. and Richard Steiff under his aunt Margarete Steiffs company in Germany in the early 20th century, the teddy bear, named after President Theodore Roosevelt, became a popular childrens toy and has been celebrated in story, song, and film.','https://media.istockphoto.com/id/1403599412/photo/cute-teddy-cute-teddy-bear-isolated-on-white-background.jpg?s=2048x2048&w=is&k=20&c=Wou-E4r0Rxgrxb5Or1J8uPiqB2-fODmiqnn4zSsYC4M=','https://media.istockphoto.com/id/1543977313/photo/concept-of-childhood-big-plush-teddy-bear-sitting-alone-on-green-grass-lawn-in-summer.jpg?s=2048x2048&w=is&k=20&c=e0UIyEiyNiZtidMVLFeDtvCjkB3djWL3RaDNT0PjdX4=',20,85.6),
('hp laptop','Shop a wide range of HP laptops at the official HP® Store. Find the perfect laptop for work, gaming, or everyday use. Enjoy free shipping on all orders.','https://media.istockphoto.com/id/519518538/photo/windows-8-1-on-hp-pavilion-ultrabook.jpg?s=2048x2048&w=is&k=20&c=X5KHqNYH0KT1JvQst6HPqIJp9ijUyYhAu3zfAyOfZWk=','https://media.istockphoto.com/id/498569393/photo/windows-8-1-on-hp-pavilion-ultrabook.jpg?s=2048x2048&w=is&k=20&c=hMIt4AyLS7aq4H4AJr3p2bEey3adJVLdw_s33DZJVjc=',9,2100),
('bicycle','Bicycle, two-wheeled steerable machine that is pedaled by the rider’s feet. On a standard bicycle the wheels are mounted in-line in a metal frame, with the front wheel held in a rotatable fork.','https://media.istockphoto.com/id/1420196426/photo/bicycle-blue-sky-isolated-on-white-background-with-clipping-path.jpg?s=2048x2048&w=is&k=20&c=dSmwQJRsWVRqDH_c34f7QaqfabHePPHxRKakItd_0AM=','https://media.istockphoto.com/id/1479489385/photo/blue-retro-bicycle-with-brown-saddle-and-handles-generic-bike-side-view.jpg?s=2048x2048&w=is&k=20&c=Aar-Bbe80JZ6KydyLo_cb82tNEK3sNq_NpEmDYWfVv0=',4,5990.85),
('samsung tv','Explore TV models ranging from 4K & 8K Samsung Neo QLED, QLED, The Frame, OLED, 4K UHD and more with curved & flat screens. Find the best new Samsung Smart','https://media.istockphoto.com/id/529869211/photo/samsung-smart-tv-and-social-media.jpg?s=2048x2048&w=is&k=20&c=AYipnnKEl7Z2u8BD5uWVdqPHyEIyeExEN5GGiQUzpBo=','https://media.istockphoto.com/id/483551726/photo/samsung-smart-tv-logo.jpg?s=2048x2048&w=is&k=20&c=0e1c4Nn4MfFk45jZWA1HEGBJTDaSqcVF4E7xsIWL4C4=',20,950),
('scooter','You ve arrived at the right place! The City bug Koning 48V is considered the most reliable scooter in the world. The only scooter in the world with a two-year warranty','https://media.istockphoto.com/id/1444115644/photo/electric-scooter-isolated-on-white.jpg?s=2048x2048&w=is&k=20&c=QpiS1ecx4Ruao4jAejkP-OBurihd4go-rbYWqTYJMCU=','https://media.istockphoto.com/id/1566575285/photo/student-on-scooter-outside-on-fresh-air.jpg?s=2048x2048&w=is&k=20&c=K1xa_2q-GWBN3OO0FW5puapsvrKZSnA7HkqB3q6xqtM=',8,2002.23),
('basketball','Basketball is a team sport in which two teams, most commonly of five players each, opposing one another on a rectangular court, compete with the primary objective of shooting a basketball (approximately 9.4 inches (24 cm) in diameter) through the defender s hoop (a basket 18 inches (46 cm) in diameter mounted 10 feet (3.048 m) high to a backboard at each end of the court), while preventing the opposing team from shooting through their own hoop.','https://media.istockphoto.com/id/1636022764/photo/basketball-ball.jpg?s=2048x2048&w=is&k=20&c=-OfPLc9FOl9Jehwrs3Ldvj5xBk3ttfNXY1-nHSbHhTk=','https://media.istockphoto.com/id/1480105317/photo/close-up-image-of-basketball-ball-over-floor-in-the-gym-orange-basketball-ball-on-wooden.jpg?s=2048x2048&w=is&k=20&c=L51G89q0QQNiLjHKVSPK9fu0JyZTyWOgfUOcyF3Bfuc=',50,15.9),
('baseball','To review, baseball is a popular sport where each team tries to hit a baseball and run around three bases in order to score a run. Whichever team scores the most runs in nine innings wins the game.','https://media.istockphoto.com/id/529982024/photo/baseball-on-white.jpg?s=2048x2048&w=is&k=20&c=8mBos74ujOp56EJcSc88Sxc_649exzKnFcKdWFmiHhA=','https://media.istockphoto.com/id/1471217278/photo/baseball-ball-in-a-grass-of-baseball-arena-stadium.jpg?s=2048x2048&w=is&k=20&c=WGdlhF-R9zkdE0mZvKYnVOK9L3SFR1LuzbEUWYggf3c=',38,10.9),
('baseball bat + ball','To review, baseball is a popular sport where each team tries to hit a baseball and run around three bases in order to score a run. Whichever team scores the most runs in nine innings wins the game.','https://media.istockphoto.com/id/818317166/photo/baseball-equipment.jpg?s=2048x2048&w=is&k=20&c=GId0y9sRYBCBOrIXZUVsljia5OFhxh7ZrPyCqe9WZek=','https://media.istockphoto.com/id/1449631889/photo/baseball-baseball-player-and-bat-ball-swing-at-a-baseball-field-during-training-fitness-and.jpg?s=2048x2048&w=is&k=20&c=YKjm0O5LPONHAy1HM_MKOoIlVSv7TQp6oWiiTOqDS4w=',3,26.9),
('3 pink tennis ball','Tennis is a good sport for maintaining health, fitness, strength and agility. It also has social and psychological benefits. You can play with a club or with friends and family as a social activity. Make sure you have plenty of fluids on hand and rehydrate regularly.','https://media.istockphoto.com/id/185236215/photo/tennis-balls.jpg?s=2048x2048&w=is&k=20&c=IlQnDJmcskn3A8xeSVO8lL9qBm7R8xEJfkcjKhT5AYI=','https://media.istockphoto.com/id/1934503513/photo/pink-tennis-ball-with-shadow-on-a-pink-background.jpg?s=2048x2048&w=is&k=20&c=FbLHMFqfXKh0Gyv4BiOwQjlPvUd7XQP9VRRPWa0Ifwk=',19,7.9),
('tennis racket','Tennis is a good sport for maintaining health, fitness, strength and agility. It also has social and psychological benefits. You can play with a club or with friends and family as a social activity. Make sure you have plenty of fluids on hand and rehydrate regularly.','https://media.istockphoto.com/id/1284445875/vector/tennis-racket-icon-on-transparent-background.jpg?s=2048x2048&w=is&k=20&c=LOPsZ8Sh9nz9OSenKB3X9iwv6DC00fLgEHPU0evxBGA=','https://media.istockphoto.com/id/1429410523/photo/tennis-racket-and-tennis-ball-isolated-on-white-background.jpg?s=2048x2048&w=is&k=20&c=LsZ4yKU12MjwMqa0CrutCME2IFs3R-BO80jzZ53BrW0=',10,6.95),
('football','Football is a family of team sports that involve, to varying degrees, kicking a ball to score a goal. Unqualified, the word football normally means the form of football that is the most popular where the word is used. Sports commonly called football include association football (known as soccer in Australia, Canada, South Africa, the United States, and sometimes in Ireland and New Zealand); Australian rules football; Gaelic football; gridiron football (specifically American football, Arena football, or Canadian football); International rules football; rugby league football; and rugby union football.[1] These various forms of football share, to varying degrees, common origins and are known as "football codes".','https://media.istockphoto.com/id/545376530/photo/soccer-ball-on-the-green-grass-of-soccer-field.jpg?s=2048x2048&w=is&k=20&c=ziPixs7gG4_ND3RnYEUUzhCcKnbQnUpAZRORb7DbWN8=','https://media.istockphoto.com/id/1487218062/photo/soccer-ball-flew-into-the-goal-soccer-ball-bends-the-net-against-the-background-of-flashes-of.jpg?s=2048x2048&w=is&k=20&c=46eFtnWfoBCwpOTCvhblyo_dG7roUy7LLYxukxBKUmo=',40,15.9),
('xbox 720','The Xbox 720 is a home video game console developed by Microsoft. As the successor to the original Xbox, it is the second console in the Xbox series.','https://media.istockphoto.com/id/472044719/photo/xbox-one.jpg?s=2048x2048&w=is&k=20&c=BqQ2NbrNp2pOKaXTEILKjF6e8I086VRL_OPwuQ-vugk=','https://media.istockphoto.com/id/1495364554/photo/grayscale-shot-of-an-xbox-one-s-on-the-table.jpg?s=2048x2048&w=is&k=20&c=uYwDu5qS6U2q4FjmFcHMeLlg8ah91h6NW4XrWX8Qj7w=',12,220.5),
('playstation 4','n PlayStation. In 2013 Sony released the PlayStation 4 (PS4), a next-generation console designed to compete with the Xbox One. Critics and players embraced the new platform, which boasted outstanding graphics and a smooth online multiplayer experience.','https://media.istockphoto.com/id/472046637/photo/console-playstation-4-and-pad-dualshock-on-white-background.jpg?s=2048x2048&w=is&k=20&c=MxJJGk9yFrC2aBjUVp_yChwA526tkeU_IYKjo-Ntqao=','https://media.istockphoto.com/id/1284696241/photo/sony-play-station-4-pro-gaming-console-isolated-on-the-black-table-background-with-the.jpg?s=2048x2048&w=is&k=20&c=8h8eJIP88MmIGl5GtF9ovpWOKQOXClNDwqmDBoggxgE=',14,225.5),
('bluetooth speaker','Shop through a wide selection of Portable Bluetooth Speakers at Amazon.com. Free shipping and free returns on eligible items.','https://media.istockphoto.com/id/1059154330/photo/boombox.jpg?s=2048x2048&w=is&k=20&c=iN5xamuHbsPuOY7IAHReP5JpHebOeEIm-DB_DShVjX8=','https://media.istockphoto.com/id/1276617997/photo/connecting-a-phone-and-smart-speaker.jpg?s=2048x2048&w=is&k=20&c=4mbOo44zdalXU7KsTS-BZg_HEVY8TzEcMG-ghrmmnM0=',55,70),
('iphone 12 pro max','he Apple iPhone 12 is a sleek and powerful smartphone with a stunning 6.1-inch Super Retina XDR display, providing vibrant colors and sharp details. Equipped with the A14 Bionic chip, this device delivers impressive performance, making it ideal for multitasking and gaming.','https://media.istockphoto.com/id/1313990743/photo/newly-released-iphone-12-purple-color-mockup-set-with-different-angles.jpg?s=2048x2048&w=is&k=20&c=xS3AzSZY7tYM9ZJUJiv1Kr19i3IpfvCHFgJXRNQslq0=','https://media.istockphoto.com/id/1288681553/photo/studio-shot-of-brand-new-apple-iphone-12-mini-blue-rear-view-on-the-box-on-wooden-background.jpg?s=2048x2048&w=is&k=20&c=YfbGzB5UM12VABglzAFxbD7-UDOdSV1dnFsxDymmABA=',44,349.9),
('iphone 13 pro silver','The iPhone 13 features a 6.1-inch (155 mm) display with Super Retina XDR OLED technology at a resolution of 2532×1170 pixels and a pixel density of about 460 PPI with a refresh rate of 60 Hz.','https://media.istockphoto.com/id/1371790777/photo/iphone-13-pro-silver.jpg?s=2048x2048&w=is&k=20&c=CZ6DJlkaYNaXFkll8KqqdfvThzAhLGyLGdwqF3MjMH8=','https://media.istockphoto.com/id/1387063662/photo/iphone-13-pro-and-iphone-13-mini-boxes.jpg?s=2048x2048&w=is&k=20&c=vllh17qs2T-6T3fysDsPmkiat9Aeb2YoJsFOhd6Y7qE=',110,529.9),
('iphone 14','The iPhone 14 and iPhone 14 Plus feature a 6.1-inch (15 cm) and 6.7-inch (17 cm) display, improvements to the rear-facing camera, and satellite connectivity for contacting emergency services when a user in trouble is beyond the range of Wi-Fi or cellular networks.','https://media.istockphoto.com/id/1360563991/photo/iphone-13-pro-in-white-background-new-smartphone-from-apple-company-close-up.jpg?s=2048x2048&w=is&k=20&c=FRmtvA6keWAMkVNqATHY4xD4GWoQnDGxYBNVvmktqGM=','https://media.istockphoto.com/id/1488572504/photo/hand-holding-new-black-iphone-14-pro-max-editorial-use.jpg?s=2048x2048&w=is&k=20&c=8sov0PLam5bBUwBpz8IIuWLxFYcsbOvKvkV4b1u4sIQ=',59,629.9),
('airpods','AirPods are wireless Bluetooth earbuds designed by Apple Inc. They were first announced on September 7, 2016, alongside the iPhone 7. Within two years, they became Apple s most popular accessory. AirPods are Apple s entry-level wireless headphones, sold alongside the AirPods Pro and AirPods Max. AirPods.','https://media.istockphoto.com/id/1485954517/photo/white-wireless-headphones-in-a-storage-and-charging-box-white-isolated-background.jpg?s=2048x2048&w=is&k=20&c=7R67-IVPjICqGF-qgBeG0rksuqNeYF8W9QVOHB1hjvA=','https://media.istockphoto.com/id/1244934122/photo/one-woman-listening-music-on-wireless-headphones-by-the-river.jpg?s=2048x2048&w=is&k=20&c=GMN8hRvXZzFNg1U107Su5rzpTZuRzroTCgugMxgT9WE=',99,90),
('apple macbook','The MacBook is Apple s third laptop computer family, introduced in 2006. Prior laptops were the PowerBook and iBook. The MacBook Air and MacBook Pro are the remaining models.','https://media.istockphoto.com/id/506061551/photo/laptop-on-white-table.jpg?s=2048x2048&w=is&k=20&c=HWPXQF_NKwqhNH3TgfOAo9kGTRI31d6q6YubpCW_es4=','https://media.istockphoto.com/id/492106324/photo/macbook-pro.jpg?s=2048x2048&w=is&k=20&c=FeTHfDCdDwbxY9DIuY4QY9ytELBPW6meIAEFJX5ht_U=',7,1590.9),
('gamer keyboard','Technically, any keyboard you use for gaming is a gaming keyboard. But a keyboard built for gaming often has features mainstream boards can t match, like ultra-fast polling rates, per-game macro profiles, dedicated media controls, and robust RGB lighting customization (and integration with other parts of your setup).','https://media.istockphoto.com/id/1394788004/photo/gamer-work-space-concept-top-view-a-gaming-gear-mouse-keyboard-joystick-headset-mobile.jpg?s=2048x2048&w=is&k=20&c=HN-gR1OWOelT0xKsgUebCccY3SMoFBQs5awMKAL3Pmg=','https://media.istockphoto.com/id/1305173866/photo/gaming-keyboard-with-rgb-light-white-mechanical-keyboard-gamers-workspace-neon-light.jpg?s=2048x2048&w=is&k=20&c=h8JCY1wYDobSvWCKHebjqKbH5s8YC-67-vT8JAe_4EY=',100,22.8),
('coffee machine','Coffee machines and professional manual and automatic Espresso machines ,Coffee Grinders, Gourmet Coffee ... Coffee, Coffee Roasters, Tea ,Accessories for Coffee.','https://media.istockphoto.com/id/1194659852/photo/coffee-machine.jpg?s=2048x2048&w=is&k=20&c=zHy7J6pleWUdWoCCKg7PbVVyiXjQYK-nF2pc_0JhM1c=','https://media.istockphoto.com/id/1406299770/photo/womans-hands-serve-coffee-prepared-in-a-coffee-maker-to-start-the-day-with-the-benefits-of.jpg?s=2048x2048&w=is&k=20&c=UkIebZpCNYEKGPHo5hburzNSgky5g-k3gARF148A9Zk=',2,999.79);

INSERT INTO user (first_name, last_name, email, phone_number, address, username, password, active, roles, permissions) VALUES
('A', 'A','a@a.com','123-456-7890','undefine undefine 123','a','a', 1, '', ''),
('Tik', 'Tok','tik@tok.com','050-123-4567','Boltimor Masuchetos','TikTok','Aa123456!', 1, '', ''),
('Ben','Jamin','ben@jamin.com','051-012-3456','Denver, Cansas','BenJamin','Bb12345@', 1, '', '');

INSERT INTO favorite_list (item_id, user_id) VALUES
(2,1),
(3,1),
(9,1),
(17,1);

INSERT INTO order_list (user_id, shipping_address, total_price) VALUES (1,'Yehuda Hamacabi 12 st. , Hadera',120.2);

