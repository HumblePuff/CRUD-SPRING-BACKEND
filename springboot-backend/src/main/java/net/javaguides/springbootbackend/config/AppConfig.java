package net.javaguides.springbootbackend.config;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppConfig extends WebSecurityConfigurerAdapter {
    public static final String[] PUBLIC_MATCHERS = {
        "/employees"
    };
    public static final String[] PUBLIC_MATCHERS_GET = {
        "**"
    };
    public static final String[] PUBLIC_MATCHERS_POST = {
        "**"
    };
    public static final String[] PUBLIC_MATCHERS_PUT = {
        "**"
    };
    public static final String[] PUBLIC_MATCHERS_DELETE = {
        "**"
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable();
        http.authorizeHttpRequests()
        .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
        .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
        .antMatchers(HttpMethod.PUT, PUBLIC_MATCHERS_POST).permitAll()
        .antMatchers(HttpMethod.DELETE, PUBLIC_MATCHERS_DELETE).permitAll()
        .antMatchers(PUBLIC_MATCHERS).permitAll()
        .anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST","GET","PUT","DELETE","OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}