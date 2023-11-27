create database HelmetManager;
use HelmetManager;

create table Users(
	`UserID` int auto_increment not null primary key,
    `Username` varchar(25) character set utf8mb4 not null,
    `Email` varchar(100) character set utf8mb4 not null,
    `Password` varchar(25) character set utf8mb4 not null,
    `Role` enum('User', 'Admin') default 'User'
);

create table Categories(
    `CategoryID` int auto_increment not null primary key,
    `CategoryName` varchar(50) character set utf8mb4 not null
);

create table Products(
    `ProductID` int auto_increment not null primary key,
    `CategoryID` int not null,
    `ProductName` varchar(100) character set utf8mb4 not null,
    `ProductImage` varchar(255) character set utf8mb4 not null,
    `ProductDescription` text character set utf8mb4 not null,
    `ProductBrand` varchar(50) character set utf8mb4 not null,
    `ProductSize` varchar(5) character set utf8mb4 not null,
    `ProductPrice` int not null,
    constraint foreign key (`CategoryID`) references Categories(`CategoryID`)
);

insert into Users(`Username`, `Email`, `Password`, `Role`) values
('Admin', 'admin@gmail.com', 'admin', 'Admin');
insert into Users(`Username`, `Email`, `Password`) values
('TigerNixon', 'tigernixon@gmail.com', 'nixon'),
('GarrettWinters', 'garrettwinters@gmail.com', 'winters');

insert into Categories(`CategoryName`) values
('Mũ 3/4 đầu'), ('Mũ 1/2 đầu'), ('Mũ full-face'), ('Mũ lật cằm'),
('Mũ xe đạp'), ('Mũ trẻ em');

insert into Products(`CategoryID`, `ProductName`, `ProductImage`, `ProductDescription`, `ProductBrand`, `ProductSize`, `ProductPrice`) values
(1, 'Mũ bảo hiểm 3/4 đầu Royal Helmet M20C', 'img/product-img/non-bao-hiem-3-4-dau-royal-m20c-1.jpg',
    'Nón bảo hiểm Royal Helmet là thương hiệu Việt Nam, được nhiều người tin tưởng, sử dụng. Nón bảo hiểm 3/4 sử dụng an toàn khi di chuyển xa, dân phượt. Mũ bảo hiểm Royal Helmet M20C sử dụng vỏ bằng nhựa ABS nguyên sinh cao cấp, chưa qua sử dụng. Sử dụng chất liệu này có khả năng chống va đập, trầy xước. Phần đệm và vải lót bên trong êm ái, ôm sát đầu. Có khả năng kháng khuẩn, chống ẩm giúp mũ không bị ẩm mốc, mùi hôi khó chịu. Phần đệm bảo vệ má, mặt khỏi những chấn thương khi xảy ra tai nạn. Quai an toàn dễ dàng thao tác, tiện dụng. Đây là kiểu mũ 3/4 đầu không có kính trông rất phong cách, mạnh mẽ.',
    'Royal Helmet', 'XL', 495000),
(1, 'Mũ bảo hiểm Royal Helmet M01 trơn', 'img/product-img/mu-bao-hiem-royal-m01-tron-do.jpg',
    'Royal Helmet M01 là loại nón bảo hiểm 3/4 đầu, với màu sắc khác nhau cho người sử dụng lựa chọn. Chiếc mũ này được thiết kế cao cấp, đạt tiêu chuẩn về độ an toàn cho những người lái xe mô tô. Vỏ mũ bảo hiểm bằng nhựa ABS nguyên sinh - là loại nhựa tinh khiết chưa qua sử dụng, được sử dụng cho các sản phẩm tiêu chuẩn an toàn cao vỏ thiết bị y tế, dược phẩm, vỏ máy bay... có độ bền cao và chịu va đập tốt. Phần đệm lót bên trong nón còn có lớp vải kháng khuẩn, chống ẩm, rất tốt cho việc bảo vệ chiếc nón khỏi mùi hôi, ẩm mốc. Vẻ đẹp huyền bí, sang trọng với những hoạ tiết độc đáo.',
    'Royal Helmet', 'XL', 384000),
