package com.matzalal.web.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class MatzalalSecurityConfig {

    @Autowired
    private DataSource dataSource;
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode("111"));

        return encoder;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/commu/**")
                                .hasAnyRole("ADMIN", "RICE","ROWRICE")
                                .requestMatchers("/user/**")
                                .hasAnyRole("ADMIN", "RICE","ROWRICE")
                                .anyRequest().permitAll())
                .formLogin(
                        form -> form.loginPage("/stranger/login"))
                .logout(
                        logout -> logout
                                .logoutUrl("/user/logout") // 로그아웃 페이지
                                .logoutSuccessUrl("/index") // 로그아웃 성공시 이동페이지
                                .deleteCookies("JSESSIONID")
                                 .invalidateHttpSession(true));

        return http.build();
    }

//    @Bean
    public UserDetailsService jdbcUserDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        manager.setUsersByUsernameQuery("select email username, pwd password, 1 enabled from user where email = ?");

//        manager.setAuthoritiesByUsernameQuery("select user_name username, 'ROLE_MEMBER' authority from member where user_name = ?");

        String sqlForAuthorities = "select "+
                "   u.email username, " +
                "   g.grade authority "+
                "from user_grades ug "+
                "left join user u on u.user_id = ug.user_id "+
                "left join grades g on g.grades_id = ug.grades_id " +
                "where u.email = ?";

        manager.setAuthoritiesByUsernameQuery(sqlForAuthorities);

        return manager;
    }

//    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("newlec@newlec.com")
                .password("{noop}111")
                .roles("ADMIN", "RICE")
                .build();

        UserDetails user2 = User.builder()
                .username("new")
                .password("{noop}111")
                .roles("RICE")
                .build();

        System.out.println(user1);
        System.out.println(user2);

        return new InMemoryUserDetailsManager(user1, user2);
    }

}
