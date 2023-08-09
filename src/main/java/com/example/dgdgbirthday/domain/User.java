package com.example.dgdgbirthday.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //@Entity - 테이블과 1:1로 맵핑되는 어노테이션 [해당 어노테이션이 붙으면 JPA가 해당 클래스를 관리함]
@Data			// Getter Setter
//@Builder		// DTO -> Entity화
//@AllArgsConstructor    // 모든 컬럼 생성자 생성
@NoArgsConstructor    // 기본 생성자 선언 * 기본생성자 비선언시 java.lang.NoSuchMethodException <init>() 에러 발생
@Table(name = "user")
public class User {

    /*
    Domain(=Entity)
    DB 테이블과 직접 맵핑되는 클래스로서 JPA 사용 시 어노테이션을 이용하여 테이블, 필드, 등을 설정한다.
    또한 Domain과 Client를 직접 연동하지 않고 DTO를 통해 분리하는 이유가 있는데,
    Client 쪽과 연결된 부분은 잦은 변경사항이 있을 수 있는데 Domain과 연결되어 자주 변경되게 된다면 여러 클래스에 영향을 미치기 때문에 분리한다.
    DTO는 Domain Model을 복사한 형태로 다양한 Presentation Logic을 추가한 정도로 사용하며 Domain Model 객체는 Persistent만을 위해서 사용한다.
    View 단과 DB 단을 확실하게 분리하기 위해서
     */

    @Id //@Id - PK를 지정하는 어노테이션
    @GeneratedValue(strategy =  GenerationType.IDENTITY) // 기본키 생성을 데이터베이스에게 위임하는 방식으로 id값을 따로 할당하지 않아도 데이터베이스가 자동으로 AUTO_INCREMENT를 하여 기본키를 생성해준다.
    private String id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String mbti;


}