(3, 'Mũ bảo hiểm nguyên đầu Asia MT-120', 'img/product-img/non-bao-hiem-nguyen-dau-asia-mt-120.jpg',
    'Nón bảo hiểm Royal Helmet là thương hiệu Việt Nam, được nhiều người tin tưởng, sử dụng. Nón bảo hiểm 3/4 sử dụng an toàn khi di chuyển xa, dân phượt. Mũ bảo hiểm Royal Helmet M20C sử dụng vỏ bằng nhựa ABS nguyên sinh cao cấp, chưa qua sử dụng. Sử dụng chất liệu này có khả năng chống va đập, trầy xước. Phần đệm và vải lót bên trong êm ái, ôm sát đầu. Có khả năng kháng khuẩn, chống ẩm giúp mũ không bị ẩm mốc, mùi hôi khó chịu. Phần đệm bảo vệ má, mặt khỏi những chấn thương khi xảy ra tai nạn. Quai an toàn dễ dàng thao tác, tiện dụng. Đây là kiểu mũ 3/4 đầu không có kính trông rất phong cách, mạnh mẽ.',
    'Asia', 'XL', 420000),
(4, 'Mũ bảo hiểm Royal M179', 'img/product-img/mu-bao-hiem-royal-m179.jpg',
    'Vỏ bằng nhựa ABS nguyên sinh có độ bền cao. Miếng lót bên trong nón có thể được tháo rời giúp việc vệ sinh dễ dàng. Đệm lót có lớp vải kháng khuẩn, chống ẩm mốc. Thiết kế cằm lật độc đáo, dễ dàng tùy biến thành 1 chiếc mũ 3/4 tiện dụng. Trọng lượng của nón bảo hiểm khoảng 1400g, khá nhẹ so với phiên bản lật hàm. Bên trong lớp mút có thể tháo rời vệ sinh dễ dàng. Ngoài ra, phần đệm lót bên trong nón còn có lớp vải kháng khuẩn, chống ẩm, rất tốt cho việc bảo vệ chiếc nón khỏi mùi hôi, ẩm mốc.',
    'Royal Helmet', 'XL', 657000),
(3, 'Mũ bảo hiểm full-face Royal Helmet M02 tem', 'img/product-img/mu-bao-hiem-fullface-royal-m02-tem-1.jpg',
    'Mũ bảo hiểm Royal Helmet tự hào là thương hiệu Việt được tin dùng nhiều nhất nhì trên thị trường hiện nay. Bạn sẽ cảm thấy an tâm trên mọi cung đường, đặc biệt là người cần di chuyển xa, phượt thủ. Khi sử dụng bạn sẽ hạn chế được tối đa các chấn thương khi vô tình bị tai nạn giao thông. Đây là một trong những chiếc mũ bạn nên sử hữu để di chuyển khi đi xa. Thiết kế kiểu dáng ôm sát vòng đầu và khuôn mặt ngăn gió tuyệt đối. Với những mảng màu kết hợp mang lại sự khỏe khoắn, mới lạ. Vỏ nón full-face Royal Helmet M02 làm bằng hạt nhựa ABS nguyên sinh, chịu đựng va đập và chống trầy xước. Phần đệm lót bên trong nón còn có lớp vải kháng khuẩn, chống ẩm, rất tốt cho việc bảo vệ chiếc nón khỏi mùi hôi, ẩm mốc. Quai nón bảo hiểm Royal Helmet M02 được dệt 2 lớp bằng sợi tổng hợp chịu lực kéo tốt, siêu bền và dễ dàng thao tác khi sử dụng. Kính với kích thước lớn chống tia nano, tia uv và chống lóe khi tiếp xúc ánh sáng mặt trời theo công nghệ của Đức. Chống vỡ, chống xước và khả năng xoay lên xuống linh hoạt. Kính có 2 màu trắng và đen, tiện lợi khi đi ban ngày hay ban đêm. Khe thông gió được thiết kế khoa học, tạo cảm giác thoáng mát khi đội.',
    'Royal Helmet', 'XL', 505000),

