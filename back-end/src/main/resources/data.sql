USE bangjwo;

INSERT INTO member (member_id, kakao_id, name, birthday, phone, profile_url, nickname,
                    created_at, updated_at, deleted_at, is_auth)
SELECT 1,
       3904577474,
       '황인준',
       '19970514',
       '01030222851',
       'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/members/e1f801c5-b774-4a16-a2a2-22615c0cf8d7_room.png',
       '집주인',
       NOW(),
       NOW(),
       NULL,
       true
WHERE NOT EXISTS (SELECT 1 FROM member WHERE member_id = 1);

INSERT INTO member (member_id, kakao_id, name, birthday, phone, profile_url, nickname,
                    created_at, updated_at, deleted_at, is_auth)
SELECT 2,
       987654321,
       '김철수',
       '1998-03-22',
       '010-5678-1234',
       'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/members/e1f801c5-b774-4a16-a2a2-22615c0cf8d7_room.png',
       '철수핑',
       NOW(),
       NOW(),
       NULL,
       true
WHERE NOT EXISTS (SELECT 1 FROM member WHERE member_id = 2);

INSERT INTO member (member_id, kakao_id, name, birthday, phone, profile_url, nickname,
                    created_at, updated_at, deleted_at, is_auth)
SELECT 3,
       123456789,
       '김성수',
       '1998-03-22',
       '010-5678-1234',
       'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/members/e1f801c5-b774-4a16-a2a2-22615c0cf8d7_room.png',
       '테스터',
       NOW(),
       NOW(),
       NULL,
       true
WHERE NOT EXISTS (SELECT 1 FROM member WHERE member_id = 3);

INSERT INTO member (member_id, kakao_id, name, birthday, phone, profile_url, nickname,
                    created_at, updated_at, deleted_at, is_auth)
SELECT 4,
       4206299294,
       '하정수',
       '1998-03-22',
       '010-5678-1234',
       'https://i.pinimg.com/236x/d8/a6/cb/d8a6cbb02bc2c5c27ae238db2e89425d.jpg',
       '정수핑',
       NOW(),
       NOW(),
       NULL,
       true
WHERE NOT EXISTS (SELECT 1 FROM member WHERE member_id = 4);

INSERT INTO room (room.room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 1,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-1999-533320',
       1170000,
       430000,
       45.75,
       55.32,
       100,
       '5층',
       15,
       1,
       '2025-05-01',
       '2010-08-10',
       '조용한 주택가에 위치한 아파트',
       '가까운 거리에 마트와 지하철역이 있으며, 남향이라 채광이 좋습니다.',
       10,
       3,
       2,
       'SOUTH',
       TRUE,
       TRUE,
       TRUE,
       '보증금 조절 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1
                  FROM room
                  WHERE room_id = 1);



INSERT INTO options (room_id, option_name)
SELECT 1, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 1 AND option_name = 'AIR_CONDITIONER');
INSERT INTO options (room_id, option_name)
SELECT 1, 'REFRIGERATOR'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 1 AND option_name = 'REFRIGERATOR');
INSERT INTO options (room_id, option_name)
SELECT 1, 'WASHING_MACHINE'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 1 AND option_name = 'WASHING_MACHINE');



INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 1, 'WATER'
WHERE NOT EXISTS (SELECT 1
                  FROM maintenance_include
                  WHERE room_id = 1
                    AND maintenance_include_name = 'WATER');
INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 1, 'INTERNET'
WHERE NOT EXISTS (SELECT 1
                  FROM maintenance_include
                  WHERE room_id = 1
                    AND maintenance_include_name = 'INTERNET');
INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 1, 'ELECTRICITY'
WHERE NOT EXISTS (SELECT 1
                  FROM maintenance_include
                  WHERE room_id = 1
                    AND maintenance_include_name = 'ELECTRICITY');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 1,
       '서울특별시 강남구 봉은사로 290',
       '102동 843호',
       '04583',
       37.501277,
       127.0396,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1
                  FROM address
                  WHERE room_id = 1);

-- === Room 2 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 2,
       1,
       'VILLA_HOUSE',
       'ON_SALE',
       '1146-1999-937154',
       2420000,
       630000,
       38.20,
       48.00,
       20,
       '2층',
       5,
       0,
       '2025-06-01',
       '2015-03-05',
       '아늑한 빌라, 정원 포함',
       '한적한 동네에 위치한 빌라, 마당이 있어 반려동물과 생활하기 좋음.',
       5,
       2,
       1,
       'WEST',
       TRUE,
       TRUE,
       FALSE,
       '보증금, 월세 협의 가능',
       TRUE,
       TRUE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 2);



INSERT INTO options (room_id, option_name)
SELECT 2, 'MICROWAVE'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 2 AND option_name = 'MICROWAVE');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 2, 'GAS'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 2 AND maintenance_include_name = 'GAS');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 2,
       '서울시 마포구 정원빌라',
       '103동 164호',
       '06466',
       37.5663,
       126.9015,
       '서울특별시',
       '마포구',
       '성산동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 2);


-- === Room 3 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 3,
       1,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2003-052628',
       2880000,
       330000,
       25.00,
       30.00,
       10,
       '1층',
       3,
       0,
       '2025-04-15',
       '2018-01-20',
       '1.5룸 구조의 원룸',
       '역세권에 위치, 풀옵션 원룸으로 자취 초보에게 적합.',
       8,
       2,
       1,
       'EAST',
       TRUE,
       FALSE,
       TRUE,
       '단기임대도 가능',
       TRUE,
       TRUE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 3);



INSERT INTO options (room_id, option_name)
SELECT 3, 'INDUCTION'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 3 AND option_name = 'INDUCTION');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 3, 'INTERNET'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 3 AND maintenance_include_name = 'INTERNET');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 3,
       '서울시 동작구 신대방원룸',
       '106동 756호',
       '01249',
       37.4993,
       126.9272,
       '서울특별시',
       '동작구',
       '신대방동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 3);


-- === Room 4 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 4,
       1,
       'OFFICETEL',
       'ON_SALE',
       '1146-2004-178014',
       2190000,
       920000,
       42.00,
       52.00,
       300,
       '12층',
       20,
       2,
       '2025-07-01',
       '2020-11-11',
       '테라스 있는 오피스텔',
       '회사와 가까운 위치, 혼자 살기 좋은 구조와 뷰.',
       15,
       2,
       1,
       'SOUTHEAST',
       TRUE,
       TRUE,
       TRUE,
       '주차공간 넉넉함',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 4);



INSERT INTO options (room_id, option_name)
SELECT 4, 'BED'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 4 AND option_name = 'BED');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 4, 'CLEANING'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 4 AND maintenance_include_name = 'CLEANING');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 4,
       '서울시 강서구 테라스오피스텔',
       '102동 705호',
       '01608',
       37.5481,
       126.8361,
       '서울특별시',
       '강서구',
       '화곡동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 4);


-- === Room 5 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 5,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-2020-348901',
       2700000,
       720000,
       50.00,
       60.00,
       150,
       '7층',
       18,
       1,
       '2025-08-10',
       '2005-09-01',
       '학군 좋은 아파트',
       '근처에 초·중·고 위치, 가족 단위 거주에 적합.',
       12,
       3,
       2,
       'NORTHEAST',
       TRUE,
       TRUE,
       TRUE,
       '조용한 단지 내 위치',
       TRUE,
       TRUE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 5);



INSERT INTO options (room_id, option_name)
SELECT 5, 'ELEVATOR'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 5 AND option_name = 'ELEVATOR');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 5, 'HEATING'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 5 AND maintenance_include_name = 'HEATING');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 5,
       '서울시 양천구 학군아파트',
       '102동 499호',
       '01649',
       37.5242,
       126.8566,
       '서울특별시',
       '양천구',
       '목동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 5);

