package com.web.back.controller.assignment;

import com.web.back.dto.AssignmentDto;
import com.web.back.dto.StudentDataDto;
import com.web.back.dto.SubmitDto;


import com.web.back.dto.TestcaseDto;
import com.web.back.service.AssignmentService;
import com.web.back.utils.ErrorResponseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private ErrorResponseManager errorResponseManager;


    // 과제 목록 조회
    @GetMapping("/lesson/{lessonIdx}/assignment")
    public ResponseEntity<?> getAssignmentList(@PathVariable("lessonIdx") Long lessonIdx) throws Exception {
        try {
            List<AssignmentDto> assignments = assignmentService.getAssignmentList(lessonIdx);
            return new ResponseEntity<>(assignments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 과제 상세 조회
    @GetMapping("/assignment/{assignmentIdx}")
    public ResponseEntity<?> getAssignmentDetail(@PathVariable("assignmentIdx") Long assignmentIdx) throws Exception {
        try {
            AssignmentDto assignment = assignmentService.getAssignmentDetail(assignmentIdx);
            return new ResponseEntity<>(assignment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 과제 등록
    @PostMapping("/assignment/add")
    public ResponseEntity<?> addAssignment(@RequestBody AssignmentDto assignmentDto) throws Exception {
        try {
            AssignmentDto assignment = assignmentService.addAssignment(assignmentDto);
            return new ResponseEntity<>(assignment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 과제 수정
    @PutMapping("/assignment/edit/{assignmentIdx}")
    public ResponseEntity<?> editAssignment(@PathVariable("assignmentIdx") Long assignmentIdx, @RequestBody AssignmentDto assignmentDto) throws Exception {
        try {
            AssignmentDto assignment = assignmentService.editAssignment(assignmentIdx, assignmentDto);
            return new ResponseEntity<>(assignment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 과제 삭제
    @DeleteMapping("/assignment/delete/{assignmentIdx}")
    public ResponseEntity<?> deleteAssignment(@PathVariable("assignmentIdx") Long assignmentIdx) throws Exception {
        try {
            assignmentService.deleteAssignment(assignmentIdx);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 과제 코드 실행
    @PostMapping("/assignment/run/{assignmentIdx}")
    public ResponseEntity<?> runAssignmentCode(@PathVariable("assignmentIdx") Long assignmentIdx, @RequestBody SubmitDto submitDto) throws Exception {
        try {
            SubmitDto submit = assignmentService.runAssignmentCode(assignmentIdx, submitDto);
            return new ResponseEntity<>(submit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 과제 코드 제출
    @PostMapping("/assignment/submit/{assignmentIdx}")
    public ResponseEntity<?> submitAssignmentCode(@PathVariable("assignmentIdx") Long assignmentIdx, @RequestBody SubmitDto submitDto) throws Exception {
        try {
            SubmitDto submit = assignmentService.submitAssignmentCode(assignmentIdx, submitDto);
            return new ResponseEntity<>(submit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }


    // 멤버별 과제 채점 결과 조회
    @GetMapping("/lesson/{lessonIdx}/assignment/score/{memberIdx}")
    public ResponseEntity<?> getMyAssignmentScoreList(@PathVariable("lessonIdx") Long lessonIdx, @PathVariable("memberIdx") Long memberIdx) throws Exception {
        try {
            List<StudentDataDto> datas = assignmentService.getMyAssignmentScoreList(lessonIdx, memberIdx);
            return new ResponseEntity<>(datas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 과제별 채점 결과 조회
    @GetMapping("/assignment/{assignmentIdx}/score")
    public ResponseEntity<?> getAssignmentScoreList(@PathVariable("assignmentIdx") Long assignmentIdx) throws Exception {
        try {
            List<StudentDataDto> datas = assignmentService.getAssignmentScoreList(assignmentIdx);
            return new ResponseEntity<>(datas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 과제 채점 결과 상세 조회
    @GetMapping("/assignment/score/{submitIdx}")
    public ResponseEntity<?> getAssignmentScoreDetail(@PathVariable("submitIdx") Long submitIdx) throws Exception {
        try {
            SubmitDto Submit = assignmentService.getAssignmentScoreDetail(submitIdx);
            return new ResponseEntity<>(Submit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }


    // 과제 채점 결과 수정
    @PutMapping("/assignment/score/edit/{submitIdx}")
    public ResponseEntity<?> editAssignmentScoreDetail(@PathVariable("submitIdx") Long submitIdx, @RequestBody SubmitDto submitDto) throws Exception {
        // 이거도 클라이언트에서 점수 매핑해서 DTO를 보낼 건지 아니면 백에서 찾아서 점수만 수정할 건지
        try {
            SubmitDto Submit = assignmentService.editAssignmentScoreDetail(submitIdx, submitDto);
            return new ResponseEntity<>(Submit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 과제 테스트케이스 조회
    @GetMapping("/assignment/{assignmentIdx}/testcase")
    public ResponseEntity<?> getProblemTestcase(@PathVariable("assignmentIdx") Long assignmentIdx) throws Exception {
        try {
            List<TestcaseDto> testcases = assignmentService.getAssignmentTestcaseList(assignmentIdx);
            return new ResponseEntity<>(testcases, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 과제 테스트케이스 등록
    @PostMapping("/assignment/{assignmentIdx}/testcase/add")
    public ResponseEntity<?> addProblemTestcase(@PathVariable("assignmentIdx") Long assignmentIdx, @RequestBody TestcaseDto testcaseDto) throws Exception {
        try {
            return new ResponseEntity<>(assignmentService.addAssignmentTestcase(testcaseDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 과제 테스트케이스 수정
    @PutMapping("/assignment/{assignmentIdx}/testcase/edit/{testcaseIdx}")
    public ResponseEntity<?> editProblemTestcase(@PathVariable("assignmentIdx") Long assignmentIdx, @PathVariable("testcaseIdx") Long testcaseIdx, @RequestBody TestcaseDto testcaseDto) throws Exception {
        try {
            TestcaseDto testcase = assignmentService.editAssignmentTestcase(testcaseIdx, testcaseDto);
            return new ResponseEntity<>(testcase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 과제 테스트케이스 삭제
    @DeleteMapping("/assignment/{assignmentIdx}/testcase/delete/{testcaseIdx}")
    public ResponseEntity<?> deleteProblemTestcase(@PathVariable("assignmentIdx") Long assignmentIdx, @PathVariable("testcaseIdx") Long testcaseIdx) throws Exception {
        try {
            assignmentService.deleteAssignmentTestcase(testcaseIdx);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

}
