USE bangjwo;

INSERT INTO member (
    member_id, kakao_id, name, birthday, phone, profile_url, nickname,
                    created_at, updated_at, deleted_at, is_auth
)
SELECT

    1, 3904577474, '황인준', '19970514', '01030222851',
    'https://example1.com', '황황인인준준',
    NOW(), NOW(), NULL, true
    WHERE NOT EXISTS (SELECT 1 FROM member WHERE member_id = 1);

INSERT INTO member (
    member_id, kakao_id, name, birthday, phone, profile_url, nickname,
    created_at, updated_at, deleted_at, is_auth
)
SELECT
    2, 987654321, '김철수', '1998-03-22', '010-5678-1234',
    'https://example2.com', '철수핑',
    NOW(), NOW(), NULL, false
    WHERE NOT EXISTS (SELECT 1 FROM member WHERE member_id = 2);

INSERT INTO room (
    member_id, building_type, status, real_estate_id,
    deposit, monthly_rent, exclusive_area, supply_area,
    total_units, floor, max_floor, parking_spaces,
    available_from, permission_date, simple_description, description,
    maintenance_cost, room_cnt, bathroom_cnt, direction,
    verified, registry_paid, discussable, discuss_detail,
    reviewable, is_phone_public, created_at, updated_at
) VALUES (
             2, 'APARTMENT', 'ON_SALE', 'RE12345678',
             1000, 50, 45.75, 55.32,
             100, '5', 15, 1,
             '2025-05-01', '2010-08-10', '조용한 주택가에 위치한 아파트',
             '가까운 거리에 마트와 지하철역이 있으며, 남향이라 채광이 좋습니다.',
             10, 3, 2, 'SOUTH',
             true, true, true, '보증금 조절 협의 가능',
             true, false, NOW(), NOW()
         );