-- === Room 6 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 6,
       3,
       'APARTMENT',
       'ON_SALE',
       '1146-2006-645729',
       1160000,
       1180000,
       51.44,
       42.22,
       72,
       '12층',
       18,
       1,
       '2025-05-10',
       '2020-04-11',
       '강변 근처 아파트',
       '한강 조망 가능, 산책로와 공원 가까움',
       11,
       3,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 6);



INSERT INTO options (room_id, option_name)
SELECT 6, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 6 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 6, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 6 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 6,
       '서울특별시 강남구 선릉로 2',
       '104동 173호',
       '03161',
       37.297,
       128.3152,
       '서울특별시',
       '강남구',
       '삼성동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 6);

-- === Room 7 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 7,
       3,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-1997-595704',
       2880000,
       480000,
       27.21,
       56.41,
       52,
       '2층',
       20,
       1,
       '2025-05-11',
       '2019-04-12',
       '역세권 신축 오피스텔',
       '지하철 도보 3분 거리, 깔끔한 내부 구조',
       13,
       1,
       2,
       'WEST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 7);



INSERT INTO options (room_id, option_name)
SELECT 7, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 7 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 7, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 7 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 7,
       '경기도 분당구 강남대로 189',
       '108동 596호',
       '06160',
       36.2196,
       128.102,
       '경기도',
       '분당구',
       '정자동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 7);

-- === Room 8 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 8,
       2,
       'VILLA_HOUSE',
       'ON_SALE',
       '1146-2008-943212',
       2670000,
       1080000,
       48.88,
       37.47,
       199,
       '8층',
       17,
       0,
       '2025-05-12',
       '2018-04-12',
       '조용한 단독주택',
       '단독 주택 단지, 이웃 간섭 없는 환경',
       11,
       2,
       2,
       'SOUTH',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 8);



INSERT INTO options (room_id, option_name)
SELECT 8, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 8 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 8, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 8 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 8,
       '부산광역시 해운대구 테헤란로 145',
       '106동 762호',
       '06184',
       36.2959,
       127.0929,
       '부산광역시',
       '해운대구',
       '중동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 8);

-- === Room 9 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 9,
       1,
       'OFFICETEL',
       'ON_SALE',
       '1146-2004-174942',
       1970000,
       710000,
       42.77,
       41.24,
       136,
       '5층',
       20,
       1,
       '2025-05-13',
       '2017-04-12',
       '학세권 투룸',
       '학교와 도서관 인근, 학생 또는 교직원 추천',
       5,
       2,
       2,
       'NORTH',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 9);



INSERT INTO options (room_id, option_name)
SELECT 9, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 9 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 9, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 9 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 9,
       '대구광역시 수성구 봉은사로 134',
       '101동 369호',
       '07626',
       35.2773,
       126.8545,
       '대구광역시',
       '수성구',
       '범어동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 9);

-- === Room 10 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 10,
       3,
       'APARTMENT',
       'ON_SALE',
       '1146-2021-658417',
       2290000,
       1130000,
       49.57,
       62.87,
       11,
       '9층',
       10,
       0,
       '2025-05-14',
       '2016-04-12',
       '주차 공간 넉넉한 빌라',
       '차량 2대 이상 주차 가능, 도로 접근성 좋음',
       12,
       2,
       2,
       'NORTHWEST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 10);



INSERT INTO options (room_id, option_name)
SELECT 10, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 10 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 10, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 10 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 10,
       '인천광역시 연수구 강남대로 291',
       '107동 865호',
       '01318',
       37.0922,
       128.2703,
       '인천광역시',
       '연수구',
       '송도동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 10);

-- === Room 11 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 11,
       1,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2015-729907',
       1600000,
       930000,
       36.23,
       42.01,
       200,
       '15층',
       17,
       0,
       '2025-05-15',
       '2015-04-13',
       '강변 근처 아파트',
       '한강 조망 가능, 산책로와 공원 가까움',
       11,
       2,
       2,
       'NORTHEAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 11);



INSERT INTO options (room_id, option_name)
SELECT 11, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 11 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 11, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 11 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 11,
       '서울특별시 강남구 도산대로 128',
       '103동 290호',
       '04501',
       35.2102,
       127.745,
       '서울특별시',
       '강남구',
       '삼성동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 11);

-- === Room 12 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 12,
       1,
       'VILLA_HOUSE',
       'ON_SALE',
       '1146-2010-919524',
       1250000,
       1050000,
       46.1,
       55.97,
       39,
       '6층',
       19,
       0,
       '2025-05-16',
       '2014-04-13',
       '역세권 신축 오피스텔',
       '지하철 도보 3분 거리, 깔끔한 내부 구조',
       9,
       1,
       2,
       'SOUTHWEST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 12);



INSERT INTO options (room_id, option_name)
SELECT 12, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 12 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 12, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 12 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 12,
       '경기도 분당구 봉은사로 254',
       '109동 491호',
       '01205',
       37.5972,
       128.4878,
       '경기도',
       '분당구',
       '정자동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 12);

-- === Room 13 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 13,
       1,
       'OFFICETEL',
       'ON_SALE',
       '1146-1999-704989',
       1380000,
       330000,
       43.99,
       64.48,
       111,
       '10층',
       11,
       1,
       '2025-05-17',
       '2013-04-13',
       '조용한 단독주택',
       '단독 주택 단지, 이웃 간섭 없는 환경',
       14,
       3,
       1,
       'SOUTHEAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 13);



INSERT INTO options (room_id, option_name)
SELECT 13, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 13 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 13, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 13 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 13,
       '부산광역시 해운대구 언주로 69',
       '102동 203호',
       '04466',
       35.111,
       127.6113,
       '부산광역시',
       '해운대구',
       '중동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 13);

-- === Room 14 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 14,
       2,
       'APARTMENT',
       'ON_SALE',
       '1146-2021-096211',
       2150000,
       840000,
       47.1,
       37.68,
       20,
       '11층',
       15,
       0,
       '2025-05-18',
       '2012-04-13',
       '학세권 투룸',
       '학교와 도서관 인근, 학생 또는 교직원 추천',
       7,
       2,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 14);



INSERT INTO options (room_id, option_name)
SELECT 14, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 14 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 14, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 14 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 14,
       '대구광역시 수성구 테헤란로 264',
       '108동 520호',
       '06225',
       35.1066,
       128.3638,
       '대구광역시',
       '수성구',
       '범어동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 14);

-- === Room 15 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 15,
       1,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2015-010956',
       1560000,
       980000,
       28.56,
       36.13,
       180,
       '7층',
       9,
       1,
       '2025-05-19',
       '2011-04-14',
       '주차 공간 넉넉한 빌라',
       '차량 2대 이상 주차 가능, 도로 접근성 좋음',
       7,
       2,
       1,
       'WEST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 15);



INSERT INTO options (room_id, option_name)
SELECT 15, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 15 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 15, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 15 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 15,
       '인천광역시 연수구 도산대로 281',
       '107동 187호',
       '07713',
       37.1562,
       127.4077,
       '인천광역시',
       '연수구',
       '송도동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 15);

-- === Room 16 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 16,
       1,
       'VILLA_HOUSE',
       'ON_SALE',
       '1146-2023-085792',
       2520000,
       900000,
       49.16,
       43.49,
       12,
       '15층',
       17,
       2,
       '2025-05-20',
       '2010-04-14',
       '강변 근처 아파트',
       '한강 조망 가능, 산책로와 공원 가까움',
       6,
       2,
       1,
       'SOUTH',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 16);



INSERT INTO options (room_id, option_name)
SELECT 16, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 16 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 16, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 16 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 16,
       '서울특별시 강남구 도산대로 262',
       '106동 817호',
       '01568',
       35.6418,
       128.5523,
       '서울특별시',
       '강남구',
       '삼성동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 16);

-- === Room 17 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 17,
       1,
       'OFFICETEL',
       'ON_SALE',
       '1146-2024-720020',
       2310000,
       510000,
       51.65,
       60.1,
       159,
       '1층',
       1,
       2,
       '2025-05-21',
       '2009-04-14',
       '역세권 신축 오피스텔',
       '지하철 도보 3분 거리, 깔끔한 내부 구조',
       5,
       2,
       1,
       'NORTH',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 17);



