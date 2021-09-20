package the.biagi.DesafioMagaLab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import the.biagi.DesafioMagaLab.repositories.UsuarioRepository;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    AutenticacaoService autenticacaoService;

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Configuracoes de autorizacoes
    @Override
    protected  void configure(HttpSecurity http)throws Exception{
        http.authorizeRequests()//http autoriza as seguintes requisições
                .antMatchers(HttpMethod.POST,"/enderecos").authenticated()
                .antMatchers(HttpMethod.GET,"/enderecos/**").authenticated()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .antMatchers(HttpMethod.POST, "/autenticacoes").permitAll()
                .antMatchers(HttpMethod.POST, "/usuarios").permitAll()
                .anyRequest()//Para outras requisições
                .authenticated()//é necessário autenticacao
                .and().csrf().disable() //Cross Site Request Forgery //Estamos desabilitanto a verificação do csrf pois vamos fazer a autenticação via token e ele por si só já se protege desse tipo de ataque.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Estamos dizendo para o spring que não vamos criar sessões ao se autenticar será stateless;
                .and().cors()//Compartilhamento de recursos com origens diferentes (Cross Origin Recsource Sharing)
                .and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class); //Estamos dizendo aqui que é para chamar chamar o filtro de token antes de fazer a requisição.

        //.and().formLogin(); //Para buscar o formulário de padrão do spring
    }


    //Configurações de autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Configuracoes de recursos estaticos(JS, CSS, Imagens)
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/h2-console/**","/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }



    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }

}