(3, 'Mũ bảo hiểm Royal M136 trơn', 'img/product-img/mu-bao-hiem-royal-m01-tron-do.jpg',
    'Mũ bảo hiểm fullface bạn có thể dễ nhận diện qua hình thức, khi đội sẽ bao trùm toàn bộ đầu, vùng tai, mặt và có kính chắn bụi. Đây là loại mũ thích hợp cho những người hay phải di chuyển xa hoặc các phượt phủ. Mũ bảo hiểm Royal Helmet có khả năng chống và hạn chế chấn thương mà các loại mũ khác không làm được. Khi đội mũ mang lại cho người sử dụng cảm giác mạnh mẽ, năng động. Nón bảo hiểm M136 có thiết kế vỏ ngoài làm bằng hạt nhựa ABS chịu lực va đập và tránh trầy xước, độ bền cao. Bên trong có mút xốp làm bằng nhựa EPS giúp hạn chế chấn thương tốt nhất. Quai mũ dệt 2 lớp bằng sợi tổng hợp, chịu lực kéo lực văng tốt, dễ thao tác khi dùng. Kính che bụi sử dụng nhựa PC và xử lý nano theo công nghệ của Đức. Có khả năng hạn chế vỡ, trầy xước, chống tia UV, nâng lên hạ xuống dễ dàng, cho hình ảnh trung thực cao, an toàn trên mỗi cung đường. Vải lót dày sử dụng nguyên liệu thấm hút cao giúp cho người dùng thoải mái, không bí bách. Ngoài ra, còn có khe thông gió luôn tạo cảm giác thoáng mát khi đội.',
    'Royal Helmet', 'XL', 384000),
(6, 'Mũ đội đầu cho bé Scoot and Ride size S', 'img/product-img/mu-doi-dau-cho-be-scoot-and-ride-size-s-1.jpg',
    'Mũ đội đầu cho bé Scoot and Ride size S là dòng mũ bảo hiểm bảo vệ bé khi sử dụng xe scooter tham gia các hoạt động ngoài trời. Mũ được thiết kế với độ an toàn cao giúp các bậc phụ huynh yên tâm. Bộ sản phẩm có nhiều màu sắc tươi sáng khác nhau cho bé lựa chọn theo sở thích, kích thích sự vận động của trẻ. Mũ bảo hiểm cho bé Scoot and Ride side S đáp ứng kích thước vùng đầu từ 51 - 56cm. Bạn có thể tùy chỉnh độ dài dây sao cho vừa vặn với bé thông qua nút xoay tăng đơ phía sau. Mũ được làm từ chất liệu cao cấp an toàn tuyệt đối với sức khỏe của bé. Trong đó phần vỏ làm từ nhựa PC siêu bền, chịu lực tốt, dễ vệ sinh. Lớp lót bên trong mũ làm từ vải lông cừu êm, thoáng khí, có thể vệ sinh sạch sẽ. Lớp vải này kết hợp với các lỗ thoáng khí bên trong mang đến sự thoáng mát, thoải mái khi đội cho bé. Khóa cài của mũ là loại khóa từ tính chắc chắn, chống kẹt tay, dễ đóng/mở. Phía sau mũ có trang bị đèn LED cảnh báo giúp mọi người xung quanh có thể nhận biết được trẻ khi di chuyển. Với những ưu điểm trên, mũ bảo hiểm Scoot and Ride đạt tiêu chuẩn CE, EN1078, U.S. CPSC.',
    'Scoot and Ride', 'S', 960000),