INSERT INTO options (room_id, option_name)
SELECT 17, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 17 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 17, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 17 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 17,
       '경기도 분당구 테헤란로 151',
       '106동 235호',
       '04758',
       35.3498,
       126.8263,
       '경기도',
       '분당구',
       '정자동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 17);

-- === Room 18 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 18,
       3,
       'APARTMENT',
       'ON_SALE',
       '1146-2019-960903',
       2170000,
       740000,
       51.67,
       53.92,
       96,
       '1층',
       12,
       1,
       '2025-05-22',
       '2008-04-14',
       '조용한 단독주택',
       '단독 주택 단지, 이웃 간섭 없는 환경',
       12,
       2,
       1,
       'NORTHWEST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 18);



INSERT INTO options (room_id, option_name)
SELECT 18, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 18 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 18, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 18 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 18,
       '부산광역시 해운대구 테헤란로 129',
       '105동 360호',
       '02921',
       36.7415,
       128.6057,
       '부산광역시',
       '해운대구',
       '중동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 18);

-- === Room 19 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 19,
       2,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-1995-694557',
       2320000,
       300000,
       25.37,
       56.21,
       183,
       '3층',
       12,
       0,
       '2025-05-23',
       '2007-04-15',
       '학세권 투룸',
       '학교와 도서관 인근, 학생 또는 교직원 추천',
       9,
       2,
       2,
       'NORTHEAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 19);



INSERT INTO options (room_id, option_name)
SELECT 19, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 19 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 19, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 19 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 19,
       '대구광역시 수성구 테헤란로 66',
       '109동 424호',
       '02906',
       35.2219,
       128.4593,
       '대구광역시',
       '수성구',
       '범어동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 19);

-- === Room 20 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 20,
       1,
       'VILLA_HOUSE',
       'ON_SALE',
       '1146-2014-440414',
       1150000,
       740000,
       33.01,
       59.99,
       188,
       '15층',
       18,
       2,
       '2025-05-24',
       '2006-04-15',
       '주차 공간 넉넉한 빌라',
       '차량 2대 이상 주차 가능, 도로 접근성 좋음',
       14,
       2,
       2,
       'SOUTHWEST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 20);



INSERT INTO options (room_id, option_name)
SELECT 20, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 20 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 20, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 20 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 20,
       '인천광역시 연수구 봉은사로 28',
       '104동 328호',
       '09654',
       36.5042,
       127.6048,
       '인천광역시',
       '연수구',
       '송도동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 20);

-- === Room 21 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 21,
       3,
       'OFFICETEL',
       'ON_SALE',
       '1146-2003-882938',
       1460000,
       300000,
       30.74,
       36.32,
       200,
       '8층',
       19,
       2,
       '2025-05-25',
       '2005-04-15',
       '강변 근처 아파트',
       '한강 조망 가능, 산책로와 공원 가까움',
       12,
       3,
       2,
       'SOUTHEAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 21);



INSERT INTO options (room_id, option_name)
SELECT 21, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 21 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 21, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 21 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 21,
       '서울특별시 강남구 강남대로 100',
       '107동 597호',
       '07717',
       36.9187,
       127.9191,
       '서울특별시',
       '강남구',
       '삼성동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 21);

-- === Room 22 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 22,
       3,
       'APARTMENT',
       'ON_SALE',
       '1146-2013-159162',
       2460000,
       820000,
       38.41,
       50.75,
       106,
       '3층',
       16,
       1,
       '2025-05-26',
       '2004-04-15',
       '역세권 신축 오피스텔',
       '지하철 도보 3분 거리, 깔끔한 내부 구조',
       15,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 22);



INSERT INTO options (room_id, option_name)
SELECT 22, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 22 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 22, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 22 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 22,
       '경기도 분당구 테헤란로 114',
       '101동 248호',
       '01462',
       36.8895,
       128.513,
       '경기도',
       '분당구',
       '정자동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 22);

-- === Room 23 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 23,
       2,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2010-163505',
       2640000,
       500000,
       50.76,
       62.34,
       12,
       '9층',
       19,
       0,
       '2025-05-27',
       '2003-04-16',
       '조용한 단독주택',
       '단독 주택 단지, 이웃 간섭 없는 환경',
       9,
       3,
       2,
       'WEST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 23);



INSERT INTO options (room_id, option_name)
SELECT 23, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 23 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 23, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 23 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 23,
       '부산광역시 해운대구 강남대로 261',
       '103동 474호',
       '05028',
       37.1629,
       128.1354,
       '부산광역시',
       '해운대구',
       '중동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 23);

-- === Room 24 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 24,
       1,
       'VILLA_HOUSE',
       'ON_SALE',
       '1146-2010-861930',
       2340000,
       720000,
       31.0,
       48.79,
       197,
       '6층',
       11,
       2,
       '2025-05-28',
       '2002-04-16',
       '학세권 투룸',
       '학교와 도서관 인근, 학생 또는 교직원 추천',
       5,
       3,
       1,
       'SOUTH',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 24);



INSERT INTO options (room_id, option_name)
SELECT 24, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 24 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 24, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 24 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 24,
       '대구광역시 수성구 도산대로 198',
       '105동 805호',
       '04331',
       36.5158,
       127.8325,
       '대구광역시',
       '수성구',
       '범어동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 24);

-- === Room 25 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 25,
       2,
       'OFFICETEL',
       'ON_SALE',
       '1146-2016-023667',
       1590000,
       950000,
       25.47,
       49.43,
       138,
       '1층',
       16,
       2,
       '2025-05-29',
       '2001-04-16',
       '주차 공간 넉넉한 빌라',
       '차량 2대 이상 주차 가능, 도로 접근성 좋음',
       15,
       1,
       1,
       'NORTH',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 25);



INSERT INTO options (room_id, option_name)
SELECT 25, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 25 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 25, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 25 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 25,
       '인천광역시 연수구 강남대로 177',
       '101동 473호',
       '06093',
       36.3987,
       128.4067,
       '인천광역시',
       '연수구',
       '송도동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 25);

-- === Room 26 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 26,
       3,
       'APARTMENT',
       'ON_SALE',
       '1146-2003-576831',
       2150000,
       950000,
       26.47,
       46.93,
       77,
       '8층',
       20,
       0,
       '2025-05-30',
       '2000-04-16',
       '강변 근처 아파트',
       '한강 조망 가능, 산책로와 공원 가까움',
       10,
       2,
       2,
       'NORTHWEST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 26);



INSERT INTO options (room_id, option_name)
SELECT 26, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 26 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 26, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 26 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 26,
       '서울특별시 강남구 역삼로 273',
       '105동 891호',
       '07308',
       36.8187,
       127.2378,
       '서울특별시',
       '강남구',
       '삼성동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 26);

-- === Room 27 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 27,
       2,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2023-009189',
       1180000,
       710000,
       48.99,
       50.74,
       31,
       '14층',
       15,
       1,
       '2025-05-31',
       '1999-04-17',
       '역세권 신축 오피스텔',
       '지하철 도보 3분 거리, 깔끔한 내부 구조',
       8,
       1,
       2,
       'NORTHEAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 27);



INSERT INTO options (room_id, option_name)
SELECT 27, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 27 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 27, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 27 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 27,
       '경기도 분당구 역삼로 219',
       '102동 700호',
       '08701',
       36.0817,
       127.0031,
       '경기도',
       '분당구',
       '정자동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 27);

-- === Room 28 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 28,
       1,
       'VILLA_HOUSE',
       'ON_SALE',
       '1146-2000-172495',
       1480000,
       480000,
       28.76,
       30.07,
       195,
       '11층',
       18,
       1,
       '2025-06-01',
       '1998-04-17',
       '조용한 단독주택',
       '단독 주택 단지, 이웃 간섭 없는 환경',
       5,
       3,
       1,
       'SOUTHWEST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 28);



