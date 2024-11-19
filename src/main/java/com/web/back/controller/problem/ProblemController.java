package com.web.back.controller.problem;

import com.web.back.dto.*;
import com.web.back.service.ProblemService;
import com.web.back.utils.ErrorResponseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProblemController {

    @Autowired
    private ErrorResponseManager errorResponseManager;

    @Autowired
    private ProblemService problemService;


    // 문제 목록 조회
    @GetMapping("/problem")
    public ResponseEntity<?> readProblemList() throws Exception {
        try {
            List<ProblemDto> problems = problemService.getProblemList();
            return new ResponseEntity<>(problems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 출제 문제 목록 조회
    @GetMapping("/myProblem")
    public ResponseEntity<?> readMyProblemList() throws Exception {
        try {
            List<ProblemDto> problems = problemService.getMyProblemList();
            return new ResponseEntity<>(problems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 문제 상세 조회
    @GetMapping("/problemDetail/{problemIdx}")
    public ResponseEntity<?> openPostDetail(@PathVariable("problemIdx") Long problemIdx) throws Exception {
        try {
            ProblemDataDto problem = problemService.getProblemDetail(problemIdx);
            return new ResponseEntity<>(problem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 문제 등록
    @PostMapping("/problem/write")
    public ResponseEntity<?> addProblem(@RequestBody ProblemDto problemDto) throws Exception {
        try {
            return new ResponseEntity<>(problemService.registerProblem(problemDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 문제 수정
    @PutMapping("/problem/edit/{problemIdx}")
    public ResponseEntity<?> editProblem(@PathVariable("problemIdx") Long problemIdx, @RequestBody ProblemDto problemDto) throws Exception {
        try {
            ProblemDto problem = problemService.editProblem(problemIdx, problemDto);
            return new ResponseEntity<>(problem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 문제 삭제
    @DeleteMapping("/problem/delete/{problemIdx}")
    public ResponseEntity<?> deleteProblem(@PathVariable("problemIdx") Long problemIdx) throws Exception {
        try {
            problemService.deleteProblem(problemIdx);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }



    // 문제 코드 실행
    @PostMapping("/problem/run/{problemIdx}")
    public ResponseEntity<?> runProblem(@PathVariable("problemIdx") Long problemIdx, @RequestBody SubmitDto submitDto) {
        try {

            SubmitDto result = problemService.runProblemCode(problemIdx, submitDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 문제 풀이 제출
    @PostMapping("/problem/submit/{problemIdx}")
    public ResponseEntity<?> submitProblem(@PathVariable("problemIdx") Long problemIdx, @RequestBody SubmitDto submitDto) throws Exception {
        try {
            // 클라이언트에서 보낼 때 DTO에 문제 정보 같이 넣어서 보낼지, 아니면 문제 ID 넣고 여기서 DTO에 넣어줄지
            return new ResponseEntity<>(problemService.submitProblemCode(problemIdx, submitDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/problem/search")
    public ResponseEntity<?> searchProblem(@RequestParam(value="keyword") String keyword,
                                           @RequestParam(value = "page") int page) throws Exception {
        try {
            SearchResultDto problems = problemService.searchProblem(keyword, page);
            return new ResponseEntity<>(problems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 문제 테스트케이스 조회
    @GetMapping("/problem/{problemIdx}/testcase")
    public ResponseEntity<?> getProblemTestcase(@PathVariable("problemIdx") Long problemIdx) throws Exception {
        try {
            List<TestcaseDto> testcases = problemService.getProblemTestcaseList(problemIdx);
            return new ResponseEntity<>(testcases, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 문제 테스트케이스 등록
    @PostMapping("/problem/{problemIdx}/testcase/add")
    public ResponseEntity<?> addProblemTestcase(@PathVariable("problemIdx") Long problemIdx, @RequestBody TestcaseDto testcaseDto) throws Exception {
        try {
            // Dto에 problem id 담아서 프론트에서 백으로 보낼지 아니면 아이디랑 DTO 따로 보내고 받아서 DTO에 담아주고 실행할지 결정해야 할듯
            return new ResponseEntity<>(problemService.addProblemTestcase(testcaseDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 문제 테스트케이스 수정
    @PutMapping("/problem/{problemIdx}/testcase/edit/{testcaseIdx}")
    public ResponseEntity<?> editProblemTestcase(@PathVariable("problemIdx") Long problemIdx, @PathVariable("testcaseIdx") Long testcaseIdx, @RequestBody TestcaseDto testcaseDto) throws Exception {
        try {
            TestcaseDto testcase = problemService.editProblemTestcase(testcaseIdx, testcaseDto);
            return new ResponseEntity<>(testcase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    // 문제 테스트케이스 삭제
    @DeleteMapping("/problem/{problemIdx}/testcase/delete/{testcaseIdx}")
    public ResponseEntity<?> deleteProblemTestcase(@PathVariable("problemIdx") Long problemIdx, @PathVariable("testcaseIdx") Long testcaseIdx) throws Exception {
        try {
            problemService.deleteProblemTestcase(testcaseIdx);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(errorResponseManager.getErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

}
