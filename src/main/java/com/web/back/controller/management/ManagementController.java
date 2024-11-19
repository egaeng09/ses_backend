package com.web.back.controller.management;

import com.web.back.dto.SearchResultManageDto;
import com.web.back.service.ManagementService;
import com.web.back.utils.ErrorResponseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagementController {

    @Autowired
    private ErrorResponseManager errorResponseManager;

    @Autowired
    private ManagementService managementService;

    //관리자 입장 - 회원 전체 조회
    @GetMapping("/manage")
    public ResponseEntity<?> readManageMemberList(@RequestParam(value = "page") int page) throws Exception {
        try {
            SearchResultManageDto members = managementService.getManageMemberList(page);
            return new ResponseEntity<>(members, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }
}