INSERT INTO options (room_id, option_name)
SELECT 28, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 28 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 28, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 28 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 28,
       '부산광역시 해운대구 역삼로 46',
       '105동 658호',
       '03223',
       37.4749,
       128.0728,
       '부산광역시',
       '해운대구',
       '중동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 28);

-- === Room 29 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 29,
       2,
       'OFFICETEL',
       'ON_SALE',
       '1146-2011-574487',
       1390000,
       570000,
       41.48,
       56.73,
       124,
       '15층',
       18,
       0,
       '2025-06-02',
       '1997-04-17',
       '학세권 투룸',
       '학교와 도서관 인근, 학생 또는 교직원 추천',
       13,
       3,
       2,
       'SOUTHEAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 29);



INSERT INTO options (room_id, option_name)
SELECT 29, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 29 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 29, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 29 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 29,
       '대구광역시 수성구 도산대로 38',
       '105동 107호',
       '02116',
       36.852,
       128.3152,
       '대구광역시',
       '수성구',
       '범어동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 29);

-- === Room 30 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 30,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-2016-360971',
       1470000,
       640000,
       25.42,
       62.61,
       123,
       '10층',
       12,
       2,
       '2025-06-03',
       '1996-04-17',
       '주차 공간 넉넉한 빌라',
       '차량 2대 이상 주차 가능, 도로 접근성 좋음',
       10,
       2,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 30);



INSERT INTO options (room_id, option_name)
SELECT 30, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 30 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 30, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 30 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 30,
       '인천광역시 연수구 테헤란로 44',
       '103동 470호',
       '01112',
       37.0052,
       128.484,
       '인천광역시',
       '연수구',
       '송도동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 30);

-- === Room 31 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 31,
       3,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2017-903881',
       2270000,
       940000,
       28.33,
       40.0,
       113,
       '8층',
       14,
       2,
       '2025-06-04',
       '1995-04-18',
       '강변 근처 아파트',
       '한강 조망 가능, 산책로와 공원 가까움',
       8,
       1,
       1,
       'WEST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 31);



INSERT INTO options (room_id, option_name)
SELECT 31, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 31 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 31, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 31 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 31,
       '서울특별시 강남구 테헤란로 131',
       '107동 818호',
       '08596',
       35.0272,
       128.5095,
       '서울특별시',
       '강남구',
       '삼성동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 31);

-- === Room 32 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 32,
       1,
       'VILLA_HOUSE',
       'ON_SALE',
       '1146-2024-479534',
       2580000,
       440000,
       31.25,
       40.21,
       160,
       '8층',
       18,
       1,
       '2025-06-05',
       '1994-04-18',
       '역세권 신축 오피스텔',
       '지하철 도보 3분 거리, 깔끔한 내부 구조',
       6,
       3,
       2,
       'SOUTH',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 32);



INSERT INTO options (room_id, option_name)
SELECT 32, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 32 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 32, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 32 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 32,
       '경기도 분당구 선릉로 52',
       '104동 477호',
       '06658',
       35.483,
       126.9653,
       '경기도',
       '분당구',
       '정자동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 32);

-- === Room 33 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 33,
       2,
       'OFFICETEL',
       'ON_SALE',
       '1146-2009-684263',
       1220000,
       930000,
       37.95,
       56.56,
       61,
       '8층',
       11,
       1,
       '2025-06-06',
       '1993-04-18',
       '조용한 단독주택',
       '단독 주택 단지, 이웃 간섭 없는 환경',
       15,
       2,
       1,
       'NORTH',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 33);



INSERT INTO options (room_id, option_name)
SELECT 33, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 33 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 33, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 33 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 33,
       '부산광역시 해운대구 언주로 101',
       '103동 711호',
       '09481',
       36.0929,
       127.1413,
       '부산광역시',
       '해운대구',
       '중동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 33);

-- === Room 34 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 34,
       3,
       'APARTMENT',
       'ON_SALE',
       '1146-2019-033479',
       2760000,
       370000,
       49.91,
       31.22,
       134,
       '8층',
       19,
       1,
       '2025-06-07',
       '1992-04-18',
       '학세권 투룸',
       '학교와 도서관 인근, 학생 또는 교직원 추천',
       14,
       2,
       2,
       'NORTHWEST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 34);



INSERT INTO options (room_id, option_name)
SELECT 34, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 34 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 34, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 34 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 34,
       '대구광역시 수성구 도산대로 107',
       '108동 477호',
       '05785',
       36.3547,
       127.2715,
       '대구광역시',
       '수성구',
       '범어동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 34);

-- === Room 35 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 35,
       1,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2003-401064',
       1050000,
       700000,
       36.37,
       50.79,
       118,
       '3층',
       11,
       2,
       '2025-06-08',
       '1991-04-19',
       '주차 공간 넉넉한 빌라',
       '차량 2대 이상 주차 가능, 도로 접근성 좋음',
       14,
       2,
       2,
       'NORTHEAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 35);



INSERT INTO options (room_id, option_name)
SELECT 35, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 35 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 35, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 35 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 35,
       '인천광역시 연수구 도산대로 249',
       '105동 125호',
       '06170',
       35.6394,
       126.9525,
       '인천광역시',
       '연수구',
       '송도동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 35);

-- === Room 36 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 36,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-2014-621261',
       9000000,
       650000,
       25.46,
       48.32,
       129,
       '4층',
       6,
       2,
       '2025-05-10',
       '2020-04-11',
       '지하철역 근처 근처 빌라, 편의시설 밀접. 인근에 공원과 산책로가 있어 여가 생활에도 좋습니다.',
       '지하철역 근처 빌라, 편의시설 밀접',
       15,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 36);



INSERT INTO options (room_id, option_name)
SELECT 36, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 36 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 36, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 36 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 36,
       '서울특별시 강남구 테헤란로 74',
       '102동 120호',
       '08199',
       37.504209,
       127.026527,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 36);

-- === Room 37 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 37,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-2003-265792',
       5000000,
       1350000,
       35.58,
       37.44,
       97,
       '14층',
       19,
       1,
       '2025-05-10',
       '2020-04-11',
       '강변 리모델링 완료, 주차 가능',
       '강변 근처 리모델링 완료, 주차 가능. 인근에 공원과 산책로가 있어 여가 생활에도 좋습니다.',
       5,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 37);



INSERT INTO options (room_id, option_name)
SELECT 37, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 37 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 37, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 37 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 37,
       '서울특별시 강남구 강남대로 105',
       '104동 242호',
       '02613',
       37.490048,
       127.036119,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 37);

-- === Room 38 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 38,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-2006-700081',
       1500000,
       760000,
       42.12,
       52.56,
       163,
       '15층',
       19,
       1,
       '2025-05-10',
       '2020-04-11',
       '강변 복층 구조, 햇빛 잘 듬',
       '강변 근처 복층 구조, 햇빛 잘 듬. 최근 리모델링으로 내외부 상태가 매우 우수합니다.',
       14,
       2,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 38);



INSERT INTO options (room_id, option_name)
SELECT 38, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 38 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 38, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 38 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 38,
       '서울특별시 강남구 언주로 5',
       '104동 838호',
       '01281',
       37.506431,
       127.031701,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 38);

-- === Room 39 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 39,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-2004-164863',
       20000000,
       1000000,
       49.28,
       61.82,
       175,
       '5층',
       10,
       1,
       '2025-05-10',
       '2020-04-11',
       '남산 신축 건물, 층간소음 적음',
       '남산 근처 신축 건물, 층간소음 적음. 교통 접근성이 뛰어나 출퇴근이 편리합니다.',
       6,
       1,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 39);



INSERT INTO options (room_id, option_name)
SELECT 39, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 39 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 39, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 39 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 39,
       '서울특별시 강남구 언주로 195',
       '108동 306호',
       '09547',
       37.490138,
       127.036174,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 39);