(2, 'Mũ bảo hiểm 1/2 đầu Asia MT-105', 'img/product-img/non-bao-hiem-1-2-dau-asia-mt-105-s2.jpg',
    'Sở hữu thiết kế trẻ trung, hiện đại, nón bảo hiểm nửa đầu Asia MT-105 không chỉ mang tới tính thẩm mỹ cao mà còn giúp người dùng an toàn hơn. Được sản xuất trên dây chuyền hiện đại, trải qua quá trình thử nghiệm nghiêm ngặt, nón bảo hiểm nửa đầu Asia MT-105 chắc chắn sẽ mang tới cho bạn sự hài lòng. Phần vỏ được làm từ chất liệu nhựa ABS cao cấp, có thể chịu được va đập mang tới sự an toàn cho người sử dụng. Chất liệu ABS này cũng được ứng dụng khá phổ biến, nó còn được dùng để chế tạo vỏ máy bay, thiết bị y tế... Phần ruột mũ được làm từ các hạt xốp EPS. Đây là thành phần quan trọng nhất của chiếc mũ bảo hiểm, giúp mũ có khả năng chịu lực va đập khi có tai nạn xảy ra. Phần quai, khóa cài, ốp, lưỡi trai cũng được chú ý và làm bằng những chất liệu cao cấp nhằm đảm bảo độ bền cho sản phẩm và độ an toàn cho người đội.',
    'Asia', 'L', 197000),
(2, 'Mũ bảo hiểm 1/2 đầu Asia MT-117', 'img/product-img/non-bao-hiem-1-2-dau-asia-mt-117.jpg',
    'Asia MT-117 là loại nón bảo hiểm nửa đầu, với thiết kế chắc chắn giúp người tham gia giao thông được đảm bảo an toàn. Mũ bảo hiểm có chất liệu cao cấp, cứng cáp và bền bỉ. Sản phẩm được sản xuất theo dây chuyền tiên tiến, đảm bảo tiêu chuẩn về chất lượng. Vỏ nón bảo hiểm: được làm từ 100% hạt nhựa ABS và nung trong nhiệt độ cao để kết thành phôi. Ruột nón: Hạt xốp EPS là thành phần chính để tạo nên phần ruột xốp bên trong nón. Đây cũng được cho là những hạt xốp có chất lượng cao và đạt tiêu chuẩn của Cục. Quai nón: được làm từ sợi nilon dai giúp dây nón chắc chắn hơn chịu được ảnh hưởng thời tiết thất thường và lực kéo mạnh trong quá trình sử dụng. Thành phần phụ: khóa cài, lưỡi trai nón, ốp nón cũng được ép bằng máy chuyên dụng và được công nhân sơ chế thủ công cho phù hợp với từng loại nón.',
    'Asia', 'L', 192000),
