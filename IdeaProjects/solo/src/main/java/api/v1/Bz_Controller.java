package api.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Bz_Controller {

    private final Bz_Service bz_service;

    // 전체 조회
    @GetMapping("/search")
    public ResponseEntity All_Search() {
        List<Member_Dto> list = bz_service.find_All();

        return new ResponseEntity<>(new Multi_ResponseDto<>(list),HttpStatus.OK);
    }

    // 지역조건 조회(코드번호 중 일부가 포함되면 포함목록 모두 조회, 내림차순)
    @GetMapping("/if_location")
    public ResponseEntity if_location(@RequestParam String company_location) {
        List<Member_Dto> list = bz_service.findIf_location(company_location);
        return new ResponseEntity<>(new Multi_ResponseDto<>(list) ,HttpStatus.OK);
    }

    // 업종조건 조회(코드번호 중 일부가 포함되면 포함목록 모두 조회, 내림차순)
    @GetMapping("/if_type")
    public ResponseEntity if_type(@RequestParam String company_type) {
        List<Member_Dto> list = bz_service.findIf_type(company_type);
        return new ResponseEntity<>(new Multi_ResponseDto<>(list) ,HttpStatus.OK);
    }
}