-- === Room 40 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 40,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-2010-677388',
       1500000,
       600000,
       44.66,
       63.16,
       30,
       '8층',
       15,
       0,
       '2025-05-10',
       '2020-04-11',
       '남산 아파트, 편의시설 밀접',
       '남산 근처 아파트, 편의시설 밀접. 생활 편의시설이 가까워 일상생활이 매우 편리합니다.',
       5,
       1,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 40);



INSERT INTO options (room_id, option_name)
SELECT 40, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 40 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 40, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 40 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 40,
       '서울특별시 강남구 봉은사로 7',
       '104동 838호',
       '03715',
       37.495092,
       127.030957,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 40);

-- === Room 41 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 41,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-2022-675159',
       10000000,
       900000,
       39.81,
       36.9,
       41,
       '9층',
       9,
       1,
       '2025-05-10',
       '2020-04-11',
       '조용한 주택가 쓰리룸, 보안 철저',
       '조용한 주택가 근처 쓰리룸, 보안 철저. 넓은 실내 공간과 효율적인 구조로 거주 만족도가 높습니다.',
       6,
       1,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 41);



INSERT INTO options (room_id, option_name)
SELECT 41, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 41 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 41, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 41 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 41,
       '서울특별시 강남구 봉은사로 36',
       '103동 888호',
       '04926',
       37.491351,
       127.036883,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 41);

-- === Room 42 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 42,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-2018-369670',
       1460000,
       950000,
       32.1,
       32.42,
       40,
       '13층',
       16,
       1,
       '2025-05-10',
       '2020-04-11',
       '강변 테라스 포함, 관리비 저렴',
       '강변 근처 테라스 포함, 관리비 저렴. 넓은 실내 공간과 효율적인 구조로 거주 만족도가 높습니다.',
       8,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 42);



INSERT INTO options (room_id, option_name)
SELECT 42, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 42 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 42, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 42 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 42,
       '서울특별시 강남구 강남대로 177',
       '104동 256호',
       '07264',
       37.508336,
       127.035698,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 42);

-- === Room 43 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 43,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-1996-218872',
       1560000,
       1030000,
       48.18,
       41.02,
       33,
       '14층',
       18,
       1,
       '2025-05-10',
       '2020-04-11',
       '상업지구 아파트, 엘리베이터 있음',
       '상업지구 근처 아파트, 엘리베이터 있음. 최근 리모델링으로 내외부 상태가 매우 우수합니다.',
       13,
       3,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 43);



INSERT INTO options (room_id, option_name)
SELECT 43, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 43 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 43, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 43 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 43,
       '서울특별시 강남구 강남대로 143',
       '109동 553호',
       '04268',
       37.502843,
       127.034936,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 43);

-- === Room 44 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 44,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-2014-096395',
       1500000,
       100000,
       40.31,
       64.2,
       27,
       '12층',
       13,
       1,
       '2025-05-10',
       '2020-04-11',
       '도심 투룸, 햇빛 잘 듬',
       '도심 근처 투룸, 햇빛 잘 듬. 보안 시스템이 잘 갖춰져 있어 안심하고 생활할 수 있습니다.',
       14,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 44);



INSERT INTO options (room_id, option_name)
SELECT 44, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 44 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 44, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 44 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 44,
       '서울특별시 강남구 강남대로 167',
       '108동 487호',
       '04585',
       37.497977,
       127.026814,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 44);

-- === Room 45 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 45,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-1999-680470',
       2000000,
       800000,
       34.99,
       50.91,
       64,
       '8층',
       14,
       2,
       '2025-05-10',
       '2020-04-11',
       '조용한 주택가 아파트, 햇빛 잘 듬',
       '조용한 주택가 근처 아파트, 햇빛 잘 듬. 채광이 좋고 통풍이 잘 되어 쾌적한 실내 환경을 제공합니다.',
       14,
       3,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 45);



INSERT INTO options (room_id, option_name)
SELECT 45, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 45 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 45, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 45 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 45,
       '서울특별시 강남구 도산대로 200',
       '107동 585호',
       '06402',
       37.493582,
       127.029216,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 45);

-- === Room 46 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 46,
       4,
       'APARTMENT',
       'ON_SALE',
       '1146-2011-821167',
       3000000,
       700000,
       35.12,
       50.76,
       71,
       '8층',
       8,
       0,
       '2025-05-10',
       '2020-04-11',
       '상업지구 복층 구조, 관리비 저렴',
       '상업지구 근처 복층 구조, 관리비 저렴. 주변 환경이 조용하고 안전하여 가족 단위 거주에 적합합니다.',
       10,
       3,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 46);



INSERT INTO options (room_id, option_name)
SELECT 46, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 46 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 46, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 46 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 46,
       '서울특별시 강남구 역삼로 96',
       '106동 564호',
       '01524',
       37.494639,
       127.024219,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 46);

-- === Room 47 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 47,
       4,
       'APARTMENT',
       'ON_SALE',
       '1146-2019-678172',
       2600000,
       1140000,
       44.48,
       46.73,
       131,
       '15층',
       17,
       0,
       '2025-05-10',
       '2020-04-11',
       '버스정류장 앞 룸, 햇빛 잘 듬',
       '버스정류장 앞 근처 쓰리룸, 햇빛 잘 듬. 채광이 좋고 통풍이 잘 되어 쾌적한 실내 환경을 제공합니다.',
       9,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 47);



INSERT INTO options (room_id, option_name)
SELECT 47, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 47 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 47, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 47 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 47,
       '서울특별시 강남구 언주로 102',
       '106동 390호',
       '09968',
       37.503295,
       127.031881,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 47);

-- === Room 48 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 48,
       4,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2016-932364',
       1820000,
       320000,
       25.31,
       46.74,
       124,
       '4층',
       14,
       0,
       '2025-05-10',
       '2020-04-11',
       '대학교 근처 테라스 포함, 층간소음 적음',
       '대학교 근처 근처 테라스 포함, 층간소음 적음. 최근 리모델링으로 내외부 상태가 매우 우수합니다.',
       7,
       3,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 48);



INSERT INTO options (room_id, option_name)
SELECT 48, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 48 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 48, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 48 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 48,
       '서울특별시 강남구 언주로 98',
       '105동 572호',
       '09509',
       37.497208,
       127.028668,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 48);

-- === Room 49 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 49,
       4,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2018-450116',
       10000000,
       700000,
       39.99,
       50.37,
       163,
       '9층',
       16,
       1,
       '2025-05-10',
       '2020-04-11',
       '지하철역 근처 투룸, 햇빛 잘 듬',
       '지하철역 근처 근처 투룸, 햇빛 잘 듬. 주변 환경이 조용하고 안전하여 가족 단위 거주에 적합합니다.',
       7,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 49);



INSERT INTO options (room_id, option_name)
SELECT 49, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 49 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 49, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 49 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 49,
       '서울특별시 강남구 강남대로 15',
       '105동 192호',
       '04664',
       37.502539,
       127.02537,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 49);

-- === Room 50 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 50,
       4,
       'APARTMENT',
       'ON_SALE',
       '1146-2019-865445',
       8000000,
       1000000,
       38.02,
       52.36,
       45,
       '5층',
       13,
       0,
       '2025-05-10',
       '2020-04-11',
       '한강 아파트, 편의시설 밀접',
       '한강 근처 아파트, 편의시설 밀접. 보안 시스템이 잘 갖춰져 있어 안심하고 생활할 수 있습니다.',
       5,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 50);



INSERT INTO options (room_id, option_name)
SELECT 50, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 50 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 50, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 50 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 50,
       '서울특별시 강남구 테헤란로 210',
       '104동 187호',
       '09003',
       37.495689,
       127.03715,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 50);

-- === Room 51 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 51,
       4,
       'APARTMENT',
       'ON_SALE',
       '1146-2014-806449',
       14000000,
       880000,
       43.31,
       36.73,
       47,
       '8층',
       14,
       2,
       '2025-05-10',
       '2020-04-11',
       '남산 신축 건물, 층간소음 적음',
       '남산 근처 신축 건물, 층간소음 적음. 교통 접근성이 뛰어나 출퇴근이 편리합니다.',
       9,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 51);