(2, 'Mũ bảo hiểm Andes 109K', 'img/product-img/3S109KM-Nham-Xanh-768x768.jpg',
    'Mũ bảo hiểm nửa đầu Andes 109K là chiếc nón cải tiến dành cho khách hàng có size vòng đầu lớn từ 57- 59cm. Đây là dòng sản phẩm truyền thống phù hợp với khách hàng thường xuyên di chuyển trong thành phố. Lớp vỏ ngoài của mũ bảo hiểm nửa đầu Andes 3S109 được làm từ nhựa ABS nguyên sinh cho lớp vỏ cứng cáp chống va đập cực tốt. Nhựa ABS có đặc tính cứng, bền với nhiệt độ và hoá chất; tính dẻo, dai giúp đảm bảo độ an toàn khi khách hàng sử dụng trong thời gian dài. Phần mút xốp bên trong được làm từ nhựa EPS với lực nén tỷ trọng cao giúp cho xốp dày dặn và cứng cáp, đồng thời với thiết kế, kết hợp và phân bố đặc biệt giữa phần vỏ ngoài và phần mút xốp của mũ bảo hiểm Andes tạo giảm cảm giác an toàn, chắc chắn khi sử dụng. Mút xốp sẽ hấp thụ và triệt tiêu lực tác động từ vỏ mũ vào bên trong, đảm bảo an toàn tối đa cho phần đầu của người đội, hạn chế những hậu quả đáng tiếc khi có tai nạn xảy ra. Lớp vải lót 3S (sạch sẽ – sảng khoái – sức khỏe) là phát minh đột phá của Andes đối với sản phẩm mũ bảo hiểm tại Việt Nam. Lớp vải lót 3S bao gồm lớp lưới thoáng khí kết hợp với lớp mút mỏng nhẹ tạo sự êm dịu cho người dùng, không gây hầm bí khi đội mũ cả ngày. Đặc biệt hơn, với công nghệ 3S cải tiến có thể tháo rời nhanh chóng và dễ dàng, giúp khử sạch ẩm mốc, mùi hôi, vi khuẩn tích tụ bên trong lòng mũ ngăn ngừa các bệnh cho da đầu: nấm tóc, rụng tóc, gàu, vảy nến,... Phần vành nón để giúp che nắng cũng như giảm va chạm vào da thịt khi xảy ra sự cố bất ngờ. Vành nón được gắn liền với nón nhờ 3 nút bấm inox 304 chống gỉ cao cấp, trong trường hợp bạn muốn thay đổi và lắp đặt kính chắn thì phần nút bấm này sẽ giúp bạn thay thế phụ kiện một cách dễ dàng. Dây quai mũ bảo hiểm Andes an toàn chắc chắn, tăng chỉnh dễ dàng để ôm sát đầu giữ mũ không bị lung lay khi có tác động cho bạn thoải mái lái xe. Phần ốp bên tai được thiết kế hở giúp thoải mái và thoáng mát khi sử dụng. Khóa và ốc được làm từ inox 304 cao cấp chống gỉ sét cực tốt, mang đến sự sang trọng đẳng cấp. Với thiết kế hiện đơn giản mà không kém phần sang trọng, mũ bảo hiểm nửa đầu Andes 109K sẽ là sự lựa chọn thích hợp cho khách hàng nam hoặc những khách hàng yêu thích sự tối giản, thanh lịch.',
    'Andes', 'L', 500000),

(4, 'Mũ bảo hiểm Royal M08', 'img/product-img/OA5B2P_MG_1524.jpg',
    'Điểm thu hút đầu tiên đó là form nón của Royal M08 khá đẹp. Nón Royal M là nón lật cằm hay còn gọi là lật hàm, rất linh hoạt trong việc sử dụng. Vỏ nón Royal M08 đen đỏ được làm từ ABS nguyên sinh, giúp chống va đập mạnh vào nón. Xốp của nón Royal M08 làm từ EPS giúp hấp thụ lực bảo vệ đầu tốt hơn. Lớp lót của nón Royal M179 đen đỏ tháo rời dể dàng; bộ thông gió trước trên và sau hoàn thiện. Khóa nón là khóa tầng. Khóa này dễ dàng sử dụng và an toàn cao hơn khóa bấm nên được ưa chuộng.',
    'Royal Helmet', 'L', 745000),
(1, 'Mũ bảo hiểm Protec Viva VALWKZ', 'img/product-img/mu-bao-hiem-protec-viva-1-mau-valwkz.jpg',
    'Mũ bảo hiểm Viva VALWKZ là sản phẩm của thương hiệu Protec của Mỹ, có nhà máy tại Việt Nam. Chiếc mũ là bước đột phá mới cho kiểu mũ bảo hiểm 3/4. Protec Viva VALWKZ sở hữu nét thiết kế tinh tế với kiểu dáng thon gọn, bắt mắt. Mũ có khả năng ôm vừa vặn vào đầu người đội, kết hợp với hệ thống khe thông gió thông minh tạọ cảm giác thoải mái. Kính chắn gió được làm từ chất liệu đặc biệt không bị chói lóa giúp người dùng dễ dàng quan sát khi sử dụng. Ngoài ra chất liệu này cũng mang tới sự dẻo dai bền bỉ cho sản phẩm.',
    'Protec', 'L', 434000),
