INSERT INTO MEMBER (member_id, kakao_id, name, birthday, phone, profile_url, created_at, updated_at, deleted_at)
SELECT 1, 123456789, '홍길동', '1995-07-15', '010-1234-5678',
       'https://knowing-bush-74b.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F7dec9e0b-3d54-45f5-a846-7f1738a418c3%2F1f7fadc5-6219-41c7-b728-835f78dbf3b9%2F%25ED%2595%2598%25EB%25A3%25A8%25EC%2582%25AC%25EA%25B3%25BC.jpg?table=block&id=176a6fb1-eeec-8065-bdf5-e8532f87d31a&spaceId=7dec9e0b-3d54-45f5-a846-7f1738a418c3&width=400&userId=&cache=v2',
       NOW(), NOW(), NULL
    WHERE NOT EXISTS (SELECT 1 FROM MEMBER);

INSERT INTO MEMBER (member_id, kakao_id, name, birthday, phone, profile_url, created_at, updated_at, deleted_at)
SELECT 2, 987654321, '김철수', '1998-03-22', '010-5678-1234',
       'https://knowing-bush-74b.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F7dec9e0b-3d54-45f5-a846-7f1738a418c3%2F3951838f-2781-4aca-a534-bb3842822fec%2F88.jpg?table=block&id=184a6fb1-eeec-80ae-8203-c59856bb83ea&spaceId=7dec9e0b-3d54-45f5-a846-7f1738a418c3&width=700&userId=&cache=v2',
       NOW(), NOW(), NULL
    WHERE NOT EXISTS (SELECT 1 FROM MEMBER WHERE member_id = 2);