INSERT INTO options (room_id, option_name)
SELECT 51, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 51 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 51, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 51 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 51,
       '서울특별시 강남구 강남대로 204',
       '107동 520호',
       '07647',
       37.498043,
       127.027086,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 51);

-- === Room 52 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 52,
       4,
       'APARTMENT',
       'ON_SALE',
       '1146-2003-369926',
       11500000,
       800000,
       29.04,
       57.13,
       111,
       '4층',
       9,
       0,
       '2025-05-10',
       '2020-04-11',
       '남산 아파트, 편의시설 밀접',
       '남산 근처 아파트, 편의시설 밀접. 생활 편의시설이 가까워 일상생활이 매우 편리합니다.',
       15,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 52);



INSERT INTO options (room_id, option_name)
SELECT 52, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 52 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 52, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 52 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 52,
       '서울특별시 강남구 테헤란로 28',
       '105동 437호',
       '06860',
       37.490022,
       127.031971,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 52);

-- === Room 53 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 53,
       4,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2016-829723',
       2710000,
       760000,
       30.54,
       55.89,
       98,
       '14층',
       17,
       2,
       '2025-05-10',
       '2020-04-11',
       '한강 복층 구조, 보안 철저',
       '한강 근처 복층 구조, 보안 철저. 넓은 실내 공간과 효율적인 구조로 거주 만족도가 높습니다.',
       12,
       1,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 53);



INSERT INTO options (room_id, option_name)
SELECT 53, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 53 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 53, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 53 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 53,
       '서울특별시 강남구 봉은사로 48',
       '108동 616호',
       '02022',
       37.508222,
       127.0297,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 53);

-- === Room 54 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 54,
       4,
       'VILLA_HOUSE',
       'ON_SALE',
       '1146-1996-087710',
       1190000,
       1160000,
       49.47,
       37.28,
       162,
       '13층',
       19,
       2,
       '2025-05-10',
       '2020-04-11',
       '도심 빌라, 주차 가능',
       '도심 근처 빌라, 주차 가능. 보안 시스템이 잘 갖춰져 있어 안심하고 생활할 수 있습니다.',
       13,
       3,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 54);



INSERT INTO options (room_id, option_name)
SELECT 54, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 54 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 54, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 54 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 54,
       '서울특별시 강남구 선릉로 220',
       '109동 208호',
       '04497',
       37.498429,
       127.028845,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 54);

-- === Room 55 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 55,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-1995-660000',
       15000000,
       1000000,
       47.33,
       58.79,
       185,
       '12층',
       16,
       2,
       '2025-05-10',
       '2020-04-11',
       '조용한 주택가 투룸, 주차 가능',
       '조용한 주택가 근처 투룸, 주차 가능. 넓은 실내 공간과 효율적인 구조로 거주 만족도가 높습니다.',
       14,
       2,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 55);



INSERT INTO options (room_id, option_name)
SELECT 55, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 55 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 55, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 55 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 55,
       '서울특별시 강남구 도산대로 78',
       '103동 327호',
       '01089',
       37.50678,
       127.026414,
       '서울특별시',
       '강남구',
       '역삼동'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 55);

-- === Room 56 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 56,
       1,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2002-684165',
       5000000,
       550000,
       35.22,
       56.5,
       187,
       '10층',
       15,
       1,
       '2025-05-10',
       '2020-04-11',
       '버스정류장 앞 복층 구조, 관리비 저렴',
       '버스정류장 앞 근처 복층 구조, 관리비 저렴. 생활 편의시설이 가까워 일상생활이 매우 편리합니다.',
       9,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 56);



INSERT INTO options (room_id, option_name)
SELECT 56, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 56 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 56, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 56 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 56,
       '서울특별시 강남구 봉은사로 190',
       '103동 566호',
       '05489',
       37.499174,
       127.0368,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 56);

-- === Room 57 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 57,
       1,
       'APARTMENT',
       'ON_SALE',
       '1146-1995-625209',
       2620000,
       800000,
       36.69,
       37.65,
       144,
       '7층',
       7,
       0,
       '2025-05-10',
       '2020-04-11',
       '강변 테라스 포함, 층간소음 적음',
       '강변 근처 테라스 포함, 층간소음 적음. 최근 리모델링으로 내외부 상태가 매우 우수합니다.',
       15,
       2,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 57);



INSERT INTO options (room_id, option_name)
SELECT 57, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 57 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 57, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 57 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 57,
       '서울특별시 강남구 강남대로 139',
       '104동 794호',
       '07375',
       37.492726,
       127.021202,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 57);

-- === Room 58 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 58,
       4,
       'VILLA_HOUSE',
       'ON_SALE',
       '1146-2010-384042',
       4000000,
       700000,
       29.48,
       30.21,
       160,
       '10층',
       13,
       0,
       '2025-05-10',
       '2020-04-11',
       '지하철역 근처 빌라, 층간소음 적음',
       '지하철역 근처 근처 빌라, 층간소음 적음. 넓은 실내 공간과 효율적인 구조로 거주 만족도가 높습니다.',
       15,
       3,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 58);



INSERT INTO options (room_id, option_name)
SELECT 58, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 58 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 58, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 58 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 58,
       '서울특별시 강남구 강남대로 142',
       '105동 473호',
       '01963',
       37.49058,
       127.030071,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 58);

-- === Room 59 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 59,
       4,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2002-986125',
       3000000,
       700000,
       53.18,
       37.84,
       136,
       '15층',
       17,
       1,
       '2025-05-10',
       '2020-04-11',
       '조용한 주택가 투룸, 관리비 저렴',
       '조용한 주택가 근처 투룸, 관리비 저렴. 채광이 좋고 통풍이 잘 되어 쾌적한 실내 환경을 제공합니다.',
       7,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 59);



INSERT INTO options (room_id, option_name)
SELECT 59, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 59 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 59, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 59 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 59,
       '서울특별시 강남구 도산대로 40',
       '106동 403호',
       '07881',
       37.493693,
       127.031107,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 59);

-- === Room 60 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 60,
       4,
       'APARTMENT',
       'ON_SALE',
       '1146-2024-897135',
       4500000,
       500000,
       34.64,
       31.72,
       179,
       '10층',
       17,
       2,
       '2025-05-10',
       '2020-04-11',
       '대학교 근처 리모델링 완료, 보안 철저',
       '대학교 근처 근처 리모델링 완료, 보안 철저. 생활 편의시설이 가까워 일상생활이 매우 편리합니다.',
       12,
       3,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 60);



INSERT INTO options (room_id, option_name)
SELECT 60, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 60 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 60, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 60 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 60,
       '서울특별시 강남구 언주로 294',
       '101동 719호',
       '01874',
       37.508899,
       127.033343,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 60);

-- === Room 61 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 61,
       4,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-1996-115465',
       6800000,
       800000,
       28.01,
       56.39,
       51,
       '11층',
       16,
       0,
       '2025-05-10',
       '2020-04-11',
       '한강 원룸, 주차 가능',
       '한강 근처 원룸, 주차 가능. 최근 리모델링으로 내외부 상태가 매우 우수합니다.',
       8,
       1,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 61);



INSERT INTO options (room_id, option_name)
SELECT 61, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 61 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 61, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 61 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 61,
       '서울특별시 강남구 언주로 263',
       '109동 873호',
       '08238',
       37.500346,
       127.031464,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 61);

-- === Room 62 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 62,
       4,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2018-933054',
       5000000,
       900000,
       28.11,
       51.73,
       37,
       '11층',
       20,
       2,
       '2025-05-10',
       '2020-04-11',
       '강변 쓰리룸, 반려동물 가능',
       '강변 근처 쓰리룸, 반려동물 가능. 넓은 실내 공간과 효율적인 구조로 거주 만족도가 높습니다.',
       5,
       1,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 62);