(6, 'Mũ bảo hiểm Protec Kitty S 2 màu', 'img/product-img/mu-bao-hiem-royal-m01-tron-do.jpg',
    'Mũ bảo hiểm Protec là thương hiệu mũ bảo hiểm nổi tiếng được nhiều bậc phụ huynh tin tưởng lựa chọn để bảo vệ bé khỏi những chấn thương không mong muốn. Vỏ mũ bảo hiểm được làm từ nhựa ABS có độ ứng cao, chịu va đập tốt. Trên đỉnh mũ có bố trí các lỗ thông khí giúp mũ luôn thoáng và khô ráo, không ẩm ướt gây khó chịu vùng da đầu của bé. Lớp vỏ nhựa trơn bóng không bám bụi, dễ vệ sinh. Bên trong được lót lớp xốp dày để vừa giảm lực tác động lên đầu của bé vừa mang lại cảm giác êm ái cho đầu. Mũ bảo hiểm trẻ em thiết kế dây khóa gài chắc chắn, có độ đàn hồi cao, dễ chỉnh cho vừa đầu của bé. Mặt trước mũ bảo hiểm có kính che chắn bụi và được thiết kế trong suốt để không làm giảm tầm nhìn của bé.',
    'Protec', 'S', 314000),
(3, 'Mũ bảo hiểm fullface Andes 3S-555', 'img/product-img/mu-doi-dau-cho-be-scoot-and-ride-size-s-1.jpg',
    'Nếu bạn là người đề cao sự an toàn, và thường xuyên phải di chuyển quãng đường xa, thì mũ bảo hiểm fullface 3S-555 sẽ là lựa chọn hoàn toàn lý tưởng. Mũ này cũng có khả năng chịu được va đập cực tốt. Mút xốp dày, vừa vặn bám chặt với phần vỏ giúp triệt tiêu hoàn toàn xung lực mạnh từ bên ngoài tác động vào. Mũ ôm trọn vòng đầu, trọng lượng được phân tán đều không gây cảm giác nặng và đau đầu, tạo cho người đội cảm giác thoải mái và thoáng mát khi sử dụng. Vải lót bên trong được xử lý kháng khuẩn, hút ẩm tốt, tạo cảm giác êm ái và thoáng mát. Khóa và dây quai chịu lực cực mạnh, độ dài linh động phù hợp với mọi đối tượng. Kính chắn gió được làm từ vật liệu đặc biệt, cho hình ảnh rõ nét, không bị chói đèn.',
    'Andes', 'M', 555000),
(2, 'Mũ bảo hiểm Protec Rosa 2 màu RLWF', 'img/product-img/non-bao-hiem-1-2-dau-asia-mt-105-s2.jpg',
    'Mũ bảo hiểm Protec là thương hiệu được ưa chuộng tại thị trường Việt Nam bởi độ an toàn, bền, giá cả phải chăng. Vỏ mũ bảo hiểm Protec Rosa RLWF được làm từ nhựa tổng hợp (ABS) nguyên sinh, có khả năng chịu va đập tốt, chống mài mòn hiệu quả. Mũ bảo hiểm Protec Rosa có thiết kế pha màu kiểu nửa đầu tạo sự thoải mái cho người sử dụng. Mũ bảo hiểm Protec Rosa 2 màu RLWF sử dụng lõi xốp mũ EPS hấp thu xung đột tốt, hạn chế chấn thương tối đa khi xảy ra tai nạn, va chạm. Mũ Protec đã thông qua các quy trình kiểm định nghiêm ngặt về độ va đập, độ đâm xuyên và độ ổn định của khóa mũ.',
    'Protec', 'M', 252000);

select * from Users;
select * from Categories;
select * from Products;
select * from Products limit 9 offset 0;
select * from Products where `CategoryID` = 6 limit 9 offset 0;