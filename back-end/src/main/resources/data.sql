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