- 프로젝트 이름
  - SpringBoot_MemberBoard
- 기본패키지 
  - com.icia.sej
- dependency
  - gradle project
  - Spring web
  - lombok
  - Thymeleaf,
  - Validation
  - Spring Data JPA
  - Mysql Driver
- 서버포트
  - 8099
### 기능
  - Member
    1. 회원 등록
       1. 이메일, 비밀번호, 이름, 프로필사진 입력 받음
       2. ajax로 이메일 중복 확인
    2. 로그인
       1. 로그인 완료 시 페이징 처리된 글 목록 화면(/board/findAll)으로 이동
    3. 로그아웃
       1. 로그아웃 완료 시 index 페이지로 이동
    4. 일반 회원
       1. 게시글 작성, 조회 가능
       2. 본인이 작성한 글에 대해서만 글 상세 조회 시 수정, 삭제 버튼 보임
    5. 관리자
       1. 관리자 페이지 있음
       2. 관리자 페이지에서 회원 목록 페이지로 이동
       3. 회원 목록 페이지에서 회원 삭제 가능
  - Board
    1. 글쓰기
       1. 작성자 따로 입력 x, 글쓰기 화면에 로그인한 계정이 작성자 칸에 입력되어 있음(수정 불가능)
       2. 제목, 내용, 첨부파일 입력 받음