USE bangjwo;


INSERT INTO member (member_id, kakao_id, name, birthday, phone, profile_url, created_at, updated_at, deleted_at)
SELECT 1, 123456789, '홍길동', '1995-07-15', '010-1234-5678',
       'https://example1.com',
       NOW(), NOW(), NULL
    WHERE NOT EXISTS (SELECT 1 FROM member);

INSERT INTO member (member_id, kakao_id, name, birthday, phone, profile_url, created_at, updated_at, deleted_at)
SELECT 2, 987654321, '김철수', '1998-03-22', '010-5678-1234',
       'https://example2.com',
       NOW(), NOW(), NULL
    WHERE NOT EXISTS (SELECT 1 FROM member WHERE member_id = 2);