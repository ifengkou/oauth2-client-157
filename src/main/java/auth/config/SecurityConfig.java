package auth.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author shenlongguang<https://github.com/ifengkou>
 * @date: 2019/11/27
 */
@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/print_request**", "/login**").permitAll()
                .antMatchers("/user_name**","/user_attr**").authenticated();
        //http.antMatcher("/**").authorizeRequests()
        //        .antMatchers("/", "/login**").permitAll()
        //        .anyRequest().authenticated()
        //        ;
        http.csrf().disable();
    }
}
