package com.web.back.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Objects;

@Controller
public class WebController implements ErrorController {
    // 백엔드에서 React.js 라우터에 있는 주소로 주소를 임의 변경하면,
    // 매핑되는 주소가 없으므로 404 오류가 생긴다.
    // 이를 방지하기 위해 에러가 발생하면 프론트엔드에서 작성한 frontend/src/index.html을 전송한다.

    // 통신 테스트
    @GetMapping("/test")
    public ResponseEntity<?> testApi(HttpServletResponse response) throws Exception {
        System.out.println("요청");
        return new ResponseEntity<>("응답", HttpStatus.OK);
    }

    @GetMapping({ "/", "/error" })
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/error")
    public ResponseEntity<Object> handleNoHandlerFoundException(HttpServletResponse response, HttpServletRequest request) {
        int status = response.getStatus();

        if (Objects.equals(request.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
            Map<String, Object> body = Map.of("error", "Not Found", "timestamp", System.currentTimeMillis());
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

}