INSERT INTO options (room_id, option_name)
SELECT 62, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 62 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 62, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 62 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 62,
       '서울특별시 강남구 도산대로 27',
       '107동 761호',
       '08412',
       37.496087,
       127.031119,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 62);

-- === Room 63 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 63,
       4,
       'VILLA_HOUSE',
       'ON_SALE',
       '1146-2008-895660',
       11000000,
       650000,
       31.28,
       56.59,
       86,
       '8층',
       17,
       0,
       '2025-05-10',
       '2020-04-11',
       '버스정류장 앞 리모델링 완료, 관리비 저렴',
       '버스정류장 앞 근처 리모델링 완료, 관리비 저렴. 채광이 좋고 통풍이 잘 되어 쾌적한 실내 환경을 제공합니다.',
       9,
       2,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 63);



INSERT INTO options (room_id, option_name)
SELECT 63, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 63 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 63, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 63 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 63,
       '서울특별시 강남구 도산대로 52',
       '103동 215호',
       '09135',
       37.50233,
       127.030357,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 63);

-- === Room 64 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 64,
       4,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2008-550343',
       4000000,
       600000,
       44.62,
       33.11,
       49,
       '3층',
       7,
       2,
       '2025-05-10',
       '2020-04-11',
       '조용한 주택가 원룸, 채광 좋음',
       '조용한 주택가 근처 원룸, 채광 좋음. 인근에 공원과 산책로가 있어 여가 생활에도 좋습니다.',
       11,
       2,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 64);



INSERT INTO options (room_id, option_name)
SELECT 64, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 64 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 64, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 64 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 64,
       '서울특별시 강남구 역삼로 260',
       '106동 778호',
       '07792',
       37.50456,
       127.027145,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 64);

-- === Room 65 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 65,
       4,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2023-315688',
       3000000,
       400000,
       29.09,
       52.48,
       188,
       '15층',
       19,
       0,
       '2025-05-10',
       '2020-04-11',
       '대학교 근처 원룸, 편의시설 밀접',
       '대학교 근처 근처 원룸, 편의시설 밀접. 생활 편의시설이 가까워 일상생활이 매우 편리합니다.',
       5,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 65);



INSERT INTO options (room_id, option_name)
SELECT 65, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 65 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 65, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 65 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 65,
       '서울특별시 강남구 역삼로 211',
       '105동 860호',
       '01471',
       37.506286,
       127.02561,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 65);

-- === Room 66 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 66,
       4,
       'VILLA_HOUSE',
       'ON_SALE',
       '1146-1996-540704',
       7000000,
       600000,
       32.32,
       46.7,
       192,
       '6층',
       7,
       0,
       '2025-05-10',
       '2020-04-11',
       '상업지구 리모델링 완료, 반려동물 가능',
       '상업지구 근처 리모델링 완료, 반려동물 가능. 넓은 실내 공간과 효율적인 구조로 거주 만족도가 높습니다.',
       6,
       2,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 66);



INSERT INTO options (room_id, option_name)
SELECT 66, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 66 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 66, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 66 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 66,
       '서울특별시 강남구 강남대로 56',
       '104동 634호',
       '08198',
       37.502017,
       127.027322,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 66);

-- === Room 67 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 67,
       4,
       'APARTMENT',
       'ON_SALE',
       '1146-2021-759644',
       20000000,
       1500000,
       38.26,
       44.16,
       88,
       '3층',
       3,
       2,
       '2025-05-10',
       '2020-04-11',
       '한강 신축 건물, 주차 가능',
       '한강 근처 신축 건물, 주차 가능. 인근에 공원과 산책로가 있어 여가 생활에도 좋습니다.',
       15,
       2,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 67);



INSERT INTO options (room_id, option_name)
SELECT 67, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 67 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 67, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 67 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 67,
       '서울특별시 강남구 테헤란로 57',
       '102동 544호',
       '02050',
       37.495089,
       127.023012,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 67);

-- === Room 68 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 68,
       4,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2010-318611',
       5000000,
       600000,
       42.47,
       63.62,
       118,
       '4층',
       19,
       1,
       '2025-05-10',
       '2020-04-11',
       '대학교 근처 투룸, 보안 철저',
       '대학교 근처 근처 투룸, 보안 철저. 생활 편의시설이 가까워 일상생활이 매우 편리합니다.',
       5,
       2,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 68);



INSERT INTO options (room_id, option_name)
SELECT 68, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 68 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 68, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 68 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 68,
       '서울특별시 강남구 테헤란로 113',
       '106동 286호',
       '02536',
       37.497475,
       127.020235,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 68);

-- === Room 69 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 69,
       4,
       'APARTMENT',
       'ON_SALE',
       '1146-2017-536012',
       12000000,
       900000,
       31.18,
       55.91,
       80,
       '5층',
       10,
       0,
       '2025-05-10',
       '2020-04-11',
       '도심 테라스 포함, 편의시설 밀접',
       '도심 근처 테라스 포함, 편의시설 밀접. 주변 환경이 조용하고 안전하여 가족 단위 거주에 적합합니다.',
       15,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 69);



INSERT INTO options (room_id, option_name)
SELECT 69, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 69 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 69, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 69 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 69,
       '서울특별시 강남구 강남대로 39',
       '103동 747호',
       '02144',
       37.493311,
       127.022033,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 69);

-- === Room 70 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 70,
       4,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-1997-259072',
       2000000,
       500000,
       31.41,
       49.47,
       36,
       '4층',
       7,
       1,
       '2025-05-10',
       '2020-04-11',
       '버스정류장 앞 원룸, 햇빛 잘 듬',
       '버스정류장 앞 근처 원룸, 햇빛 잘 듬. 교통 접근성이 뛰어나 출퇴근이 편리합니다.',
       13,
       2,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 70);



INSERT INTO options (room_id, option_name)
SELECT 70, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 70 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 70, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 70 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 70,
       '서울특별시 강남구 언주로 275',
       '101동 186호',
       '03103',
       37.499465,
       127.032342,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 70);

-- === Room 71 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 71,
       4,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-2008-812340',
       8000000,
       700000,
       54.08,
       63.1,
       127,
       '8층',
       16,
       2,
       '2025-05-10',
       '2020-04-11',
       '강변 복층 구조, 햇빛 잘 듬',
       '강변 근처 복층 구조, 햇빛 잘 듬. 최근 리모델링으로 내외부 상태가 매우 우수합니다.',
       6,
       1,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 71);



INSERT INTO options (room_id, option_name)
SELECT 71, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 71 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 71, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 71 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 71,
       '서울특별시 강남구 테헤란로 211',
       '102동 213호',
       '05974',
       37.502269,
       127.034674,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 71);

-- === Room 72 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 72,
       4,
       'APARTMENT',
       'ON_SALE',
       '1146-2008-911809',
       13000000,
       1000000,
       30.96,
       36.55,
       88,
       '9층',
       10,
       2,
       '2025-05-10',
       '2020-04-11',
       '상업지구 아파트, 엘리베이터 있음',
       '상업지구 근처 아파트, 엘리베이터 있음. 최근 리모델링으로 내외부 상태가 매우 우수합니다.',
       12,
       3,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 72);



INSERT INTO options (room_id, option_name)
SELECT 72, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 72 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 72, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 72 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 72,
       '서울특별시 강남구 선릉로 259',
       '107동 330호',
       '06068',
       37.496917,
       127.038894,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 72);

-- === Room 73 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 73,
       4,
       'ONEROOM_TWOROOM',
       'ON_SALE',
       '1146-1996-826688',
       4500000,
       600000,
       48.04,
       54.66,
       78,
       '9층',
       12,
       2,
       '2025-05-10',
       '2020-04-11',
       '도심 투룸, 햇빛 잘 듬',
       '도심 근처 투룸, 햇빛 잘 듬. 보안 시스템이 잘 갖춰져 있어 안심하고 생활할 수 있습니다.',
       15,
       1,
       1,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 73);



