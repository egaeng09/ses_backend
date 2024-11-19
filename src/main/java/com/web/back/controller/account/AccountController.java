package com.web.back.controller.account;

import java.util.Objects;

import com.web.back.dto.EmailDto;
import com.web.back.dto.LoginDto;
import com.web.back.dto.MemberDto;
import com.web.back.dto.TokenDto;
import com.web.back.model.Member;
import com.web.back.security.JwtTokenProvider;
import com.web.back.utils.ErrorResponseManager;
import jakarta.servlet.http.HttpServletResponse;

import com.web.back.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Value("${jwt.http.request.header}")
    private String tokenHeader;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ErrorResponseManager errorResponseManager;

    // 로그인
    @RequestMapping(value = "${jwt.get.token.url}", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginDto authenticationRequest, HttpServletResponse response) throws Exception {
        try {
            authenticate(authenticationRequest.getId(), authenticationRequest.getPassword());
            final UserDetails userDetails = accountService.loadUserByUsername(authenticationRequest.getId());
            final String token = jwtTokenProvider.generateToken(userDetails);
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setMemberDto(MemberDto.setFormat((Member) userDetails));
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.UNAUTHORIZED);
        }
    }

    // 로그아웃
    @PostMapping("/logoutAccount")
    public ResponseEntity<?> logout(HttpServletResponse response) throws Exception {
        // 토큰 만료 시켜야 하나?
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody MemberDto memberDto) throws Exception {
        try {
            MemberDto signUpMemberDto = accountService.signUp(memberDto);
            return new ResponseEntity<>(signUpMemberDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 이메일 인증번호 전송 요청
    @PostMapping("/mail")
    public ResponseEntity<?> mailSend(@RequestBody EmailDto emailDto){
        try {
            EmailDto signUpMemberDto = accountService.sendEmailVerificationCode(emailDto);
            return new ResponseEntity<>(signUpMemberDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 이메일 인증번호 확인 요청
    @PostMapping("/mailVerify")
    public ResponseEntity<?> mailVerify(@RequestBody EmailDto emailDto){
        try {
            EmailDto signUpMemberDto = accountService.validateEmailVerificationCode(emailDto);
            return new ResponseEntity<>(signUpMemberDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 비밀번호 변경
    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody MemberDto memberDto) throws Exception {
        try {
            MemberDto newMemberDto = accountService.changePassword(memberDto);
            return new ResponseEntity<>(newMemberDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 비밀번호 찾기
    @PutMapping("/findPassword")
    public ResponseEntity<?> findPassword(@RequestBody MemberDto memberDto) throws Exception {
        try {
            MemberDto newMemberDto = accountService.findPassword(memberDto);
            return new ResponseEntity<>(newMemberDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 내 정보 조회
    @GetMapping("/my")
    public ResponseEntity<?> myPage(@RequestBody MemberDto memberDto) throws Exception {
        try {
            // 받아오도록
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 키 인증 과정
    private void authenticate(String id, String password) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(id, password));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("ID OR PW IS NOT CORRECT.", e);
        } catch (DisabledException e) {
            throw new DisabledException("DISABLED MEMBER", e);
        }
    }

    //    // 회원 탈퇴
//    @PutMapping("/deleteAccount")
//    public ResponseEntity<?> deleteAccount(HttpServletResponse response) throws Exception {
//        try {
//            accountService.deleteAccount();
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(errorResponseManager.makeErrorResponse(e), HttpStatus.BAD_REQUEST);
//        }
//    }


}