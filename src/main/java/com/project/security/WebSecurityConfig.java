package com.project.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security 설정
 */
@Configuration
public class WebSecurityConfig {
    @Autowired // 객체 주입. 객체는 이미 만들어져 있어야 하고 만들어져 있는 메모리상의 객체를 넣어줌
    private DataSource dataSource; // db 연결 객체

    //설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/", // 사람들이 들어와서 뭔가 눌러봄 // 그냥 들어와도 되는 경로 등록해야 아무나 들어올 수 있음
        		"/member/join", // 가입하기 경로
        		"/member/idcheck", // id중복검사 경로
                "/board/list", // 글 목록보고
                "/board/read", // 글 읽기는 아무나
                "/board/download",
                "/img/**", // ~밑까지 static 밑 폴더. 다 열어놓음
                "/css/**",
                "/js/**").permitAll()		//설정한 리소스의 접근을 인증절차 없이 허용 // 어떤 권한을 가졌든지 다 허락
        .anyRequest().authenticated()   	//위의 경로 외에는 모두 로그인을 해야 함 // 그 외에는 다 인증받고 들어와야 함
        .and()
        .formLogin()						//일반적인 폼을 이용한 로그인 처리/실패 방법을 사용 // ~ 로그인 처리에 관한. 로그인 처리
        .loginPage("/member/loginForm")		//시큐리티에서 제공하는 기본 폼이 아닌 사용자가 만든 폼 사용 // 이 경로 요청이 들어오면 html 파일을 보여줌 // loginForm.html로 감
        .loginProcessingUrl("/member/login").permitAll()	//인증 처리를 하는 URL을 설정. 로그인 폼의 action으로 지정 // 요청처리 들어오면 폼 보여주는 부분(이름 정하면 알아서 만들어짐), // action 목적지
        .usernameParameter("memberid")		//로그인폼의 아이디 입력란의 name // 서버 전달 파라미터를 보여줌. 파라미터의 이름이 정해짐
        .passwordParameter("memberpw")		//로그인폼의 비밀번호 입력란의 name
        .and()
        .logout() // 자동으로 만들어짐
        .logoutSuccessUrl("/").permitAll()	//로그아웃시에 이동할 경로 // 로그아웃하고 어디로 돌아올지 경로. 보통 메인화면
        .and()
        .cors()
        .and()
        .httpBasic();

        return http.build();
    }

    //인증을 위한 쿼리
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        // 인증 (로그인)
        .usersByUsernameQuery( //           읽어서      전달      로그인 시켜줘도 되는지. 쓰면 안되는 건지
        		"select memberid username, memberpw password, enabled " +
                "from spring5_member " +
                "where memberid = ?") // DB연결 프로그래밍을 할 때 전달된 값 끼워넣어서 sql 실행하는 게 ? // mybatis에서의 #
        // 권한
        .authoritiesByUsernameQuery( // 로그인을 했느냐가 중요한 기능도 있고 관리자인지 일반회원인지 구분
        		"select memberid username, rolename role_name " +
                "from spring5_member " +
                "where memberid = ?");
    }

    // 단방향 비밀번호 암호화 // select하면 비밀번호가 다 보임
    @Bean
    public PasswordEncoder passwordEncoder() { // passwordencoder 객체 리턴. 어디선가 autowired 쓸 수 있게 준비한 것
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
// 로그인 성공 실패하면 어디로 가는지 등등은 우리가 알려줘야 됨. 그게 securityclass
// 경로 파일의 위치, 폼, 비밀번호 맞으면 로그인 시켜주는데 로그인하고 나면 session에 가지고 있어야 함. 값 가지고 있는 객체 
// 각 단계별로 필요한 변수들이 다름. 가입할 때는 rolename, 읽어올 때는 일부만 쓰고 @는 lombok 때문에. jpa쓰게 되면 더 많아짐