INSERT INTO options (room_id, option_name)
SELECT 73, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 73 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 73, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 73 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 73,
       '서울특별시 강남구 테헤란로 219',
       '102동 552호',
       '06442',
       37.50293,
       127.036411,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 73);

-- === Room 74 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 74,
       4,
       'OFFICETEL',
       'ON_SALE',
       '1146-2004-476704',
       15000000,
       900000,
       33.94,
       59.07,
       59,
       '8층',
       13,
       2,
       '2025-05-10',
       '2020-04-11',
       '남산 신축 건물, 층간소음 적음',
       '남산 근처 신축 건물, 층간소음 적음. 교통 접근성이 뛰어나 출퇴근이 편리합니다.',
       6,
       3,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 74);



INSERT INTO options (room_id, option_name)
SELECT 74, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 74 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 74, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 74 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 74,
       '서울특별시 강남구 테헤란로 179',
       '107동 115호',
       '04331',
       37.509679,
       127.030118,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 74);

-- === Room 75 ===
INSERT INTO room (room_id, member_id, building_type, status, real_estate_id,
                  deposit, monthly_rent, exclusive_area, supply_area,
                  total_units, floor, max_floor, parking_spaces,
                  available_from, permission_date, simple_description, description,
                  maintenance_cost, room_cnt, bathroom_cnt, direction,
                  verified, registry_paid, discussable, discuss_detail,
                  reviewable, is_phone_public, created_at, updated_at)
SELECT 75,
       4,
       'OFFICETEL',
       'ON_SALE',
       '1146-2015-430188',
       10000000,
       700000,
       34.97,
       50.32,
       181,
       '12층',
       15,
       1,
       '2025-05-10',
       '2020-04-11',
       '버스정류장 앞 리모델링 완료, 관리비 저렴',
       '버스정류장 앞 근처 리모델링 완료, 관리비 저렴. 채광이 좋고 통풍이 잘 되어 쾌적한 실내 환경을 제공합니다.',
       9,
       1,
       2,
       'EAST',
       TRUE,
       TRUE,
       TRUE,
       '임대 조건 협의 가능',
       TRUE,
       FALSE,
       NOW(),
       NOW()
WHERE NOT EXISTS (SELECT 1 FROM room WHERE room_id = 75);



INSERT INTO options (room_id, option_name)
SELECT 75, 'AIR_CONDITIONER'
WHERE NOT EXISTS (SELECT 1 FROM options WHERE room_id = 75 AND option_name = 'AIR_CONDITIONER');

INSERT INTO maintenance_include (room_id, maintenance_include_name)
SELECT 75, 'WATER'
WHERE NOT EXISTS (SELECT 1 FROM maintenance_include WHERE room_id = 75 AND maintenance_include_name = 'WATER');

INSERT INTO address (room_id, name, address_detail, postal_code, lat, lng, province, city, district)
SELECT 75,
       '서울특별시 강남구 강남대로 180',
       '106동 361호',
       '04991',
       37.490824,
       127.034018,
       '서울특별시',
       '강남구',
       '강남대로'
WHERE NOT EXISTS (SELECT 1 FROM address WHERE room_id = 75);

-- Rewritten image inserts
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (1, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room1.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (1, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room2.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (1, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room3.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (2, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room4.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (2, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room5.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (2, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room6.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (3, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room7.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (3, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room8.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (3, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room9.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (4, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room10.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (4, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room11.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (4, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room12.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (5, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room13.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (5, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room14.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (5, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room15.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (6, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room16.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (6, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room17.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (6, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room18.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (7, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room19.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (7, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room20.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (7, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room21.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (8, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room22.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (8, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room23.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (8, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room24.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (9, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room25.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (9, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room26.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (9, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room27.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (10, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room28.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (10, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room29.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (10, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room30.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (11, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room31.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (11, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room32.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (11, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room33.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (12, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room34.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (12, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room35.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (12, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room36.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (13, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room37.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (13, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room38.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (13, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room39.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (14, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room40.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (14, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room41.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (14, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room42.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (15, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room43.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (15, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room44.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (15, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room45.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (16, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room46.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (16, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room47.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (16, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room48.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (17, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room49.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (17, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room50.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (17, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room51.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (18, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room52.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (18, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room53.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (18, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room54.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (19, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room55.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (19, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room56.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (19, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room57.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (20, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room58.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (20, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room59.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (20, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room60.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (21, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room61.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (21, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room62.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (21, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room63.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (22, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room64.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (22, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room65.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (22, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room66.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (23, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room67.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (23, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room68.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (23, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room69.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (24, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room70.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (24, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room71.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (24, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room72.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (25, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room73.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (25, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room74.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (25, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room75.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (26, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room76.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (26, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room77.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (26, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room78.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (27, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room79.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (27, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room80.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (27, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room81.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (28, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room82.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (28, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room83.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (28, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room84.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (29, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room85.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (29, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room86.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (29, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room87.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (30, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room88.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (30, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room89.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (30, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room90.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (31, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room91.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (31, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room92.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (31, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room93.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (32, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room94.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (32, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room95.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (32, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room96.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (33, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room97.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (33, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room98.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (33, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room99.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (34, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room100.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (34, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room101.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (34, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room102.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (35, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room103.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (35, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room104.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (35, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room105.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (36, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room106.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (36, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room107.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (36, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room108.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (37, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room109.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (37, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room110.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (37, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room111.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (38, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room112.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (38, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room113.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (38, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room114.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (39, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room115.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (39, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room116.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (39, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room117.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (40, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room118.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (40, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room119.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (40, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room120.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (41, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room121.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (41, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room122.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (41, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room123.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (42, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room124.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (42, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room125.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (42, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room126.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (43, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room127.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (43, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room128.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (43, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room129.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (44, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room130.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (44, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room131.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (44, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room132.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (45, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room133.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (45, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room134.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (45, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room135.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (46, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room136.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (46, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room137.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (46, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room138.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (47, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room139.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (47, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room140.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (47, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room141.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (48, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room142.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (48, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room143.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (48, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room144.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (49, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room145.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (49, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room146.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (49, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room147.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (50, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room148.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (50, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room149.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (50, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room150.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (51, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room151.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (51, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room152.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (51, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room153.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (52, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room154.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (52, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room155.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (52, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room156.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (53, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room157.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (53, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room158.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (53, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room159.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (54, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room160.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (54, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room161.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (54, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room162.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (55, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room163.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (55, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room164.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (55, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room165.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (56, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room166.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (56, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room167.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (56, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room168.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (57, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room169.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (57, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room170.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (57, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room171.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (58, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room172.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (58, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room173.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (58, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room174.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (59, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room175.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (59, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room176.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (59, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room177.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (60, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room178.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (60, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room179.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (60, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room180.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (61, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room181.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (61, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room182.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (61, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room183.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (62, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room184.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (62, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room185.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (62, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room186.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (63, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room187.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (63, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room188.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (63, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room189.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (64, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room190.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (64, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room191.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (64, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room192.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (65, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room193.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (65, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room194.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (65, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room195.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (66, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room196.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (66, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room197.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (66, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room198.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (67, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room199.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (67, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room200.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (67, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room201.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (68, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room202.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (68, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room203.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (68, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room204.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (69, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room205.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (69, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room206.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (69, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room207.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (70, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room208.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (70, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room209.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (70, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room210.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (71, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room211.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (71, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room212.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (71, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room213.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (72, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room214.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (72, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room215.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (72, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room216.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (73, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room217.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (73, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room218.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (73, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room219.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (74, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room220.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (74, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room221.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (74, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room222.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (75, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room223.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (75, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room224.webp', NOW(), NOW());
INSERT INTO image (room_id, image_url, created_at, updated_at)
VALUES (75, 'https://bangjwo-s3.s3.ap-northeast-2.amazonaws.com/room2/room225.webp', NOW(), NOW());