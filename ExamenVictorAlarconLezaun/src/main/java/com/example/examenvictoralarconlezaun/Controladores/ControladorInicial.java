package com.example.examenvictoralarconlezaun.Controladores;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;

@Controller
@RequestMapping("/")
public class ControladorInicial {

    private static final String CONTADOR_NAME_APP = "numVisitasApp";
    private static final String CONTADOR_NAME_INDEX = "numVisitasIndex";

    @GetMapping({"","/index"})
        public String bienvenida(HttpServletRequest request, HttpServletResponse response) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String usuario = authentication.getName();
                HttpSession session = request.getSession();
                boolean primeraVez = (session.getAttribute(CONTADOR_NAME_APP) == null);


                if (primeraVez) {
                    Optional<Cookie> cookieEncontrada = Arrays.stream(request.getCookies())
                            .filter(cookie -> usuario.equals(cookie.getName()))
                            .findAny();
                    int contador = 1;
                    if (cookieEncontrada.isEmpty()) {  // si no existe la cookie >> primera visita
                        Cookie cookie = new Cookie("victor", "1");
                        cookie.setPath("/mascota/new");
                        cookie.setDomain("localhost");
                        cookie.setMaxAge(7 * 24 * 60 * 60);  // 7 días
                        cookie.setSecure(true);
                        cookie.setHttpOnly(true);

                        response.addCookie(cookie);

                    } else {
                        Cookie cookie = cookieEncontrada.get();
                        contador = Integer.parseInt(cookie.getValue()) + 1;
                        cookie.setValue(String.valueOf(contador));
                        cookie.setMaxAge(7 * 24 * 60 * 60);  // 7 días
                        response.addCookie(cookie);

                    }
                    session.setAttribute(CONTADOR_NAME_APP, contador);
                }
                Object contadorIndex = session.getAttribute(CONTADOR_NAME_INDEX);
                session.setAttribute(CONTADOR_NAME_INDEX, (contadorIndex == null) ? 1 : (int)contadorIndex + 1);
            }

            return "index";
        }

        @GetMapping("/login")
        public String login(){
            return "login";
        }

        @GetMapping("/logout")
        public String logout(){
            return "redirect:/index";
        }

}
