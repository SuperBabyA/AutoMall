/*
 Navicat Premium Data Transfer

 Source Server         : LocalMySQL
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : automall

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 26/11/2022 16:38:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` bigint(0) NOT NULL COMMENT '地址id',
  `receive_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收件人姓名',
  `receive_telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收件人联系电话',
  `province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '省',
  `city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '市',
  `district` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区',
  `particular` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详情',
  `default_setting` int(0) NOT NULL COMMENT '是否为默认设置（0：默认，1：不默认）',
  `user_address_id` bigint(0) NOT NULL COMMENT '地址与用户关联的外键',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_address_fk`(`user_address_id`) USING BTREE,
  CONSTRAINT `user_address_fk` FOREIGN KEY (`user_address_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1586712528883417088, '李福强', '13838383838', '云南', '昆明', '朝阳', '朝阳公园288', 1, 1586699203718021120);
INSERT INTO `address` VALUES (1586712528883417089, '子君', '13412341234', '上海市', '上海市', '静安区', '高平路131号', 1, 1586699203718021121);
INSERT INTO `address` VALUES (1586712528883417090, '王同', '10086', '江西省', '抚州市', '临川区', '罗湖镇', 0, 1586699203718021121);

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `id` bigint(0) NOT NULL COMMENT '汽车id',
  `car_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '汽车名称',
  `price` float(32, 2) NOT NULL COMMENT '价格',
  `thumb_img` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '外观图',
  `selling` int(0) NOT NULL DEFAULT 0 COMMENT '是否在销售，用于逻辑删除（0：在售，1：下架）',
  `shelves_time` date NOT NULL COMMENT '上架时间',
  `category_car_id` bigint(0) NULL DEFAULT NULL COMMENT '汽车类型外键',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_car_fk`(`category_car_id`) USING BTREE,
  CONSTRAINT `category_car_fk` FOREIGN KEY (`category_car_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES (111111, '宝马X4', 55.26, 'BavarianMotoWork_X5.jpg', 0, '2022-10-20', 100001);
INSERT INTO `car` VALUES (666661, '宝马X5', 42.35, 'BavarianMotoWork_X5.jpg', 0, '2019-11-27', 100001);
INSERT INTO `car` VALUES (666662, '奔驰E300', 48.63, 'Mercedes-Benz_E300.jpg', 0, '2022-09-29', 100002);
INSERT INTO `car` VALUES (666663, '奥迪RS7', 136.56, 'Audi_RS7.jpg', 0, '2020-06-16', 100003);
INSERT INTO `car` VALUES (666664, '凯迪拉克CT5', 29.24, 'Cadillac_CT5.jpg', 0, '2019-11-16', 100004);
INSERT INTO `car` VALUES (888881, '奥迪Q5', 46.31, 'Audi_Q5.jpg', 0, '2018-02-16', 100006);
INSERT INTO `car` VALUES (888882, '特斯拉model3', 29.26, 'Tesla_Model3.jpg', 0, '2020-10-16', 100007);
INSERT INTO `car` VALUES (888883, '丰田Supra', 52.54, 'Toyota_Supra.jpg', 0, '2016-04-16', 100008);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(0) NOT NULL COMMENT '类别id',
  `genre` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '车型',
  `series` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '系别',
  `brand` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (100001, 'SUV', '德系', '宝马');
INSERT INTO `category` VALUES (100002, '轿车', '德系', '奔驰');
INSERT INTO `category` VALUES (100003, '轿跑车&敞篷跑车', '德系', '奥迪');
INSERT INTO `category` VALUES (100004, '轿车', '美系', '凯迪拉克');
INSERT INTO `category` VALUES (100005, 'SUV', '德系', '奔驰');
INSERT INTO `category` VALUES (100006, 'SUV', '德系', '奥迪');
INSERT INTO `category` VALUES (100007, '纯电动', '美系', '特斯拉');
INSERT INTO `category` VALUES (100008, '轿跑车&敞篷跑车', '日系', '丰田Supra');
INSERT INTO `category` VALUES (100009, '轿跑车&敞篷跑车', '德系', '奔驰');
INSERT INTO `category` VALUES (100010, 'MPV', '日系', '丰田');
INSERT INTO `category` VALUES (100011, '轿车', '中系', '比亚迪');
INSERT INTO `category` VALUES (100014, '轿车', '日系', '雷克萨斯');

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `id` bigint(0) NOT NULL COMMENT '收藏id',
  `collection_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '收藏时间',
  `user_collection_id` bigint(0) NOT NULL COMMENT '收藏关联用户表的外键',
  `car_collection_id` bigint(0) NOT NULL COMMENT '收藏关联汽车的外键',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_collection_fk`(`user_collection_id`) USING BTREE,
  INDEX `car_collection_fk`(`car_collection_id`) USING BTREE,
  CONSTRAINT `car_collection_fk` FOREIGN KEY (`car_collection_id`) REFERENCES `car` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_collection_fk` FOREIGN KEY (`user_collection_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES (1586678254618021122, '2022-11-18 00:00:00.000', 1586699203718021121, 666663);
INSERT INTO `collection` VALUES (1586678254618021123, '2022-11-18 00:00:00.000', 1586699203718021121, 666664);
INSERT INTO `collection` VALUES (1586678254618021124, '2022-11-18 00:00:00.000', 1586699203718021120, 888881);
INSERT INTO `collection` VALUES (1586678254618021125, '2022-11-18 00:00:00.000', 1586699203718021120, 888882);
INSERT INTO `collection` VALUES (1586678254618021126, '2022-11-18 00:00:00.000', 1586699203718021120, 888883);
INSERT INTO `collection` VALUES (1594247413051293696, '2022-11-20 16:32:57.617', 1586699203718021121, 111111);
INSERT INTO `collection` VALUES (1594247456034521088, '2022-11-20 16:33:07.863', 1586699203718021121, 888883);

-- ----------------------------
-- Table structure for detail
-- ----------------------------
DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail`  (
  `id` bigint(0) NOT NULL COMMENT '详情id',
  `color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '外观颜色',
  `detail_img` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '外观图片',
  `variable_speed` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '挡的类型（自动挡，手动挡，怀挡）',
  `payload` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '座位数',
  `axle_base` int(0) NOT NULL COMMENT '轴距',
  `torque` int(0) NOT NULL COMMENT '扭矩',
  `weight` float NOT NULL COMMENT '车重',
  `engine` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '发动机位置（前置，中置，后置）',
  `dynamical_system` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '动力系统',
  `displacement` float NOT NULL COMMENT '排量',
  `cylinder_number` int(0) NOT NULL COMMENT '气缸数',
  `drive_mode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '驱动方式',
  `descriptive` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '暂无' COMMENT '描述',
  `car_detail_id` bigint(0) NOT NULL COMMENT '详情与汽车表关联的外键',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `car_detail_fk`(`car_detail_id`) USING BTREE,
  CONSTRAINT `car_detail_fk` FOREIGN KEY (`car_detail_id`) REFERENCES `car` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detail
-- ----------------------------
INSERT INTO `detail` VALUES (990101, '蓝', '20141216234606pu0O8pu0O8.jpg', '自动挡', '四座', 3105, 400, 1.2, '前置发动机', '燃油', 1.6, 4, '后驱', '超长轴距，X型智能激光大灯，M运动卡钳，M运动套装，22英寸铝合金轮毂，“天使之翼”迎宾光毯', 666661);
INSERT INTO `detail` VALUES (990102, '白', '20150928002719.jpg', '自动挡', '四座', 3105, 400, 1.2, '前置发动机', '燃油', 1.6, 4, '后驱', '超长轴距，X型智能激光大灯，M运动卡钳，M运动套装，22英寸铝合金轮毂，“天使之翼”迎宾光毯', 666661);
INSERT INTO `detail` VALUES (990103, '黑', 'R-C.jfif', '自动挡', '四座', 3105, 400, 1.2, '前置发动机', '燃油', 1.6, 4, '后驱', '超长轴距，X型智能激光大灯，M运动卡钳，M运动套装，22英寸铝合金轮毂，“天使之翼”迎宾光毯', 666661);
INSERT INTO `detail` VALUES (990201, '白', 'R-C -2.jfif', '自动挡', '四座', 3079, 370, 1.5, '前置发动机', '燃油', 1.9, 4, '后驱', '长轴距E级轿车，驭时以型，驭时以尊，驭时以智', 666662);
INSERT INTO `detail` VALUES (990202, '黑', 'abb1983aaa8474338c2606757ff19a67.jpg', '自动挡', '四座', 3079, 370, 1.5, '前置发动机', '燃油', 1.9, 4, '后驱', '长轴距E级轿车，驭时以型，驭时以尊，驭时以智', 666662);
INSERT INTO `detail` VALUES (990301, '红', 't019bc100410361de94.jpg', '手自一体', '四座', 2929, 800, 2.1, '前置发动机', '燃油', 3.9, 8, '四驱', '极致态度，天性使燃，承载梦想与情怀。', 666663);
INSERT INTO `detail` VALUES (990302, '银', 'R-C-3.jfif', '手自一体', '四座', 2929, 800, 2.1, '前置发动机', '燃油', 3.9, 8, '四驱', '极致态度，天性使燃，承载梦想与情怀。', 666663);
INSERT INTO `detail` VALUES (990303, '绿', 'ChMlWl5MhNKIXOITAAXfsaQIsKQAANYTQOY86UABd_J108.jpg', '手自一体', '四座', 2929, 800, 2.1, '前置发动机', '燃油', 3.9, 8, '四驱', '极致态度，天性使燃，承载梦想与情怀。', 666663);
INSERT INTO `detail` VALUES (990304, '蓝', 'R-C-4.jfif', '手自一体', '四座', 2929, 800, 2.1, '前置发动机', '燃油', 3.9, 8, '四驱', '极致态度，天性使燃，承载梦想与情怀。', 666663);
INSERT INTO `detail` VALUES (990401, '翠绿', 'R-C-5.jfif', '手自一体', '五座', 2947, 350, 2.1, '前置发动机', '燃油', 1.9, 4, '后驱', '豪华运动，格调之选，高能，更智能', 666664);
INSERT INTO `detail` VALUES (990402, '橙', '10487084_181024825000_2.jpg', '手自一体', '五座', 2947, 350, 2.1, '前置发动机', '燃油', 1.9, 4, '后驱', '豪华运动，格调之选，高能，更智能', 666664);
INSERT INTO `detail` VALUES (990403, '白', 't012c48221869d2379a.jpg', '手自一体', '五座', 2947, 350, 2.1, '前置发动机', '燃油', 1.9, 4, '后驱', '豪华运动，格调之选，高能，更智能', 666664);
INSERT INTO `detail` VALUES (990404, '蓝', '013.jpg', '手自一体', '五座', 2947, 350, 2.1, '前置发动机', '燃油', 1.9, 4, '后驱', '豪华运动，格调之选，高能，更智能', 666664);
INSERT INTO `detail` VALUES (990405, '黑', '20160219211849.jpg', '手自一体', '五座', 2947, 350, 2.1, '前置发动机', '燃油', 1.9, 4, '后驱', '豪华运动，格调之选，高能，更智能', 666664);
INSERT INTO `detail` VALUES (990601, '黑', 'R-C-6.jfif', '手自一体', '五座', 2907, 320, 2.1, '前置发动机', '燃油', 2, 4, '四驱', '全新奥迪 Q5L，配备双脸版本外观，以焕然之姿，激发您对自由的无尽渴望。 一部为自由而生的座驾，带您开启全新旅程。', 888881);
INSERT INTO `detail` VALUES (990602, '白', 'R-C-7.jfif', '手自一体', '五座', 2907, 320, 2.1, '前置发动机', '燃油', 2, 4, '四驱', '全新奥迪 Q5L，配备双脸版本外观，以焕然之姿，激发您对自由的无尽渴望。 一部为自由而生的座驾，带您开启全新旅程。', 888881);
INSERT INTO `detail` VALUES (990603, '蓝', 'R-C-8.jfif', '手自一体', '五座', 2907, 320, 2.1, '前置发动机', '燃油', 2, 4, '四驱', '全新奥迪 Q5L，配备双脸版本外观，以焕然之姿，激发您对自由的无尽渴望。 一部为自由而生的座驾，带您开启全新旅程。', 888881);
INSERT INTO `detail` VALUES (990604, '灰', 'R-C.png', '手自一体', '五座', 2907, 320, 2.1, '前置发动机', '燃油', 2, 4, '四驱', '全新奥迪 Q5L，配备双脸版本外观，以焕然之姿，激发您对自由的无尽渴望。 一部为自由而生的座驾，带您开启全新旅程。', 888881);
INSERT INTO `detail` VALUES (990701, '白', '968c003293209f15ac9a44e028a910b9.jpg', '自动挡', '五座', 2875, 659, 1.4, '前置+后置电机', '电动', 0, 2, '四驱', 'Model 3 Performance 高性能版搭载双电机全轮驱动、19 英寸零重力高性能轮毂和高级制动系统，在绝大部分天气条件下都拥有极佳的操控体验。碳纤维扰流板可提升高速行驶时的稳定性，使 Model 3 的加速性能达到 3.3 秒 0-100 公里/小时*。', 888882);
INSERT INTO `detail` VALUES (990702, '红', 'R-C-9.jfif', '自动挡', '五座', 2875, 659, 1.4, '前置+后置电机', '电动', 0, 2, '四驱', 'Model 3 Performance 高性能版搭载双电机全轮驱动、19 英寸零重力高性能轮毂和高级制动系统，在绝大部分天气条件下都拥有极佳的操控体验。碳纤维扰流板可提升高速行驶时的稳定性，使 Model 3 的加速性能达到 3.3 秒 0-100 公里/小时*。', 888882);
INSERT INTO `detail` VALUES (990703, '银', 'R-C-10.jfif', '自动挡', '五座', 2875, 659, 1.4, '前置+后置电机', '电动', 0, 2, '四驱', 'Model 3 Performance 高性能版搭载双电机全轮驱动、19 英寸零重力高性能轮毂和高级制动系统，在绝大部分天气条件下都拥有极佳的操控体验。碳纤维扰流板可提升高速行驶时的稳定性，使 Model 3 的加速性能达到 3.3 秒 0-100 公里/小时*。', 888882);
INSERT INTO `detail` VALUES (990704, '灰', 'R-C-11.jfif', '自动挡', '五座', 2875, 659, 1.4, '前置+后置电机', '电动', 0, 2, '四驱', 'Model 3 Performance 高性能版搭载双电机全轮驱动、19 英寸零重力高性能轮毂和高级制动系统，在绝大部分天气条件下都拥有极佳的操控体验。碳纤维扰流板可提升高速行驶时的稳定性，使 Model 3 的加速性能达到 3.3 秒 0-100 公里/小时*。', 888882);
INSERT INTO `detail` VALUES (990801, '白', '20150908001129.jpg', '自动挡', '四座', 2780, 632, 1.8, '前置发动机', '燃油', 3.8, 6, '四驱', '采用了RAYS锻造的超轻量化6轮辐黑色铝合金轮毂、独特的红黑配色内饰、RECARO皮革赛车座椅以及碳纤维尾翼，整车设计更显时尚动感', 888883);
INSERT INTO `detail` VALUES (990802, '黑', 'R-C-12.jfif', '自动挡', '四座', 2780, 632, 1.8, '前置发动机', '燃油', 3.8, 6, '四驱', '采用了RAYS锻造的超轻量化6轮辐黑色铝合金轮毂、独特的红黑配色内饰、RECARO皮革赛车座椅以及碳纤维尾翼，整车设计更显时尚动感', 888883);
INSERT INTO `detail` VALUES (990803, '红', 't01e6ca1aa13a245a19.png', '自动挡', '四座', 2780, 632, 1.8, '前置发动机', '燃油', 3.8, 6, '四驱', '采用了RAYS锻造的超轻量化6轮辐黑色铝合金轮毂、独特的红黑配色内饰、RECARO皮革赛车座椅以及碳纤维尾翼，整车设计更显时尚动感', 888883);

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` bigint(0) NOT NULL COMMENT '反馈id',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `email` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '反馈内容',
  `motorcycle_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '想要车型',
  `dispose` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '未处理' COMMENT '是否处理反馈',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES (777701, '18365343996', '453@qq.com', '反馈内容上的飞机啊饭卡上的机房监控力度的设计费', '法拉利f8', '未处理');
INSERT INTO `feedback` VALUES (777702, '13843987888', '123@qq.com', '反馈内容2哦欸为何如看法', '劳斯莱斯库里南', '未处理');
INSERT INTO `feedback` VALUES (777703, '15832498881', '333@qq.com', '测试修改', '福特野马', '未处理');
INSERT INTO `feedback` VALUES (1586733099205656576, '18879449252', NULL, 'test edit api', NULL, '已处理');
INSERT INTO `feedback` VALUES (1595498489637371904, '123123123', '1234@exam.com', '这是一条反馈', '888884', '未处理');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint(0) NOT NULL COMMENT '订单id',
  `order_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号',
  `trading_hour` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '交易时间',
  `money` double NOT NULL COMMENT '订单金额',
  `state` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '进行中' COMMENT '状态',
  `detail_order_id` bigint(0) NOT NULL COMMENT '订单与汽车详情表关联的外键',
  `address_order_id` bigint(0) NOT NULL COMMENT '订单与地址表关联的外键',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_order_fk`(`address_order_id`) USING BTREE,
  INDEX `category_order_fk`(`detail_order_id`) USING BTREE,
  CONSTRAINT `address_order_fk` FOREIGN KEY (`address_order_id`) REFERENCES `address` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detail_order_fk` FOREIGN KEY (`detail_order_id`) REFERENCES `detail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1591437359061782752, 'AM1591437356622282752', '2022-11-18 00:00:00', 36.1, '交易中', 990101, 1586712528883417089);
INSERT INTO `orders` VALUES (1591437359061782753, 'AM1591437356622282753', '2022-11-18 00:00:00', 42.5, '交易中', 990304, 1586712528883417089);
INSERT INTO `orders` VALUES (1591437359061782755, 'AM1591437356622282755', '2022-11-18 00:00:00', 33.8, '交易中', 990604, 1586712528883417088);
INSERT INTO `orders` VALUES (1591437359061782756, 'AM1591437356622282756', '2022-11-18 00:00:00', 46, '交易中', 990702, 1586712528883417088);
INSERT INTO `orders` VALUES (1591437359061782757, 'AM1591437356622282757', '2022-11-18 00:00:00', 41.2, '交易中', 990803, 1586712528883417088);
INSERT INTO `orders` VALUES (1594223776155959296, 'AM1668927542138', '2022-11-20 00:00:00', 136.56, '交易中', 990303, 1586712528883417089);
INSERT INTO `orders` VALUES (1594223880346664960, 'AM1668927566979', '2022-11-20 00:00:00', 29.24, '交易中', 990405, 1586712528883417089);
INSERT INTO `orders` VALUES (1594224064308838400, 'AM1668927610839', '2022-11-20 00:00:00', 29.24, '交易中', 990401, 1586712528883417089);

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation`  (
  `id` bigint(0) NOT NULL COMMENT '在线预约的id',
  `subscribers` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '预约人姓名',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '预约人邮箱',
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '预约人联系方式',
  `reservation_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '预约车型',
  `reservation_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '预约时间',
  `dispose` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '未处理' COMMENT '是否处理预约',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reservation
-- ----------------------------
INSERT INTO `reservation` VALUES (7837487923, 'test', '123@qq.com', '18879449252', '特斯拉 model3', '2022-10-31', '未处理');
INSERT INTO `reservation` VALUES (1587099677914955776, '张三', '2134@qq.com', '18897449252', '特斯拉 model 3', '2022-11-05', '已处理');
INSERT INTO `reservation` VALUES (1595489887686098944, '王同', 'w_zijun@exam.com', '15012341234', '888885', '2022-11-30', '未处理');
INSERT INTO `reservation` VALUES (1595491290848559104, '王同', 'w_zijun@exam.com', '15012341234', '888885', '2022-11-30', '未处理');
INSERT INTO `reservation` VALUES (1595491653131567104, '王同', 'w_zijun@exam.com', '15007947944', '888884', '2022-11-29', '未处理');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL COMMENT '用户id',
  `account_name` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `head_portrait` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户姓名',
  `sex` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户性别',
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `id_card` bigint(0) NOT NULL COMMENT '身份证号',
  `is_admin` int(0) NOT NULL DEFAULT 0 COMMENT '是否为管理员',
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份效验码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_account`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1586699203718021120, '沙北', '12308@exam.com', '4c5e52b76ac49125d1aec2024042592e', NULL, '李福强', '男', '15079412193', '2022-10-20', 876787678762637654, 0, NULL);
INSERT INTO `user` VALUES (1586699203718021121, 't_admin', 'w_zijun0801@tom.com', '727b6ad0cbd8d126f0478ac61fb02c7f', NULL, '子君', '男', '15007947944', '2002-06-18', 362502200206233011, 1, 'bb11bbc85385e5bc23f7801652c5329d');
INSERT INTO `user` VALUES (1591437356622282752, '逍遥游', NULL, 'ae8563c3137e3b754bd1fefd4a33f2c0', 'b445c269-7708-499f-9431-eab1179e4187.jpg', 'XiaoYao', '男', '13838383838', '2002-06-23', 111122223333444455, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
